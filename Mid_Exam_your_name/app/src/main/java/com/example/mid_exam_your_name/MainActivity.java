package com.example.mid_exam_your_name;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "key_current_index";

    private TextView tvCounter;
    private TextView tvTitle;
    private TextView tvArtistYear;
    private ImageView ivArtwork;
    private Button btnPrevious;
    private Button btnNext;

    private List<Artwork> artworks;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        ivArtwork = findViewById(R.id.ivArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtistYear);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        loadArtworks();

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateUI();

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < artworks.size() - 1) {
                currentIndex++;
                updateUI();
            }
        });

        ivArtwork.setOnClickListener(v -> {
            Artwork selected = artworks.get(currentIndex);
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("EXTRA_TITLE", selected.getTitle());
            intent.putExtra("EXTRA_ARTIST", selected.getArtist());
            intent.putExtra("EXTRA_YEAR", selected.getYear());
            intent.putExtra("EXTRA_URL", selected.getUrl());
            startActivity(intent);
        });
    }

    private void loadArtworks() {
        artworks = new ArrayList<>();
        artworks.add(new Artwork(R.drawable.art1, "The Starry Night", "Vincent van Gogh", "1889", "https://www.moma.org/collection/works/79802"));
        artworks.add(new Artwork(R.drawable.art2, "The Great Wave", "Katsushika Hokusai", "1831", "https://www.metmuseum.org/art/collection/search/45434"));
        artworks.add(new Artwork(R.drawable.art3, "The Persistence of Memory", "Salvador Dalí", "1931", "https://www.moma.org/collection/works/79018"));
        artworks.add(new Artwork(R.drawable.art4, "Wanderer above the Sea of Fog", "Caspar David Friedrich", "1818", "https://online-sammlung.hamburger-kunsthalle.de/en/objekt/HK-5161"));
        artworks.add(new Artwork(R.drawable.art5, "Mona Lisa", "Leonardo da Vinci", "1503", "https://www.louvre.fr"));
        artworks.add(new Artwork(R.drawable.art6, "Girl with a Pearl Earring", "Johannes Vermeer", "1665", "https://www.mauritshuis.nl"));
    }

    private void updateUI() {
        Artwork current = artworks.get(currentIndex);
        tvCounter.setText((currentIndex + 1) + " / " + artworks.size());
        ivArtwork.setImageResource(current.getImageResId());
        tvTitle.setText(current.getTitle());
        tvArtistYear.setText(current.getArtist() + " (" + current.getYear() + ")");

        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < artworks.size() - 1);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }
}