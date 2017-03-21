package com.androidexample.swipetorefresh_demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView rdm = (TextView) findViewById(R.id.random);
        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        //to setup different colors while the swipe progress bar is in operation
        swipe.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        //Set what happens on swipe for now we show a random number between 0 to 100 and display a toast msg
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                        double number = (double) Math.random();
                        int ans = (int) (number * 100);
                        rdm.setText(String.valueOf(ans));

                        Toast.makeText(getBaseContext(), "Value Updated", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);// the 2nd parameter refers to how much time you want to display the animation
            }
        });
    }
}
