package com.example.ljudevit.florasearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ljudevit on 5.4.2016..
 */
public class FCDSearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcd_search_display);

        EditText podrazred = (EditText) findViewById(R.id.PodrazredInput);
        EditText nadrazred = (EditText) findViewById(R.id.NadrazredInput);
        EditText red = (EditText) findViewById(R.id.RedInput);
        EditText porodica = (EditText) findViewById(R.id.PorodicaInput);
        EditText rod = (EditText) findViewById(R.id.RodInput);
        EditText LatIme = (EditText) findViewById(R.id.LatImeInput);
        EditText narodnoIme = (EditText) findViewById(R.id.NarodnoImeInput);
        EditText sinonim = (EditText) findViewById(R.id.SinonimInput);

        Button Search = (Button) findViewById(R.id.FCDSearchButton);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String additionalQueries = "?from=";

                additionalQueries += "&count=";
                additionalQueries += "&sort=";
                additionalQueries += "&desc=";
                additionalQueries += "&filter";
            }
        });
    }

}
