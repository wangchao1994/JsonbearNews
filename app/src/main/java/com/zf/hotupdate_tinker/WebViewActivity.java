package com.zf.hotupdate_tinker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.zf.hotupdate_tinker.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        if (url != null){
            webView.loadUrl(url);
        }else{
            Toast.makeText(this,"地址不合法",Toast.LENGTH_SHORT).show();
        }
    }
}
