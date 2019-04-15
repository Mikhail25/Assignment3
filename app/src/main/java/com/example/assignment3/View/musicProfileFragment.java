package com.example.assignment3.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment3.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class musicProfileFragment extends Fragment {
String song_Name, song_Dj, song_Dj_Email, song_Listeners, song_Genre, StringLargeImage;

TextView songName, songDj, songDjEmail, songListeners, songGenre;
ImageView largeImage;
    public musicProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_profile, container, false);

        songName = view.findViewById(R.id.tv_radio_song_profile);
        songDj = view.findViewById(R.id.tv_radio_dj_profile);
        songDjEmail = view.findViewById(R.id.tv_radio_song_dj_email_profile);
        songListeners = view.findViewById(R.id.tv_radio_listeners_profile);
        songGenre = view.findViewById(R.id.tv_radio_genre_profile);
        largeImage = view.findViewById(R.id.iv_radio_image_profile);

        if(getArguments() != null){
         /* bundle.putString("largeImage",StringLargeImage);*/

            song_Name = getArguments().getString("RadTitle");
            song_Dj = getArguments().getString("Dj");
            song_Dj_Email = getArguments().getString("DjEmail");
            song_Listeners = getArguments().getString("listener");
            song_Genre = getArguments().getString("genre");
            StringLargeImage = getArguments().getString("largeImage");
        }

        songName.setText(song_Name);
        songDj.setText(song_Dj);
        songDjEmail.setText(song_Dj_Email);
        songListeners.setText(song_Listeners);
        songGenre.setText(song_Genre);
        Picasso.get().load(StringLargeImage).into(largeImage);

        return view;
    }

}
