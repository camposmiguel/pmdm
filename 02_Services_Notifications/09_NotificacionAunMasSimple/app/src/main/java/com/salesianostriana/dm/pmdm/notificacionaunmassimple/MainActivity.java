package com.salesianostriana.dm.pmdm.notificacionaunmassimple;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder mBuilder;
    int mNotificationOneId = 001;
    int mNotificationTwoId = 002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lanzarNotificacion(View v) {
        int id = v.getId();

        if(id==R.id.buttonNotifyOne) {
            notificacion(mNotificationOneId);
        } else {
            notificacion(mNotificationTwoId);
        }
    }

    public void notificacion(int mNotificationId) {

        //********************************************************
        // PASO 1: DEFINIR EL CONTENIDO DE LA NOTIFICACIÓN
        //********************************************************
        mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_notify_sdcard)
                        .setContentTitle("Titulo")
                        .setContentText("Descripcion");


        //********************************************************
        // PASO 2: DEFINIR EL ACTIVITY ASOCIADO A LA NOTIFICACIÓN
        //********************************************************

        // Defino un Intent que será lanzado cuando el usuario haga clic
        // en la notificación
        Intent resultIntent = new Intent(this, NotificactionActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        //********************************************************
        // PASO 3: LANZAR LA NOTIFICACIÓN
        //********************************************************
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}
