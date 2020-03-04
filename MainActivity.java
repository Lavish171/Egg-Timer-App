package com.example.elavi.timerforegg;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
//https://youtu.be/AHctD6XFhlw//
public class MainActivity extends AppCompatActivity {
     TextView timertextview;
    SeekBar timerseekbar;
    Button gobutton;
    CountDownTimer countDownTimer;
    boolean counterisactive=false;
    public void resetTimer()
    {
        timertextview.setText("0:30");
        timerseekbar.setProgress(30);
        timerseekbar.setEnabled(true);
        countDownTimer.cancel();
        gobutton.setText("Go");
        counterisactive=false;
    }
    public void onclick(View view)
    {
        if(counterisactive)
        {
            resetTimer();
        }
        else {
            counterisactive=true;
            timerseekbar.setEnabled(false);
            gobutton.setText("STOP!");
                countDownTimer = new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {
                    @Override
                public void onTick(long l) {
                    updatetimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    Toast.makeText(getApplicationContext(), "Bye-Bye next time", Toast.LENGTH_SHORT).show();
                    final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.police);
                    mediaPlayer.start();
               /*Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mediaPlayer.stop();
                   }
               }, 3 * 1000);*/
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mediaPlayer.stop();
                        }
                    }, 3 * 1000);
                    resetTimer();
                }
            }.start();
        }

    }

    public void updatetimer(int secondsleft)
    {
        int min=secondsleft/60;
        int sec=secondsleft-60*min;
        String minutes="";
        String seconds="";
        if(sec<10)
        {
            seconds="0"+Integer.toString(sec);
        }
        else
        {
            seconds=Integer.toString(sec);
        }
        if(min<1)
        {
            minutes= "0"+Integer.toString(min);
        }
        else
        {
            minutes=Integer.toString(min);
        }
        timertextview.setText(minutes+":"+seconds);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerseekbar=findViewById(R.id.eggtimerseekbar);
       timertextview=findViewById(R.id.eggtimertextview);
       gobutton=findViewById(R.id.eggtimerbutton);
        timerseekbar.setProgress(30);
        timerseekbar.setMax(660);
        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatetimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
