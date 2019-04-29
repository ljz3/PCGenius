package com.example.seth.pc_genius.SavedPartsScreen;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PartsFragmentContents extends Fragment {
    private static List<Part> parts = new ArrayList<>();

    public void initPartsListParts(List<Part> list, Context context) {
    readPartData();

    list = parts;
    }


    private void readPartData() {
        InputStream is = getResources().openRawResource(R.raw.gpu);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                // Split by ','
                String[] tokens = line.split(",");

                //Read the data
                //Type,Part Number,Brand,Model,Rank,Benchmark,Samples,URL
                Part part = new Part();
                part.setmType(tokens[0]);
                part.setmNum(tokens[1]);
                part.setmBrand(tokens[2]);
                part.setmModel(tokens[3]);
                part.setmRank(Integer.getInteger(tokens[4]));
                part.setmBenchmark(Integer.getInteger(tokens[5]));
                part.setmSamples(Integer.getInteger(tokens[6]));
                part.setmURL(tokens[7]);

                parts.add(part);

            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }


    }


}
