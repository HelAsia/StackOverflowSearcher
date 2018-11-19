package com.helasia.stackoverflowsearcher.search_with_results;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.helasia.stackoverflowsearcher.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragmentView extends Fragment {
  Context context;
  View view;

  public DetailsFragmentView() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    context = getActivity();

    View view = inflater.inflate(R.layout.fragment_details_fragment_view, container, false);
    setView(view);
    setWebView(getUrl());

    return view;
  }

  @Nullable
  @Override
  public View getView() {
    return view;
  }

  public void setView(View view) {
    this.view = view;
  }

  public String getUrl(){
    String url;
    try{
       url = getArguments().getString("url");
    }catch(NullPointerException e){
      url = "https://stackoverflow.com/";
    }
    return url;
  }

  public void setWebView(String url){
    WebView webView = (WebView)getView().findViewById(R.id.web_view);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(url);
  }

}
