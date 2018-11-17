package com.helasia.stackoverflowsearcher.search_with_results;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.helasia.stackoverflowsearcher.data.model.Item;
import java.util.List;

public interface SearchContract {
  interface View{
    Context getContext();
    void setToolbar();
    RecyclerView getRecyclerView();
    TextView getErrorMessageTextView();
    void goToDetails(String url);
  }

  interface Presenter{
    void getItemsFromServer(String title);
    String getLastQueryFromPreferences();
    void setRecyclerView(List<Item> itemList);
  }
}
