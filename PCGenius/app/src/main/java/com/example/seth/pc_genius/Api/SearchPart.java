package com.example.seth.pc_genius.Api;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class SearchPart {
    private String productName;
    public SearchPart(String name){
        productName = name;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.bestbuy.com/v1/products(categoryPath.id=pcmcat748302027818))?apiKey=QWjD2MfkLiW4eAR2Bx1X37YM&sort=regularPrice.asc&show=regularPrice,name,categoryPath.id&format=json");
        Log.i("test","test");
    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            Log.i("test","test2");
           URL url = QueryUtils.createUrl(urls[0]);
           String result="";

            try {
              result = QueryUtils.makeHttpRequest(url);
              Log.i("JSONResponse", QueryUtils.makeHttpRequest(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);

            } catch (JSONException e) {
                Log.i("info","failed to get products");
                e.printStackTrace();
            }
        }
    }
}
