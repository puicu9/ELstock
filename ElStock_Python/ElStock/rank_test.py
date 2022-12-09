# 네이버 기본 정보 크롤링(종목코드, 시가총액, 시가총액순위,상장주식수, 액면가, 투자의견, 목표주가, 52주 최고, 52주 최저, 기업 주소 크롤링, 기업 개요 크롤링)

#지민이형 크로링 하셨죠??

import urllib.request
from datetime import datetime
import pandas as pd
from pandas import DataFrame
from pykrx import stock

from bs4 import BeautifulSoup
from selenium import webdriver
import requests

from ElStock import dbInsert

saveData = []  # 엑셀로 저장될 리스트

today = datetime.today().strftime("%y%m%d")
# print(today)

# 종목코드 리스트
ticker_list = stock.get_market_ticker_list(date=today)
# ticker_list = stock.get_market_ticker_list(date=today, market="KOSPI" and "KOSDAQ")
# print(ticker_list)
print('종목코드 리스트 개수 : ', len(ticker_list))

# 기업 정보 얻기 위한 주소
base_url = 'https://finance.naver.com/item/main.nhn?code='

# 기업 사이트 얻기 위한 주소
get_url_site = 'https://navercomp.wisereport.co.kr/v2/company/c1020001.aspx?cmp_cd='

# 기업 설명 얻기 위한 주소
get_comment_url = 'https://finance.naver.com/item/coinfo.naver?code='

# 공백 제거해주는 함수
def no_space(target):
    itemList = target.text.split()
    # text를 사용한 이유 : td 안에 <em></em> 를 제거하기 위하여 안쪾에 있는 text를 인식하고자 함.
    # print(itemList)
    result = ''
    for item in itemList[0::]:
        result += item + " "
    # print(result)
    return result.strip()


# end def no_space(target):

# 크롤링하는 함수
def getData():
    count = 0  # 종목코드로 크롤링 얼마나 했는지 세는 count 변수
    ticker_code = ''  # 종목 코드
    company_capitalization = ''  # 시가총액
    company_rank = ''  # 시가 총액 순위
    company_share = ''  # 상장주식수
    company_value = ''  # 액면가

    company_opinion = ''  # 투자의견
    company_targetPrice = ''  # 목표주가
    company_52weeks_max = ''  # 52주 최고가
    company_52weeks_min = ''  # 52주 최저가

    company_url = ''  # 회사 주소
    company_comment = '' # 회사 개요

    # 오류 나는 곳
    # 35번쨰 코드명 : 365550 // 64번째, '432320'
    # (총액 / None / 상장주식 수 / None)
    # tr  /  tr(상장주식수)

    # 950210 :
    # 총액 / 순위 / 상장주식수 / None(액면가만 없음)
    #  tr  /  tr  /  tr(상장주식수)

    for ticker_code in ticker_list[0:50:]:
        print('종목코드 : ' + ticker_code)

# 기업정보 크롤링
        url = base_url + ticker_code
        html = requests.get(url)
        html = html.content.decode('cp949', 'replace')
        soup = BeautifulSoup(html, 'html.parser')

