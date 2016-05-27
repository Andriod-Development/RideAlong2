package com.ridealong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

//car details for passenger
public class DriverActivity extends AppCompatActivity {

    EditText dfirstname, dto, dfrom, ddate, dtime, dmodel, dlicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        dfirstname = (EditText) findViewById(R.id.dfirstname);
        dto = (EditText) findViewById(R.id.dto);
        dfrom = (EditText) findViewById(R.id.dfrom);
        ddate = (EditText) findViewById(R.id.ddate);
        dtime = (EditText) findViewById(R.id.dtime);
        dmodel = (EditText) findViewById(R.id.dmodel);
        dlicense = (EditText) findViewById(R.id.dlicense);

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
