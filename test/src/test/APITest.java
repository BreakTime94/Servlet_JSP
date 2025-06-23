package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import attraction.Attribution;


public class APITest {
	
	public static void main(String[] args) throws IOException {
		String apiKey = "6a594745646b696d3435774a61436d";
        String urlStr = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/TbVwAttractions/1/10/";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        System.out.println("응답코드: " + conn.getResponseCode());

        BufferedReader rd = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8")
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        
        System.out.println(sb);
        System.out.println(sb.toString());
        System.out.println(JsonParser.parseString(sb.toString()));
       
        JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
        
        System.out.println(JsonParser.parseString(sb.toString()) == (json));
        System.out.println(json);
//        
        JsonArray rows = json.getAsJsonObject("TbVwAttractions").getAsJsonArray("row");
        
        Gson gson = new Gson();
        Attribution[] arrays = gson.fromJson(rows, Attribution[].class);
        
        List<Attribution> list = Arrays.asList(arrays);
        
        for(Attribution a : list) {
        	System.out.println("POSTSJ가 뭐지? :: " + a.getPOST_SJ());
        	System.out.println("주소 :: " + a.getADDRESS());
        	System.out.println("언어코드 :: " + a.getLANG_CODE_ID());
        	System.out.println("시리얼넘버 :: " + a.getPOST_SN());
        	System.out.println("운영시간 :: " + a.getCMMN_USE_TIME());
        	System.out.println("지하철 정보 :: " + a.getSUBWAY_INFO());
        	System.out.println();
        }
//        System.out.println(rows);
//        for (JsonElement el : rows) {
//        	JsonObject obj = el.getAsJsonObject();
//        	
//        }
//        
//        System.out.println("결과:");
//        System.out.println(sb.toString()); // JSON 문자열 출력
        
       
//        Attribution attr = gson.fromJson(json, null);
        
        
        
        
	}
	
}
