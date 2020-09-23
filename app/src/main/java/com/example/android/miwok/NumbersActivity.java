package com.example.android.miwok;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_numbers);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "ஒன்று", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "இரண்டு", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "மூன்று", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "நான்கு", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "ஐந்து", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "ஆறு", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "ஏழு", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "எட்டு", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "ஒன்பது", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "பத்து", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(this,  words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId() );

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
