package com.example.project;

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
import android.graphics.Paint;
import android.graphics.Point;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class level3_1 extends AppCompatActivity {
    protected int width, height;
    protected int x,y;
    protected class MyView extends View {
        int j=0;
        int[] checkCnt=new int[] {0,0,0,0,0,0,0,0,0,0};
        int[] x2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] y2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        public int cntCrt=0, cntWrg=0;
        public int totalcntCrt=0, totalcntWrg =0;
        public int remain=30;

        public MyView(Context context) {
            super(context);
        }
        private Paint paint = new Paint();
        @Override
        protected void onDraw(Canvas canvas) {

            width = canvas.getWidth();
            height = canvas.getHeight();

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.level3_1);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);

            canvas.drawBitmap(resize_bitmap, 0, 0, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);

            for (int i = 0; i < j; i++) {
                if (x2[i] != 0 && y2[i] != 0) {
                    canvas.drawCircle(x2[i]*width/100, y2[i]*height/100, 30, paint);
                }
            }

            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText("맞은개수: " + cntCrt, 100, 50, paint);
            paint.setColor(Color.RED);
            canvas.drawText("틀린개수: " + cntWrg, 450, 50, paint);
            paint.setColor(Color.MAGENTA);
            canvas.drawText("남은기회: " + remain, 800, 50, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int)(event.getX()/width*100);
                    y = (int)(event.getY()/height*100);
                    //x 330~400 ,480~520
                    //y 150~230 ,210~250
                    if ((9 <= x && x <= 11) && (16 <= y&& y <= 18)&&checkCnt[0]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[0] = 1;
                        invalidate();
                    }
                    else if ((9 <= x && x <= 11) && (16 <= y&& y <= 18)&&checkCnt[0]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //2
                    else if ((30 <= x && x <= 32) && (19 <= y&& y <= 20) &&checkCnt[1]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[1] = 1;
                        invalidate();
                    }
                    else if ((30 <= x && x <= 32) && (19 <= y&& y <= 20)&&checkCnt[1]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //3
                    else if ((39 <= x && x <= 40) && (23 <= y&& y <= 24)&&checkCnt[2]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[2] = 1;
                        invalidate();
                    }
                    else if ((39 <= x && x <= 40) && (23 <= y&& y <= 24)&&checkCnt[2]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //4
                    else if ((52 <= x && x <= 53) && (15 <= y&& y <=17)&&checkCnt[3]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[3] = 1;
                        invalidate();
                    }
                    else if ((52 <= x && x <= 53) && (15 <= y&& y <= 17)&&checkCnt[3]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //5
                    else  if ((59 <= x && x <= 60) && (18 <= y&& y <= 18) &&checkCnt[4]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[4] = 1;
                        invalidate();
                    }
                    else if ((60 <= x && x <= 60) && (18 <= y&& y <= 18)&&checkCnt[4]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //6
                    else  if ((84 <= x && x <= 84) && (19 <= y&& y <= 24) &&checkCnt[5]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[5] = 1;
                        invalidate();
                    }
                    else if ((84 <= x && x <= 84) && (19 <= y&& y <= 24)&&checkCnt[5]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //5
                    else  if ((66 <= x && x <= 66) && (16 <= y&& y <= 17) &&checkCnt[6]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[6] = 1;
                        invalidate();
                    }
                    else if ((66 <= x && x <= 66) && (16 <= y&& y <= 17)&&checkCnt[6]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //7
                    else  if ((75 <= x && x <= 77) && (35 <= y&& y <= 36) &&checkCnt[7]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[7] = 1;
                        invalidate();
                    }
                    else if ((75 <= x && x <= 77) && (35 <= y&& y <= 36)&&checkCnt[7]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //8
                    else  if ((54 <= x && x <= 56) && (34 <= y&& y <= 39) &&checkCnt[8]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[8] = 1;
                        invalidate();
                    }
                    else if ((54 <= x && x <= 56) && (34 <= y&& y <= 39)&&checkCnt[8]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //9
                    else  if ((29 <= x && x <= 30) && (36 <= y&& y <= 38) &&checkCnt[9]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[9] = 1;
                        invalidate();
                    }
                    else if ((29 <= x && x <= 30) && (36 <= y&& y <= 38)&&checkCnt[9]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //밑 그림 터치시
                    else if ((0 < x && x < 100) && (50 < y&& y < 100)){
                        Toast.makeText(getApplicationContext(), "위에 그림을 터치해주세요!", Toast.LENGTH_SHORT).show();
                        ++remain;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "오답입니다", Toast.LENGTH_SHORT).show();
                        invalidate();
                        cntWrg++;
                        totalcntWrg++;
                    }

                    j++;
                    if(cntCrt==10){
                        Intent intent = new Intent(getApplicationContext(), level3_2.class);
                        intent.putExtra("totalcntCrt",totalcntCrt);
                        intent.putExtra("totalcntWrg",totalcntWrg);
                        startActivity(intent);
                    }
                    if(cntCrt+cntWrg>=30){
                        Toast.makeText(getApplicationContext(), "횟수 초과! 다시 시작.", Toast.LENGTH_LONG).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    remain--;
                    //Toast.makeText(getApplicationContext(), x+ " "+y, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),width+ " "+ height,Toast.LENGTH_LONG).show();
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
            }
            return true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView w = new MyView(this);
        setContentView(w);
    }

    //옵션메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.regame:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            case R.id.goHome:
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
