package com.android.udacity.vjauckus.jokerviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by veronika on 15.03.18.
 */

public class JokerView extends AppCompatActivity{
    private TextView mJokeLabel;

    private TextView mJokeTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);
        ButterKnife.bind(this);

        mJokeLabel = findViewById(R.id.joke_label);
        mJokeLabel.setText("I am in Main");

        Intent  intentThatStartThisActivity = getIntent();
        String joke;
        if (intentThatStartThisActivity!=null){

            joke = intentThatStartThisActivity.getStringExtra("joke");
            if (!joke.equals("")){
                mJokeTextView = (TextView) findViewById(R.id.tv_show_joke);
                mJokeTextView.setText(joke);

            }
        }



    }
}