# csv열 순서 : 종목코드, 시가총액, 시가순위, 상장주식수, 액면가 찾기

        # 포괄적인 div 찾기
        findFirst = soup.find('div', attrs={'class': 'first'})
        # print(findFirst)

        # 구체적 table 찾기(이걸 활용해서 시가총액, 시가총액순위  찾기)
        findTable = findFirst.find('table')
        # print(findTable)

        # 시가총액, company_capitalization
        find_company_capital = findTable.select_one('tr:nth-of-type(1)').find('td')
        # print(find_company_capital)
        company_capitalization = no_space(find_company_capital)

        # 시가총액순위, company_rank
        find_second_td = findTable.select_one('tr:nth-of-type(2)').find('td')
        # print(find_company_rank)
        second_td = no_space(find_second_td)

                # 끝글자가 '위'로 시작하면 company_rank 2번쨰 값은 company_rank
        if second_td[-1:] == '위':
            rankWithSomething = second_td[4::] #ddd위

            company_rank = rankWithSomething[:len(rankWithSomething)-1:]
            print(company_rank)

            # 3번째 순위부터 None인 경우, 해당 -> (상장주식수 : company_share 액면가 : company_value)
            third_tr = findTable.select_one('tr:nth-of-type(3)')
            if third_tr == None:  # 3번째 없는 경우
                company_share = 'None'
            else:  # 3번째 상장주식수가 있는 경우
                # 상장주식수, company_share
                find_company_share = findTable.select_one('tr:nth-of-type(3)').find('td')
                # print(find_company_share)
                company_share = no_space(find_company_share)
                # print(company_share)
            # end if
        else:
            # 끝 글자가 '위'가 아니라면, 상장주식수에 해당하니, 찾은 2번쨰 값은 company_share로 할당
            company_share = second_td
            company_rank = '-위'
        # end if second_td[-1:] == '위':
        # print('company_rank : ', company_rank)
        # 4번째 없는 경우
        forth_tr = findTable.select_one('tr:nth-of-type(4)')  # 액면가 0원인 경우
        if forth_tr == None:
            company_value = '0원'
        else:
            # 액면가, company_value
            find_company_value = findTable.select_one('tr:nth-of-type(4)').find('td')
            company_value = find_company_value.select_one('em').string + '원'
            # print(control_value)
        # end if

# 투자의견, 목표주가, 52주 최고, 52주 최저 찾기
        find_second_table = soup.find('table', attrs={'class': 'rwidth'})
        # print(find_second_table)
        # find_trs = find_second_table.find_all('tr')
        # print(find_trs)

        # 투자의견, 목표주가 없이 52주 최고, 52주 최저만 나올 경우가 있음.
        # 1단계 : "투자의견l목표주가"인 경우와 "52주최고l최저" 경우로 나눔
        # 2단계, "투자의견l목표주가"인 경우 : "투자의견l목표주가" 찾은 값 할당, "52주최고l최저" 찾은 값 할당
        # 3단계, "52주최고l최저"인 경우 :  "투자의견l목표주가"는 "N/A" 값 할당, "52주최고l최저" 찾은 값 할당

        # 첫번쨰 tr찾기
        first_tr = find_second_table.select_one('tr:nth-of-type(1)')
        # print(first_tr)
        _first_th = first_tr.find('th')
        # print(first_th)
        first_th = no_space(_first_th)
        # print(first_th) # "투자의견l목표주가"

        # 두번째 tr 찾기
        second_tr = find_second_table.select_one('tr:nth-of-type(2)')
        # print(second_tr)
        # _second_th = second_tr.find('th')
        # print(_second_th)
        # second_th = no_space(_second_th)
        # print(second_th)

        # 첫번째 "투자의견l목표주가" 인 경우
        if (first_th == "투자의견l목표주가"):
            # 투자의견
            notRemoveSpace01 = first_tr.find('td').find_all('span')[0]
            company_opinion = no_space(notRemoveSpace01)
            # print(company_opinion)

            # 목표주가
            notRemoveSpace02 = first_tr.find('td').find_all('em')[1]
            # print(notRemoveSpace02)
            company_targetPrice = no_space(notRemoveSpace02)
            # print(company_targetPrice)

            # 52주최고, 최저 찾기
            if (second_tr != None):
                # print(second_tr)
                _second_th = second_tr.find('th')
                # print(_second_th)
                second_th = no_space(_second_th)
                # print(second_th)

                # 52주최고
                first_em = no_space(second_tr.find('td').find_all('em')[0])
                company_52weeks_max = first_em
                # print(company_52weeks_max)

                # 52주최저
                second_em = no_space(second_tr.find('td').find_all('em')[1])
                company_52weeks_min = second_em
                # print(company_52weeks_min)
            #end if (second_th == "52주최고l최저"):

        else:
            # 처음부터 52주최고l최저 나왔다는 말은 투자의견과 목표주가는 "N/A" 값이란 말
            company_opinion = "N/A"
            company_targetPrice = "N/A"

            # 52주최고
            notRemoveSpace01 = first_tr.find_all('em')[0]
            # print(notRemoveSpace01)
            company_52weeks_max = no_space(notRemoveSpace01)
            # print(company_52weeks_max)

            # 최저
            notRemoveSpace02 = first_tr.find_all('em')[1]
            # print(notRemoveSpace01)
            company_52weeks_min = no_space(notRemoveSpace02)
            # print(company_52weeks_max)

        #end if (first_th == "투자의견l목표주가"):

