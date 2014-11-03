package com.tyfkda.gawanativeandroid;

import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;

public class JavaScriptInterface {
  private Context context;
  private WebView webView;
  
  public JavaScriptInterface(Context context, WebView webView) {
    this.context = context;
    this.webView = webView;
  }

  public void showToast(String string) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show();

    evaluateJs("addTextNode('" + string + "')");
  }

  private void evaluateJs(String script) {
    webView.loadUrl("javascript:" + script);
  }
}
