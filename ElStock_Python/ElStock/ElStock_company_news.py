import requests
from bs4 import BeautifulSoup as bs
import pandas as pd

import re  # 정규표현식

import dbInsert

headers = {
 'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36'}  # 네이버가 크롤링 접속 차단해놓아서 headers 넣어줌 https://m.blog.naver.com/kiddwannabe/221185808375 참고
url = 'https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=259'  # 네이버 경제 뉴스 url
page = requests.get(url, headers=headers)

soup = bs(page.text, "html.parser")

news_url_list = []  # 뉴스 url 리스트 생성

# https://toentoi.tistory.com/43 참고하기
for href in soup.find("div", class_="list_body newsflash_body").find_all(
        "li"):  # div의 클래스가 list_body newsflash_body 인 것을 찾아 li 태그 부분만을 찾음
 news_url_list.append(href.find("a")["href"])  # 찾은 것 중에서 a 태그의 링크를 가져와서 리스트에 넣어줌

# print(news_url_list) # 뉴스 url 리스트 출력


news_info_list = []  # 뉴스 정보 리스트

for url in news_url_list:  # 뉴스 url 리스트에서 url를 하나씩 들고옴
 page = requests.get(url, headers=headers)
 soup = bs(page.text, "html.parser")

 tmp = []  # 임시 리스트 생성

 tmp.append(url)  # url 넣기

 # header 가져오기
 header = soup.find("h2", class_="media_end_head_headline")  # h2의 클래스가 media_end_head_headline 부분 찾기 ( 기사 제목 )
 header = re.sub('(<([^>]+)>)', '', str(
  header))  # html 문자 제거 https://code-study.tistory.com/55 참고, str은 문자열로 바꾸는 함수임! 크롤링한 내용을 문자열로 바꿔서 넣어줌
 header = header.replace('\n', '').replace('\t', '')  # \n 줄바꿈 태그가 들어가있어서 제거해줌
 tmp.append(header)

 # body 가져오기
 body = soup.find("div",
                  class_="newsct_article _article_body")  # div의 클래스가 newsct_article _article_body 부분 찾기 ( 기사 내용 )
 body = re.sub('(<([^>]+)>)', '',
               str(body))  # html 문자 제거 https://code-study.tistory.com/55 참고, str은 문자열로 바꾸는 함수임! 크롤링한 내용을 문자열로 바꿔서 넣어줌
 body = body.replace('\n', '').replace('\t', '')  # \n 줄바꿈 태그가 들어가있어서 제거해줌
 body = body.strip()  # 좌우 공백 제거
 tmp.append(body)

 # 이미지 가져오기
 img = soup.find("img", id="img1")
 try:
  img = img.get('data-src')
 except:
  img = "None"
 tmp.append(img)

 news_info_list.append(tmp)

df = pd.DataFrame(news_info_list)
df.columns = ['news_url','news_header','news_body','news_img']
print(df)
dbInsert.dbInsert(df,'news')
