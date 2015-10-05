package com.miguelcr.holamundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends Activity {

    private TextView textoBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Log.i("INFO", "UserActivity: onCreate");

        textoBienvenida = (TextView)findViewById(R.id.textViewWelcome);

        // obtengo el Intent que hizo que se lanzara
        // este Activity
        Intent i = getIntent();
        Bundle datos = i.getExtras();
        String emailUsuario = (String)datos.get("email");

        // Cargamos en el texto de Bienvenida
        // el email del usuario
        textoBienvenida.setText(textoBienvenida.getText().toString()+emailUsuario+"!");

        Log.i("PARAMETROS","emailUsuario: "+emailUsuario);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO", "UserActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INFO", "UserActivity: onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("INFO", "UserActivity: onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout(View v) {
        this.onDestroy();
    }
}
