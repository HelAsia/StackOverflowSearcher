package com.helasia.stackoverflowsearcher.search_with_results;

import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.Item;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.search_with_results.ResultCardsAdapter.OnShareWebViewDetailsListener;
import com.helasia.stackoverflowsearcher.utils.Constant;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter,
    OnShareWebViewDetailsListener,
    QueryRepository.OnQueryResultDisplayListener{
  private QueryRepository queryRepository;
  private SearchContract.View searchView;

  public SearchPresenter(SearchContract.View searchView, QueryRepository queryRepository){
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
    ResultCardsAdapter adapter = new ResultCardsAdapter(searchView.getContext(), itemList);
    searchView.getRecyclerView().setAdapter(adapter);
    searchView.getRecyclerView().setLayoutManager(new LinearLayoutManager(searchView.getContext()));
    adapter.setCallbackWebViewOnShareClickedListener(this);
  }

  @Override
  public void setSwipeRefreshLayout() {
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

  @Override
  public void getItemsFromServer(String title) {
    queryRepository.getQueryResult(title, this);
  }

  @Override
  public String getLastQueryFromPreferences(){
    return PreferenceManager.getDefaultSharedPreferences(searchView.getContext())
        .getString(Constant.PREF_LAST_QUERY, "");
  }

  @Override
  public void shareCardClicked(String url) {
    if(searchView.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
      searchView.goToDetails(url);
    }else {
      searchView.goToFragment(url);
    }
  }

  @Override
  public void onSuccess(List<Item> itemList) {
    setRecyclerView(itemList);
    searchView.getSwipeRefreshLayout().setRefreshing(false);
  }

  @Override
  public void  onError(String errorMessageText) {
    searchView.getErrorMessageTextView().setVisibility(View.VISIBLE);
    searchView.getErrorMessageTextView().setText(errorMessageText);
  }
}
