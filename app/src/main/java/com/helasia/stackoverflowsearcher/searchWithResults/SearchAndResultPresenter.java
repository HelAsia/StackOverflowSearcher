package com.helasia.stackoverflowsearcher.searchWithResults;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import com.helasia.stackoverflowsearcher.R;
import com.helasia.stackoverflowsearcher.data.model.Item;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.searchWithResults.ResultCardsAdapter.OnShareWebViewDetailsListener;
import com.helasia.stackoverflowsearcher.utils.Constant;
import java.util.List;

public class SearchAndResultPresenter implements SearchAndResultContract.Presenter,
    QueryRepository.OnQueryResultDisplayListener{
  private QueryRepository queryRepository;
  private SearchAndResultContract.View searchView;

  public SearchAndResultPresenter(SearchAndResultContract.View searchView, QueryRepository queryRepository){
    this.queryRepository = queryRepository;
    this.searchView = searchView;
  }

  @Override
  public void setFirstScreen(){
    searchView.setToolbar();
    if(!getLastQueryFromPreferences().equals("")){
      getItemsFromServer();
    }

    searchView.setSwipeRefreshLayout();
  }

  @Override
  public void getItemsFromServer() {
    if(queryRepository != null){
      String title = getLastQueryFromPreferences();
      queryRepository.getQueryResult(title, this);
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
  public void onSuccess(List<Item> itemList) {
    if(searchView != null){
      searchView.setRecyclerView(itemList);
    }
  }

  @Override
  public void onError(String errorMessageText) {
    searchView.setErrorMessage(errorMessageText);
  }

  @Override
  public void saveLastQueryInPreferences(String title){
    SharedPreferences.Editor lastQuery = PreferenceManager.getDefaultSharedPreferences(searchView.getContext()).edit();
    lastQuery.putString(Constant.PREF_LAST_QUERY, title).apply();
    lastQuery.commit();
  }
}

