package com.ridealong;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.ridealong.models.PassengerDetails;
import com.ridealong.models.ServerRequest;
import com.ridealong.models.ServerResponse;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.entity.mime.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    String passengerFrom;
    String passengerTo;



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

        passengerFrom = getActivity().getIntent().getExtras().getString("startPt");
        passengerTo = getActivity().getIntent().getExtras().getString("destPt");
        Log.v("passgr from",passengerFrom);
        Log.v("passgr to",passengerTo);




        PassengerDetails passengerDetails = new PassengerDetails();
        passengerDetails.setFrom(passengerFrom);
        passengerDetails.setDestination(passengerTo);
        passengerDetails.setLeavingDate(null);
        passengerDetails.setUserId(0);



        try {
            JSONObject obj = new JSONObject();
            obj.put("passgrFrom", passengerFrom);
            obj.put("passgrTo",passengerTo);
            String jsonString = obj.toString();
            StringEntity stringEntity = new StringEntity(jsonString);
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding("UTF-8");

            Log.v("json str",jsonString);
            Log.v("string entity",stringEntity.toString());


            AsyncHttpClient client = new AsyncHttpClient();



            client.post(this.getActivity(), "http://www.ridealong.lewebev.com/driver_list.php", stringEntity, "application/json", new AsyncHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseString) {
                    Log.v("response Str",responseString.toString());
                    try {
                        String jsonStr = new String(responseString,"UTF-8");
                        Log.v("json str",jsonStr);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        JSONArray jsonArray = new JSONArray(responseString);
//                        for(int i=0;i<jsonArray.length();i++){
//                            Log.v("index",String.valueOf(jsonArray.getInt(i)));
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }


                }
            });



//            URL url = new URL("http://www.ridealong.lewebev.com/driver_list.php");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            BufferedReader bufferedReader = null;
//
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                return null;
//            }
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                buffer.append(line + "\n");
//            }
//
//            if (buffer.length() == 0) {
//                return null;
//            }
//            String driverList = buffer.toString();
//            Log.v("driver list",driverList);




//
//                @Override
//                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
//
//
//
////                    try {
////                        String responseStr = new String(responseBody,"UTF-8");
////                        Log.v("response Str",responseStr);
////                    } catch (UnsupportedEncodingException e) {
////                        e.printStackTrace();
////                    }
//                }
//
//                @Override
//                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
//                    Log.v("status code failure",String.valueOf(statusCode));
//
//                }
//            });
//
        }catch (JSONException e) {
            e.printStackTrace();
        }catch(UnsupportedEncodingException ee){
            ee.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        LatLng driverFromLatLong = getLocationFromAddress(getActivity(),driverFrom);
//        LatLng driverToLatLong = getLocationFromAddress(getActivity(),driverDest);
//        LatLng passengerFromLatLong = getLocationFromAddress(getActivity(),passengerFrom);
//        LatLng passengerToLatLong = getLocationFromAddress(getActivity(),passengerTo);
//
//
//
//        double driverTotalDist = calculationByDistance(driverFromLatLong,driverToLatLong);
//        double passengrDistToDriverDest = calculationByDistance(passengerToLatLong,driverToLatLong);
//        double driverFromToPassgrDestDist = calculationByDistance(driverFromLatLong,passengerToLatLong);
//
//
//        if ((passengrDistToDriverDest <= driverTotalDist) && (driverTotalDist <= driverFromToPassgrDestDist)){
//            Log.v(LOG_TAG,"display the chosen drivers in driverlist cheers!");
//        }




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
