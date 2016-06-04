package com.ridealong;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class DriverListActivityFragment extends Fragment {

    private static final String LOG_TAG = DriverListActivityFragment.class.getSimpleName();

    public DriverListActivityFragment() {
    }

    ArrayAdapter adapter;
    List<String> y = new ArrayList<>(Arrays.asList("1", "2"));
    String driverId = "123@gmail.com";
    String driverFrom = "Los Angeles";
    String driverDest = "San Jose";
    String passengerFrom = "Los Angeles";
    String passengerTo = "Fresno";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.driverList);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.driver_list_items, R.id.driver_list_item, y);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), adapter.getItem(i).toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), DriverDetailActivity.class);
                intent.putExtra("driver", adapter.getItem(i).toString());
                startActivity(intent);
            }
        });

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
