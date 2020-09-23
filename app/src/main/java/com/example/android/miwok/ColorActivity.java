package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "சிவப்பு", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "பச்சை", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "பழுப்பு", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "சாம்பல்", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "கருப்பு", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "வெள்ளை", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("yellow", "மஞ்சள்", R.drawable.color_mustard_yellow, R.raw.color_yellow));



        WordAdapter adapter = new WordAdapter(this,  words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(ColorActivity.this, word.getAudioResourceId() );
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
}
