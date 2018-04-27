package com.example.admin.myapplication;


import android.content.Intent;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.media.SoundPool;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;

        import java.util.ArrayList;

public class MyPiano extends AppCompatActivity {
    private static final String TAG = "tiger";
    Handler handler = null;
    int count=0;
    Button[] btns = new Button[20];
    SoundPool pool;
    int ck = 0;
    int cnt = 0;
    int[] ar = new int[11];

    ArrayList<Integer[]> song1 = new ArrayList<Integer[]>();
    int[] br = {3, 2, 1, 2, 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 2, 1, 2, 3, 3, 3, 2, 2, 3, 2, 1};
    int[] cr = {750, 250, 500, 500, 500, 500, 1000, 500, 500, 1000, 500, 500, 1000, 750, 250, 500, 500, 500, 500, 1000, 500, 500, 750, 250, 2000};


    ArrayList<Integer[]> song2 = new ArrayList<Integer[]>();
    int[] dr = {5, 5, 6, 6, 5, 5, 3, 5, 5, 3, 3, 2, 5, 5, 6, 6, 5, 5, 3, 5, 3, 2, 3, 1};
    int[] er = {1000, 1000, 1000, 1000, 1000, 1000, 2000, 1000, 1000, 1000, 1000, 3000, 1000, 1000, 1000, 1000, 1000, 1000, 2000, 1000, 1000, 1000, 1000, 3000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        for (int i = 0; i < 11; i++) {
            int id = getResources().getIdentifier("btn" + (i + 1), "id", "com.example.admin.myapplication");
            btns[i] = findViewById(id);
            btns[i].setOnClickListener(clickListener);
        }

        pool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);

        for (int i = 0; i < ar.length; i++) {
            int id = getResources().getIdentifier("sound" + (i + 1), "raw", "com.example.admin.myapplication");
            ar[i] = pool.load(this, id, 1);
        }

        setting_song1();
        setting_song2();

    }

    final int FLY_ID = 10;
    final int STOP_ID = 20;
    final int SCHOOL_ID = 30;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,FLY_ID,1,"비행기");
        menu.add(0,STOP_ID,3,"stop");
        menu.add(0, SCHOOL_ID, 2, "학교종");
        return super.onCreateOptionsMenu(menu);
    }

   public void timer(){

       handler = new Handler() {
           @Override
           public void handleMessage(Message msg) {
               if(ck==0) {
                   handler.sendEmptyMessageDelayed(0, song1.get(cnt)[1]);
                   pool.play(ar[song1.get(cnt)[0] - 1], 1, 1, 0, 0, 1);
                   cnt++;
                   if (cnt == br.length) {
                       handler.removeMessages(0);
                       return;
                   }
               } else {
                   handler.sendEmptyMessageDelayed(0, song2.get(cnt)[1]);
                   pool.play(ar[song2.get(cnt)[0] - 1], 1, 1, 0, 0, 1);
                   cnt++;
                   if (cnt == dr.length) {
                       handler.removeMessages(0);
                       return;
                   }
               }
           }
       };handler.sendEmptyMessage(0);
    }
    public void stop() {
        cnt = 0;
        if(handler!=null) {
            handler.removeMessages(0);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case 10:
                ck=0;
                if(handler!=null) {
                    stop();
                }
                timer();
                break;
            case 30:
                ck=1;
                if(handler!=null) {
                    stop();
                }
                timer();
                break;
            case 20:
                stop();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    pool.play(ar[0], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn2:
                    pool.play(ar[1], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn3:
                    pool.play(ar[2], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn4:
                    pool.play(ar[3], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn5:
                    pool.play(ar[4], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn6:
                    pool.play(ar[5], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn7:
                    pool.play(ar[6], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn8:
                    pool.play(ar[7], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn01:
                    pool.play(ar[8], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn03:
                    pool.play(ar[9], 1, 1, 0, 0, 1);
                    break;
                case R.id.btn04:
                    pool.play(ar[10], 1, 1, 0, 0, 1);
                    break;

            }
        }
    };

    public void setting_song1() {
        for (int i = 0; i < br.length; i++) {
            Integer[] note = {br[i], cr[i]};
            song1.add(note);
        }
    }
    public void setting_song2() {
        for (int i = 0; i < dr.length; i++) {
            Integer[] note = {dr[i], er[i]};
            song2.add(note);
        }
    }
}