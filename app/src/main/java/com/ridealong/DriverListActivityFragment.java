package com.ridealong;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DriverListActivityFragment extends Fragment {

    public DriverListActivityFragment() {
    }

    ArrayAdapter adapter;
    List<String> y = new ArrayList<>(Arrays.asList("1", "2"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.driverList);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.driver_list_items, R.id.driver_list_item, y);
        listView.setAdapter(adapter);
        return view;
    }
}
