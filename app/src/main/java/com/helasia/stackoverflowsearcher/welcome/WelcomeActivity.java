package com.helasia.stackoverflowsearcher.welcome;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.searchWithResults.SearchAndResultActivity;

public class WelcomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_view);

    new Handler().postDelayed(() -> {
      Intent intent = new Intent(this, SearchAndResultActivity.class);
      startActivity(intent);
      WelcomeActivity.this.finish();
    }, 1500);
  }
}

