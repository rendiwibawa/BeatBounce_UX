package com.example.beatbounce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.beatbounce.History.HistoryActivity;
import com.example.beatbounce.Home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StudioFavoritActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudioFavoritAdapter studioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio_favorit);

        recyclerView = findViewById(R.id.recycler_view_studios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Toolbar toolbar = new Toolbar(this);
//        toolbar.setTitle("Faforite");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        studioAdapter = new StudioFavoritAdapter(getStudiosFavorite());
        recyclerView.setAdapter(studioAdapter);

        int spacing = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacing));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_fafourite);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_fafourite:
                    return true;
                case R.id.bottom_history:
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.botton_account:
                    startActivity(new Intent(getApplicationContext(), StudioPencarianActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
            }
            return false;
        });
    }

    private List<Studio> getStudiosFavorite() {
        List<Studio> studios = new ArrayList<>();

        studios.add(new Studio(
                "Studio Ballroom Dance - XBY",
                R.drawable.ballrom,
                "Bekasi",
                "150.000",
                "4.8"
        ));

        studios.add(new Studio(
                "Studio BaletRoom",
                R.drawable.studio_dance_ballet,
                "Berlina",
                "355.000",
                "4.9"
        ));

        studios.add(new Studio(
                "Hiphop Room Dance",
                R.drawable.hiphop,
                "Bekasi",
                "250.000",
                "4.8"
        ));

        return studios;
    }
}
