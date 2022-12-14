package com.analyzer.analyzer.controller.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequest {

    private static final String USER_AGENT = "Mozilla/5.0";
    private String POST_URL = "http://localhost:8082/ok";

    private String POST_PARAMS = "";

    public HttpRequest(String url, String xmlString) {
        POST_PARAMS = xmlString;

        // ! *** CHANGE POST URL
        // POST_URL += url;
        System.out.println("====" + POST_URL);
        // ! ****

        try {
            sendPOST();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "Application/xml");

        // For POST only - START
        con.setDoInput(true);
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            // System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
}
