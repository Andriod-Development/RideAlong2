package com.ridealong;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ridealong.models.ServerRequest;
import com.ridealong.models.ServerResponse;
import com.ridealong.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeActivityFragment extends Fragment implements View.OnClickListener{

    public WelcomeActivityFragment() {
    }

    Button driver;
    private SharedPreferences pref;
    private TextView u_name , u_email ,u_logout;
    private Button btn_logout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_welcome, container, false);

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        driver = (Button) view.findViewById(R.id.driver);

        driver.setOnClickListener(this);
        return view;
    }

    private void initViews(View view){

        u_name = (TextView)view.findViewById(R.id.u_name);
        u_email = (TextView)view.findViewById(R.id.u_email);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);
        u_name.setText("Welcome : "+pref.getString(Constants.NAME,""));
        u_email.setText(pref.getString(Constants.EMAIL,""));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.driver:
                startActivity(new Intent(getActivity(), CarDetailsActivity.class));
                break;

            case R.id.btn_logout:
                logout();
                break;

        }
    }


    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.NAME,"");
        editor.putString(Constants.UNIQUE_ID,"");
        editor.apply();
        goToLogin();
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }

}
