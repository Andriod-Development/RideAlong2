package com.ridealong;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DriverDetailActivityFragment extends Fragment implements View.OnClickListener {

    public DriverDetailActivityFragment() {
    }
    Button mapButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_driver_detail, container, false);
        TextView movieText = (TextView) rootView.findViewById(R.id.driverListDetail);

        Intent intent = getActivity().getIntent();
        String reviewString = intent.getStringExtra("driver");
        movieText.setText(reviewString);
        mapButton = (Button) rootView.findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mapButton){
            startActivity(new Intent(getActivity(),MapsActivity.class));
        }
    }
}