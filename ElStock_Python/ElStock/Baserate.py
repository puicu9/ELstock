import requests
from bs4 import BeautifulSoup
from html_table_parser import parser_functions
import pandas as pd
import dbInsert

url = 'https://www.bok.or.kr/portal/singl/baseRate/list.do?dataSeCd=01&menuNo=200643'
html = requests.get(url)
html = html.content.decode('utf-8', 'replace')
soup = BeautifulSoup(html, 'html.parser')

data = soup.find('table', attrs={'class': 'fixed'})
print(data)

table = parser_functions.make2d(data)
df = pd.DataFrame(data=table[2:], columns=table[1])
df.columns = ['year','monthdate','rate']

print(df);
dbInsert.dbInsert(df,'bmrate')
print('작업 종료')