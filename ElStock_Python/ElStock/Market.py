import time
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

# 2022-12-03 형태
today = datetime.today().strftime('%Y-%m-%d')
# print(today)

# 종목코드 리스트
ticker_list = stock.get_market_ticker_list()# 현재일자 기준 가장 가까운 영업일의 코스피 상장종목 리스트
# print(ticker_list)
# 날짜	시가	고가	저가	종가	거래량   총거래대금   변동률 	종목코드	종목명
mycolumns = ['date',
            'open',
            'high',
            'low',
            'close',
            'volume',
            'total',
             'rate',
             'ticker_code',
             'ticker_name'
]

res = pd.DataFrame()
for ticker_code in ticker_list[:50:]:
    df = stock.get_market_ohlcv_by_date(fromdate="20210101", todate=today, ticker=ticker_code)
    df = df.assign(종목코드=ticker_code, 종목명=stock.get_market_ticker_name(ticker_code))
    res = pd.concat([res, df], axis=0)
    # time.sleep(1)
    print('종목 코드 : ', ticker_code)
res = res.reset_index()

#열 이름 변경
res.columns = mycolumns

dbInsert.dbInsert(res, 'markets')

print('작업 끝')
