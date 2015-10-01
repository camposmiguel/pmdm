package com.miguelcr.holamundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

    private EditText emailForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailForm = (EditText)findViewById(R.id.editTextEmail);

        Log.i("INFO", "LoginActivity: onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO", "LoginActivity: onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INFO", "LoginActivity: onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("INFO", "LoginActivity: onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    // 1. public
    // 2. que devuelva void
    // 3. reciba como parámetro un View

    public void login(View v) {
        // Lanzamos el segundo Activity, es decir,
        // una nueva pantalla, interfaz.
        Intent i = new Intent(LoginActivity.this, UserActivity.class);
        // <nombre,valor> => <"email","un ejemplo de email">
        i.putExtra("email",emailForm.getText().toString());

        startActivity(i);

    }
}
