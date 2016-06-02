package com.ridealong;

import android.content.Intent;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassengerListActivityFragment extends Fragment {

    public PassengerListActivityFragment() {
    }

    ArrayAdapter adapter;
    List<String> x = new ArrayList<>(Arrays.asList("1", "2"));
    String driverId = "123@gmail.com";
    String driverFrom = "Los Angeles";
    String driverDest = "San Jose";
    String passengerFrom = "Los Angeles";
    String passengerTo = "Fresno";


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

//        display the passengers list for the driver selected destination

        String driverFromJsonStr = getLocationFromAddress(driverFrom);
        String driverToJsonStr = getLocationFromAddress(driverDest);
        String passengerFromJsonStr = getLocationFromAddress(passengerFrom);
        String passengerToJsonStr = getLocationFromAddress(passengerTo);

        Log.v("Driver From Address",driverFromJsonStr);
        Log.v("Driver To Address",driverToJsonStr);
        Log.v("Passgr From Address",passengerFromJsonStr);
        Log.v("Passgr To Address",passengerToJsonStr);


        return view;
    }

    private String getLocationFromAddress(String address){

        String jsonStr = null;

        BufferedReader reader = null;

        final String latlongUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        final String paramAddress = "address";
        final String apiKeyParam = "key";

        Uri buildLatLongForDriverToUri = Uri.parse(latlongUrl).buildUpon()
                .appendQueryParameter(paramAddress,address)
                .appendQueryParameter(apiKeyParam,"AIzaSyBumwldQrWor7bifatqi79qH1o224TYhAQ").build();

        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(buildLatLongForDriverToUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            jsonStr = buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;


    }

}

