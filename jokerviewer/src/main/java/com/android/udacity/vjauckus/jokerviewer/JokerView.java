package com.android.udacity.vjauckus.jokerviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by veronika on 15.03.18.
 */

public class JokerView extends AppCompatActivity{
    private TextView mJokeLabel;

    private TextView mJokeTextView;
    public final String GET_JOKE_CONSTANT = "joke";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);
       // ButterKnife.bind(this);

        mJokeLabel = findViewById(R.id.joke_label);
       // mJokeLabel.setText("I am in Main");

        Intent  intentThatStartThisActivity = getIntent();
        String joke;
        if (intentThatStartThisActivity!=null){

            joke = intentThatStartThisActivity.getStringExtra(GET_JOKE_CONSTANT);
            if (!joke.equals("")){
                mJokeTextView = findViewById(R.id.tv_show_joke);
                mJokeTextView.setText(joke);

            }
            else {
                Toast.makeText(this, R.string.joke_get_error, Toast.LENGTH_SHORT).show();
            }
        }



    }
}
