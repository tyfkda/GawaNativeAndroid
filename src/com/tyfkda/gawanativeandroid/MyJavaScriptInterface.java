package com.tyfkda.gawanativeandroid;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyJavaScriptInterface {
  private Context context;
  
  public MyJavaScriptInterface(Context context) {
    this.context = context;
  }

  @JavascriptInterface
  public void showToast(String string) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
  }
}
