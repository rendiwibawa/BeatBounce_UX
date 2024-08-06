package com.example.beatbounce.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.beatbounce.R;
import com.example.beatbounce.SpaceItemDecoration;
import com.example.beatbounce.Studio;
import com.example.beatbounce.StudioAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioAdapter studioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studioAdapter = new StudioAdapter(getStudios());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));
    }

    private List<Studio> getStudios() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "BP Studio Dance - Jazz Dance",
                R.drawable.studio_dance_jazz,
                "Bogor",
                "Rp. 250.000 / jam",
                "4.5"
        ));

        studios.add(new Studio(
                "TI Studio Dance for Ballet",
                R.drawable.studio_dance_ballet,
                "Jakarta",
                "Rp. 345.000 / jam",
                "4.8"
        ));

        return studios;
    }
}
