import urllib.request
import numpy as np
import requests
from bs4 import BeautifulSoup
import ssl
myencoding='cp949'

# 환율 : 엔화는 url주소는 JPYKRW로 바꾸면된다.
url = 'https://finance.naver.com/marketindex/exchangeDailyQuote.naver?marketindexCd=FX_USDKRW&page='
er_colunm=['일자','환율']
er_result= []
for page in range(30):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    soup = soup.findAll('tr', attrs={'class': ['down', 'up', 'same2']})
    data=np.array(soup,dtype=object)
    # print(data)
    for aaa in data:
        date = aaa[1].string
        print(date)
        er=aaa[3].string
        print(er)
        # print(er)
        er_result.append([date,er])


import pymysql
# #
for list in er_result:
    # print(list)
    conn = pymysql.connect(host='localhost', user='root', password='mysql',
                                db='elstock', charset='utf8')
    curs = conn.cursor()
    try:
        curs.execute("create table USDs("
                         " usd_date varchar(30), "
                         " usd_price varchar(30)) "

                        )
        conn.commit()
    except Exception:
            pass
    sql = """insert into USDs(usd_date,usd_price)
                            values (%s,%s)"""
    curs.execute(sql, list)
    conn.commit()
    conn.close()

print('달러 끝!')

# 엔화

url = 'https://finance.naver.com/marketindex/exchangeDailyQuote.naver?marketindexCd=FX_JPYKRW&page='
er_colunm=['일자','환율']
er_result= []
for page in range(30):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    soup = soup.findAll('tr', attrs={'class': ['down', 'up', 'same2']})
    data=np.array(soup,dtype=object)
    # print(data)
    for aaa in data:
        date = aaa[1].string
        print(date)
        er=aaa[3].string
        print(er)
        # print(er)
        er_result.append([date,er])


import pymysql
# #
for list in er_result:
    # print(list)
    conn = pymysql.connect(host='localhost', user='root', password='mysql',
                                db='elstock', charset='utf8')
    curs = conn.cursor()
    try:
        curs.execute("create table JPYs("
                         " jpy_date varchar(30), "
                         " jPY_price varchar(30)) "

                        )
        conn.commit()
    except Exception:
            pass
    sql = """insert into JPYs(jpy_date,jPY_price)
                            values (%s,%s)"""
    curs.execute(sql, list)
    conn.commit()
    conn.close()


# filename='환율(엔화).csv'
# er_frame.to_csv(filename,encoding='utf-8')
print('끝!')

