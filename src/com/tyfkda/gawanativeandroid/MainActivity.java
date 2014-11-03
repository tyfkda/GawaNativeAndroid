package com.tyfkda.gawanativeandroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    WebView webView = (WebView) findViewById(R.id.webView);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.addJavascriptInterface(new JavaScriptInterface(this, webView), "Native");
    webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!Uri.parse(url).getScheme().equals("native"))
          return false;
        Toast.makeText(MainActivity.this, "Url requested: " + url, Toast.LENGTH_SHORT).show();
        return true;
      }
    });
    webView.loadUrl("file:///android_asset/index.html");
  }
}
