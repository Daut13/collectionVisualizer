package com.example.collectionvisualizer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ScryfallApi {

    @GET("cards/named")
    Call<Card> getCards(
            @Query("fuzzy") String fuzzy
    );
}
