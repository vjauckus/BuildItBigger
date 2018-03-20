/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/
package com.udacity.gradle.builditbigger.backend;

import com.android.udacity.vjauckus.jokewizard.JokeWizard;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that gets a joke from jokewizard.JokeWizard */
    @ApiMethod(name = "getJokeFromCloudServer")
    public MyBean getJokeFromCloudServer() {

        MyBean response = new MyBean();

        response.setData(JokeWizard.getJoke());

        return response;
    }

}
