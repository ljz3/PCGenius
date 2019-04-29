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

    public static void initPartsListParts(List<Part> list, Context context, List<Part> parts) {

        for(Part part: parts){
            list.add(part);
        }

      //  list.add(new Part("random", "Motherboard", 199.99, R.drawable.image_icon));
    }

}
