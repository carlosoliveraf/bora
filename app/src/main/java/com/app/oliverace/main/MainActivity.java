package com.app.oliverace.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.oliverace.maps.MapsActivity;
import com.app.oliverace.maps.NavActivity;
import com.app.oliverace.maps.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mLogInButton = (Button) findViewById(R.id.maps);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapa();
            }
        });

        Button mMenuButton = (Button) findViewById(R.id.register);
        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNav();
            }
        });




    }

    public void goNav(){
        Intent nav = new Intent(getApplicationContext(), NavActivity.class);
        startActivity(nav);
    }



    public void mapa(){
        Intent mapa = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(mapa);
    }

}
