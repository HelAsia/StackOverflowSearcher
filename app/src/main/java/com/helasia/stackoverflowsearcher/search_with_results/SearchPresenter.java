package com.helasia.stackoverflowsearcher.search_with_results;

import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.helasia.stackoverflowsearcher.data.model.Item;
import com.helasia.stackoverflowsearcher.data.repositories.QueryRepository;
import com.helasia.stackoverflowsearcher.utils.Constant;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter,
    ResultCardsAdapter.OnShareWebViewDetailsListener,
    QueryRepository.OnQueryResultDisplayListener{
  private QueryRepository queryRepository;
  private SearchContract.View searchView;

  public SearchPresenter(SearchContract.View searchView, QueryRepository queryRepository){
    this.queryRepository = queryRepository;
    this.searchView = searchView;
  }

  @Override
  public void setRecyclerView(List<Item> itemList) {
    ResultCardsAdapter adapter = new ResultCardsAdapter(searchView.getContext(), itemList);
    searchView.getRecyclerView().setAdapter(adapter);
    searchView.getRecyclerView().setLayoutManager(new LinearLayoutManager(searchView.getContext()));
    adapter.setCallbackWebViewOnShareClickedListener(this);
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
    searchView.goToDetails(url);
  }

  @Override
  public void onSuccess(List<Item> itemList) {
    setRecyclerView(itemList);
  }

  @Override
  public void  onError(String errorMessageText) {
    searchView.getErrorMessageTextView().setVisibility(View.VISIBLE);
    searchView.getErrorMessageTextView().setText(errorMessageText);
  }
}
