from pykrx import stock
import pandas as pd
import requests
from datetime import date

import dbInsert # 데이터

today = date.today()
#종목 코드 전체 불러오기
stock_code = stock.get_market_ticker_list(date=today, market="ALL")

for code in stock_code:
 print(code)
 try:
  URL = f"https://finance.naver.com/item/main.nhn?code={code}"

  webdata = requests.get(URL)
  html = webdata.text
  # print(html)

  financial_stmt = pd.read_html(webdata.text)[3]


  financial_stmt.set_index(('주요재무정보', '주요재무정보', '주요재무정보'), inplace=True)
  financial_stmt.index.rename('주요재무정보', inplace=True)
  financial_stmt.columns = financial_stmt.columns.droplevel(0)
  financial_stmt.columns = financial_stmt.columns.droplevel(1)


  financial_stmt = financial_stmt.transpose()
  financial_stmt = financial_stmt.reset_index()
  financial_stmt = financial_stmt[4:] # 연간 제무정보 제외(분기별 제무정보만 포함)



  columns = ['date','revenue','operating_profit','earnings','operation_income',
   'net_profit_rate','roe','debt_ratio','quick_ratio','reserve_ratio','eps',
   'per','bps','pbr','dividend_per_share','dividend_ratio','dividend_tendency']
  financial_stmt.columns = columns # 칼럼명 설정
  financial_stmt['ticker_code'] = code # 종목 코드 추가

  dbInsert.dbInsert(financial_stmt,'fs') #db 저장
 except Exception:
  print("err")
