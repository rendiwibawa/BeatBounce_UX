package com.example.beatbounce.Detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beatbounce.R;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private List<String> genreNames;

    public GenreAdapter(List<String> genreNames) {
        this.genreNames = genreNames;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_layout, parent, false);
        return new GenreViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        String genreName = genreNames.get(position);
        holder.textView.setText(genreName);
    }

    @Override
    public int getItemCount() {
        return genreNames.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public GenreViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.genre_name);
        }
    }
}