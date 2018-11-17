package com.helasia.stackoverflowsearcher.search_with_results;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.details.WebViewActivity;
import com.helasia.stackoverflowsearcher.utils.Constant;

public class SearchActivityView extends AppCompatActivity implements SearchContract.View {
  @BindView(R.id.itemsRecyclerView) RecyclerView itemsRecyclerView;
  @BindView(R.id.errorMessage) TextView errorMessage;
  private SearchContract.Presenter presenter;
  Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_view);
    ButterKnife.bind(this);
    context = getApplicationContext();


    presenter = new SearchPresenter(this, new QueryRepository());
    setToolbar();

    if(!presenter.getLastQueryFromPreferences().equals("")){
      presenter.getItemsFromServer(presenter.getLastQueryFromPreferences());
    }
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
    setSupportActionBar(toolbar);
  }

  @Override
  public void goToDetails(String url) {
    Intent intent = new Intent(SearchActivityView.this, WebViewActivity.class);
    intent.putExtra("url", url);
    startActivity(intent);
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
