package apiservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestAPIService {

    public static void main(String[] args) throws Exception {
        String endpoint = "https://apis.data.go.kr/B551011/KorService2";
        String serviceKey = "1U35k8kmbxQjdBviatyoykg8OTeyjdfvMcNNfvg5X3lnu52Cgb/QYO3zi6ilSJzhoiWD8xcmjki3g8edhQTdQQ==";

        String xmlRequest =
        	    "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
        	    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://apis.data.go.kr/\">" +
        	    "  <soapenv:Header/>" +
        	    "  <soapenv:Body>" +
        	    "    <ns:areaBasedList>" +
        	    "      <serviceKey>1U35k8kmbxQjdBviatyoykg8OTeyjdfvMcNNfvg5X3lnu52Cgb/QYO3zi6ilSJzhoiWD8xcmjki3g8edhQTdQQ==</serviceKey>" +
        	    "      <MobileOS>ETC</MobileOS>" +
        	    "      <MobileApp>GAMJAS</MobileApp>" +
        	    "      <contentTypeId>15</contentTypeId>" +
        	    "      <areaCode>1</areaCode>" +
        	    "      <numOfRows>5</numOfRows>" +
        	    "      <pageNo>1</pageNo>" +
        	    "    </ns:areaBasedList>" +
        	    "  </soapenv:Body>" +
        	    "</soapenv:Envelope>";

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(xmlRequest.getBytes());
        os.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        conn.disconnect();
    }
}
