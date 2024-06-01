package com.example.myapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService {
    @GET("pokemon")
    Call<PokemonResponse> getPokemonList(@Query("limit") int limit);
}


