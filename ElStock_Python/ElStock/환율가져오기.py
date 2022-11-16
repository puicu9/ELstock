from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from bs4 import BeautifulSoup
import numpy as np

# 구글 드라이버 사용하기위해 작성, 드라이버 깔려있는 위치를 설정하면됨.
driver = webdriver.Chrome('/Users/y_chan/study/chromedriver')
# 원하는 정보가 있는 url주소 설정
driver.get("https://finance.naver.com/marketindex/exchangeDetail.naver?marketindexCd=FX_USDKRW")
# 오류를 막기위해 시간 초를 주어서 오류방지
driver.implicitly_wait(10)
# 특정 frame으로 이동시켜주기 위해 frame의 id나 class값을 넣어줌.
driver.switch_to.frame('exchangeDailyQuote')
# 페이지 정보를 가져옴
html= driver.page_source
# BeautifulSoup이용하여, 파싱함.
soup = BeautifulSoup(html,"html.parser")
# 환율에 대한 정보는 tbody안에 다 들어있음.
# print(type(soup))
soup = soup.findAll('tr',attrs={'class':['down','up','sam2']})
# print(type(soup))
#Exchange Rate 환율 영어로
# print(soup)
# print(type(soup))

er_colunm=['일자','환율']
er_result= []

for aaa in soup:
    date=aaa.find_all('td',attrs={'class','date'})
    er = aaa.find_all('td', attrs={'class': 'num'})
    er=er[0]
    # print(er)
    for bbb in date:
        date= bbb.get_text()
        er_result.insert(0,date)
        # print(date)
    for ccc in er:
        num=ccc.get_text()
        er_result.insert(1,num)

print(er_result)
from pandas import DataFrame


# 보기 좋게 출력하기 위해, 1차원 리스트를 배열로 변환
er_array = np.array(er_result)

# 2차원 배열로 만들기 위해, 총 길이를 구함. (컬럼의 숫자에 따라 다름?)
# start=len(er_array)
# print(start)
# 1차원 배열을 9행 2열로 바꿈 . 총 18의 길이를 가지는 배열이기떄문에
result = er_array.reshape(9,2)
print(result)

# 데이터프레임 형태로 변환(컬럼설정함)
er_frame=DataFrame(result,columns=er_colunm)
print(er_frame)


    # print(type(date))
    # print(type(er))

