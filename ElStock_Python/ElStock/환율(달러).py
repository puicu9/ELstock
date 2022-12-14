import urllib.request
import numpy as np
import requests
from bs4 import BeautifulSoup
import ssl

from ElStock import dbInsert

myencoding='cp949'

# 환율 : 엔화는 url주소는 JPYKRW로 바꾸면된다.
url = 'https://finance.naver.com/marketindex/exchangeDailyQuote.naver?marketindexCd=FX_USDKRW&page='
# url = 'https://finance.naver.com/marketindex/exchangeDailyQuote.naver?marketindexCd=FX_JPYKRW&page='
er_colunm=['일자','환율']
er_result= []
for page in range(30):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    soup = soup.findAll('tr', attrs={'class': ['down', 'up', 'same2']})
    for aaa in soup:
        date = aaa.find_all('td', attrs={'class', 'date'})
        er = aaa.find_all('td', attrs={'class': 'num'})
        er = er[0]
        # print(er)
        for bbb in date:
            date = bbb.get_text()
            er_result.insert(0, date)
            # print(date)
        for ccc in er:
            num = ccc.get_text()
            er_result.insert(1, num)
er_array = np.array(er_result)
# print(er_result)
start=int(len(er_array)/2)
result = er_array.reshape(start, 2)
# print(result)
from pandas import DataFrame
er_frame=DataFrame(result,columns=er_colunm)


# filename='환율(엔화).csv'
# er_frame.to_csv(filename,encoding='utf-8')


# DB에 넣기
dbTableName = 'usds'
dbInsert.dbInsert(er_frame, dbTableName)

print('환율(달러) 크롤링 끝!')

