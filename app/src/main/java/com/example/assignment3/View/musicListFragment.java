package com.example.assignment3.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.assignment3.Model.RadioPojo;
import com.example.assignment3.Presenter.RadioPresenter;
import com.example.assignment3.Presenter.RadioPresenterContract;
import com.example.assignment3.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class musicListFragment extends Fragment implements ViewContract{
    RecyclerView recyclerView;
    RadioPresenterContract radioPresenterContract;
    SearchView searchView;

    public musicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_music_list, container, false);
        radioPresenterContract = new RadioPresenter(this);
        radioPresenterContract.initializeRetrofit();

        recyclerView = rootView.findViewById(R.id.recycle_song_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);




        radioPresenterContract.getRadioSongs();

        return rootView;
    }

    @Override
    public void populateRadio(RadioPojo dataSet) {
        recyclerView.setAdapter(new RecyclerAdapter(dataSet,getContext()));
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(),errorMessage, Toast.LENGTH_SHORT).show();

    }
}
