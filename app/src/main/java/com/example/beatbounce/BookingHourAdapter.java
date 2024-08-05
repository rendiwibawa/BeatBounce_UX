package com.example.beatbounce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingHourAdapter extends RecyclerView.Adapter<BookingHourAdapter.BookingHourViewHolder> {

    private static List<BookingHour> bookingHourList;
    private boolean[] selectedHours;

    public BookingHourAdapter(List<BookingHour> bookingHourList) {
        this.bookingHourList = bookingHourList;
        this.selectedHours = new boolean[bookingHourList.size()];
    }

    @NonNull
    @Override
    public BookingHourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking_hour, parent, false);
        return new BookingHourViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookingHourViewHolder holder, int position) {
        BookingHour bookingHour = bookingHourList.get(position);
        holder.textViewHour.setText(bookingHour.getHour());
        holder.textViewPriceHour.setText(String.valueOf(bookingHour.getPriceHour()));
        holder.checkBoxIsAvailable.setChecked(bookingHour.isAvailable());

        // Change the background color based on availability and selection state
        if (bookingHour.isAvailable()) {
            if (selectedHours[position]) {
                holder.outerLinearLayout.setBackgroundResource(R.drawable.green_bg_rounded_15);
            } else {
                holder.outerLinearLayout.setBackgroundResource(R.drawable.orange_bg_rounded_15);
            }
            holder.outerLinearLayout.setClickable(true);
            holder.outerLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedHours[position] = !selectedHours[position]; // Toggle the boolean value in the array
                    notifyDataSetChanged();
                }
            });
        } else {
            holder.outerLinearLayout.setBackgroundResource(R.drawable.light_gray_bg_rounded_15);
            holder.outerLinearLayout.setClickable(false);
            holder.outerLinearLayout.setOnClickListener(null);
        }

        // Calculate the total price based on selected hours
        long totalPrice = 0;
        for (int i = 0; i < bookingHourList.size(); i++) {
            if (bookingHourList.get(i).isAvailable() && selectedHours[i]) {
                totalPrice += bookingHourList.get(i).getPriceHour();
            }
        }

        // Update the total price in the BookingActivity
        ((BookingActivity) holder.itemView.getContext()).updateTotalPrice(totalPrice);
    }



// ... other code ...

    @Override
    public int getItemCount() {
        return bookingHourList.size();
    }

    public void updateData(List<BookingHour> filteredList) {
        bookingHourList = filteredList;
        notifyDataSetChanged();

    }

    public class BookingHourViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHour;
        TextView textViewPriceHour;
        CheckBox checkBoxIsAvailable;
        LinearLayout outerLinearLayout;

        public BookingHourViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHour = itemView.findViewById(R.id.textViewHour);
            textViewPriceHour = itemView.findViewById(R.id.textViewPriceHour);
            checkBoxIsAvailable = itemView.findViewById(R.id.checkBoxIsAvailable);
            outerLinearLayout = itemView.findViewById(R.id.outerLinearLayout); // replace with your actual LinearLayout id

            outerLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBoxIsAvailable.setChecked(!checkBoxIsAvailable.isChecked());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BookingHour bookingHour = bookingHourList.get(getAdapterPosition());
                    bookingHour.setAvailable(!bookingHour.isAvailable());
                    notifyDataSetChanged();
                }
            });
        }
    }

}