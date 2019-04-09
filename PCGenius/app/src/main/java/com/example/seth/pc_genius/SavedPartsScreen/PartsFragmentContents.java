package com.example.seth.pc_genius.SavedPartsScreen;

import android.app.Fragment;
import android.content.Context;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import java.util.List;

public class PartsFragmentContents extends Fragment {

    public static void initPartsListParts(List<Part> list, Context context) {
        list.add(new Part("Z170-A", "Motherboard", 199.99, R.drawable.image_icon));
    }
}
