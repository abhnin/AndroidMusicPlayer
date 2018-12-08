package com.abhnin.androidmusicplayer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestAPI {
    @GET("anime/1/characters_staff")
    Call<AnimeCharactersStaffList> getCharacters();
}
