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
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class level3_2 extends AppCompatActivity {
    protected int width, height;
    protected int x,y;
    private String userid;
    private int remain;



    int time ;
    Timer timer;
    Context mContext;



    protected class MyView extends View {
        int j=0;
        int[] checkCnt=new int[] {0,0,0,0,0,0,0,0};
        int[] x2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] y2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        public int cntCrt=0, cntWrg=0;
        public int totalcntCrt=0, totalcntWrg =0;


        public MyView(Context context) {
            super(context);
        }
        private Paint paint = new Paint();
        public void sendNotification() {
            String channelId = "channel";
            String channelName = "Channel Name";

            NotificationManager notifManager = (NotificationManager) getSystemService  (Context.NOTIFICATION_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(
                        channelId, channelName, importance);
                notifManager.createNotificationChannel(mChannel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId);



            Intent notificationIntent = new Intent(getApplicationContext(), Main2Activity.class);

            notificationIntent.putExtra("totalcntCrt",totalcntCrt);
            notificationIntent.putExtra("totalcntWrg",totalcntWrg);
            notificationIntent.putExtra("userid",userid);

            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            int requestID = (int) System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), requestID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle("축하합니다!") // required
                    .setContentText("전부 맞추셨습니다! *터치시 결과창으로 이동")  // required
                    .setDefaults(Notification.DEFAULT_ALL) // 알림, 사운드 진동 설정
                    .setAutoCancel(true) // 알림 터치시 반응 후 삭제
                    .setSound(RingtoneManager
                            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(android.R.drawable.btn_star)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.android))
                    .setContentIntent(pendingIntent);
            notifManager.notify(0, builder.build());
        }
        @Override
        protected void onDraw(Canvas canvas) {

            width = canvas.getWidth();
            height = canvas.getHeight();

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.level3_2);
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
            //paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.FILL);
//            canvas.drawText("맞은개수: " + cntCrt, 100, 50, paint);
//            paint.setColor(Color.RED);
//            canvas.drawText("틀린개수: " + cntWrg, 450, 50, paint);
            paint.setColor(Color.MAGENTA);
            canvas.drawText("남은기회: " + remain, 800, 50, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int)(event.getX()/width*100);
                    y = (int)(event.getY()/height*100);
                    if ((8 < x && x < 11) && (20 <= y&& y < 22)&&checkCnt[0]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[0] = 1;
                        invalidate();
                    }
                    else if ((8 < x && x < 11) && (20 <= y&& y < 22)&&checkCnt[0]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //2
                    else if ((29 <= x && x <= 30) && (8 < y&& y < 13) &&checkCnt[1]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[1] = 1;
                        invalidate();
                    }
                    else if ((29 <= x && x <= 30) && (8 < y&& y < 13)&&checkCnt[1]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //3
                    else if ((51 < x && x < 55) && (2 < y&& y < 5)&&checkCnt[2]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[2] = 1;
                        invalidate();
                    }
                    else if ((51 < x && x < 55) && (2 < y&& y < 5)&&checkCnt[2]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //4
                    else if ((54 < x && x < 58) && (10 < y&& y < 14)&&checkCnt[3]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[3] = 1;
                        invalidate();
                    }
                    else if ((54 < x && x < 58) && (10 < y&& y < 14)&&checkCnt[3]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //5
                    else  if ((13 < x && x < 21) && (9 < y&& y < 21) &&checkCnt[4]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[4] = 1;
                        invalidate();
                    }
                    else if ((13 < x && x < 21) && (9 < y&& y < 21)&&checkCnt[4]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //6
                    else  if ((72 < x && x < 84) && (38 < y&& y < 44) &&checkCnt[5]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[5] = 1;
                        invalidate();
                    }
                    else if ((72 < x && x < 84) && (38 < y&& y <44)&&checkCnt[5]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //7
                    else  if ((81 < x && x < 87) && (17 < y&& y < 21) &&checkCnt[6]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[6] = 1;
                        invalidate();
                    }
                    else if ((81 < x && x < 87) && (17 < y&& y < 21)&&checkCnt[6]==1){
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //8
                    else  if ((85 < x && x < 87) && (11 < y&& y < 14) &&checkCnt[7]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        totalcntCrt++;
                        checkCnt[7] = 1;
                        invalidate();
                    }
                    else if ((85 < x && x < 87) && (11 < y&& y < 14)&&checkCnt[7]==1){
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
                    if(cntCrt==8){
                        sendNotification();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("stotalcntCrt",totalcntCrt);
                        intent.putExtra("totalcntWrg",totalcntWrg);
                        intent.putExtra("userid",userid);

                        startActivity(intent);
                        finish();
                    }
                    if(remain == 0){
                        Toast.makeText(getApplicationContext(), "횟수 초과! 다시 시작.", Toast.LENGTH_LONG).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    remain--;
                    //Toast.makeText(getApplicationContext(), x+ " "+y, Toast.LENGTH_LONG).show();
                    //1080,2064
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
        mContext = this;
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        remain = intent.getIntExtra("remain", remain)-1;
        time = intent.getIntExtra("time",0);
        MyView w = new MyView(this);
        setContentView(w);


        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                time--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTitle("틀린그림 찾기 #남은시간: "+time);
                    }
                });
                if(time <= 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setTitle("틀린그림 찾기");
                            Toast.makeText(mContext,"시간 끝!", Toast.LENGTH_SHORT).show();
                            timer.cancel();
                        }
                    });
                }
            }
        };
        timer = new Timer();
        timer.schedule(tt,0,1000L);

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

    @Override
    public void onDestroy(){
        super.onDestroy();
        timer.cancel();
        timer = null;
    }
}
