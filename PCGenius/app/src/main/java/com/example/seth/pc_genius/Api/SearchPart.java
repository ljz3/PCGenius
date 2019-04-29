package com.example.seth.pc_genius.Api;

import android.os.AsyncTask;

public class SearchPart {
    private String productName;
    public SearchPart(String name){
        productName = name;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("");
    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return "-1";
        }
    }
}
