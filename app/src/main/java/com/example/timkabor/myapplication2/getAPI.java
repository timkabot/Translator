package com.example.timkabor.myapplication2;

import android.text.Editable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Timkabor on 4/19/2017.
 */

public interface getAPI {
    @GET("api/v1.5/tr.json/translate?lang=en-ru")
    Call<Word> getWord(@Query("text") String word,
                       @Query("key")  String key);;
                        }
