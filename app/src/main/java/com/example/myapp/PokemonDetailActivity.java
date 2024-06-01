package com.example.myapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

        // Extract the Pokémon ID from the URL
        String[] urlParts = pokemonUrl.split("/");
        String pokemonId = urlParts[urlParts.length - 1];

        // Load the Pokémon image
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png";
        Glide.with(this).load(imageUrl).into(pokemonImageView);
    }
}
