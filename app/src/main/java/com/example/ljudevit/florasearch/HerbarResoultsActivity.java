package com.example.ljudevit.florasearch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HerbarResoultsActivity extends Activity {

    TextView ResoultsDisplay;
    List<HerbarObject> herbarObjectList;
    String serverResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herbar_resoults_activity);

        ResoultsDisplay = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        new Dobavljanje().execute(url);

        try {
            wait(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            herbarObjectList = new HttpHandler().getHerbarResults(serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Dobavljanje extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            List<HerbarObject> lista = new ArrayList<HerbarObject>();
            HttpHandler JSONresoult = new HttpHandler();

            try {
                return JSONresoult.getResponse(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //herbarObjectList=result;
            ResoultsDisplay.setText(result);
            serverResponse="\"biljke\":"+result;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}


