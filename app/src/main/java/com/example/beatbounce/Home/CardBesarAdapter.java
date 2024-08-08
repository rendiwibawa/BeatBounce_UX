package com.example.beatbounce.Home;

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
import com.example.beatbounce.R;

import java.util.List;

public class CardBesarAdapter extends RecyclerView.Adapter<CardBesarAdapter.CardBesarViewHolder> {
    private static List<CardBesarItem> cardBesarItemList;

    public CardBesarAdapter(List<CardBesarItem> cardBesarItemList) {
        this.cardBesarItemList = cardBesarItemList;
    }

    @NonNull
    @Override
    public CardBesarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kotak_utama, parent, false);
        return new CardBesarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardBesarViewHolder holder, int position) {
        CardBesarItem cardBesarItem = cardBesarItemList.get(position);
        holder.imageView.setImageResource(cardBesarItem.getImageResId());
        holder.textViewTitle.setText(cardBesarItem.getTitle());
        holder.textViewPrice.setText(cardBesarItem.getPrice());
        holder.textViewLocation.setText(cardBesarItem.getLocation());
        holder.textViewRating.setText(cardBesarItem.getRating());

        holder.favoriteButton.setSelected(cardBesarItem.isFavorite());

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = !holder.favoriteButton.isSelected();
                holder.favoriteButton.setSelected(isSelected);
                cardBesarItem.setFavorite(isSelected);
                // Update only the favorite button state without affecting the entire item view
                holder.favoriteButton.setBackgroundResource(isSelected ? R.drawable.baseline_favorite_red_24 : R.drawable.baseline_favorite_shadow_24);


            }
        });

    }

    @Override
    public int getItemCount() {
        return cardBesarItemList.size();
    }

    public static class CardBesarViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;
        public TextView textViewPrice;
        public TextView textViewLocation;
        public TextView textViewRating;

        Button favoriteButton;

        public CardBesarViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.studio_image);
            textViewTitle = itemView.findViewById(R.id.studio_name);
            textViewPrice = itemView.findViewById(R.id.studio_price);
            textViewLocation = itemView.findViewById(R.id.studio_location);
            textViewRating = itemView.findViewById(R.id.studio_rating);
            favoriteButton = itemView.findViewById(R.id.favorite_button_change);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        CardBesarItem clickedItem = cardBesarItemList.get(position);
                        // Start DetailActivity with the clicked item's details
                        Context context = v.getContext();
//                        Intent intent = new Intent(context, DetailActivity.class);
                        Intent intent = new Intent(context, DetailActivity.class);

                        intent.putExtra("imageResId", clickedItem.getImageResId());
                        intent.putExtra("title", clickedItem.getTitle());
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
