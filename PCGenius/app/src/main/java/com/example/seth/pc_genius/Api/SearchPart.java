package com.example.seth.pc_genius.Api;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import com.example.seth.pc_genius.Api.SearchFragment;

public class SearchPart {
    private String productName;

    public SearchPart(String name) {
        productName = name;
        Log.i("test", "test1");
        Log.i("importantInfo", "https://api.bestbuy.com/v1/products((" + formatUserInput(productName) + ")&(categoryPath.id=abcat0501000))?apiKey=QWjD2MfkLiW4eAR2Bx1X37YM&sort=salePrice.asc&show=salePrice,image,shortDescription,name&format=json");
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.bestbuy.com/v1/products((" + formatUserInput(productName) + ")&(categoryPath.id=abcat0501000))?apiKey=QWjD2MfkLiW4eAR2Bx1X37YM&sort=salePrice.asc&show=salePrice,image,shortDescription,name&format=json");
        Log.i("test", "test");
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            Log.i("test", "test2");
            URL url = QueryUtils.createUrl(urls[0]);
            String result = "";

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

               JSONArray m = jsonObject.getJSONArray("products");
               for(int i = 0;i<m.length();i++){
                   Log.i("info",m.getJSONObject(i).get("name").toString());

                   SearchFragment.searchList.add(new Part(m.getJSONObject(i).get("name").toString(),m.getJSONObject(i).get("shortDescription").toString(),Double.parseDouble(m.getJSONObject(i).get("salePrice").toString()), R.drawable.image_icon));
               }
                SearchFragment.adapter.notifyDataSetChanged();

                Log.i("importantinfo", jsonObject.get("products").toString());


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
