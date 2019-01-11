package com.helasia.stackoverflowsearcher.searchWithResults;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.helasia.stackoverflowsearcher.data.model.Item;
import java.util.List;

public interface SearchAndResultContract {
  interface View{
    Context getContext();
    void setToolbar();
    void setRecyclerView(List<Item> itemList);
    void setErrorMessage(String errorMessageText);
    void setLinearLayoutForRecyclerView(List<Item> itemList);
    void setSwipeRefreshLayoutEnabledStatus();
    void setSwipeRefreshLayout();
  }

  interface Presenter{
    void setFirstScreen();
    void getItemsFromServer();
    void getItemsFromServer(String title);
    String getLastQueryFromPreferences();
    void saveLastQueryInPreferences(String title);
  }
}
