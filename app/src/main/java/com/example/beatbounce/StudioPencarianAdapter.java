package com.example.beatbounce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.Detail.DetailActivity;

import java.util.List;

public class StudioPencarianAdapter extends RecyclerView.Adapter<StudioPencarianAdapter.StudioViewHolder> {
    private static List<Studio> studioList;

    public StudioPencarianAdapter(List<Studio> studios) {
        this.studioList = studios;
    }

    @NonNull
    @Override
    public StudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kotak_utama, parent, false);
        return new StudioViewHolder(view);
    }

    public void updateData(List<Studio> newStudioList) {
        this.studioList = newStudioList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull StudioViewHolder holder, int position) {
        Studio studio = studioList.get(position);
        holder.nameTextView.setText(studio.getName());
        holder.locationTextView.setText(studio.getLocation());
        holder.priceTextView.setText(studio.getPrice());
        holder.ratingTextView.setText(studio.getRating());
        holder.imageView.setImageResource(studio.getImageResourceId());

        holder.favoriteButton.setSelected(studio.isFavorite());

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = !holder.favoriteButton.isSelected();
                holder.favoriteButton.setSelected(isSelected);
                studio.setFavorite(isSelected);
                // Update only the favorite button state without affecting the entire item view
                holder.favoriteButton.setBackgroundResource(isSelected ? R.drawable.baseline_favorite_shadow_24 : R.drawable.baseline_favorite_red_24);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studioList.size();
    }

    public static class StudioViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;
        TextView priceTextView;
        TextView ratingTextView;
        ImageView imageView;
        Button favoriteButton;

        public StudioViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.studio_name);
            locationTextView = itemView.findViewById(R.id.studio_location);
            priceTextView = itemView.findViewById(R.id.studio_price);
            ratingTextView = itemView.findViewById(R.id.studio_rating);
            imageView = itemView.findViewById(R.id.studio_image);
            favoriteButton = itemView.findViewById(R.id.favorite_button_change);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)

                    {
                        Studio clickedItem = studioList.get(position);
                        // Start DetailActivity with the clicked item's details
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("imageResId", clickedItem.getImageResourceId());
                        intent.putExtra("title", clickedItem.getName());
                        intent.putExtra("price", clickedItem.getPrice());
                        intent.putExtra("location", clickedItem.getLocation());
                        intent.putExtra("rating", clickedItem.getRating());
                        context.startActivity(intent);
                    }
                }
            });


        }


    }
}
