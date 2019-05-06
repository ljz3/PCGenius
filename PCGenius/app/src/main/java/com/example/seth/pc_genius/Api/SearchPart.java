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
        Log.i("test","test1");
       Log.i("importantInfo","https://api.bestbuy.com/v1/products(("+formatUserInput(productName)+")&(categoryPath.id=abcat0501000))?apiKey=QWjD2MfkLiW4eAR2Bx1X37YM&sort=salePrice.asc&show=salePrice,image,shortDescription,name&format=json");
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.bestbuy.com/v1/products(("+formatUserInput(productName)+")&(categoryPath.id=abcat0501000))?apiKey=QWjD2MfkLiW4eAR2Bx1X37YM&sort=salePrice.asc&show=salePrice,image,shortDescription,name&format=json");
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

                Log.i("importantinfo",jsonObject.get("products").toString());
                JSONObject test = new JSONObject(jsonObject.get("products").toString());
                Log.i("importantinfo",test.get("salePrice").toString());


            } catch (JSONException e) {
                Log.i("info","failed to get products");
                e.printStackTrace();
            }
        }
    }

    public String formatUserInput (String userInput){
       String[] user =  userInput.split(" ");
       String result = "";
       result = "search="+user[0];
        for(int i = 1; i<user.length; i++){
            result = result + "&search="+user[i];
}
        return result;
}
}
