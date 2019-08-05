package com.example.collectionvisualizer;

import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("name")
    private String name;

    @SerializedName("oracle_text")
    private String oracleText;

    @SerializedName("mana_cost")
    private String manaCost;

    @SerializedName("type_line")
    private String typeLine;

    @SerializedName("image_uris")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public String getOracleText() {
        return oracleText;
    }

    public String getManaCost() {
        return manaCost;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
