package com.salesianostriana.dam.pmdm.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)this.findViewById(R.id.webViewMiguel);

        webView.loadUrl("http://www.miguelcr.com/");
    }
}
