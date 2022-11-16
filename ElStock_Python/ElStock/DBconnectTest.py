import pymysql
conn=pymysql.connect(host='localhost',user='root',password='qkrdudcks1!',db='teampr',charset='utf8')
cur=conn.cursor()
cur.execute("CREATE TABLE test "
            "(id varchar(4))")
conn.commit()
conn.close()