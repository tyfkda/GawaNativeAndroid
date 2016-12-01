package com.tyfkda.gawanativeandroid

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class MyJavaScriptInterface(private val context: Context) {
  @JavascriptInterface
  fun showToast(string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
  }
}
