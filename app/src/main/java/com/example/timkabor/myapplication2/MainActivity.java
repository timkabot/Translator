package com.example.timkabor.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText rusWord;
    TextView kazword;
    getAPI service;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rusWord = (EditText) findViewById(R.id.russianWord);
                System.out.println(rusWord.getText().toString());
                Call<Word> word = service.getWord(rusWord.getText().toString(),getString(R.string.api_key));
                word.enqueue(new Callback<Word>() {
                    @Override
                    public void onResponse(Call<Word> call, Response<Word> response) {
                        if (response.isSuccessful()) {
                            Word word = response.body();
                            kazword = (TextView) findViewById(R.id.kazakhWord);
                            kazword.setText(word.text.toString());
                        } else {
                            int statusCode = response.code();
                            ResponseBody errorBody = response.errorBody();}
                    }

                    @Override
                    public void onFailure(Call<Word> call, Throwable t) {

                    }
                });
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(getAPI.class);

    }
}
