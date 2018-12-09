package com.helasia.stackoverflowsearcher.search_with_results;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.Item;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.search_with_results.ResultCardsAdapter.OnShareWebViewDetailsListener;
import com.helasia.stackoverflowsearcher.utils.Constant;
import java.util.List;

public class SearchAndResultPresenter implements SearchAndResultContract.Presenter,
    OnShareWebViewDetailsListener,
    QueryRepository.OnQueryResultDisplayListener{
  private QueryRepository queryRepository;
  private SearchAndResultContract.View searchView;
  private LinearLayoutManager linearLayoutManager;

  public SearchAndResultPresenter(SearchAndResultContract.View searchView, QueryRepository queryRepository){
    this.queryRepository = queryRepository;
    this.searchView = searchView;
  }

  @Override
  public void setFirstScreen(){
    if(!getLastQueryFromPreferences().equals("")){
      getItemsFromServer(getLastQueryFromPreferences());
    }
  }

  @Override
  public void setRecyclerView(List<Item> itemList) {
    if(itemList == null){
      showErrorMessage(searchView.getContext().getResources()
          .getString(R.string.empty_list_error));
    }else{
      setLinearLayoutForRecyclerView(itemList);
      setSwipeRefreshLayoutEnabledStatus();
    }
  }

  @Override
  public void showErrorMessage(String errorMessageText){
    if(searchView != null) {
      searchView.getErrorMessageTextView().setVisibility(View.VISIBLE);
      searchView.getErrorMessageTextView().setText(errorMessageText);
    }
  }

  @Override
  public void setLinearLayoutForRecyclerView(List<Item> itemList){
    if(searchView != null){
      final ResultCardsAdapter adapter = new ResultCardsAdapter(itemList);
      searchView.getRecyclerView().setAdapter(adapter);
      linearLayoutManager = new LinearLayoutManager(searchView.getContext());
      searchView.getRecyclerView().setLayoutManager(linearLayoutManager);
      adapter.setCallbackWebViewOnShareClickedListener(this);
    }
  }

  @Override
  public void setSwipeRefreshLayoutEnabledStatus(){
    if(searchView != null && linearLayoutManager != null){
      searchView.getRecyclerView().addOnScrollListener(new OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          if(linearLayoutManager.findFirstVisibleItemPosition() == 0){
            searchView.getSwipeRefreshLayout().setEnabled(true);
          }else{
            searchView.getSwipeRefreshLayout().setEnabled(false);
          }
        }
      });
    }
  }

  @Override
  public void setSwipeRefreshLayout() {
    if(searchView != null) {
      searchView.getSwipeRefreshLayout().setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh() {
          getItemsFromServer(getLastQueryFromPreferences());
        }
      });
      searchView.getSwipeRefreshLayout().setColorSchemeResources(R.color.primary,
          android.R.color.holo_green_dark,
          android.R.color.holo_orange_dark,
          android.R.color.holo_blue_dark);
    }
  }

  @Override
  public void getItemsFromServer(String title) {
    if(queryRepository != null){
      queryRepository.getQueryResult(title, this);
    }
  }

  @Override
  public String getLastQueryFromPreferences(){
    return PreferenceManager.getDefaultSharedPreferences(searchView.getContext())
        .getString(Constant.PREF_LAST_QUERY, "");
  }

  @Override
  public void shareCardClicked(String url) {
    if(searchView != null) {
      if (searchView.getContext().getResources().getConfiguration().orientation
          == Configuration.ORIENTATION_PORTRAIT) {
        searchView.goToDetails(url);
      } else {
        searchView.goToFragment(url);
      }
    }
  }

  @Override
  public void onSuccess(List<Item> itemList) {
    if(searchView != null){
      setRecyclerView(itemList);
      searchView.getErrorMessageTextView().setVisibility(View.GONE);
      searchView.getSwipeRefreshLayout().setRefreshing(false);
    }
  }

  @Override
  public void onError(String errorMessageText) {
    showErrorMessage(errorMessageText);
  }


  @Override
  public void saveLastQueryInPreferences(String title){
    SharedPreferences.Editor lastQuery = PreferenceManager.getDefaultSharedPreferences(searchView.getContext()).edit();
    lastQuery.putString(Constant.PREF_LAST_QUERY, title).apply();
    lastQuery.commit();
  }
}

