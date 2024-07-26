package com.example.beatbounce;

// BookingAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingDateAdapter extends RecyclerView.Adapter<BookingDateAdapter.BookingViewHolder> {

    private List<BookingDate> bookingList;
    private int selectedPosition = 0; // Add this line

    public BookingDateAdapter(List<BookingDate> bookingList) {
        this.bookingList = bookingList;
    }

    private OnDateClickListener listener; // Add this line

    public void setOnDateClickListener(OnDateClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking_date, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingDate bookingItem = bookingList.get(position);
        holder.textView.setText(bookingItem.getText());


        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.white_bg_rounded_10); // selected date is white
        } else {
            holder.itemView.setBackgroundResource(R.drawable.orange_bg_rounded_10); // other dates are default color
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

                    // Call the listener
                    if (listener != null) {
                        listener.onDateClick(bookingList.get(selectedPosition).getHours());
                    }
                }
            });
        }
    }
    public void updateData(List<BookingDate> newList) {
        this.bookingList = newList;
        notifyDataSetChanged();
    }
    public interface OnDateClickListener {
        void onDateClick(List<BookingHour> hours);
    }
}