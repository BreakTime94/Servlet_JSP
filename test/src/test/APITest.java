package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        
        
//        Gson gson = new Gson();
//        ApiResponse apiResponse = gson.fromJson(json, ApiResponse.class);
//
//        // 명소 리스트 뽑기
//        List<Attraction> attractions = apiResponse.getTbVwAttractions().getRow();
//
//        // 출력
//        for (Attraction a : attractions) {
//            System.out.println("▶ 명소 이름: " + a.getPOST_SJ());
//            System.out.println("  - 주소: " + a.getADDRESS());
//            System.out.println("  - 지하철: " + a.getSUBWAY_INFO());
//            System.out.println();
//        }
        
        
        JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
        
        JsonArray rows = json.getAsJsonObject("TbVwAttractions").getAsJsonArray("row");
        System.out.println(rows);
        for (JsonElement el : rows) {
        	JsonObject obj = el.getAsJsonObject();
        	
        }
        
        System.out.println("결과:");
        System.out.println(sb.toString()); // JSON 문자열 출력
        
        
	}
	
}
