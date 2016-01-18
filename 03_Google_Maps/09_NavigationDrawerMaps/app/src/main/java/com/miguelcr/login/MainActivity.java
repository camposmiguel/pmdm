package com.miguelcr.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("FRIENDER_PREF", 0);
        boolean isLogged = settings.getBoolean("login", false);

        if(isLogged) {
            // Go directly to the HomeActivity
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // findViewByID...
        btnLogin = (Button)findViewById(R.id.buttonLogin);
        email = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);

        // setOnClickListener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("email@email.com")
                        && password.getText().toString().equals("1234")) {

                    // Save the data
                    SharedPreferences settings = getSharedPreferences("FRIENDER_PREF", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("email",email.getText().toString());
                    editor.putBoolean("login",true);

                    // Commit the edits!
                    editor.commit();

                    // Reset the values of the form elements
                    email.setText("");
                    password.setText("");

                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);

                    // Destroy this Activity because if someone go BACK we don't want to show
                    // the login page
                    MainActivity.this.finish();
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect login. Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
