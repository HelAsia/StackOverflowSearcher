package com.helasia.stackoverflowsearcher.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.search_with_results.SearchActivityView;

public class WelcomeActivityView extends AppCompatActivity {
  private final int SPLASH_DISPLAY_LENGTH = 1500;
  Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_view);

    context = getApplicationContext();

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(WelcomeActivityView.this, SearchActivityView.class);
        startActivity(intent);
        WelcomeActivityView.this.finish();
      }
    }, SPLASH_DISPLAY_LENGTH);
  }
}
