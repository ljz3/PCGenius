package com.example.seth.pc_genius;

import android.app.Fragment;
import android.content.Context;

import java.util.List;

public class PartsFragmentContents extends Fragment {

    public static void initPartsListParts(List<Part> list, Context context) {
        list.add(new Part("Z170-A", "Motherboard", 199.99, R.drawable.image_icon));
    }
}
