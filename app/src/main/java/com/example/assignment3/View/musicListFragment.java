package com.example.assignment3.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    RecyclerAdapter adapter;


    public musicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_music_list, container, false);
        radioPresenterContract = new RadioPresenter(this);


        searchView = rootView.findViewById(R.id.sv_search_dj);
        Log.d("searchView: ", "onCreateView: "+searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        radioPresenterContract.initializeRetrofit();


        recyclerView = rootView.findViewById(R.id.recycle_song_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);




        radioPresenterContract.getRadioSongs();

        return rootView;
    }


    @Override
    public void populateRadio(RadioPojo dataSet) {
        adapter = new RecyclerAdapter(dataSet,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(),errorMessage, Toast.LENGTH_SHORT).show();

    }
}
