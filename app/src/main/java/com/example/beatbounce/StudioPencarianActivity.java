package com.example.beatbounce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudioPencarianActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioPencarianAdapter studioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio_pencarian);

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studioAdapter = new StudioPencarianAdapter(getStudios());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));
    }

    private List<Studio> getStudios() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "Studio Ballroom Dance - XBY",
                R.drawable.studio_dance_jazz,
                "Bekasi",
                "150.000",
                "4.8"
        ));

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_ballet,
                "Jakarta",
                "345.000",
                "4"
        ));

        return studios;
    }
}
