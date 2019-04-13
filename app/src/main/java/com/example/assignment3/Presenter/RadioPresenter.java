package com.example.assignment3.Presenter;

import com.example.assignment3.Model.RadioApi;
import com.example.assignment3.Model.RadioPojo;
import com.example.assignment3.View.ViewContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RadioPresenter implements RadioPresenterContract {
    ViewContract view;
    RadioApi api;

    public RadioPresenter(ViewContract view) {
        this.view = view;
    }

    @Override
    public void bindView(ViewContract view) {
        this.view = view;
    }

    @Override
    public void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RadioApi.class);

    }

    @Override
    public void getRadioSongs() {
        api.getRadioSong().enqueue(new Callback<RadioPojo>() {
            @Override
            public void onResponse(Call<RadioPojo> call, Response<RadioPojo> response) {
                view.populateRadio(response.body());
            }

            @Override
            public void onFailure(Call<RadioPojo> call, Throwable t) {
                view.onError(t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
