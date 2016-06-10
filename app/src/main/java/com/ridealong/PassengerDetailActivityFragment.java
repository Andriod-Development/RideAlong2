package com.ridealong;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassengerDetailActivityFragment extends Fragment implements View.OnClickListener {

    public PassengerDetailActivityFragment() {
    }

    Button mapButton;
    List<String> passengerDetails = new ArrayList<String>();
    ArrayAdapter adapter;
    String passengerId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_passenger_detail, container, false);
        TextView passengerText = (TextView) rootView.findViewById(R.id.passengerListDetail);



        Intent intent = getActivity().getIntent();
        String reviewString = intent.getStringExtra("passenger");
        passengerText.setText(reviewString);
        mapButton = (Button) rootView.findViewById(R.id.mapButton);

        mapButton.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mapButton) {
            startActivity(new Intent(getActivity(), MapsActivity.class));
        }
    }


}

