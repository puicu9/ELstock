from sqlalchemy import create_engine

def dbInsert(df,tableName): # 매개변수 (데이터 프레임, 테이블 명)
    db_connection_str = 'mysql+pymysql://root:mysql@localhost/elstock'
    db_connection = create_engine(db_connection_str)
    conn = db_connection.connect()
    df.to_sql(name=tableName,con=conn, if_exists='replace', index=False)