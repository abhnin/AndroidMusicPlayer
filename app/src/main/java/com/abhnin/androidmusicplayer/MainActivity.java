package com.abhnin.androidmusicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.abhnin.androidmusicplayer.Characters;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://jsonplaceholder.typicode.com/")
                //.baseUrl(APP_URL)
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //JsonPlaceholderAPI jsonPlaceHolderApi = retrofit.create(JsonPlaceholderAPI.class);
        //JsonBaseAPI jsonPlaceHolderApi = retrofit.create(JsonBaseAPI.class);
        RestAPI jsonPlaceHolderApi = retrofit.create(RestAPI.class);

        Call<AnimeCharactersStaffList> call = jsonPlaceHolderApi.getCharacters();

        call.enqueue(new Callback<AnimeCharactersStaffList>() {
            @Override
            public void onResponse(Call<AnimeCharactersStaffList> call, Response<AnimeCharactersStaffList> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }

                AnimeCharactersStaffList posts = response.body();


                    List<Characters> quotes = posts.getCharacter();
                    for (Characters quote: quotes){
                        String content = "";
                        content += "Author: " + quote.getName() + "\n\n";
                        textView .append(content);
                    }

            }

            @Override
            public void onFailure(Call<AnimeCharactersStaffList> call, Throwable t) {
                textView .setText(t.getMessage());
                System.out.println(t.getLocalizedMessage());
            }
        });


    }
}
