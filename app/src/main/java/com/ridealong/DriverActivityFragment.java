package com.ridealong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.ridealong.models.DriverDetails;
import com.ridealong.models.ServerRequest;
import com.ridealong.models.ServerResponse;
import com.ridealong.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class DriverActivityFragment extends Fragment implements View.OnClickListener{

    private Button submitBtn;
    private EditText driverFrom,driverTo,carModel,license,leavingDate;
    private int userId;


    public DriverActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_driver, container, false);
        driverFrom = (EditText) view.findViewById(R.id.dfrom);
        driverTo = (EditText) view.findViewById(R.id.dto);
        carModel = (EditText) view.findViewById(R.id.dmodel);
        license = (EditText) view.findViewById(R.id.dlicense);
        leavingDate = (EditText) view.findViewById(R.id.ddate);
        submitBtn = (Button) view.findViewById(R.id.dbutton);

        submitBtn.setOnClickListener(this);

        userId = getActivity().getIntent().getExtras().getInt("userId");
        Log.v("user Id in driver",String.valueOf(userId));
        return view;
    }

    @Override
    public void onClick(View v) {

       String driverStartPlc = driverFrom.getText().toString();
       String driverDestPlc = driverTo.getText().toString();
       String driverCarModel = carModel.getText().toString();
       String driverLicense = license.getText().toString();

       if(!driverStartPlc.isEmpty() && !driverDestPlc.isEmpty() && !driverCarModel.isEmpty() && !driverLicense.isEmpty()){
           String driverJsonData = insertDriverInfo(driverStartPlc,driverDestPlc,driverCarModel,driverLicense);
           Log.v("driver json str",driverJsonData.toString());
           startActivity(new Intent(getActivity(), PassengerListActivity.class));
       }else{
           Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
       }
    }

    private String insertDriverInfo(String startPlc, String destPlc, String carModel, String license){

        String driverJsonStr;
        final JSONObject driverJsonObject = new JSONObject();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setUserId(userId);
        driverDetails.setcar_no(license);
        driverDetails.setCarModel(carModel);

        driverDetails.setfrom_place(startPlc);
        driverDetails.setDestination(destPlc);
        driverDetails.setLeavingDate(new java.util.Date());
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.DRIVER_TRAVEL_DETAILS_OPERATION);
        serverRequest.setDriverDetails(driverDetails);
        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);

        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                ServerResponse resp = response.body();

                try {
                    driverJsonObject.put("driverId",resp.getDriverDetails().getId());
                    Log.v("driver id",String.valueOf(resp.getDriverDetails().getId()));
                    driverJsonObject.put("driverFrom",resp.getDriverDetails().getfrom_place());
                    driverJsonObject.put("driverDest",resp.getDriverDetails().getDestination());
                    driverJsonObject.put("leaveDate",resp.getDriverDetails().getLeavingDate());
                    driverJsonObject.put("userId",resp.getDriverDetails().getUserId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });

        Gson gson = new Gson();
        driverJsonStr = gson.toJson(driverJsonObject);
        return driverJsonStr;


    }
}
