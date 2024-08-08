package com.example.beatbounce.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.Detail.DetailActivity;
import com.example.beatbounce.R;

import java.util.ArrayList;
import java.util.List;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.StudioViewHolder> {

    private static List<Studio> studioListBesar;

    public StudioAdapter(List<Studio> studioList) {
        this.studioListBesar = studioList;
    }

    @NonNull
    @Override
    public StudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_besar, parent, false);
        return new StudioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudioViewHolder holder, int position) {
        Studio studio = studioListBesar.get(position);
        holder.imageView.setImageResource(studio.getImageResource());
        holder.textViewTitle.setText(studio.getName());
        holder.textViewPrice.setText(studio.getPrice());
        holder.textViewRating.setText(String.valueOf(studio.getRating()));
        holder.textViewLocation.setText(studio.getLocation());
    }

    @Override
    public int getItemCount() {
        return studioListBesar.size();
    }

    public static class StudioViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;
        public TextView textViewPrice;
        public TextView textViewRating;
        public TextView textViewLocation;

        public StudioViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.studioName);
            textViewPrice = itemView.findViewById(R.id.priceText);
            textViewLocation = itemView.findViewById(R.id.locationText);
            textViewRating = itemView.findViewById(R.id.ratingText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Studio clickedStudio = studioListBesar.get(position);
                        // Start DetailActivity with the clicked item's details
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DetailActivity.class);

                        intent.putExtra("imageResId", clickedStudio.getImageResource());
                        intent.putExtra("title", clickedStudio.getName());
                        intent.putExtra("price", clickedStudio.getPrice());
                        intent.putExtra("location", clickedStudio.getLocation());
                        intent.putExtra("rating", clickedStudio.getRating());
                        intent.putExtra("owner", clickedStudio.getOwner());
                        intent.putExtra("facilities", new ArrayList<>(clickedStudio.getFacilities()));
                        intent.putExtra("genres", new ArrayList<>(clickedStudio.getGenres()));

                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}