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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private long backKeyPressedTime = 0;
    private EditText id_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage_login);//첫번째 시작화면 띄워주기
        id_input = findViewById(R.id.signID);
        Button id = (Button) findViewById(R.id.loginbutton);//아이디 버튼 생성
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = id_input.getText().toString();
                if (!userid.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), Startpage_design.class);//게임시작버튼 누르면 난이도 선택창이로 이동
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()>backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "뒤로가기 버튼을 한번더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(System.currentTimeMillis()<=backKeyPressedTime + 2000){
            finish();
        }
    }
}