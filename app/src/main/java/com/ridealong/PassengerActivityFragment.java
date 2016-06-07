package com.ridealong;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
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
import com.ridealong.models.DriverDetails;
import com.ridealong.models.PassengerDetails;
import com.ridealong.models.ServerRequest;
import com.ridealong.models.ServerResponse;

import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassengerActivityFragment extends Fragment implements View.OnClickListener{

    private EditText fromCity,toCity,leavingDate;
    private Button submitBtn;

    public PassengerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_passenger, container, false);
        submitBtn = (Button) rootView.findViewById(R.id.pbutton);
        submitBtn.setOnClickListener(this);
        fromCity = (EditText) rootView.findViewById(R.id.pfrom);
        toCity = (EditText) rootView.findViewById(R.id.pto);
        leavingDate = (EditText) rootView.findViewById(R.id.pdate);
        return rootView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.pbutton:

                String fcity = fromCity.getText().toString();
                String tcity = toCity.getText().toString();
                String date = leavingDate.getText().toString();

                if(!fcity.isEmpty() && !tcity.isEmpty() && !date.isEmpty()){
                    insertPassgrTravelInfo(fcity,tcity,date);
                    startActivity(new Intent(getActivity(), DriverListActivity.class));
                }else{
                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }

        }

    }

    private void insertPassgrTravelInfo(String startCity,String destination,String leavingDate){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        PassengerDetails passengerDetails = new PassengerDetails();
        passengerDetails.setFrom(startCity);
        passengerDetails.setDestination(destination);

        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.PASSENGER_TRAVEL_DETAILS_OPERATION);
        serverRequest.setPassengerDetails(passengerDetails);

        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });


    }





}
