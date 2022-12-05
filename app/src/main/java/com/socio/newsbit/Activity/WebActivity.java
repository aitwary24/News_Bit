package com.socio.newsbit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.socio.newsbit.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final String url=getIntent().getStringExtra("url");
        WebView webView=findViewById(R.id.activity_web);
        webView.loadUrl(url);
    }
}