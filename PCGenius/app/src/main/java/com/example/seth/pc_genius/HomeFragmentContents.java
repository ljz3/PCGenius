package com.example.seth.pc_genius;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.List;
public class HomeFragmentContents extends Fragment{

    public static void initFoodsList(List<Part> list, Context context) {
        list.add(new Part(" GTX 1060 6GB", "Brief description of the part", 599.99, R.drawable.image_icon));
        list.add(new Part("hi",R.drawable.image_icon));
    }

}
