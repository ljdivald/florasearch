package com.example.ljudevit.florasearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class HerbarSearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herbar_search_display);

        final EditText id_herbara = (EditText) findViewById(R.id.IdHerbaraInput);
        final EditText ime_svojte = (EditText) findViewById(R.id.ImeSvojteInput);
        EditText rod = (EditText) findViewById(R.id.RodInputHerbar);
        EditText sabirac = (EditText) findViewById(R.id.SabiracInput);
        final Spinner naziv_zbirke = (Spinner) findViewById(R.id.NazivZbirke);
        EditText nalaziste = (EditText) findViewById(R.id.Nalazište);
        final Spinner sa_slikom = (Spinner) findViewById(R.id.SaSlikom);
        EditText Inv_broj = (EditText) findViewById(R.id.InvBrojInput);
        EditText porodica = (EditText) findViewById(R.id.PorodicaInputHerbar);
        Spinner tip_primjerka = (Spinner) findViewById(R.id.TipPrimjerka);
        EditText datum_sabiranja = (EditText) findViewById(R.id.DatumSabiranjaInput);
        final EditText godina_sabiranja = (EditText) findViewById(R.id.GodinaSabiranjaInput);
        Spinner zemlja = (Spinner) findViewById(R.id.ZemljaSpinner);

        final TextView resoult = (TextView) findViewById(R.id.resoults);
        Button search = (Button) findViewById(R.id.HerbarSearchButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String additionalQueries = "http://hirc.botanic.hr/services/Herbar.svc/Search?=";
                //additionalQueries += "godina=" + godina_sabiranja.getText() + "/";

                additionalQueries+= "idzbirke=3&count=20";
                Intent HerbarResoultIntent = new Intent(HerbarSearchActivity.this, HerbarResoultsActivity.class);
                HerbarResoultIntent.putExtra("url", additionalQueries);
                startActivity(HerbarResoultIntent);
            }
        });

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
