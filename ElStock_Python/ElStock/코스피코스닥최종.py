import numpy as np
import requests
from bs4 import BeautifulSoup
import ssl
myencoding='cp949'

# # # https같은경우 안읽어질떄, encoding는 utf-8방식은 한글이 깨져서 cp949로 인코딩함.
# # # context = ssl._create_unverified_context()
url = 'https://finance.naver.com/sise/sise_index_day.naver?code=KOSPI&page='
kospi_list=[]
kospi_columns=['날짜','지수']
kosdaq_list=[]
kosdaq_columns=['날짜','지수']
import unicodedata
for page in range(50):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    td=soup.findAll('td',attrs={'class':['date','number_1']})
    td_array = np.array(td, dtype=object)
    start = int(len(td_array) / 5)
    kospi_test=td_array.reshape(start,5)
    # print(kospi_test)
    for kospi_data in kospi_test:
        kospi_date=kospi_data[0].string
        print(kospi_date)
        kospi_index=str(kospi_data[1].string)
        # kospi_index=kospi_index.replace(',','')
        print(kospi_index)
        kospi_list.append([kospi_date,kospi_index])
#
print(kospi_list)
from pandas import DataFrame

# myframe=DataFrame(kospi_list,columns=kospi_columns)
# print(myframe)
# # filename='코스프지수정보.csv'
# # myframe.to_csv(filename,encoding='utf-8')
print('끝!')
#
import pymysql
for list in kospi_list:
    conn = pymysql.connect(host='localhost', user='root', password='mysql',
                                db='elstock', charset='utf8')
    curs = conn.cursor()
    # kospi_index=float(list[1])
    try:
        curs.execute("create table kospis("
                         " kospi_date varchar(30) , "
                         " kospi_index varchar(30)) "

                        )
        conn.commit()
    except Exception:
            pass
    sql = """insert into kospis(kospi_date,kospi_index)
                 values (%s, %s)"""
    curs.execute(sql, list)
    conn.commit()
    conn.close()
print('코스피 끝!')

url = 'https://finance.naver.com/sise/sise_index_day.naver?code=KOSDAQ&page='

for page in range(50):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    td=soup.findAll('td',attrs={'class':['date','number_1']})
    td_array = np.array(td, dtype=object)
    start = int(len(td_array) / 5)
    kosdaq_test=td_array.reshape(start,5)

    for kosdaq_data in kosdaq_test:
        kosdaq_date=kosdaq_data[0].string
        # print(kosdaq_date)
        kosdaq_index=kosdaq_data[1].string
        # kosdaq_index = kosdaq_index.replace(',', '')
        # kosdaq_index = float(kosdaq_index)
        # print(kosdaq_index)
        kosdaq_list.append([kosdaq_date,kosdaq_index])

print(kosdaq_list)

for list in kosdaq_list:
    conn = pymysql.connect(host='localhost', user='root', password='mysql',
                                db='elstock', charset='utf8')
    curs = conn.cursor()
    try:
        curs.execute("create table kosdaqs("
                         " kosdaq_date varchar(30) , "
                         " kosdaq_index varchar(30)) "

                        )
        conn.commit()
    except Exception:
            pass
    sql = """insert into kosdaqs(kosdaq_date,kosdaq_index)
                 values (%s, %s)"""
    curs.execute(sql, list)
    conn.commit()
    conn.close()