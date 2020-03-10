package com.example.demoapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.demoapplication.API.API;
import com.example.demoapplication.Modal.Song;
import com.example.demoapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  TextView  textViewResult;
    List<Song> songs;

    RecyclerView songListrv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       textViewResult = findViewById(R.id.texttest);
       songListrv=findViewById(R.id.rvSongs);
       songListrv.setLayoutManager(new LinearLayoutManager(this));
       getSongs();




    }


    public  void getSongs()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://intern.mandin.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API songsAPI = retrofit.create(API.class);

        Call<List<Song>> call = songsAPI.getSongs();

        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                // If Response Is Succesful: Data Is Sent to Recycler View
                 songs = response.body();
                 songListrv.setAdapter(new SongsAdapter(songs,MainActivity.this));


            }


            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });



    }
}







