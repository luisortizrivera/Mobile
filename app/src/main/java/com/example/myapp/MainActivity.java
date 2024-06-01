package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.adapter.PokemonAdapter;
import com.example.myapp.model.Pokemon;
import com.example.myapp.network.PokeApiService;
import com.example.myapp.network.PokemonResponse;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private ListView pokemonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pokemonListView = findViewById(R.id.pokemon_list_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService pokeApiService = retrofit.create(PokeApiService.class);
        Call<PokemonResponse> call = pokeApiService.getPokemonList(8);

        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    List<Pokemon> pokemonList = Objects.requireNonNull(response.body()).getResults();
                    PokemonAdapter adapter = new PokemonAdapter(MainActivity.this, pokemonList);
                    pokemonListView.setAdapter(adapter);
                    pokemonListView.setOnItemClickListener((parent, view, position, id) -> {
                        Pokemon selectedPokemon = pokemonList.get(position);
                        Intent intent = new Intent(MainActivity.this, PokemonDetailActivity.class);
                        intent.putExtra("pokemon_name", selectedPokemon.getName());
                        intent.putExtra("pokemon_url", selectedPokemon.getUrl());
                        startActivity(intent);
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}