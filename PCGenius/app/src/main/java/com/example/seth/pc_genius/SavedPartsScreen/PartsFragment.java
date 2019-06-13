package com.example.seth.pc_genius.SavedPartsScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.seth.pc_genius.MainActivity.darkTheme;

public class PartsFragment extends Fragment {
    protected List<Part> list = new ArrayList<>();
    private List<Part> parts = new ArrayList<>();
    boolean ifDisplay = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Parts");
        readPartData();
        PartsFragmentContents.initPartsListParts(list, getContext(), parts, ifDisplay);

        PartAdapter adapter = new PartAdapter(getActivity(), -1, list);
        View view = inflater.inflate(R.layout.fragment_parts, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listSavedParts);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                InfoPartDisplay infoPartDisplay = new InfoPartDisplay();
                bundle.putString("Name", list.get(position).getmName());
                bundle.putString("Description", list.get(position).getmDescription());
                bundle.putInt("ImageResource", list.get(position).getmImageResourceId());
                bundle.putDouble("Price", list.get(position).getmPrice());
                bundle.putString("Vendor", list.get(position).getmVendor());
                bundle.putString("Bench",list.get(position).getmBenchmark());
                infoPartDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoPartDisplay, null);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });
        ifDisplay = false;
        return view;

    }

    private void readPartData() {

    //    String fileName = "parts";
  //      for (int i = 0; i < 100; i++)
  //          Log.d("PATH IS", getActivity().getFilesDir().getPath());

        try {

            File file = new File(getActivity().getFilesDir().getPath() + "/parts.csv");


      //      for (int i = 0; i < 100; i++)
     //           Log.d("TEST", "SUCCESS");

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

            /*
            for(int l = 0; i<30; i++)
                Log.d("name", savedParts[0] + "\n");

            for(String st: savedParts){
                for(int l = 0; i<30; i++)
                Log.d("name", st + "\n");

            }
*/

            for(String savedP:savedParts){

                String[] savedInfo = savedP.split(",");
                Part part = new Part();

                if(savedInfo.length!=0) {
                    part.setmModel(savedInfo[0]);
                    Log.d("model", savedInfo[0]);

                    part.setmVendor(savedInfo[1]);
                    Log.d("vendor", savedInfo[1]);

                    part.setmPrice(Double.parseDouble(savedInfo[2]));
                    Log.d("price", savedInfo[2]);


                    part.setmBenchmark(savedInfo[3]);
                    Log.d("bench", savedInfo[3]);

                    parts.add(part);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

