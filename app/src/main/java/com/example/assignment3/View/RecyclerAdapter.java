package com.example.assignment3.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.Model.Channel;
import com.example.assignment3.Model.RadioPojo;
import com.example.assignment3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> implements Filterable {
    private Context context;
    RadioPojo dataSet;
    private List<Channel> channelsFull;

    public RecyclerAdapter(RadioPojo dataSet, Context context) {
        this.context = context;
        this.dataSet = dataSet;
        channelsFull = new ArrayList<>(dataSet.channels);
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


    //todo: need to fix adapter variable to fix search filter
  @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Channel> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(channelsFull);
            }else{
                String filteredPattern = constraint.toString().toLowerCase().trim();

                for (Channel item : channelsFull){
                    if(item.getDj().toLowerCase().contains(filteredPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.channels.clear();
            dataSet.channels.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView radioSongName, radioSongDj, radioSongDescription;
        ImageView radioImage;
        public final LinearLayout songItemView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            songItemView = itemView.findViewById(R.id.cv_song_info);

            radioSongName = itemView.findViewById(R.id.tv_radio_song_name);
            radioSongDj = itemView.findViewById(R.id.tv_radio_song_dj);
            radioSongDescription = itemView.findViewById(R.id.tv_radio_song_description);
            radioImage = itemView.findViewById(R.id.iv_radio_image);
            songItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String song_Name, song_Dj, song_Dj_Email, song_Listeners, song_Genre, StringLargeImage;
            int mPosition = getLayoutPosition();

            song_Name = dataSet.channels.get(mPosition).getTitle();
            song_Dj = dataSet.channels.get(mPosition).getDj();
            song_Dj_Email = dataSet.channels.get(mPosition).getDjmail();
            song_Listeners = dataSet.channels.get(mPosition).getListeners();
            song_Genre = dataSet.channels.get(mPosition).getGenre();
            StringLargeImage = dataSet.channels.get(mPosition).getLargeimage();

            Toast.makeText(context,"Item Clicked!", Toast.LENGTH_LONG).show();

            musicProfileFragment profileFragment = new musicProfileFragment();
            AppCompatActivity activity = (AppCompatActivity) v.getContext();

            Bundle bundle = new Bundle();
            bundle.putString("RadTitle", song_Name);
            bundle.putString("Dj", song_Dj);
            bundle.putString("DjEmail",song_Dj_Email);
            bundle.putString("listener",song_Listeners);
            bundle.putString("genre",song_Genre);
            bundle.putString("largeImage",StringLargeImage);

            profileFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = activity
                    .getSupportFragmentManager().beginTransaction();


            fragmentTransaction.setCustomAnimations
                    (R.anim.enter_bottom_to_top,R.anim.exit_bottom_to_top,
                            R.anim.enter_top_to_bottom,R.anim.exit_top_to_bottom)
                    .replace(R.id.fragment_container_layout,profileFragment,null)
                    .addToBackStack(null).commit();
        }
    }
/*    public void updateList(List<Channel> newList){
        dataSet.channels = new ArrayList<>();
        dataSet.channels.addAll(newList);
        notifyDataSetChanged();
    }*/

}
