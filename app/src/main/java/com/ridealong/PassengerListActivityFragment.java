package com.ridealong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.ridealong.models.PassengerDetails;
import com.ridealong.models.ServerRequest;
import com.ridealong.models.ServerResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassengerListActivityFragment extends Fragment {

    private static final String LOG_TAG = PassengerListActivityFragment.class.getSimpleName();

    public PassengerListActivityFragment() {
    }

    ArrayAdapter adapter;
    List<String> x = new ArrayList<>(Arrays.asList("1", "2"));
    String driverId = "123@gmail.com";
    String driverFrom = "Los Angeles";
    String driverDest = "San Jose";
    String passengerFrom;
    String passengerTo;
    private SharedPreferences sharedPreferences;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_passenger_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.passengerList);
        adapter = new ArrayAdapter<String >(getActivity(),R.layout.passenger_list_items, R.id.passenger_list_item, x);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Toast.makeText(getActivity(), adapter.getItem(i).toString(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), PassengerDetailActivity.class);
                intent.putExtra("passenger", adapter.getItem(i).toString());
                startActivity(intent);
            }
        });

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString(Constants.EMAIL, "");
        Log.v("user email in passenger",userEmail);
        String username = sharedPreferences.getString(Constants.NAME,"");
        Log.v("user name",username);
        String uniqueId = sharedPreferences.getString(Constants.UNIQUE_ID,"");
        Log.v("user unique id",uniqueId);



//        display the passengers list for the driver selected destination




        LatLng driverFromLatLong = getLocationFromAddress(getActivity(),driverFrom);
        LatLng driverToLatLong = getLocationFromAddress(getActivity(),driverDest);
        LatLng passengerFromLatLong = getLocationFromAddress(getActivity(),passengerFrom);
        LatLng passengerToLatLong = getLocationFromAddress(getActivity(),passengerTo);



        double driverTotalDist = calculationByDistance(driverFromLatLong,driverToLatLong);
        double passengrDistToDriverDest = calculationByDistance(passengerToLatLong,driverToLatLong);
        double driverFromToPassgrDestDist = calculationByDistance(driverFromLatLong,passengerToLatLong);

        Log.v("driver total", String.valueOf(driverTotalDist));
        Log.v("passgr total", String.valueOf(passengrDistToDriverDest));

        if ((passengrDistToDriverDest <= driverTotalDist) && (driverTotalDist <= driverFromToPassgrDestDist)){
            Log.v(LOG_TAG,"display the chosen drivers cheers!");
        }

        return view;
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    //  got this method from stackoverflow
    public double calculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

}

