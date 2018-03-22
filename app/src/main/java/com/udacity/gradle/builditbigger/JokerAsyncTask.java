package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by veronika on 19.03.18.
 */
/**
 * AsyncTask enables proper and easy use of the UI thread.
 * This class allows us to perform background operations and publish results on the UI thread without having to manipulate threads and/or handlers.
 * 1. Void: Params, the type of the parameters sent to the task upon execution.
 * 2. Void: Progress, the type of the progress units published during the background computation.
 * 3. String: Result, the type of the result of the background computation.

 */
public class JokerAsyncTask extends AsyncTask<Void, Void, String> {

    private final String TAG = JokerAsyncTask.class.getSimpleName();

    private MyApi myApiService = null;
    private boolean isLoading;
    private ProgressDialog pDialog;
    private Context mContext;


    public interface JokeCallback{
        void sendResultToActivity(String result);

    }
    private JokeCallback callback;

    //Constructor
    public JokerAsyncTask(Context context, JokeCallback listener, boolean showDialog){
        this.mContext = context;
        this.callback= listener;
        this.isLoading = showDialog;

    }

    @Override
    protected void onPreExecute() {
        Log.v(TAG, "Starting Loading");
        super.onPreExecute();
        if(!isLoading){
            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage(mContext.getString(R.string.loading));
            pDialog.show();
        }

    }

    @Override
    protected final String doInBackground(Void... params ) {

        if (myApiService == null) { // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();


             }

      //  this.mTestName = params[0];

        try {
            return myApiService.getJokeFromCloudServer().execute().getData();
        }
        catch (IOException ex){
            Log.v(TAG, "Error in AsyncTask");

            return ex.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String results) {

        Timber.d("onPostExecute"+" "+results);

        //Log.v(JokerAsyncTask.class.getSimpleName(), results);

         if(results.equals("")) {
            Log.v(TAG, "No Joke available");
        }
        if(pDialog != null && pDialog.isShowing()){
             pDialog.dismiss();
        }
        callback.sendResultToActivity(results);
    }
}
