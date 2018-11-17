package com.helasia.stackoverflowsearcher.data.network;

import com.helasia.stackoverflowsearcher.data.model.QueryResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchAPI {
  @GET("/2.2/search?order=desc&sort=activity&intitle={title}&site=stackoverflow")
  Call<QueryResult> getQueryResult(@Path("title") String title);
}
