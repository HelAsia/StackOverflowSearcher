package com.helasia.stackoverflowsearcher.data.network;

import com.helasia.stackoverflowsearcher.data.model.QueryResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {
  @GET("2.2/search?order=desc&sort=activity&site=stackoverflow")
  Call<QueryResult> getQueryResult(@Query("intitle") String title);
}