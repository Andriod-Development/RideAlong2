package com.ridealong;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PassengerActivity extends AppCompatActivity implements View.OnClickListener {

    EditText pfirstname, pto, pfrom, pdate, ptime;
    Button pbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

//        pfirstname = (EditText) findViewById(R.id.pfirstname);
        pto = (EditText) findViewById(R.id.pto);
        pfrom = (EditText) findViewById(R.id.pfrom);
        pdate = (EditText) findViewById(R.id.pdate);
        ptime = (EditText) findViewById(R.id.ptime);
        pbutton = (Button) findViewById(R.id.pbutton);

        pbutton.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.pbutton){
            startActivity(new Intent(this, DriverListActivity.class));
        }
    }
}
