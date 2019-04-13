package com.example.seth.pc_genius.BuildScreen;

import android.app.Fragment;
import android.content.Context;

import com.example.seth.pc_genius.BuildObject.Build;
import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import java.util.ArrayList;
import java.util.List;

public class BuildsFragmentContents extends Fragment {

    public static void initPartsListBuilds(List<Build> buildList, Context context) {
        ArrayList partList = new ArrayList<>();
        partList.add(new Part("Z170-A", "Motherboard", 200.00, R.drawable.image_icon));
        partList.add(new Part("Intel", "CPU", 199.99, R.drawable.image_icon));
        partList.add(new Part("1060", "GPU", 199.99, R.drawable.image_icon));
        partList.add(new Part("box", "Case", 199.99, R.drawable.image_icon));
        partList.add(new Part("8gb", "Ram", 199.99, R.drawable.image_icon));
        partList.add(new Part("1T", "HD", 199.99, R.drawable.image_icon));

        buildList.add(new Build("Budget Build", "Brief description of the build", 1250.00, partList));
    }

}
