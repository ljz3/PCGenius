package com.example.seth.pc_genius.Api;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.example.seth.pc_genius.Api.SearchFragment;

public class SearchPart {
    public String productName;

    public SearchPart(String name) {
        productName = name;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.bestbuy.com/v1/products((" + formatUserInput(productName) + ")&((categoryPath.id=abcat0507000)))?apiKey=QWjD2MfkLiW4eAR2Bx1X37YM&pageSize=100&sort=salePrice.asc&show=salePrice,image,shortDescription,name&format=json");
        SearchFragment.searchList.clear();
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url = QueryUtils.createUrl(urls[0]);
            String result = "";

            try {
                result = QueryUtils.makeHttpRequest(url);
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

                JSONArray m = jsonObject.getJSONArray("products");

                for (int i = 0; i < m.length(); i++) {
                   Bitmap myImage = null;

                    try {
                        DownloadImage downloadImage = new DownloadImage();
                        myImage=downloadImage.execute(m.getJSONObject(i).get("image").toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    SearchFragment.searchList.add(new Part(m.getJSONObject(i).get("name").toString(), m.getJSONObject(i).get("shortDescription").toString(), Double.parseDouble(m.getJSONObject(i).get("salePrice").toString()), myImage,"Best Buy"));
                }
                SearchFragment.adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                Log.i("info", "failed to get products");
                e.printStackTrace();
            }
        }
    }

    public String formatUserInput(String userInput) {
        String[] user = userInput.split(" ");
        String result = "";
        result = "search=" + user[0];
        for (int i = 1; i < user.length; i++) {
            result = result + "&search=" + user[i];
        }
        return result;
    }
}
