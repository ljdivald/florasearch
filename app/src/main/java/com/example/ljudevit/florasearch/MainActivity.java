package com.example.ljudevit.florasearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button FCDButton = (Button) findViewById(R.id.FCDActivityButton);
        Button HerbarButton = (Button) findViewById(R.id.HerbarActivityButton);

        FCDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FCDIntent = new Intent(MainActivity.this, FCDSearchActivity.class);
                startActivity(FCDIntent);
            }
        });

        HerbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HerbarIntent = new Intent(MainActivity.this, HerbarSearchActivity.class);
                startActivity(HerbarIntent);
            }
        });

        tvData = (TextView) findViewById(R.id.tv);
        //new JSONTask().execute("http://hirc.botanic.hr/services/Herbar.svc/40835/Details");

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return new HttpHandler().getResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvData.setText(result);
        }
    }
}
