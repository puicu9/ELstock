# 네이버 기본 정보 크롤링(종목코드, 시가총액, 시가총액순위,상장주식수, 액면가)

import urllib.request
from datetime import datetime
import pandas as pd
from pandas import DataFrame
from pykrx import stock

from bs4 import BeautifulSoup
from selenium import webdriver
import requests

saveData = []  # 엑셀로 저장될 리스트

# 종목코드 리스트

# 삼성전자 : 005930
# naver : 035420
# 카카오 : 035720
# 현대차 : 005380
# sk바이오사이언스 : 320440

company_list = ['005930', '035420', '035720', '005380', '320440']
query_list=['삼성전자', '네이버', '카카오', '현대차', 'sk바이오사이언스']

# 기업 정보 얻기 위한 주소
# get_news_url = 'https://finance.naver.com/item/news.naver?code='
search_news_url = 'https://search.naver.com/search.naver?where=news&sm=tab_jum&query='

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
    company_code = ''
    news_title = '' # 뉴스 제목
    news_info = '' # 정보제공
    news_date = '' # 기사 기재 일자

    for company_code in company_list[0::]:  # 오류 :
        print('종목코드 : ' + company_code)

# 기업정보 크롤링
    for query in query_list[0::]:
        print('검색어 명칭 : ' + query)
        url = search_news_url + query

        html = requests.get(url)
        html = html.content.decode('cp949', 'replace')
        soup = BeautifulSoup(html, 'html.parser')

        # req = requests.get(url, headers={'User-agent': 'Mozilla/5.0'})
        # soup = BeautifulSoup(req.text, "lxml")  # html에 대하여 접근할 수 있도록

        list_news = soup.find({'ul': 'list_news'}).select_one('li:nth-of-type(1)')
        print(list_news)

        # news_area = list_news.find('div', attrs={'class':'news_area'})
        # print(news_area)





        # # 포괄적인 div 찾기
        # findFirst = soup.find('div', attrs={'class': 'first'})
        # # print(findFirst)
        #
        # # 구체적 table 찾기(이걸 활용해서 시가총액, 시가총액순위  찾기)
        # findTable = findFirst.find('table')
        # # print(findTable)
        #
        # # 시가총액, company_capitalization
        # find_company_capital = findTable.select_one('tr:nth-of-type(1)').find('td')
        # # print(find_company_capital)
        # company_capitalization = no_space(find_company_capital)
        #
        # # 시가총액순위, company_rank
        # find_second_td = findTable.select_one('tr:nth-of-type(2)').find('td')
        # # print(find_company_rank)
        # second_td = no_space(find_second_td)









        count += 1
        # end for
        mydata = [company_code, news_title, news_info, news_date]
        saveData.append(mydata)
    # print(saveData)
    print('총 크롤링한 종목코드 개수 : ', count)

# def getData():


def saveFile():
    filename = 'company_news.csv'
    mycolumns = ['company_code', 'news_title', 'news_info', 'news_date']
    myframe = DataFrame(saveData, columns=mycolumns)
    myframe.to_csv(filename, encoding='cp949', mode='w', index=True)
    print(filename + '으로 저장되었습니다.')
# end def saveFile():

print('크롤링 시작')
getData()
# saveFile()
print('크롤링 종료')


