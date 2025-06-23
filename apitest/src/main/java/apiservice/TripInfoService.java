package apiservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import apidto.TripInfo;

public class TripInfoService {
	
	public List<TripInfo> getList() throws IOException {
		String urlStr = "http://openapi.seoul.go.kr:8088/6a594745646b696d3435774a61436d/json/TbVwAttractions/1/5/";
		
		URL url = new URL(urlStr);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET");
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		StringBuilder sb = new StringBuilder();
		String line ;
		while((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		JsonObject jobj = JsonParser.parseString(sb.toString()).getAsJsonObject();
		JsonArray rows= jobj.getAsJsonObject("TbVwAttractions").getAsJsonArray("row");
		
		Gson gson = new Gson(); 
		
		TripInfo[] arr = gson.fromJson(rows, TripInfo[].class);
		
		List<TripInfo> list = Arrays.asList(arr);
		
		return list;
	}
	
	public TripInfo selectOne(int no) throws IOException {
		
		List<TripInfo> list = (new TripInfoService()).getList();
		TripInfo ti = null;
		for(TripInfo t : list) {
			ti = list.get(no - 1);
		}
		
		return ti;
	}
	
	public static void main(String[] args) throws IOException {
		
		TripInfoService tis = new TripInfoService();
		List<TripInfo> list = tis.getList();
		
		System.out.println(list);
		
		System.out.println(tis.selectOne(2).getADDRESS());
		System.out.println(tis.selectOne(2).getCMMN_TELNO());
		System.out.println(tis.selectOne(2).getCMMN_USE_TIME());
		
	}
}
