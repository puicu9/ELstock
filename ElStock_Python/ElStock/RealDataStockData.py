from bs4 import BeautifulSoup
from datetime import datetime
import requests
import time
import pymysql

#실시간 정보 가져오기
def get_code(company_code):
    url = "https://finance.naver.com/item/main.naver?code=" + company_code
    result = requests.get(url)
    bs_obj = BeautifulSoup(result.content,"html.parser")
    return bs_obj

def getList(company_code):
    #sptxt sp_txt3
    list = []
    bs_obj = get_code(company_code)
    no_today = bs_obj.find("p", {"class" : "no_today"})
    today_jongga = int(no_today.find("span", {"class": "blind"}).text.replace(",",""))

    tableTag = bs_obj.find("table", {"class": "no_info"})
    spanTag = tableTag.find_all("span", {'class': "blind"})

    now = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    siga = int(spanTag[4].text.replace(",", ""))
    goga = int(spanTag[1].text.replace(",", ""))
    juga = int(spanTag[5].text.replace(",", ""))
    volumn = int(spanTag[3].text.replace(",", ""))


    return [now, siga, goga, juga, today_jongga, volumn, company_code]

company_codes = ['005930','035720'] #카카오, 삼성 주식 종목 코드

while True:
    for code in company_codes:
        list = getList(code)
        print(list)
        conn = pymysql.connect(host='localhost', user='root', password='mysql',
                               db='elstock', charset='utf8')

        curs = conn.cursor()
        try:
            curs.execute("create table realtimedata("
                         "date varchar(30),"
                         "open int,"
                         "high int,"
                         "low int,"
                         "close int,"
                         "volume int,"
                         "ticker_code varchar(20))")
            conn.commit()
        except Exception:
            pass

        sql = """insert into realtimedata(date,open,high,low,close,volume,ticker_code)
                 values (%s, %s, %s, %s, %s, %s, %s)"""
        curs.execute(sql, list)
        conn.commit()

        conn.close()
    time.sleep(30)


# 1년치 주가 데이터 가져오기
# df = stock.get_market_ohlcv("20211101","20221125",company_codes)
#
# header = ['date','open','high','low','close','volume']
#
# df = df.reset_index()
# df.columns = header
#
# from sqlalchemy import create_engine
# import pymysql
# import pandas as pd
#
# #mysql+pymysql://[사용자]:[비밀번호]@[주소]/[테이블명]
# try:
#     db_connection_str = 'mysql+pymysql://root:mysql@localhost/elstock'
#     db_connection = create_engine(db_connection_str)
#     conn = db_connection.connect()
#     df.to_sql(name='getMarkets',con=conn, if_exists='append', index=False)
# except:
#     pass

