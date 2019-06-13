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
import java.io.FileOutputStream;
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


                try {
                    saveToBuild(buildList.get(position).replaceAll("\\s+",""));

                    Log.e("SAVEPARTOBUILDCLASSNAME", buildList.get(position).replaceAll("\\s+",""));
                } catch (IOException e) {
                    e.printStackTrace();
                }


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

    private void saveToBuild(String path) throws IOException {

        Log.d("FILE PATH SAVEBUILD", getActivity().getFilesDir().getPath() + "/" + path +".csv");

        File file = new File(getActivity().getFilesDir().getPath() + "/" + path +".csv");

        FileOutputStream stream;
        // if file doesnt exists, then create it
        if (!file.exists()) {

            Log.d("EXISTS", "DNE");
            stream = new FileOutputStream(file);


        } else {
            Log.d("EXISTS", "EXISTS");
            stream = new FileOutputStream(file, true);

        }

        CharSequence cs = getTemp();

        Log.d("CHARSEQUENCE PRINT", getTemp().toString());

        String s = cs.toString();
        byte b[] = s.getBytes();
        stream.write(b);

        stream.close();
        stream.flush();


        FileInputStream inputStream = new FileInputStream(file);


        int i = 0;
        while ((i = inputStream.read()) != -1) {

            char ch = (char) i;
            String str = String.valueOf(ch);
            Log.d("READ", str);

        }

        Toast.makeText(getActivity(),
                "Added to Build", Toast.LENGTH_LONG).show();

    }

    private CharSequence getTemp() {

        try {

            File file = new File(getActivity().getFilesDir().getPath() + "/temp.csv");

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

            String[] savedParts = fullStr.split("@");
            Log.d("SAVED PARTS STRING", savedParts[savedParts.length-1]);
            return savedParts[savedParts.length-1];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "";

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
                //Log.d("READ", str);
                fullStr += str;

            }
            Log.d("READ", fullStr);

            String[] savedParts = fullStr.split(",");

            for (int z = buildList.size(); z < savedParts.length; z++) {
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
