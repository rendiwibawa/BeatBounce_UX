package com.example.beatbounce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardBesarAdapter extends RecyclerView.Adapter<CardBesarAdapter.CardBesarViewHolder> {
    private static List<CardBesarItem> cardBesarItemList;

    public CardBesarAdapter(List<CardBesarItem> cardBesarItemList) {
        this.cardBesarItemList = cardBesarItemList;
    }

    @NonNull
    @Override
    public CardBesarAdapter.CardBesarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_besar, parent, false);
        return new CardBesarAdapter.CardBesarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardBesarAdapter.CardBesarViewHolder holder, int position) {
        CardBesarItem cardBesarItem = cardBesarItemList.get(position);
        holder.imageView.setImageResource(cardBesarItem.getImageResId());
        holder.textViewTitle.setText(cardBesarItem.getTitle());
        holder.textViewPrice.setText(cardBesarItem.getPrice());
        holder.textViewLocation.setText(cardBesarItem.getLocation());
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

        public CardBesarViewHolder(View itemView) {
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
                        CardBesarItem clickedItem = cardBesarItemList.get(position);
                        // if clicked go to booking page
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BookingActivity.class);
                        context.startActivity(intent);

                    }
                }
            });
        }
    }
}