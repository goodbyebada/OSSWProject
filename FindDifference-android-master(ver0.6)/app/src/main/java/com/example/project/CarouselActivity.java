package com.example.project;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.os.Bundle;

public class CarouselActivity extends AppCompatActivity{
    private String userid;
    private int selectedIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userid=intent.getStringExtra("userid");
        setContentView(R.layout.demo1);
        View v1 = findViewById(R.id.v1);
        View v2 = findViewById(R.id.v2);
        View v3 = findViewById(R.id.v3);
        final MotionLayout motionLayout = findViewById(R.id.motion_container);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CarouselActivity.this.selectedIndex == 0){
                    Intent intent = new Intent(getApplicationContext(), level1_1.class);//firstImage로 이동
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
                else {
                    motionLayout.setTransition(R.id.s2, R.id.s1);
                    motionLayout.transitionToEnd();
                    selectedIndex = 0;
                }
            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CarouselActivity.this.selectedIndex == 1) {
                    Intent intent = new Intent(getApplicationContext(), level2.class);//firstImage로 이동
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
                else if (CarouselActivity.this.selectedIndex == 2) {
                    motionLayout.setTransition(R.id.s3, R.id.s2);
                    selectedIndex = 1;
                    motionLayout.transitionToEnd();
                }
                else {
                    motionLayout.setTransition(R.id.s1, R.id.s2);
                    selectedIndex = 1;
                    motionLayout.transitionToEnd();
                }
            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CarouselActivity.this.selectedIndex ==2){
                    Intent intent = new Intent(getApplicationContext(), level3_1.class);//firstImage로 이동
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
                else{
                    motionLayout.setTransition(R.id.s2,R.id.s3);
                    motionLayout.transitionToEnd();
                    selectedIndex =2;
                }
            }
        });
    }
}
