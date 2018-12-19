package com.helasia.stackoverflowsearcher.licenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.helasia.stackoverflowsearcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LicenseWebViewActivity extends AppCompatActivity {
  @BindView(R.id.web_view) WebView webView;
  @BindView(R.id.toolbar_web_view_activity_layout) Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.web_view_activity);
    ButterKnife.bind(this);

    setToolbar();
    setWebView();
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, LicensesActivity.class);
    startActivity(intent);
    LicenseWebViewActivity.this.finish();
  }

  private void setWebView(){
    Intent i = getIntent();
    String url= i.getStringExtra("url");
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
    Intent intent = new Intent(this, LicensesActivity.class);
    startActivity(intent);
    LicenseWebViewActivity.this.finish();
    return true;
  }
}
