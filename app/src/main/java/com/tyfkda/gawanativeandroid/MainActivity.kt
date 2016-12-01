package com.tyfkda.gawanativeandroid

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val webView = findViewById(R.id.webView) as WebView
    webView.settings.javaScriptEnabled = true
    webView.addJavascriptInterface(MyJavaScriptInterface(this), "Native")
    webView.setWebViewClient(object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (Uri.parse(url).scheme != "native")
          return false
        Toast.makeText(this@MainActivity, "Url requested: " + url, Toast.LENGTH_SHORT).show()
        return true
      }
    })
    webView.loadUrl("file:///android_asset/index.html")

    val button = findViewById(R.id.button) as Button
    button.setOnClickListener {
      evaluateJs(webView, "addTextNode('[Button clicked]')")
    }
  }

  private fun evaluateJs(webView: WebView, script: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
      webView.evaluateJavascript(script, null)
    else
      webView.loadUrl("javascript:" + script)
  }
}
