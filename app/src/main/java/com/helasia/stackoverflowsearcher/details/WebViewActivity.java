package com.helasia.stackoverflowsearcher.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.search_with_results.SearchAndResultActivityView;

public class WebViewActivity extends AppCompatActivity {
  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.web_view_activity);
    context = getApplicationContext();

    setToolbar();
    setWebView(getUrl());
  }

  public String getUrl(){
    String url;
    try{
      Intent i = getIntent();
      url = i.getStringExtra("url");
    }catch(NullPointerException e){
      url = "https://stackoverflow.com/";
    }
    return url;
  }

  public void setWebView(String url){
    WebView webView = (WebView) findViewById(R.id.web_view);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(url);
  }

  public void setToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_web_view_activity_layout);
    toolbar.setTitle(R.string.title_with_font);
    setSupportActionBar(toolbar);
    ActionBar actionbar = getSupportActionBar();
    actionbar.setDisplayHomeAsUpEnabled(true);
    actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
  }

  public boolean onOptionsItemSelected(MenuItem item){
    Intent intent = new Intent(context, SearchAndResultActivityView.class);
    startActivity(intent);
    WebViewActivity.this.finish();
    return true;
  }
}
