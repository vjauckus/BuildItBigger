package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.udacity.vjauckus.jokerviewer.JokerView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment  implements JokerAsyncTask.JokeCallback{

    public final String SEND_JOKE_CONSTANT = "joke";

    Button mShowJokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mShowJokeButton = (Button) root.findViewById(R.id.bt_show_joke);

        mShowJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke(v);
            }
        });


        return root;
    }

    public void tellJoke(View view) {
       // Toast.makeText(getContext(), "start AsyncTask", Toast.LENGTH_SHORT).show();

        new JokerAsyncTask(getContext(), MainActivityFragment.this, true).execute();

    }
    @Override
    public void sendResultToActivity(String results){

        Intent startJokerView = new Intent(getContext(), JokerView.class);
        startJokerView.putExtra(SEND_JOKE_CONSTANT, results);
        startActivity(startJokerView);
    }

}
