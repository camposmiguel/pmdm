package com.miguelcr.soundpool;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SoundPool soundPool;
    int sonidoExplosion;
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            // Player properties
            AudioAttributes aa = null;

            aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(aa)
                .build();

        // Load sound of guitar
        sonidoExplosion = soundPool.load(this, R.raw.powerup, 1);

        }
    }

    @Override
    public void onClick(View v) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            MediaPlayer player = MediaPlayer.create(this, R.raw.powerup);
            int duration = player.getDuration();

            for(int i=0; i<10; i++) {
                float left = 1-0.1f*i;
                float right = 0+0.1f*i;
                soundPool.play(sonidoExplosion, left, right, i, 0, 1);
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
