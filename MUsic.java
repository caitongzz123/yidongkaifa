package com.example.newmusic;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    RadioButton r1, r2, r3;
    TextView txt;
    Button mStopButton, mStartButton, mPauseButton,mXvnhuanButton,deill;
    MediaPlayer mMediaPlayer;
    SeekBar sb;
    private Timer timer;
    private int pausePosition;
    private int currentMusicIndex;

    int res_file1 = R.raw.a;
    int res_file2 = R.raw.b;
    int res_file3 = R.raw.cc;
    int [] a={res_file1,res_file2,res_file3};
    private boolean isSeekBarChanging;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMediaPlayer = new MediaPlayer();
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        deill=(Button)findViewById(R.id.del) ;
        txt = (TextView) findViewById(R.id.text1);
        sb=(SeekBar)findViewById(R.id.seekbar);
        mStopButton = (Button) findViewById(R.id.Stop);
        mStartButton = (Button) findViewById(R.id.Start);
        mPauseButton = (Button) findViewById(R.id.Pause);
        mXvnhuanButton=(Button)findViewById(R.id.Xvnhuan);
        mStopButton.setOnClickListener(new mStopClick());
        mStartButton.setOnClickListener(new mStartClick());
        mPauseButton.setOnClickListener(new mPauseClick());
        mXvnhuanButton.setOnClickListener(new mXvnhuanClick());
        deill.setOnClickListener(new cleael());
        sb.setOnSeekBarChangeListener(new MySeekBar());
    }

    class mStopClick implements OnClickListener {
        @Override
        public void onClick(View v) {

            if (mMediaPlayer.isPlaying()) {

                mMediaPlayer.reset();
            }
        }
    }

    class InnerItemOnCLickListener implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currentMusicIndex = position;

            pausePosition = 0;
            mMediaPlayer.start();
        }
    }
   class cleael implements OnClickListener {

       @Override
       public void onClick(View view) {

           Intent intent = new Intent();
           intent.setClass(MainActivity.this, Deill.class);
           intent.putExtra("name", "xiazdong");
           startActivity(intent);
       }

    }

    class mStartClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (r1.isChecked()) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.reset();
                    mMediaPlayer.release();
                }
                try {
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, res_file1);
                    mMediaPlayer.start();
                    sb.setMax(mMediaPlayer.getDuration());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(!isSeekBarChanging){
                                sb.setProgress(mMediaPlayer.getCurrentPosition());
                            }
                        }
                    },0,50);
                    while(mMediaPlayer.getCurrentPosition()==sb.getMax()){
                        mMediaPlayer.reset();
                        mMediaPlayer.release();
                        mMediaPlayer = MediaPlayer.create(MainActivity.this, res_file2);
                        mMediaPlayer.start();
                    }
                } catch (Exception e) {
                    Log.i("ch1", "res err ");
                }
            }
            if (r2.isChecked()) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.reset();
                    mMediaPlayer.release();
                }
                try {
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, res_file2);
                    mMediaPlayer.start();
                    sb.setMax(mMediaPlayer.getDuration());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(!isSeekBarChanging){
                                sb.setProgress(mMediaPlayer.getCurrentPosition());
                            }
                        }
                    },0,50);
                    while(sb.getMax()==mMediaPlayer.getCurrentPosition()){
                        sb.setProgress(0);
                        mMediaPlayer = MediaPlayer.create(MainActivity.this, res_file3);
                        mMediaPlayer.start();
                    }
                } catch (Exception e) {
                    Log.i("ch1", "res err ");
                }
            }
            if (r3.isChecked()) {
                if (mMediaPlayer.isPlaying()) {

                    mMediaPlayer.reset();
                    mMediaPlayer.release();
                }
                try {
                    mMediaPlayer = MediaPlayer.create(MainActivity.this, res_file3);
                    mMediaPlayer.start();
                    sb.setMax(mMediaPlayer.getDuration());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(!isSeekBarChanging){
                                sb.setProgress(mMediaPlayer.getCurrentPosition());
                            }
                        }
                    },0,50);
                    while(sb.getMax()==mMediaPlayer.getCurrentPosition()){

                        mMediaPlayer = MediaPlayer.create(MainActivity.this, res_file1);
                        mMediaPlayer.start();
                    }
                } catch (Exception e) {
                    Log.i("ch1", "res err ");
                }
            }
        }
    }

    private void single() {
        Toast.makeText(MainActivity.this, "单曲循环", Toast.LENGTH_SHORT).show();
        currentMusicIndex++;
        currentMusicIndex--;
        pausePosition = 0;
        mMediaPlayer.seekTo(pausePosition);
        mMediaPlayer.getCurrentPosition();
        mMediaPlayer.start();
        mMediaPlayer.setLooping(true);
    }


    private void randown() {
        currentMusicIndex = new Random().nextInt(a.length);
        Toast.makeText(MainActivity.this, "随机播放", Toast.LENGTH_SHORT).show();
        pausePosition = 0;
        mMediaPlayer.seekTo(pausePosition);
        mMediaPlayer.getCurrentPosition();
        mMediaPlayer.start();
    }


    private void sequence() {
        currentMusicIndex++;
        Toast.makeText(MainActivity.this, "列表循环", Toast.LENGTH_SHORT).show();
        if (currentMusicIndex >=a.length) {
            currentMusicIndex = 0;
        }
        pausePosition = 0;
        mMediaPlayer.seekTo(pausePosition);
        mMediaPlayer.getCurrentPosition();
        mMediaPlayer.start();


    }


    class mPauseClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                pausePosition=mMediaPlayer.getCurrentPosition();
            } else {
                mMediaPlayer.start();
            }
        }
    }

    class mXvnhuanClick implements  OnClickListener{
        @Override
        public void onClick(View view) {
            int len= new Random().nextInt(3);
          if(len==0){
              single();
          }
            if(len==1){
                randown();
            }
            if(len==2){
                sequence();
            }
        }
//            if (r1.isChecked()) {
//                  mMediaPlayer.setLooping(true);
//            }
//            if (r2.isChecked()) {
//                    mMediaPlayer.setLooping(true);
//            }
//            if (r3.isChecked()) {
//                    mMediaPlayer.setLooping(true);
//            }
//            Toast.makeText(getApplicationContext(), "已进入单曲循环", Toast.LENGTH_SHORT).show();
//
//        }
    }
    public class MySeekBar implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            mMediaPlayer.seekTo(seekBar.getProgress());
        }
    }
    public void stopmediaplayer(){ //释放资源
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();
         mMediaPlayer.release();;
          mMediaPlayer=null;
        }
    }

}
