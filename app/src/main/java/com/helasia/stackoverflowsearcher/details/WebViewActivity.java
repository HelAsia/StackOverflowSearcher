package com.helasia.stackoverflowsearcher.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.helasia.stackoverflowsearcher.R;

public class WebViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.web_view_activity);

    setToolbar();

    Intent i = getIntent();
    String url= i.getStringExtra("url");
    WebView webView = (WebView) findViewById(R.id.web_view);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(url);
  }

  public void setToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_web_view_activity_layout);
    toolbar.setSubtitle(R.string.details);
    setSupportActionBar(toolbar);
  }
}
