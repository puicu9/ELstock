import urllib.request
import numpy as np
import requests
from bs4 import BeautifulSoup
import ssl
myencoding='cp949'

# 환율 : 엔화는 url주소는 JPYKRW로 바꾸면된다.
# url = 'https://finance.naver.com/marketindex/exchangeDailyQuote.naver?marketindexCd=FX_USDKRW&page='
url = 'https://finance.naver.com/marketindex/exchangeDailyQuote.naver?marketindexCd=FX_JPYKRW&page='
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
# print(len(er_array))
result = er_array.reshape(300, 2)
# print(result)
from pandas import DataFrame
er_frame=DataFrame(result,columns=er_colunm)
# 데이터 프레임 형태로 데이터 베이스에 추가 할수있음.
import pymysql
# 구버젼 사용시 2.xx 사용하면, pip나 interpreter이용하여 mysqldb설치해줘야함.
# 3.xx 버젼 부터는 이 문장 사용가능?
pymysql.install_as_MySQLdb()
from sqlalchemy import create_engine
conn=pymysql.connect(
    host='localhost',user='root',
    password='qkrdudcks1!',
    db='teampr',
    charset='utf8')
# 각자 자신이 설정한 mysql 비번이랑 이름 지정하면됨
engin= create_engine("mysql://root:qkrdudcks1!@localhost/teampr")
# 첫문장은 생성할 테이블명, 가운대는 엔진??, if_exists 옵션은 replace는 있어도 새로운데이터로 대체, append는 추가로 계속 만들어줌
# 환율 정보같은경우는 매일 업데이트 되므로, replace를 하는게 정답인듯..?
er_frame.to_sql('er_info',engin,if_exists='replace')

conn.commit()
conn.close()


