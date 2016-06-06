package com.ridealong;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ridealong.models.DriverDetails;
import com.ridealong.models.ServerRequest;
import com.ridealong.models.ServerResponse;
import com.ridealong.models.User;

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

        return view;
    }

    @Override
    public void onClick(View v) {
        String driverStartPlc = driverFrom.getText().toString();
        String driverDestPlc = driverTo.getText().toString();
        String driverCarModel = carModel.getText().toString();
        String driverLicense = license.getText().toString();

        if(!driverStartPlc.isEmpty() && !driverDestPlc.isEmpty() && !driverCarModel.isEmpty() && !driverLicense.isEmpty()){
            insertDriverInfo(driverStartPlc,driverDestPlc,driverCarModel,driverLicense);
        }else{
            Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
        }
    }

    private void insertDriverInfo(String startPlc, String destPlc, String carModel, String license){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setCarModel(carModel);
        driverDetails.setLicense(license);
        driverDetails.setFrom(startPlc);
        driverDetails.setDestination(destPlc);

        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.DRIVER_TRAVEL_DETAILS_OPERATION);
        serverRequest.setDriverDetails(driverDetails);

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
