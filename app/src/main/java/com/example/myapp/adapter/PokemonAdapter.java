package com.example.myapp.adapter;

// src/main/java/com/example/pokemonapp/adapter/PokemonAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapp.R;
import com.example.myapp.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {
    public PokemonAdapter(@NonNull Context context, @NonNull List<Pokemon> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pokemon_item, parent, false);
        }

        Pokemon pokemon = getItem(position);
        TextView nameTextView = convertView.findViewById(R.id.pokemon_name);
        nameTextView.setText(pokemon.getName());

        return convertView;
    }
}

