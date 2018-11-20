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
import com.helasia.stackoverflowsearcher.search_with_results.SearchAndResultActivityView;
import java.util.List;

public class LicensesActivityView extends AppCompatActivity implements LicensesContract.View {
  private LicensesContract.Presenter presenter;
  private LicensesAdapter adapter;
  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_licenses_view);
    context = getApplicationContext();

    presenter = new LicensesPresenter(this);

    setToolbar();
    setRecyclerView(presenter.getLicensesList());
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  public Context getContext() {
    return context;
  }

  @Override
  public void setToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_licenses);
    toolbar.setTitle(R.string.title_with_font);
    setSupportActionBar(toolbar);
    ActionBar actionbar = getSupportActionBar();
    actionbar.setDisplayHomeAsUpEnabled(true);
    actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
  }

  @Override
  public void setRecyclerView(List<License> licenseList){
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.licencesDisplayRecyclerView);
    setAdapter(licenseList);
    LicensesAdapter adapter = getAdapter();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  public void setAdapter(List<License> licenseList){
    this.adapter = new LicensesAdapter(this, licenseList);
  }

  public LicensesAdapter getAdapter(){
    return adapter;
  }

  public void goToLicenseSource(String licenseUrl){
    Intent intent = new Intent(this, LicenseWebViewActivity.class);
    intent.putExtra("url", licenseUrl);
    startActivity(intent);
    onStop();
  }

  public boolean onOptionsItemSelected(MenuItem item){
    Intent intent = new Intent(context, SearchAndResultActivityView.class);
    startActivity(intent);
    return true;
  }
}
