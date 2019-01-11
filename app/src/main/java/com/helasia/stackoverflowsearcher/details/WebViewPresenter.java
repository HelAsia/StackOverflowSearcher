package com.helasia.stackoverflowsearcher.details;

public class WebViewPresenter implements WebViewContract.Presenter {
    private WebViewContract.View webView;

    WebViewPresenter (WebViewContract.View webView){
        this.webView = webView;
    }
    @Override
    public void setFirstScreen() {
        webView.setToolbar();
        webView.setWebView(webView.getUrl());
    }
}
