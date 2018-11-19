package com.helasia.stackoverflowsearcher.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helasia.stackoverflowsearcher.utils.Constant;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
  private static Retrofit retrofit = null;

  public static Retrofit getRetrofitInstance(String baseURL){
    if(retrofit == null){

      OkHttpClient client;
      OkHttpClient.Builder builder = new OkHttpClient.Builder()
          .readTimeout(Constant.GLOBAL_TIMEOUT_PARAMETER, TimeUnit.SECONDS)
          .connectTimeout(Constant.GLOBAL_TIMEOUT_PARAMETER, TimeUnit.SECONDS);

      client = builder.build();

      Gson gson = new GsonBuilder()
          .setLenient()
          .create();

      if (baseURL == null) {
        baseURL = Constant.BASE_URL;
      }

      retrofit = new retrofit2.Retrofit.Builder()
          .baseUrl(baseURL)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
    }
    return retrofit;
  }

}