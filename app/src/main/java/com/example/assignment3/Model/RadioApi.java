package com.example.assignment3.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RadioApi {
    @GET("jvanaria/jvanaria.github.io/master/channels.json")
    Call<RadioPojo> getRadioSong();
}
