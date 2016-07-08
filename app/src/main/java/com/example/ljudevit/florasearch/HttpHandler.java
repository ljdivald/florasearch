package com.example.ljudevit.florasearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {

    public String getResponse(String urlString, Boolean test) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONstring = "";

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line = reader.readLine()) != null) {
                JSONstring += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JSONstring = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSONstring;
    }
}
