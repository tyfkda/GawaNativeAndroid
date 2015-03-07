package com.tyfkda.gawanativeandroid;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    final WebView webView = (WebView) findViewById(R.id.webView);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.addJavascriptInterface(new MyJavaScriptInterface(this), "Native");
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
    
    Button button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        evaluateJs(webView, "addTextNode('[Button clicked]')");
      }
    });
  }

  private void evaluateJs(WebView webView, String script) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
      webView.evaluateJavascript(script, null);
    else
      webView.loadUrl("javascript:" + script);
  }
}
