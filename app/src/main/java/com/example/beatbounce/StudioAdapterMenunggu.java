package com.example.beatbounce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudioAdapterMenunggu extends RecyclerView.Adapter<StudioAdapterMenunggu.ViewHolder> {

    private List<Studio> studioList;

    public StudioAdapterMenunggu(List<Studio> studioList) {
        this.studioList = studioList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kotak_menunggu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Studio studio = studioList.get(position);
        holder.bind(studio);
    }

    @Override
    public int getItemCount() {
        return studioList.size();
    }

    public void updateData(List<Studio> newStudioList) {
        this.studioList = newStudioList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView studioImage;
        private TextView studioName;
        private TextView studioLocation;
        private TextView studioPrice;
        private TextView studioRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studioImage = itemView.findViewById(R.id.studio_image);
            studioName = itemView.findViewById(R.id.studio_name);
            studioLocation = itemView.findViewById(R.id.studio_location);
            studioPrice = itemView.findViewById(R.id.studio_price);
            studioRating = itemView.findViewById(R.id.studio_rating);
        }

        public void bind(Studio studio) {
            if (studioImage != null) {
                studioImage.setImageResource(studio.getImageResourceId());
            }
            studioName.setText(studio.getName());
            studioLocation.setText(studio.getLocation());
            studioPrice.setText(studio.getPrice());
            studioRating.setText(studio.getRating());
        }
    }
}
