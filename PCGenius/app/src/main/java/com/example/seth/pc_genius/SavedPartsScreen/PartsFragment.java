package com.example.seth.pc_genius.SavedPartsScreen;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.data.DbHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PartsFragment extends AppCompatActivity {


    DbHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parts);

        ListView listView = (ListView) findViewById(R.id.listSavedParts);
        myDB = new DbHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
    }

























}

    /*
    protected List<Part> list = new ArrayList<>();
    private List<Part> parts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        getActivity().setTitle("Parts");


        readPartData();

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
                bundle.putString("Vendor",list.get(position).getmVendor());
                infoPartDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoPartDisplay, null);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        return view;

        return null;
    }
}

    private void readPartData() {

        for(int i = 0; i <5;i++) {
            int csv = 0;
            switch(i){
                case 0:
                    csv = R.raw.gpu;
                    break;
                case 1:
                    csv = R.raw.cpu;
                    break;
                case 2:
                    csv = R.raw.hdd;
                    break;
                case 3:
                    csv = R.raw.ram;
                    break;
                case 4:
                    csv = R.raw.ssd;
                    break;
            }

            InputStream is = getResources().openRawResource(csv);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, Charset.forName("UTF-8"))
            );

            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    // Split by ','
                    String[] tokens = line.split(",");

                    //Read the data
                    //Type,Part Number,Brand,Model,Rank,Benchmark,Samples,URL
                    Part part = new Part();
                    part.setmType(tokens[0]);
                    part.setmNum(tokens[1]);
                    part.setmBrand(tokens[2]);
                    part.setmModel(tokens[3]);
                    part.setmURL(tokens[7]);

                    parts.add(part);

                }
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line " + line, e);
                e.printStackTrace();
            }
        }

    }
*/
