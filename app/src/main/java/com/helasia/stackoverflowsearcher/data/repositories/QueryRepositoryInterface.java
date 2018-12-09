package com.helasia.stackoverflowsearcher.data.repositories;

import com.helasia.stackoverflowsearcher.data.model.Item;
import java.util.List;

public interface QueryRepositoryInterface {
  interface OnQueryResultDisplayListener{
    void onSuccess(List<Item> itemList);
    void onError(String errorMessageText);
  }

  void getQueryResult(String title, OnQueryResultDisplayListener listener);
}
