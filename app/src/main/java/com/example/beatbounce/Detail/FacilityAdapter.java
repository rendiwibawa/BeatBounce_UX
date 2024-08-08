package com.example.beatbounce.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.R;

import java.util.List;

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder> {

    private List<Integer> facilityImages;

    public FacilityAdapter(List<Integer> facilityImages) {
        this.facilityImages = facilityImages;
    }

    @NonNull
    @Override
    public FacilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.facility_layout, parent, false);
        return new FacilityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityViewHolder holder, int position) {
        int facilityImage = facilityImages.get(position);
        holder.imageView.setImageResource(facilityImage);
    }

    @Override
    public int getItemCount() {
        return facilityImages.size();
    }

    public static class FacilityViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public FacilityViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.facility_image);
        }
    }
}