package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView=(TextView)findViewById(R.id.textView);
        TextView textView2=(TextView)findViewById(R.id.textView2);

        Intent main_intent = getIntent();

        int cntCrt = main_intent.getIntExtra("totalcntCrt",-1);

        textView.setText("맞은개수: " + cntCrt);

        int cntWrg = main_intent.getIntExtra("totalcntWrg",-1);

        textView2.setText("틀린개수: " + cntWrg);

        final RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingBar);

        if(cntWrg>=0&&cntWrg<=18){
            ratingBar.setRating(5);
        }
        else if(cntWrg>=19&&cntWrg<=36){
            ratingBar.setRating(4);
        }
        else if(cntWrg>=37&&cntWrg<=54) {
            ratingBar.setRating(3);
        }
        else if(cntWrg>=58&&cntWrg<=72){
            ratingBar.setRating(2);
        }
        else{
            ratingBar.setRating(1);
        }
    }
    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onPause() {
        super.onPause();
        // Remove the activity when its off the screen
        finish();
    }
}
