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
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class secondImage extends AppCompatActivity {

    int r = 50;
    int width;
    int height;

    protected class MyView extends View {

        int Xratio = width/1080;
        int Yratio = height/1776;

        class Answer{
            // 정답을 나타내는 클래스
            int x1,y1,x2,y2; // 오른쪽 ,위 왼쪽 아래
            boolean checked  =false; // 이 정답을 클릭 했으면 true 아니면 false
            int xA, yA; // 이 객체의 정답을 클릭 했을 때 x,y좌표

            public Answer(int x1, int x2, int y1, int y2) {
                this.x1 = x1 * Xratio;
                this.y1 = y1 * Yratio;
                this.x2 = x2 * Xratio;
                this.y2 = y2 * Yratio;
            }

            void debug(){
                xA = (x1+x2)/2;
                yA = (y1+y2)/2;
                checked = true;
            }

            boolean checkRange(int x, int y){
                if(x < x1 || x2 < x) return false;
                if(y < y1 || y2 < y) return false;
                if(checked) Toast.makeText(getApplicationContext(), "중복입니다.", Toast.LENGTH_SHORT).show();
                else {
                    cntCrt++;
                    checked = true;
                    xA = x;
                    yA = y;
                }
                return true;
            }
        }

        int j=0;
        int[] checkCnt=new int[] {0,0,0,0,0,0,0,0,0};
        int[] x2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] y2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        Answer[][] answers = new Answer[2][];



        int[] pictures = new int[2];
        int index = 0;

        public int cntCrt=0, cntTotal=0;
        public int chance=30;

        void setFirst(){
            pictures[0] = R.drawable.level2;
            answers[0] = new Answer[4];
            //물고기
            answers[0][0] = new Answer(741-r,829+r,639-r,762+r);
            //물방울
            answers[0][1] = new Answer(453-r,475+r,176-r,194+r);
            //공룡다리
            answers[0][2] = new Answer(342-r,380+r,440-r,476+r);
            //물결
            answers[0][3] = new Answer(640-r,650+r,547-r,822+r);
        }

        void setSecond(){
            pictures[1] = R.drawable.level2_2;

            answers[1] = new Answer[6];
            answers[1][0] = new Answer(934-r,934+r,688-r,688+r);
            //꼬리
            answers[1][1] = new Answer(477-r,477+r,376-r,376+r);
            //입
            answers[1][2] = new Answer(439-r,439+r,248-r,248+r);
            //왼쪽 소 귀
            answers[1][3] = new Answer(644-r,644+r,355-r,355+r);
            // 오른쪽 소 귀 밑
            answers[1][4] = new Answer(494-r,494+r,583-r,583+r);
            // 왼쪽 소 발
            answers[1][5] = new Answer(641 -r,641+r,754-r,754+r);
            //왼쪽 닭 배

        }

        public MyView(Context context) {
            super(context);
            setFirst();
            setSecond();
//            // for debug
//            for(Answer a : answers){
//                a.debug();
//            }
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



            Intent notificationIntent = new Intent(getApplicationContext(), Main3Activity.class);

//            notificationIntent.putExtra("cntCrt",cntCrt);
//            notificationIntent.putExtra("cntWrg",cntTotal-cntCrt);

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
                    .setBadgeIconType(R.drawable.android)
                    .setContentIntent(pendingIntent);
            notifManager.notify(0, builder.build());
        }
        @Override
        protected void onDraw(Canvas canvas) {

//            int width = canvas.getWidth();
//            int height = canvas.getHeight();

//            Log.e("Canvas", "w: "+width+", h: "+height+"\n");



            Bitmap b = BitmapFactory.decodeResource(getResources(), pictures[index]);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);

            canvas.drawBitmap(resize_bitmap, 0, 0, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);

//            for(int i=0;i<j;i++){
//                if(x2[i]!=0&&y2[i]!=0) {
//                    canvas.drawCircle(x2[i], y2[i], 30, paint);
//                }
//            }
            for(Answer now : answers[index]){
                if(now.checked) canvas.drawCircle(now.xA  , now.yA , 30, paint);
            }

            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            paint.setStrokeWidth(10);
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawText("맞은개수: " + cntCrt, 100, 50, paint);
//            paint.setColor(Color.RED);
//            canvas.drawText("틀린개수: " + (cntTotal-cntCrt), 450, 50, paint);
//            paint.setColor(Color.MAGENTA);
//            canvas.drawText("남은기회: " + (chance-cntTotal), 800, 50, paint);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
//
//                    int x = (int) (event.getX()/ width*100);
//                    int y = (int) (event.getY()/ height*100);

                    int x = (int) (event.getX());
                    int y = (int) (event.getY());

                    // Toast.makeText(getApplicationContext(),x+"."+y,Toast.LENGTH_LONG).show();
                    Log.e("Point", "x: "+x+", y: "+y+"\n");

                    for(Answer nowAnswer : answers[index]){
                        nowAnswer.checkRange(x,y);
                    }


                    cntTotal++;

                    if(cntTotal == chance){
                        finish();
                    }
                    boolean next = true;
                    for(Answer a : answers[index]){
                        if(!a.checked) {
                            next = false;
                            break;
                        }
                    }
                    if(next) {
                        // 다 맞았을 때의 행동
//                        Log.e("Yes", index + "");
                        index++;
                        if(index == answers.length) {
                            // 마지막 사진까지 왔을 때
                            sendNotification();
                            finish();
                        }
                    }

//                    if(cntCrt == answers.length){
//                        sendNotification();
//                    }
                    invalidate();
                    /*
                     //좌표확인 코드
                    String msg = "터치를 입력받음 : " +x+" / " +y;
                    Toast. makeText(MainActivity. this, msg, Toast.LENGTH_SHORT ).show();
                    */
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
            }
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
            width = getResources().getDisplayMetrics().widthPixels;
            height = getResources().getDisplayMetrics().heightPixels - actionBarHeight;
        }
        Log.e("width", width+"");
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