# 기업 주소 크롤링
        get_url = get_url_site + ticker_code
        html_url = requests.get(get_url)
        html_url = html_url.content.decode('cp949', 'replace')
        url_soup = BeautifulSoup(html_url, 'html.parser')

        find_url_table = url_soup.find('table', attrs={'id': 'cTB201'})
        # print(find_url_table)
        find_url_tr = find_url_table.find_all('tr')[2]
        # print(find_url_tr)
        company_url = find_url_tr.a.string
        # print(company_url)

# 기업 개요 크롤링
        comment_url = get_comment_url + ticker_code
        html_comment_url = requests.get(comment_url)
        html_comment = html_comment_url.content.decode('cp949', 'replace')
        comment_soup = BeautifulSoup(html_comment, 'html.parser')

        if comment_soup.find('div', attrs={'class':'summary_info'}) == None:
            company_comment = '해당 자료가 없습니다'
        else:
            find_summary_info = comment_soup.find('div', attrs={'class':'summary_info'})
            # print(find_summary_info)
            company_comment = no_space(find_summary_info).replace('기업개요','').replace('\n', '').replace('\t', '')
            # print(company_comment)

        count += 1
        # end for
        mydata = [ticker_code,# 종목 코드
                  company_capitalization,# 시가총액
                  company_rank,# 시가순위
                  company_share,# 상장주식수
                  company_value, # 액면가
                  company_opinion, # 투자의견
                  company_targetPrice, #목표주가
                  company_52weeks_max, #52주최고가
                  company_52weeks_min,# 52주최저가
                  company_comment, # 기업개요
                  company_url] # 기업 url
        saveData.append(mydata)

        mycolumns = ['ticker_code',  # 종목 코드
                     'company_capitalization',  # 시가총액
                     'company_rank',  # 시가순위
                     'company_share',  # 상장주식수
                     'company_value',  # 액면가
                     'company_opinion',  # 투자의견
                     'company_targetPrice',  # 목표주가
                     'company_52weeks_max',  # 52주최고가
                     'company_52weeks_min',  # 52주최저가
                     'company_comment',  # 기업개요
                     'company_url']  # 기업 url
        myframe = DataFrame(saveData, columns=mycolumns)

        #테이블명
        tableName = 'companys'

        # dbInsert.dbInsert(myframe, tableName)

    # print(saveData)
    print('총 크롤링한 종목코드 개수 : ', count)
# def getData():


# to_csv 넣는 함수
def saveFile():
    filename = 'company_info_file.csv'
    mycolumns = ['ticker_code',# 종목 코드
                  'company_capitalization',# 시가총액
                  'company_rank',# 시가순위
                  'company_share',# 상장주식수
                  'company_value', # 액면가
                  'company_opinion', # 투자의견
                  'company_targetPrice', #목표주가
                  'company_52weeks_max', #52주최고가
                  'company_52weeks_min', # 52주최저가
                'company_comment', # 기업개요
                 'company_url'] # 기업 url
    myframe = DataFrame(saveData, columns=mycolumns)
    myframe.to_csv(filename, encoding='cp949', mode='w', index=True)
    print(filename + '으로 저장되었습니다.')
# end def saveFile():

# DB에 넣는 함수


# def toDatabase(myframe, tableName):
#     #mysql에 필요한 것 import
#     import pymysql
#     from sqlalchemy import create_engine
#
#     db_connection_str = 'mysql+pymysql://root:mysql@localhost/elstock'
#     db_connection = create_engine(db_connection_str)
#     conn = db_connection.connect()
#
#     myframe.to_sql(name=tableName, con=db_connection, if_exists='append',index=False)
# #end def toDatabase():


############################################################
print('크롤링 시작')
getData()
# saveFile()
print('크롤링 종료')


