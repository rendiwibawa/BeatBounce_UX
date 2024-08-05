package com.example.beatbounce.PembayaranPilihJam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.R;

import java.util.List;

// BookingDateAdapter.java
public class BookingDateAdapter extends RecyclerView.Adapter<BookingDateAdapter.BookingViewHolder> {

    private List<BookingDate> bookingList;
    private int selectedPosition = -1; // Updated to -1 to handle no selection
    private OnDateClickListener listener;

    public BookingDateAdapter(List<BookingDate> bookingList) {
        this.bookingList = bookingList;
    }

    public void setOnDateClickListener(OnDateClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new BookingViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingDate bookingItem = bookingList.get(position);
        holder.textView.setText(bookingItem.getText());

        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.white_bg_rounded_10);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.orange_bg_rounded_10);
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTanggal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(selectedPosition);
                    selectedPosition = getAdapterPosition();
                    notifyItemChanged(selectedPosition);

                    if (listener != null) {
                        listener.onDateClick(bookingList.get(selectedPosition).getHours());
                    }
                }
            });
        }
    }

    public interface OnDateClickListener {
        void onDateClick(List<Hour> hours);
    }
}
