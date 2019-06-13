package com.example.seth.pc_genius;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seth.pc_genius.BuildObject.Build;
import com.example.seth.pc_genius.BuildObject.BuildsAdapter;
import com.example.seth.pc_genius.BuildScreen.InfoBuildDisplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SavePartToBuild extends Fragment {

    ArrayList<String> buildList = new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        readBuild();

        final SavePartToBuildAdapter adapter = new SavePartToBuildAdapter(getActivity(), -1, buildList);
        View view = inflater.inflate(R.layout.save_to_build, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.list_add_build);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                /*
                Bundle bundle = new Bundle();
                InfoBuildDisplay infoBuildDisplay = new InfoBuildDisplay();
                bundle.putInt("Position", position);
                infoBuildDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoBuildDisplay, null);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.i("info", "item clicked");
*/

            }
        });




        return view;
    }

    private void saveToBuild(){

    }

    private void readBuild() {


        try {

            File file = new File(getActivity().getFilesDir().getPath() + "/builds.csv");

            FileInputStream inputStream = new FileInputStream(file);

            String fullStr = "";
            int i = 0;
            while ((i = inputStream.read()) != -1) {

                char ch = (char) i;
                String str = String.valueOf(ch);
                //          Log.d("READ", str);
                fullStr += str;

            }
            Log.d("READ", fullStr);

            String[] savedParts = fullStr.split(",");

            for (int z = buildList.size(); z < savedParts.length - 1; z++) {
                buildList.add(savedParts[z]);
                Log.d("BUILD NAME", savedParts[z]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
