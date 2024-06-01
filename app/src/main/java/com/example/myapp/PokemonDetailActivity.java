package com.example.myapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class PokemonDetailActivity extends AppCompatActivity {
    private ImageView pokemonImageView;
    private TextView pokemonNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        pokemonImageView = findViewById(R.id.pokemon_image);
        pokemonNameTextView = findViewById(R.id.pokemon_name);

        String pokemonName = getIntent().getStringExtra("pokemon_name");
        String pokemonUrl = getIntent().getStringExtra("pokemon_url");

        pokemonNameTextView.setText(pokemonName);

        String[] urlParts = Objects.requireNonNull(pokemonUrl).split("/");
        String pokemonId = urlParts[urlParts.length - 1];

        String imageUrl = getString(R.string.gifs_api_base_url) + pokemonId + ".gif";
        Glide.with(this).load(imageUrl).into(pokemonImageView);
    }
}

