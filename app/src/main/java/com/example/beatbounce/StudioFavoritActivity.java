package com.example.beatbounce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StudioFavoritActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioAdapter studioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio_favorit);

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studioAdapter = new StudioAdapter(getStudiosFavorite());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));
    }

    private List<Studio> getStudiosFavorite() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "Studio Ballroom Dance - XBY",
                R.drawable.studio_dance_jazz,
                "Bekasi",
                "Rp. 150.000 / jam",
                4.8f
        ));

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_ballet,
                "Jakarta",
                "Rp. 345.000 / jam",
                4.5f
        ));

        studios.add(new Studio(
                "Hiphop Dance",
                R.drawable.studio_dance_jazz,
                "Jakarta",
                "Rp. 255.000 / jam",
                4f
        ));

        return studios;
    }
}
