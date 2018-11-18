package com.helasia.stackoverflowsearcher.search_with_results;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.details.WebViewActivity;
import com.helasia.stackoverflowsearcher.licenses.LicensesActivityView;
import com.helasia.stackoverflowsearcher.utils.Constant;

public class SearchActivityView extends AppCompatActivity implements SearchContract.View {
  @BindView(R.id.items_recycler_view) RecyclerView itemsRecyclerView;
  @BindView(R.id.error_message) TextView errorMessage;
  private SearchContract.Presenter presenter;
  Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = getApplicationContext();
    presenter = new SearchPresenter(this, new QueryRepository());

    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
      setPortraitScreen();
    }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
      setLandscapeScreen();
    }
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
  public RecyclerView getRecyclerView() {
    return itemsRecyclerView;
  }

  @Override
  public TextView getErrorMessageTextView() {
    return errorMessage;
  }

  @Override
  public void setToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_search_view);
    toolbar.setTitle(R.string.title_with_font);
    setSupportActionBar(toolbar);
  }

  @Override
  public void goToDetails(String url) {
    Intent intent = new Intent(SearchActivityView.this, WebViewActivity.class);
    intent.putExtra("url", url);
    startActivity(intent);
    onStop();
  }

  @Override
  public void goToFragment(String url) {
    Bundle data = new Bundle();
    data.putString("url", url);
    DetailsFragmentView detailsFragmentView = new DetailsFragmentView();
    detailsFragmentView.setArguments(data);
    FragmentManager fragmentManager = this.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frame_layout_details, detailsFragmentView);
    fragmentTransaction.commit();
  }

  @Override
  public void setFirstFragment(){
    DetailsFragmentView detailsFragmentView = new DetailsFragmentView();
    FragmentManager fragmentManagerDetails = this.getSupportFragmentManager();
    FragmentTransaction fragmentTransactionDetails = fragmentManagerDetails.beginTransaction();
    fragmentTransactionDetails.replace(R.id.frame_layout_details, detailsFragmentView);
    fragmentTransactionDetails.commit();
  }

  @Override
  public void setPortraitScreen(){
    setContentView(R.layout.activity_search_view_portrait);
    ButterKnife.bind(this);
    setToolbar();
    presenter.setFirstScreen();
  }

  @Override
  public void setLandscapeScreen(){
    setContentView(R.layout.activity_search_view_landscape);
    ButterKnife.bind(this);
    setToolbar();
    presenter.setFirstScreen();
    setFirstFragment();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.search_menu, menu);

    MenuItem refreshViewItem = menu.findItem(R.id.action_refresh);
    refreshViewItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem menuItem) {
        presenter.getItemsFromServer(presenter.getLastQueryFromPreferences());
        return true;
      }
    });

    MenuItem infoViewItem = menu.findItem(R.id.action_licenses);
    infoViewItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem menuItem) {
        Intent intent = new Intent(SearchActivityView.this, LicensesActivityView.class);
        startActivity(intent);
        return true;
      }
    });

    MenuItem searchViewItem = menu.findItem(R.id.action_search);
    final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
    searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String title) {
        SharedPreferences.Editor lastQuery = PreferenceManager.getDefaultSharedPreferences(context).edit();
        lastQuery.putString(Constant.PREF_LAST_QUERY, title).apply();
        lastQuery.commit();
        presenter.getItemsFromServer(title);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }
}
