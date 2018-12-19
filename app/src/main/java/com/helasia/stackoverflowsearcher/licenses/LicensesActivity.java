package com.helasia.stackoverflowsearcher.licenses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.License;
import com.helasia.stackoverflowsearcher.searchWithResults.SearchAndResultActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LicensesActivity extends AppCompatActivity implements LicensesContract.View {
  @BindView(R.id.toolbar_licenses) Toolbar toolbar;
  @BindView(R.id.licencesDisplayRecyclerView) RecyclerView recyclerView;
  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_licenses_view);
    ButterKnife.bind(this);
    context = getApplicationContext();

    LicensesContract.Presenter presenter = new LicensesPresenter(this);
    presenter.setFirstScreen();
  }

  @Override
  public Context getContext() {
    return context;
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, SearchAndResultActivity.class);
    startActivity(intent);
    LicensesActivity.this.finish();
  }

  @Override
  public void setToolbar() {
    toolbar.setTitle(R.string.title_with_font);
    setSupportActionBar(toolbar);
    ActionBar actionbar = getSupportActionBar();
    if(actionbar != null){
      actionbar.setDisplayHomeAsUpEnabled(true);
      actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }
  }

  @Override
  public void setRecyclerView(List<License> licenseList){
    LicensesAdapter adapter = new LicensesAdapter(this, licenseList);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  public void goToLicenseSource(String licenseUrl){
    Intent intent = new Intent(this, LicenseWebViewActivity.class);
    intent.putExtra("url", licenseUrl);
    startActivity(intent);
    LicensesActivity.this.finish();
  }

  public boolean onOptionsItemSelected(MenuItem item){
    Intent intent = new Intent(this, SearchAndResultActivity.class);
    startActivity(intent);
    LicensesActivity.this.finish();
    return true;
  }
}
