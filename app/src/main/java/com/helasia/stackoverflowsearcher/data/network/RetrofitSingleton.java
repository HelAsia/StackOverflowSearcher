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

  public static Retrofit getRetrofitInstance(){
    if(retrofit == null){

      OkHttpClient client;
      OkHttpClient.Builder builder = new OkHttpClient.Builder()
          .readTimeout(360, TimeUnit.SECONDS)
          .connectTimeout(360, TimeUnit.SECONDS);

      client = builder.build();

      Gson gson = new GsonBuilder()
          .setLenient()
          .create();

      retrofit = new retrofit2.Retrofit.Builder()
          .baseUrl(Constant.BASE_URL)
          .client(client)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
    }
    return retrofit;
  }

}