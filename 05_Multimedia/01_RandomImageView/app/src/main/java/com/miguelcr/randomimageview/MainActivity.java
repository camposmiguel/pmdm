package com.miguelcr.randomimageview;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView pato;
    int width, height;
    int numAleatorioX, numAleatorioY;
    RelativeLayout layout;
    SoundPool soundPool;
    int sonidoPato, sonidoClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout)findViewById(R.id.layout);

        pato = new ImageView(this);
        pato.setImageResource(R.drawable.ic_duck);

        getAleatorios();

        pato.setOnClickListener(this);

        // Añado el ImageView al RelativeLayout
        layout.addView(pato);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // Click sonido sobre el pato
            AudioAttributes aa = null;
            aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(aa)
                    .build();

            // Sonido para el pato cuando se muestra
            // y para el evento click sobre un pato
            sonidoPato = soundPool.load(this,R.raw.click,1);
            sonidoClick = soundPool.load(this,R.raw.ostias,1);

        }
    }

    @Override
    public void onClick(View v) {
        // Play del sonido click
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            soundPool.play(sonidoClick, 1, 1, 0, 0, 1);
        }

        // Movemos el pato a una nueva posición
        getAleatorios();
    }

    public void getAleatorios() {
        width = layout.getWidth()-pato.getWidth();
        height = layout.getHeight()-pato.getHeight();

        numAleatorioX = (int)Math.floor(Math.random()*width);
        numAleatorioY = (int)Math.floor(Math.random()*height);

        pato.setX(numAleatorioX);
        pato.setY(numAleatorioY);
    }
}
