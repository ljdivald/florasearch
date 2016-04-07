package com.example.ljudevit.florasearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpHandler {

    public String getResponse(String urlString) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));

            String JSONstring = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                JSONstring+=line;
            }

            return JSONstring;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<HerbarObject> getHerbarResults(String url) throws Exception {
        List<HerbarObject> resoults = new ArrayList<>();

        JSONArray parentArray = new JSONArray(new HttpHandler().getResponse(url));
        JSONObject singleObject;
        HerbarObject singleResoult = new HerbarObject();
        int i;

        for (i = 0; i < parentArray.length(); i++) {
            singleObject = parentArray.getJSONObject(i);
            singleResoult.setDrzava(singleObject.getString("Drzava"));
            singleResoult.setGodina(singleObject.getInt("Godina"));
            singleResoult.setID_herbara(singleObject.getInt("Id"));
            singleResoult.setID_slike(singleObject.getInt("IdSlike"));
            singleResoult.setIme_svojte(singleObject.getString("ImeSvojte"));
            singleResoult.setKoordinate(singleObject.getString("OznKoord"));
            singleResoult.setNaziv_zbirke(singleObject.getString("NazivZbirke"));
            singleResoult.setPorodica(singleObject.getString("Porodica"));
            //singleResoult.setSabirac(singleObject.getString("Sabiraci"));
            singleResoult.setTip(singleObject.getInt("Tip"));
            resoults.add(singleResoult);
        }

        return resoults;
    }
}
