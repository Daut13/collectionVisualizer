package com.example.collectionvisualizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    ImageView imageView;
    String cardUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textView);
        imageView = findViewById(R.id.cardImage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scryfall.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScryfallApi scryfallApi = retrofit.create(ScryfallApi.class);

        //Card name for search
        String cardName = "Peek";

        //Grabs the card with given card name
        Call<Card> call = scryfallApi.getCards(cardName);

        /*
          This calls for the card and checks if there is an
          error when searching.
          If response is unsuccessful(meaning usually a page 404, it will respond unsuccessful.
          If response is successful, it will call and display the card information.
          If response is given an error, it will respond response on failure.
        */
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code() + "\nRESPONSE UNSUCCESSFUL");
                    return;
                }
                Card card = response.body();

                /*for (Card card : cards) {*/
                    textViewResult.setText("");
                    String content = "";
                    content += "Name: " + card.getName() + "\n";
                    content += "Mana Cost: " + card.getManaCost() + "\n";
                    content += "Type Line: " + card.getTypeLine() + "\n";
                    content += "Oracle Text: " + card.getOracleText() + "\n";
                    /*cardUrl*/ content += card.getImageUrl();

                    //Load image from card URL
                    loadImageFromUrl(cardUrl);

                    textViewResult.append(content);
                //}
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                textViewResult.setText(t.getMessage() + "\nRESPONSE ON FAILURE");
            }
        });
    }

    private void loadImageFromUrl(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round) //if an error
                .into(imageView, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {
                        //placeholder
                    }

                    @Override
                    public void onError() {
                        //placeholder
                    }
                });
    }
}
