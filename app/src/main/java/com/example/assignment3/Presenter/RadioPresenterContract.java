package com.example.assignment3.Presenter;

import com.example.assignment3.View.ViewContract;

public interface RadioPresenterContract {
    void bindView(ViewContract view);
    void initializeRetrofit();
    void getRadioSongs();
    void onDestroy();
}
