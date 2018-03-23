package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.udacity.vjauckus.jokerviewer.JokerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment  implements JokerAsyncTask.JokeCallback{

    Button showJokeButton;
    String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        showJokeButton = (Button) root.findViewById(R.id.bt_show_joke);

       AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
       AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        showJokeButton.setOnClickListener(new View.OnClickListener() {
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

        mJoke = results;
        Log.v(MainActivityFragment.class.getSimpleName(), "Joke: "+results);

        Intent startJokerView = new Intent(getContext(), JokerView.class);
        startJokerView.putExtra("joke", results);
        startActivity(startJokerView);
    }

}
