package com.example.ljudevit.florasearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFERENCE_MAIN = "MainPreferences";
    private static final String SHARED_PREFERENCE_RESULT = "ResultPreferences";
    List<ServiceInfo> serviceInfoList;
    TableLayout servicesView;
    boolean testRun;
    Context context = this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent preference = new Intent(context,MainActivityPrefrences.class);
        startActivity(preference);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String serviceJSON = "[{\"Description\":\"Pretraga herbara\",\"Name\":\"Herbar\",\"Url\":\"http:\\/\\/hirc.botanic.hr\\/services\\/Herbar.svc\\/SearchItems\"}," +
                "{\"Description\":\"Pretraga biljnih vrsta\",\"Name\":\"Vrste\",\"Url\":\"http:\\/\\/hirc.botanic.hr\\/services\\/TaxaService.svc\\/SearchItems\"}]";

        SharedPreferences resultPref = getSharedPreferences(SHARED_PREFERENCE_RESULT, MODE_PRIVATE);
        SharedPreferences.Editor resultEditor = resultPref.edit();
        resultEditor.clear();

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCE_MAIN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String mainURL = sharedPref.getString("mainURL", "");
        testRun = sharedPref.getBoolean("testRun",true);

        if(!testRun){
            try {
                serviceJSON= new JSONresponse().execute(mainURL).get();
            } catch (Exception e) {
                editor.putBoolean("testRun", true);
                editor.apply();
                Toast.makeText(this, "Pogreška kod učitavanja JSON podataka, testni primjeri će se koristiti", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        serviceInfoList = JSONtoServices(serviceJSON);
        servicesView = (TableLayout) findViewById(R.id.services_table);
        addSearchOptions();
    }

    public class JSONresponse extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return new HttpHandler().getResponse(params[0],testRun);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    /*serializacija JSON-a u ServiceInfo oblik */
    public List<ServiceInfo> JSONtoServices(String JSONArray) {
        List<ServiceInfo> serviceInfoList = new ArrayList<>();
        int i;

        try {
            JSONArray parent = new JSONArray(JSONArray);
            for (i = 0; i < parent.length(); i++) {
                JSONObject child = parent.getJSONObject(i);
                ServiceInfo singleService = new ServiceInfo();

                singleService.setName(child.getString("Name"));
                singleService.setUrl(child.getString("Url"));
                singleService.setDescription(child.getString("Description"));

                serviceInfoList.add(singleService);
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Pogreška kod obrade JSON podataka, provjerite ispravnost servisa", Toast.LENGTH_SHORT).show();
        }

        return serviceInfoList;
    }

    private void addSearchOptions() {
        for (ServiceInfo serviceInfo : serviceInfoList) {
            TableRow searchOption = createSearchOption(serviceInfo);
            servicesView.addView(searchOption);
        }
    }

    private TableRow createSearchOption(final ServiceInfo serviceInfo) {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Button serviceButton = new Button(this);
        serviceButton.setText(serviceInfo.getName());
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(v.getContext(), SearchActivity.class);
                searchIntent.putExtra("url", serviceInfo.getUrl());
                v.getContext().startActivity(searchIntent);
            }
        });
        tableRow.addView(serviceButton, 0);

        TextView description = new TextView(this);
        description.setText(serviceInfo.getDescription());
        description.setGravity(Gravity.CENTER);
        tableRow.addView(description, 1);

        return tableRow;
    }
}