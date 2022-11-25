package com.elstock.member.entity;


import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class GetNickname {
    protected String Nickname() throws JSONException, IOException {
        // 닉네임 API url
        String url = "https://nickname.hwanmoo.kr/?format=json&count=2";
        JSONObject json = readJsonFromUrl(url);
        JSONArray jsonArray = json.getJSONArray("words");

//        jsonArray에는 jsonArray 형태로 2개의 닉네임을 부여함. 여기에 더하여 랜덤(0~1)값으로 닉네임 가져오게 하게끔 설정
        int random = (int) Math.floor(Math.random() * 2) ;
        String nickname = jsonArray.getString(random);
        System.out.println("회원가입 시 부여할 닉네임");
        System.out.println(nickname);

        return nickname ;
    }

    private String jsonReadAll(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();

        int cp;
        while((cp = reader.read()) != -1){
            sb.append((char) cp);
        }

        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = jsonReadAll(br);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}

