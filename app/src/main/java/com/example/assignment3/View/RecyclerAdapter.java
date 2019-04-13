package com.example.assignment3.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment3.Model.RadioPojo;
import com.example.assignment3.R;
import com.squareup.picasso.Picasso;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {
    private Context context;
    RadioPojo dataSet;
    RadioPojo dataSetFull;

    public RecyclerAdapter(RadioPojo dataSet, Context context) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from
                (viewGroup.getContext()).inflate
                (R.layout.song_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.CustomViewHolder customViewHolder, int position) {
        customViewHolder.radioSongName.setText(dataSet.channels.get(position).getTitle());
        customViewHolder.radioSongDj.setText(dataSet.channels.get(position).getDj());
        customViewHolder.radioSongDescription.setText(dataSet.channels.get(position).getDescription());

        Picasso.get().load(dataSet.channels.get(position).getImage()).into(customViewHolder.radioImage);
    }

    @Override
    public int getItemCount() {
        return dataSet.channels.size() > 0 ? dataSet.channels.size():0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView radioSongName, radioSongDj, radioSongDescription;
        ImageView radioImage;
        public final CardView songItemView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            songItemView = itemView.findViewById(R.id.cv_song_info);

            radioSongName = itemView.findViewById(R.id.tv_radio_song_name);
            radioSongDj = itemView.findViewById(R.id.tv_radio_song_dj);
            radioSongDescription = itemView.findViewById(R.id.tv_radio_song_description);
            radioImage = itemView.findViewById(R.id.iv_radio_image);
        }
    }
}
