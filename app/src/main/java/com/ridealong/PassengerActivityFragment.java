package com.ridealong;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ridealong.data.Contract;
import com.ridealong.data.RideAlongDbHelper;

import java.util.Map;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassengerActivityFragment extends Fragment implements View.OnClickListener{

    private EditText firstName;
    private EditText fromCity;
    private EditText toCity;
    private EditText leavingDate;
    private EditText leavingTime;
    private Button submitBtn;

    public PassengerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_passenger, container, false);
        submitBtn = (Button) rootView.findViewById(R.id.pbutton);
        submitBtn.setOnClickListener(this);
        firstName = (EditText) rootView.findViewById(R.id.pfirstname);
        fromCity = (EditText) rootView.findViewById(R.id.pfrom);
        toCity = (EditText) rootView.findViewById(R.id.pto);
        leavingDate = (EditText) rootView.findViewById(R.id.pdate);
        leavingTime = (EditText) rootView.findViewById(R.id.ptime);
        return rootView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.pbutton:

                String fname = firstName.getText().toString();
                String fcity = fromCity.getText().toString();
                String tcity = toCity.getText().toString();
                String date = leavingDate.getText().toString();
                String time = leavingTime.getText().toString();

                boolean invalid = false;
                if(fname.equals("")){
                    invalid = true;
                    Toast.makeText(getContext(),"Enter your first name",Toast.LENGTH_SHORT).show();
                }
                else if(fcity.equals("")){
                    invalid = true;
                    Toast.makeText(getContext(),"Enter the city",Toast.LENGTH_SHORT).show();
                }
                else if(tcity.equals("")){
                    invalid = true;
                    Toast.makeText(getContext(),"Enter the destination",Toast.LENGTH_SHORT).show();
                }
                else if(date.equals("")){
                    invalid = true;
                    Toast.makeText(getContext(),"Enter the date",Toast.LENGTH_SHORT).show();
                }
                else if(time.equals("")){
                    invalid = true;
                    Toast.makeText(getContext(),"Enter the time",Toast.LENGTH_SHORT).show();
                }
                else if(invalid == false){
                    addPassengerTravelInfo(fname,fcity,tcity,date,time);
                }
        }

    }

    private void addPassengerTravelInfo(String fname,String city,String destCity,String date,String time) {

        RideAlongDbHelper rideAlongDbHelper = new RideAlongDbHelper(getContext());
        SQLiteDatabase sqLiteDatabase = rideAlongDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.PassgrTravelInfoEntry.COLUMN_FIRST_NAME, fname);
        contentValues.put(Contract.PassgrTravelInfoEntry.COLUMN_FROM_CITY, city);
        contentValues.put(Contract.PassgrTravelInfoEntry.COLUMN_TO_CITY, destCity);
        contentValues.put(Contract.PassgrTravelInfoEntry.COLUMN_DATE, date);
        contentValues.put(Contract.PassgrTravelInfoEntry.COLUMN_TIME, time);

        Cursor cursor = null;
        try {
            validateCurrentRecordEntry(cursor, contentValues);
            sqLiteDatabase.insert(Contract.PassgrTravelInfoEntry.TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            Log.e("data insert psgr travel",e.getMessage());
        }
    }

    static boolean validateCurrentRecordEntry(Cursor valCursor, ContentValues expectedValues){
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for(Map.Entry<String, Object> entry : valueSet){
            String colName = entry.getKey();
            int index = valCursor.getColumnIndex(colName);
            if(index == -1) return false;
            String expectedVal = entry.getValue().toString();
            String retrievedVal = valCursor.getString(index);
            if(!retrievedVal.equals(expectedVal)) return false;
        }
        return true;
    }




}
