package com.miguelcr.login;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class GcmIntentService extends IntentService {
    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (extras != null && !extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Logger.getLogger("GCM_RECEIVED").log(Level.INFO, extras.toString());

                Log.i("JSON TALKME", "JSON: " + extras.getString("notificacion"));
                JSONObject jsonTiempo;
                try {
                    jsonTiempo = new JSONObject(extras.getString("notificacion"));
                    String mensajeRecibido = jsonTiempo.getString("mensaje");
                    String nickname = jsonTiempo.getString("nickname");

                    //showToast(mensajeRecibido);
                    showNotificacion(mensajeRecibido, nickname);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }



    protected  void showNotificacion(final String mensaje, final String nickname) {
        NotificationCompat.Builder mBuilder;

        // Definimos un identificador único para las notificaciones
        int mNotificationId = 1;
        NotificationManager mNotifyMgr = null;

        // Instancio el servicio NotificationManager,
        // para gestionar notificaciones.
        mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        Log.i("SERVICIO", "NOTIFICACION: " + mensaje);


        mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("De: "+ nickname)
                        .setContentText(mensaje);

        // Creo la notificación
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
