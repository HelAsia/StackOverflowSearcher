package com.helasia.stackoverflowsearcher.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.searchWithResults.SearchAndResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
  @BindView(R.id.web_view) WebView webView;
  @BindView(R.id.toolbar_web_view_activity_layout) Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.web_view_activity);
    ButterKnife.bind(this);

    setToolbar();
    setWebView(getUrl());
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, SearchAndResultActivity.class);
    startActivity(intent);
    WebViewActivity.this.finish();
  }

  public String getUrl(){
    Intent i = getIntent();
    if(i != null){
      return i.getStringExtra("url");
    }else {
      return "https://stackoverflow.com/";
    }
  }

  private void setWebView(String url){
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(url);
  }

  private void setToolbar() {
    toolbar.setTitle(R.string.title_with_font);
    setSupportActionBar(toolbar);
    ActionBar actionbar = getSupportActionBar();
    if(actionbar != null){
      actionbar.setDisplayHomeAsUpEnabled(true);
      actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }
  }

  public boolean onOptionsItemSelected(MenuItem item){
    Intent intent = new Intent(this, SearchAndResultActivity.class);
    startActivity(intent);
    WebViewActivity.this.finish();
    return true;
  }
}
