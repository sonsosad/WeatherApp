package com.vinid.myfirstproject.sunshine.api;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by hungnm24 on 5/4/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class HttpRequestTask extends AsyncTask<String, Void, Void> {

    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;

    private Callback callback;
    private HttpRequestMethod requestMethod;
    private Exception error;
    private String result;

    public HttpRequestTask(HttpRequestMethod requestMethod, Callback callback) {
        this.requestMethod = requestMethod;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String stringUrl = strings[0];
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(requestMethod.toString());
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        } catch (Exception e) {
            error = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (result != null) {
            callback.onSuccess(result);
        }

        if (error != null) {
            callback.onFailed(error);
        }
    }

    public interface Callback {
        void onSuccess(String result);

        void onFailed(Exception error);
    }
}
