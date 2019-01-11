package com.helasia.stackoverflowsearcher.searchWithResults;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.Item;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.details.WebViewActivity;
import com.helasia.stackoverflowsearcher.licenses.LicensesActivity;
import java.util.List;

public class SearchAndResultActivity extends AppCompatActivity implements SearchAndResultContract.View {
  @BindView(R.id.items_recycler_view) RecyclerView itemsRecyclerView;
  @BindView(R.id.error_message) TextView errorMessage;
  @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.toolbar_activity_search_view) Toolbar toolbar;
  private LinearLayoutManager linearLayoutManager;
  private ResultCardsAdapter adapter;
  private SearchAndResultContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_view);
    ButterKnife.bind(this);

    presenter = new SearchAndResultPresenter(this, new QueryRepository());
    presenter.setFirstScreen();
  }

  @Override
  public Context getContext() {
    return this;
  }

  @Override
  public void setRecyclerView(List<Item> itemList) {
    if(itemList == null){
      setErrorMessage(this.getResources()
              .getString(R.string.empty_list_error));
    }else{
      setLinearLayoutForRecyclerView(itemList);
      setCallbackListener();
      setSwipeRefreshLayoutEnabledStatus();
      errorMessage.setVisibility(View.GONE);
      swipeRefreshLayout.setRefreshing(false);
    }
  }

  @Override
  public void setLinearLayoutForRecyclerView(List<Item> itemList){
    adapter = new ResultCardsAdapter(itemList);
    itemsRecyclerView.setAdapter(adapter);
    linearLayoutManager = new LinearLayoutManager(this);
    itemsRecyclerView.setLayoutManager(linearLayoutManager);
  }

  private void setCallbackListener(){
    adapter.setCallbackWebViewOnShareClickedListener(url -> {
      if (this.getResources().getConfiguration().orientation
              == Configuration.ORIENTATION_PORTRAIT) {
        goToDetails(url);
      } else {
        goToFragment(url);
      }
    });
  }

  @Override
  public void setSwipeRefreshLayoutEnabledStatus(){
    if(linearLayoutManager != null){
      itemsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          if(linearLayoutManager.findFirstVisibleItemPosition() == 0){
            swipeRefreshLayout.setEnabled(true);
          }else{
            swipeRefreshLayout.setEnabled(false);
          }
        }
      });
    }
  }

  @Override
  public void setSwipeRefreshLayout() {
      swipeRefreshLayout.setOnRefreshListener(() -> presenter.getItemsFromServer());
      swipeRefreshLayout.setColorSchemeResources(R.color.primary,
              android.R.color.holo_green_dark,
              android.R.color.holo_orange_dark,
              android.R.color.holo_blue_dark);
  }

  @Override
  public void setErrorMessage(String errorMessageText){
    errorMessage.setVisibility(View.VISIBLE);
    errorMessage.setText(errorMessageText);
  }

  @Override
  public void setToolbar() {
    toolbar.setTitle(R.string.title_with_font);
    setSupportActionBar(toolbar);
  }

  private void goToDetails(String url) {
    Intent intent = new Intent(SearchAndResultActivity.this, WebViewActivity.class);
    intent.putExtra("url", url);
    startActivity(intent);
    SearchAndResultActivity.this.finish();
  }

  private void goToFragment(String url) {
    FragmentManager fragmentManager = this.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frame_layout_details, getFragmentWithArgs(url));
    fragmentTransaction.commit();
  }

  private ResultDetailsFragment getFragmentWithArgs(String url){
    Bundle data = new Bundle();
    data.putString("url", url);
    ResultDetailsFragment resultDetailsFragment = new ResultDetailsFragment();
    resultDetailsFragment.setArguments(data);
    return resultDetailsFragment;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.search_menu, menu);

    MenuItem refreshViewItem = menu.findItem(R.id.action_refresh);
    refreshViewItem.setOnMenuItemClickListener(menuItem -> {
      presenter.getItemsFromServer();
      return true;
    });

    MenuItem infoViewItem = menu.findItem(R.id.action_licenses);
    infoViewItem.setOnMenuItemClickListener(menuItem -> {
      Intent intent = new Intent(this, LicensesActivity.class);
      startActivity(intent);
      return true;
    });

    MenuItem searchViewItem = menu.findItem(R.id.action_search);
    final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
    searchViewAndroidActionBar.isBaselineAligned();
    searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String title) {
        presenter.saveLastQueryInPreferences(title);
        presenter.getItemsFromServer(title);
        searchViewAndroidActionBar.clearFocus();
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
