package com.example.project;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.Image;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Startpage_design2 extends AppCompatActivity {
    private String userid;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userid=intent.getStringExtra("userid");
        setContentView(R.layout.startpage_design2);//난이도 선택 화면창 끌고 오기

        Button level1 = (Button) findViewById(R.id.level1);//난이도 1 버튼 생성
        level1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), level1_1.class);//firstImage로 이동
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });
        Button level2 = (Button) findViewById(R.id.level2);//난이도 2 버튼 생성
        level2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), level2.class);//secondImage로 이동
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
        Button level3 = (Button) findViewById(R.id.level3);//난이도 3 버튼 생성
        level3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), thirdImage.class);//thirdImage로 이동
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
    }

}
