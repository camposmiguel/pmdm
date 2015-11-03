package com.salesianostriana.dam.di.notificacion_pendingintent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ServicioMusica extends Service {
    public ServicioMusica() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String mensajeLargo = "Lorem ipsum asdhg asjkfhdgsa fjhasdg fkjdshfg dsakjhfg kajsdhgf adjshgf asdhg asjkfhdgsa fjhasdg fkjdshfg dsakjhfg kajsdhgf adjshgf asdhg asjkfhdgsa fjhasdg fkjdshfg dsakjhfg kajsdhgf adjshgf asdhg asjkfhdgsa fjhasdg fkjdshfg dsakjhfg kajsdhgf adjshgf ";

        // 1. PROPIEDADES DE LA NOTIFICACIÓN

        // Pendint Intent - Acción Eliminar
        Intent accionIntent = new Intent(this, ReproductorActivity.class);

        PendingIntent pIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        accionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_media_play)
                        .setContentTitle("Reproduciendo")
                        .setContentText("El príncipe de Bel-Air")
                        .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(mensajeLargo))
                        .addAction(android.R.drawable.ic_media_pause,"Pausar",pIntent);


        // 2. ACCIÓN DE LA NOTIFICACIÓN (ACTIVITY QUE SE VA A LANZAR AL HACER
        // CLICK EN LA NOTIFICACIÓN)
        Intent resultIntent = new Intent(this, ReproductorActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        // 3. LANZAMOS LA NOTIFICACIÓN AL AIRE (ON AIR)

        // Sets an ID for the notification
        int mNotificationId = 001;

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

        return START_NOT_STICKY;

    }


}
