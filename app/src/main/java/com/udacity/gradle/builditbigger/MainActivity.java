package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.udacity.vjauckus.jokerviewer.JokerView;


public class MainActivity extends AppCompatActivity implements JokerAsyncTask.JokeCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Toast.makeText(this, "start AsyncTask", Toast.LENGTH_SHORT).show();

        new JokerAsyncTask(this, this, true).execute();
     /*   JokeWizard jokeWizard = new JokeWizard();
        String joke = jokeWizard.getJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();

        JokerView jokerView = new JokerView();

        Intent startJokerView = new Intent(this, JokerView.class);
        startJokerView.putExtra("joke", joke);
        startActivity(startJokerView);
        */
    }
    @Override
    public void sendResultToActivity(String results){

       /*
        if(results.equals("")){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            Log.v(JokerAsyncTask.class.getSimpleName(), "No joke is here: "+results);
            results = "Connection error";
        }
        */
        Intent startJokerView = new Intent(this, JokerView.class);
        startJokerView.putExtra("joke", results);
        startActivity(startJokerView);
    }


}
