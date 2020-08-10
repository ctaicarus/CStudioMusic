package com.example.mystylemusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityLyric extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lyric);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        Intent lyric = getIntent();
        String p = lyric.getStringExtra("namesong");
        Toast.makeText(MainActivityLyric.this , p , Toast.LENGTH_SHORT).show();
        webView.loadUrl("https://sum.vn/wEGyf");

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
