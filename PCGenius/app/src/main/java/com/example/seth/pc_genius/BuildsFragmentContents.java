package com.example.seth.pc_genius;

import android.app.Fragment;
import android.content.Context;

import java.util.List;

public class BuildsFragmentContents extends Fragment {

    public static void initPartsListBuilds(List<Part> list, Context context) {
        list.add(new Part("Budget Build", "Brief description of the build", 1250.00, R.drawable.image_icon));
    }

}
