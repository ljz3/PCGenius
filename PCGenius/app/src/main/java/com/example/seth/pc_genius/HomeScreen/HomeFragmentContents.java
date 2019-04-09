package com.example.seth.pc_genius.HomeScreen;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.seth.pc_genius.Part;
import com.example.seth.pc_genius.R;

import java.util.List;
public class HomeFragmentContents extends Fragment{

    public static void initPartsListHome(List<Part> list, Context context) {
        list.add(new Part(" GTX 1060 6GB", "Brief description of the part", 599.99, R.drawable.image_icon));
        list.add(new Part("hi",R.drawable.image_icon));
    }
}
