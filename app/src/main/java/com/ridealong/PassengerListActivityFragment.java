package com.ridealong;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        return view;
    }
}
