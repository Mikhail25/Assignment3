package com.example.assignment3.View;

import com.example.assignment3.Model.RadioPojo;

public interface ViewContract {
    void populateRadio(RadioPojo dataSet);
    void onError(String errorMessage);
}
