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
public class WelcomeActivityFragment extends Fragment implements View.OnClickListener{

    public WelcomeActivityFragment() {
    }

    Button driver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_welcome, container, false);

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        driver = (Button) view.findViewById(R.id.driver);

        driver.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.driver:
                startActivity(new Intent(getActivity(), CarDetailsActivity.class));
                break;

        }
    }
}
