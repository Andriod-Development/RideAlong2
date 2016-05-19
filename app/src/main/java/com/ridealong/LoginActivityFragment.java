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
public class LoginActivityFragment extends Fragment implements View.OnClickListener{

    public LoginActivityFragment() {
    }

    Button elogin;
    TextView registerlink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_login, container, false);

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        elogin = (Button) view.findViewById(R.id.elogin);
        registerlink = (TextView) view.findViewById(R.id.registerlink);

        elogin.setOnClickListener(this);
        registerlink.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.elogin:
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
                break;

            case R.id.registerlink:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;

        }
    }
}
