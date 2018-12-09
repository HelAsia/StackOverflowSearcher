package com.helasia.stackoverflowsearcher.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helasia.stackoverflowsearcher.utils.Constant;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
  private static RetrofitSingleton instance = null;
  private Retrofit retrofit = null;

  private RetrofitSingleton(String baseURL){
    createRetrofitInstance(baseURL);
  }

  public static RetrofitSingleton getInstance(String baseURL){
    if(instance == null){
      instance = new RetrofitSingleton(baseURL);
    }
    return instance;
  }

  private void createRetrofitInstance(String baseURL){
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
  }

  public Retrofit getRetrofit(){
    return this.retrofit;
  }
}