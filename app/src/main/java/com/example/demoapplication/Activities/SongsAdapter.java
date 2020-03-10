package com.example.demoapplication.Activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoapplication.Modal.Song;
import com.example.demoapplication.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import static com.example.demoapplication.R.drawable.ic_pause_circle_filled_black_24dp;
import static com.example.demoapplication.R.drawable.play_button;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {

    MediaPlayer mp = new MediaPlayer();
    List<Song> songs;
    Context mcontext;



    public  SongsAdapter(List<Song> songs, Context mcontext)
    {
      this.songs=songs;
      this.mcontext=mcontext;

    }


    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.song_single_item,parent,false);
        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongsViewHolder songsViewHolder, int i) {
        final int position=i;
        songsViewHolder.tvSongName.setText(songs.get(i).getSong());
        songsViewHolder.tvArtists.setText(songs.get(i).getArtists());
        Picasso.get().load(songs.get(i).getCover_image()).into(songsViewHolder.imgCover);

          songsViewHolder.imgBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                songsViewHolder.imgBtn.setImageDrawable(mcontext.getDrawable(ic_pause_circle_filled_black_24dp));
                try {

                     playSong(songs.get(position).getUrl(),position);
                   } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    void playSong(String url,int position) throws IOException {
        int preIndex=position;
         if(mp.isPlaying())
         {
              mp.reset();
             // mp.stop();
             //mp.release();
             //mp=null;
//             ;
         }
        mp.setDataSource(url);
        mp.prepare();
        mp.start();
//       song.stop();
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder{
          ImageView imgCover,imgBtn;
          TextView tvSongName,tvArtists;


        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCover=itemView.findViewById(R.id.song_cover_image);
            tvSongName=itemView.findViewById(R.id.song_name);
            tvArtists=itemView.findViewById(R.id.artist_name);
            imgBtn=itemView.findViewById(R.id.img_play_button);

        }
    }
}
