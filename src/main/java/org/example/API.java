package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    public static String getData(String endpoint) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader buff = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = buff.readLine()) != null) {
            content.append(line);
        }
        buff.close();
        connection.disconnect();
        return content.toString();
    }

}

