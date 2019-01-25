package com.example.test;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.net.http.SslError;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView mWebView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(new AndroidtoJs(), "aaaaa");//AndroidtoJS类对象映射到js的test对象
        mWebView.addJavascriptInterface(new test(), "zz");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/javascript.html");


    }
    public class AndroidtoJs  {



        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }
    public class test{
        @JavascriptInterface
        public void test(String e) {
            Toast.makeText(MainActivity.this, e, Toast.LENGTH_LONG).show();
        }
    }

}
