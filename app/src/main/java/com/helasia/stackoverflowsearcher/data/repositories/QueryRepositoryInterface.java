package com.helasia.stackoverflowsearcher.data.repositories;

public interface QueryRepositoryInterface {

  interface OnQueryResultDisplayListener{
    void onSuccess();
    void onError();
  }

  void getQueryResult(String title, OnQueryResultDisplayListener listener);

}
