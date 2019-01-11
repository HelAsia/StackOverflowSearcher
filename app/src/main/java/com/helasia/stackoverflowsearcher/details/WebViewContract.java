package com.helasia.stackoverflowsearcher.details;

public interface WebViewContract {

    interface View {
        void setToolbar();
        void setWebView(String url);
        String getUrl();
    }

    interface Presenter {
        void setFirstScreen();
    }
}
