package com.example.seth.pc_genius.Api;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SearchRelatedPart {

    private String productName;

    public SearchRelatedPart(String title) {

        productName = title;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.aliseeks.com/v1/search/realtime");

    }


    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            SearchFragment.searchRelatedList.clear();
            URL url = QueryUtils.createUrl(urls[0]);
            String result = "";

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("https://api.aliseeks.com/v1/search/realtime");
            post.addHeader("X-API-CLIENT-ID", "PBGNWVIIBOVFNGYY");
            post.addHeader("Content-Type", "application/json");
            try {
                StringEntity input = new StringEntity("{\"text\":\" " + productName + "\",\"category\":\"200002319\"}");

                post.setEntity(input);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {

                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    Log.i("infoPart", productName);
                    System.out.println(line);
                    Log.i("infoPart", line);
                    result = line;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("infoResult1", " " + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("infoResult2", " " + result);
            try {
                JSONObject jsonObject = new JSONObject(result);

                JSONArray m = jsonObject.getJSONArray("items");
                for(int i=0;i<m.length();i++) {

                    Bitmap myImage = null;

                    try {
                        DownloadImage downloadImage = new DownloadImage();
                        myImage=downloadImage.execute(m.getJSONObject(i).get("imageUrl").toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                   Log.i("importantinfo", m.getJSONObject(i).getJSONArray("priceOptions").getJSONObject(0).getJSONObject("amount").get("value").toString());
                    Log.i("importantInfo", m.getJSONObject(i).toString());
                    SearchFragment.searchRelatedList.add(new Part(m.getJSONObject(i).get("title").toString(), "", Double.parseDouble(m.getJSONObject(i).getJSONArray("priceOptions").getJSONObject(0).getJSONObject("amount").get("value").toString()), myImage,"AliExpress"));
                    if(i==5){
                        break;
                    }
                }


            } catch (JSONException e) {
                Log.i("info", "failed to get products");
                e.printStackTrace();
            }
        }
    }
}

