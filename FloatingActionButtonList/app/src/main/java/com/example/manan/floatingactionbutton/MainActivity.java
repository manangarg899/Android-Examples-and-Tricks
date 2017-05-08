package com.example.manan.floatingactionbutton;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     variables for floating action buttons and animations
     */
    FloatingActionButton fab_base,fab_one,fab_two;
    Animation fab_open,fab_close,fab_rotate_clockwise,fab_rotate_anticlockwise;
    boolean is_open;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_base = (FloatingActionButton)findViewById(R.id.fab_base);
        fab_one = (FloatingActionButton)findViewById(R.id.fab_one);
        fab_two = (FloatingActionButton)findViewById(R.id.fab_two);

        textView = (TextView)findViewById(R.id.text);

        fab_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_open)
                {
                    fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
                    fab_rotate_anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation_anticlockwise);
                    fab_one.setAnimation(fab_close);
                    fab_two.setAnimation(fab_close);
                    fab_base.setAnimation(fab_rotate_anticlockwise);
                    fab_one.setClickable(false);
                    fab_two.setClickable(false);
                    is_open = false;
                    Log.i("Clicked","true");
                    textView.setText("Fab List compressed");
                }
                else
                {
                    fab_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
                    fab_rotate_clockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation_clockwise);
                    fab_one.setAnimation(fab_open);
                    fab_two.setAnimation(fab_open);
                    fab_base.setAnimation(fab_rotate_clockwise);
                    fab_one.setClickable(true);
                    fab_two.setClickable(true);
                    is_open = true;
                    textView.setText("Fab List Expanded");
                }
            }
        });

        fab_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Fab 1 Clicked");
            }
        });

        fab_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Fab 2 Clicked");
            }
        });
    }
}