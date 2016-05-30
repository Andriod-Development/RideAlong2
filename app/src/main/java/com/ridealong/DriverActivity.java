package com.ridealong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//car details for passenger
public class DriverActivity extends AppCompatActivity implements View.OnClickListener {

    EditText dfirstname, dto, dfrom, ddate, dtime, dmodel, dlicense;
    Button dbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

//        dfirstname = (EditText) findViewById(R.id.dfirstname);
        dto = (EditText) findViewById(R.id.dto);
        dfrom = (EditText) findViewById(R.id.dfrom);
        ddate = (EditText) findViewById(R.id.ddate);
        dtime = (EditText) findViewById(R.id.dtime);
        dmodel = (EditText) findViewById(R.id.dmodel);
        dlicense = (EditText) findViewById(R.id.dlicense);

        dbutton = (Button) findViewById(R.id.dbutton);

        dbutton.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dbutton){
            startActivity(new Intent(this, PassengerListActivity.class));
        }

    }
}
