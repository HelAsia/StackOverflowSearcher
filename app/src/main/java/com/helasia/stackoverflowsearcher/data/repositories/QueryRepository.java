package com.helasia.stackoverflowsearcher.data.repositories;

import android.util.Log;
import com.helasia.stackoverflowsearcher.data.model.QueryResult;
import com.helasia.stackoverflowsearcher.data.network.RetrofitSingleton;
import com.helasia.stackoverflowsearcher.data.network.SearchAPI;
import com.helasia.stackoverflowsearcher.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QueryRepository implements QueryRepositoryInterface {
  private Retrofit retrofit;
  private SearchAPI searchAPI;
  private String baseURL;

  public QueryRepository() {
    this.baseURL = Constant.BASE_URL;
    this.retrofit = RetrofitSingleton.getRetrofitInstance(this.baseURL);
    this.searchAPI = retrofit.create(SearchAPI.class);
  }

  public QueryRepository(String url) {
    this.baseURL = url;
    this.retrofit = RetrofitSingleton.getRetrofitInstance(this.baseURL);
    this.searchAPI = retrofit.create(SearchAPI.class);
  }

  @Override
  public void getQueryResult(String title, final OnQueryResultDisplayListener listener) {
    Call<QueryResult> resp = searchAPI.getQueryResult(title);
    resp.enqueue(new Callback<QueryResult>() {
      @Override
      public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
        QueryResult queryResult = response.body();
        if(queryResult.getErrorId() == null){
          Log.i("onResponse(): ", queryResult.toString());
          listener.onSuccess(queryResult.getItems());
        }else {
          Log.i("onResponse(). error: ", queryResult.toString());
          listener.onError(queryResult.getErrorMessage());
        }
      }

      @Override
      public void onFailure(Call<QueryResult> call, Throwable t) {
        Log.i("onFailure(): Server ", t.getMessage());
        listener.onError(t.getMessage());
      }
    });
  }
}
