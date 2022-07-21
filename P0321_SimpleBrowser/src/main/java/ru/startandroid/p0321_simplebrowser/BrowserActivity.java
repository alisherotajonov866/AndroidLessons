package ru.startandroid.p0321_simplebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        WebView webView = findViewById(R.id.webView) ;
        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData() ;
        webView.loadUrl(data.toString());
        // oxirdan 3 xator kodga yaxshi tushunmadim
    }
}