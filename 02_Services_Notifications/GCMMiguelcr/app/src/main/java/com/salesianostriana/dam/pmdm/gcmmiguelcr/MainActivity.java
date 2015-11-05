package com.salesianostriana.dam.pmdm.gcmmiguelcr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editTextNombre);
        final String nombre = editText.getText().toString();

        btnRegistro = (Button)findViewById(R.id.buttonRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GcmRegistrationAsyncTask(MainActivity.this).execute(nombre);
            }
        });


    }
}
