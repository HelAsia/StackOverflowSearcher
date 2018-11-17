package com.helasia.stackoverflowsearcher.data.repositories;

import android.util.Log;
import com.helasia.stackoverflowsearcher.data.model.QueryResult;
import com.helasia.stackoverflowsearcher.data.network.RetrofitSingleton;
import com.helasia.stackoverflowsearcher.data.network.SearchAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QueryRepositiory implements QueryRepositoryInterface {
  private Retrofit retrofit;
  private SearchAPI searchAPI;

  public QueryRepositiory() {
    this.retrofit = RetrofitSingleton.getRetrofitInstance();
    this.searchAPI = retrofit.create(SearchAPI.class);
  }
  @Override
  public void getQueryResult(String title, final OnQueryResultDisplayListener listener) {
    Call<QueryResult> resp = searchAPI.getQueryResult(title);
    resp.enqueue(new Callback<QueryResult>() {
      @Override
      public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
        QueryResult queryResult = response.body();
        if(queryResult.getErrorId() != null){
          Log.i("getQueryResult.onResponse(): QueryResult: ", queryResult.toString());
          listener.onSuccess();
        }else {
          Log.i("getQueryResult.onResponse(): QueryResult: ", queryResult.toString());
          listener.onError();
        }

      }

      @Override
      public void onFailure(Call<QueryResult> call, Throwable t) {
        Log.i("getQueryResult.onFailure(): Serwer: ", t.getMessage());
        listener.onSuccess();
      }
    });
  }
}
