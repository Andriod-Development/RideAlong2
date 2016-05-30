package com.ridealong;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initFragment();
    }

    private void initFragment(){
        Fragment fragment;
        //fragment=null;
        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            System.out.println("logged in");
            fragment = new WelcomeActivityFragment();
            Intent intent = new Intent(context,(WelcomeActivity.class));
            startActivity(intent);
        }else {
            //Intent intent = new Intent(this, com.ridealong.LoginFragment.class);
            fragment = new LoginFragment();
            System.out.println("Not logged in");
            //startActivity(intent);
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();

    }

}
