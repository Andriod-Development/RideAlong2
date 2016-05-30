package com.ridealong;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PassengerDetailActivityFragment extends Fragment {

    public PassengerDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_passenger_detail, container, false);
        TextView movieText = (TextView) rootView.findViewById(R.id.passengerListDetail);

        Intent intent = getActivity().getIntent();
        String reviewString = intent.getStringExtra("passenger");
        movieText.setText(reviewString);

        return rootView;
    }
}
