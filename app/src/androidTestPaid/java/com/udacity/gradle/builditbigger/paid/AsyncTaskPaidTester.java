package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.JokerAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by veronika on 26.03.18.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskPaidTester {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) InstrumentationRegistry.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        assertTrue(networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
    // configured to check for local server.
    // As such, the test will pass on an emulator, but will fail on a physical device.

    @Test
    public void testServerResponse(){
        Context context = InstrumentationRegistry.getContext();
        final CountDownLatch latch = new CountDownLatch(1);

        try {
            new JokerAsyncTask(context, null, false){

                @Override
                protected void onPreExecute() {
                    //empty
                }

                @Override
                protected void onPostExecute(String results) {
                    latch.countDown();
                    assertNotNull(results);
                    assertTrue(results, results.length() > 0);
                    // Log.v(AsyncTaskTester.class.getSimpleName(), "Results are here: "+results);

                }
            }.execute().get();
            //wait 5 seconds
            latch.await(5, TimeUnit.SECONDS);


        }
        catch (Exception ex){
            ex.getMessage();
            fail(context.getResources().getString(R.string.async_task_error));
        }
    }
}
