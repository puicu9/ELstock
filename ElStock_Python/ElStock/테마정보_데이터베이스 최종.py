# 테마코드, 테마명, 전날대비,3일등락률에 관한 크롤링 및 데이터베이스에 넣기
# DB 비밀번호 다른점, 체크하시길바랍니당.
import urllib.request
import numpy as np
import requests
from bs4 import BeautifulSoup
import ssl
myencoding='cp949'

# # # https같은경우 안읽어질떄, encoding는 utf-8방식은 한글이 깨져서 cp949로 인코딩함.
# # # context = ssl._create_unverified_context()
url = 'https://finance.naver.com/sise/theme.naver?&page='
th_list=[]
theme_column=['테마코드','테마명','전날대비','3일등락률']

for page in range(7):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    # 테마 코드 및 코드명
    theme_data = soup.findAll('td',attrs={'class':['col_type1','col_type2','col_type3']})
    # print(theme_data)
    th_array=np.array(theme_data,dtype=object)
    start=int(len(th_array)/3)
    th_result=th_array.reshape(start,3)
    # print(th_result)
    for get_data in th_result :
        th_code=get_data[0].find('a').attrs['href']
        th_code = th_code.split('no=')
        # 테마 코드
        th_code = th_code[1]
        # 테마 명
        th_name = get_data[0].find('a').string
        # print(th_code)
        # print(th_name)
        # 전날대비
        th_updown=get_data[1].find('span').string.strip()
        # 3일평균?
        th_avg_3=get_data[2].find('span').string.strip()
        # print(th_updown)
        # print(th_avg_3)
        th_list.append([th_code, th_name, th_updown, th_avg_3])
# print(th_list)
from pandas import DataFrame

# myframe=DataFrame(th_list,columns=theme_column)
# print(myframe)
# filename='테마코드.csv'
# myframe.to_csv(filename,encoding='utf-8')
# print('끝!')

# 데이터 베이스에 추가
import pymysql
for list in th_list:
    conn = pymysql.connect(host='localhost', user='root', password='mysql',
                                db='elstock', charset='utf8')
    curs = conn.cursor()
    try:
        curs.execute("create table themes("
                         " th_code varchar(30) , "
                         " th_name varchar(30) , "
                         " th_updown varchar(30) , "
                         " th_avg_3 varchar(30)) "
                        )
        conn.commit()
    except Exception:
            pass
    sql = """insert into themes(th_code,th_name,th_updown,th_avg_3)
                 values (%s, %s, %s, %s)"""
    curs.execute(sql, list)
    conn.commit()
    conn.close()


# myframe.to_sql('themes',engin,if_exists='replace',index=False)
# conn.commit()
# conn.close()

# *******************************************
# *******************************************
# *******************************************
# 테마별 티커 정보

# db접근해서, th_code만 가져오기
pymysql.install_as_MySQLdb()
from sqlalchemy import create_engine
import pandas as pd
engin= create_engine("mysql://root:mysql@localhost/elstock")
data= pd.read_sql('SELECT * FROM themes',engin)
code_list=data['th_code']
print(code_list)
#
# # 테마별 티커정보 가져오기위한 url
ticker_url='https://finance.naver.com/sise/sise_group_detail.naver?type=theme&no='
# # 테마별 티커 정보 담을 list
ticker_list= []
ticker_column=['테마코드','티커명','티커']
#
for th_code in code_list:
    url=ticker_url+th_code
    # print(url)
    html = requests.get(url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    soup = soup.findAll('div', attrs={'class': 'name_area'})
    for abcd in soup:
        ticker = abcd.find('a').attrs['href']
        ticker_name = abcd.find('a').string
        ticker_code = ticker.split('code=')
        # print(ticker_code)
        ticker_code = ticker_code[1]
        print(th_code)
        # print(ticker_name)
        # print(ticker_code)
        ticker_list.append([th_code,ticker_name, ticker_code])
#         # result = {code: ticker_list}
#         # result_list = list(zip(result.keys(), result.values()))
#         # print(ticker_list)
#         # print(result_list)
#
for list in ticker_list:
    conn = pymysql.connect(host='localhost', user='root', password='mysql',
                                   db='elstock', charset='utf8')
    curs = conn.cursor()
    try:
        curs.execute("create table ticker_by_theme("
                             "th_code varchar(30) , "
                             "ticker_name varchar(30) , "
                             "ticker_code varchar(30)"
                                ")"
                             )
        conn.commit()
    except Exception:
        pass

    sql = """insert into ticker_by_theme(th_code,ticker_name,ticker_code)
                             values (%s, %s, %s)"""
    curs.execute(sql, list)
    conn.commit()
    conn.close()
#         # print(result)
#     # ticker_list.append([code,ticker_name, ticker])
#     # print(ticker_list)
#     # result = {code: ticker_list}
#     # print(result)
#     # print(result_list)
#     # result_list.append([result_list[0][0]])
#     # result_list=list(zip(result.keys(),result.values()))
#     # print(ticker_list)
#     # print(result)
#
# # print(result)
# # print(ticker_list)
#
ticker_frame=DataFrame(ticker_list,columns=ticker_column)
# print(myframe)
# filename='테마별 티커명과 티커11_08.csv'
# ticker_frame.to_csv(filename,encoding='utf-8')
print('끝!')

