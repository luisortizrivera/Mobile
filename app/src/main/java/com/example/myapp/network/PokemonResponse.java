package com.example.myapp.network;

import com.example.myapp.model.Pokemon;

import java.util.List;

public class PokemonResponse {
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }
}