package com.example.android.miwok;

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

public class PhrasesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "நீங்கள் எங்கே போகிறீர்கள்?", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "உங்கள் பெயர் என்ன?", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "என் பெயர்...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "நீங்கள் எப்படி உணருகிறீர்கள்?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "நான் நன்றாக உணர்கிறேன்.", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","நீ வருகிறாயா?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "ஆம், நான் வருகிறேன்.", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "நான் வருகிறேன்.", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "போகலாம்.", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "இங்கே வா.", R.raw.phrase_come_here));


        WordAdapter adapter = new WordAdapter(this,  words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId() );
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
