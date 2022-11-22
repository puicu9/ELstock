import urllib.request

import requests
from bs4 import BeautifulSoup
import ssl
myencoding='cp949'

# https같은경우 안읽어질떄, encoding는 utf-8방식은 한글이 깨져서 cp949로 인코딩함.
# context = ssl._create_unverified_context()
url = 'https://finance.naver.com/sise/theme.naver?&page='
theme_list=[]
theme_column=['테마코드','테마명']

for page in range(7):
    page_url = url + str(page + 1)
    html = requests.get(page_url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    soup = soup.findAll('td', attrs={'class': 'col_type1'})


    # print(page_url)
    for abcd in soup:
        theme_code=abcd.find('a').attrs['href']
        theme_code=theme_code.split('no=')
        theme_code=theme_code[1]
        theme_name=abcd.find('a').string
        theme_list.append([theme_code,theme_name])
        # print(theme_list)
        # print(theme_name)
        # print(theme_code)

# print(theme_list)

from pandas import DataFrame

myframe=DataFrame(theme_list,columns=theme_column)
print(myframe)
filename='테마코드.csv'
myframe.to_csv(filename,encoding='utf-8')
print('끝!')

ticker_url='https://finance.naver.com/sise/sise_group_detail.naver?type=theme&no='

code_list=myframe['테마코드']
# print(code_list)
ticker_list= []
ticker_column=['테마코드','티커명','티커']

result_list= {}
for code in code_list:
    url=ticker_url+code
    # print(url)
    html = requests.get(url)
    html = html.content.decode(myencoding, 'replace')
    soup = BeautifulSoup(html, 'html.parser')
    soup = soup.findAll('div', attrs={'class': 'name_area'})
    for abcd in soup:
        ticker = abcd.find('a').attrs['href']
        ticker_name = abcd.find('a').string
        ticker = ticker.split('code=')
        ticker = ticker[1]
        # print(ticker_name)
        # print(ticker)
        ticker_list.append([code,ticker_name, ticker])
        # result = {code: ticker_list}
        # result_list = list(zip(result.keys(), result.values()))
        # print(ticker_list)
        # print(result_list)

        # print(result)
    # ticker_list.append([code,ticker_name, ticker])
    # print(ticker_list)
    # result = {code: ticker_list}
    # print(result)
    # print(result_list)
    # result_list.append([result_list[0][0]])
    # result_list=list(zip(result.keys(),result.values()))
    # print(ticker_list)
    # print(result)

# print(result)
# print(ticker_list)

ticker_frame=DataFrame(ticker_list,columns=ticker_column)
print(myframe)
# filename='테마별 티커명과 티커11_08.csv'
# ticker_frame.to_csv(filename,encoding='utf-8')
print('끝!')

# result=[]
# for abc in ticker_list:
#     result_list={abc[0]:abc[1:]}
#     # print(type(result_list.keys()))
#     print(result_list.values())
#
#     # print(result_list)
#     # for i in range(len(result_list)):
#     #     dict[result_list[i][1]] += [result_list[i][0]]
#     # print(dict)
#
#
#
# # print(result)
# print(result_list)
# # result = {code: ticker_list}
# # print(result)
# # print(result_list)