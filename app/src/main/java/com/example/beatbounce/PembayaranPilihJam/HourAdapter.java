package com.example.beatbounce.PembayaranPilihJam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.R;

import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private List<Hour> hourList;
    private List<Hour> selectedHours = new ArrayList<>();
    private double totalPrice = 0.0; // Declare totalPrice as a field
    private TextView totalPriceTextView; // Declare totalPriceTextView as a field

    public HourAdapter(List<Hour> hourList, TextView totalPriceTextView) {
        this.hourList = hourList;
        this.totalPriceTextView = totalPriceTextView; // Initialize totalPriceTextView

    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hour, parent, false);
        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        Hour hour = hourList.get(position);
        holder.timeTextView.setText(hour.getTime());
        holder.priceTextView.setText(String.format("%.3f", hour.getPrice()));


        // Mendapatkan LinearLayout dari itemView
        LinearLayout outerLayout = holder.itemView.findViewById(R.id.outerLinearLayout);

        if (!hour.isAvailable()) {
            outerLayout.setBackgroundResource(R.drawable.background_gray); // Set gray background
            holder.itemView.setEnabled(false); // Disable item
        } else {
            outerLayout.setBackgroundResource(R.drawable.orange_bg_rounded_15); // Set original background
            holder.itemView.setEnabled(true); // Enable item
        }


        holder.itemView.setOnClickListener(v -> {
            if (hour.isAvailable()) {
                if (selectedHours.contains(hour)) {
                    outerLayout.setBackgroundResource(R.drawable.orange_bg_rounded_15);
                    holder.timeTextView.setTextColor(holder.itemView.getResources().getColor(R.color.white));
                    holder.priceTextView.setTextColor(holder.itemView.getResources().getColor(R.color.white));
                    selectedHours.remove(hour);
                    holder.itemView.setSelected(false);
                    // Subtract the price of the hour from the total price
                    totalPrice -= hour.getPrice();
                } else {
                    outerLayout.setBackgroundResource(R.drawable.background_green);
                    holder.timeTextView.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary));
                    holder.priceTextView.setTextColor(holder.itemView.getResources().getColor(R.color.colorPrimary)); // Set text color to primary
                    selectedHours.add(hour);
                    holder.itemView.setSelected(true);
                    // Add the price of the hour to the total price
                    totalPrice += hour.getPrice();
                }
                // Update the totalPriceTextView with the new total price
                totalPriceTextView.setText(String.format("Rp. %.3f", totalPrice));
            }
        });
    }

    @Override
    public int getItemCount() {
        return hourList.size();
    }

    // Method to update data
    public void updateData(List<Hour> filteredList) {
        hourList = filteredList;
        notifyDataSetChanged();
    }

    public List<Hour> getSelectedWatches() {
        return selectedHours;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        TextView priceTextView;

        public HourViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.watch_name);
            priceTextView = itemView.findViewById(R.id.watch_price);
        }
    }
    public void resetTotalPrice() {
        totalPrice = 0.0;
        totalPriceTextView.setText(String.format("Rp. %.3f", totalPrice));
    }

}
