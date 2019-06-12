package com.example.seth.pc_genius.Api;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.example.seth.pc_genius.Api.SearchFragment.searchRelatedList;

public class SearchInfoPartDisplay extends Fragment {

    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;
    private String mVendor;
    private Bitmap mBitmap = null;


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
      //  getBenchmark();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_part_info, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_part:
                savePart();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void getBenchmark() {

    }


    private void savePart() {

        // EditText fileNameEdit= (EditText) getActivity().findViewById(R.raw.parts);
        String fileName = "parts";
        for (int i = 0; i < 100; i++)
            Log.d("PATH IS", getActivity().getFilesDir().getPath());

        try {

            File file = new File(getActivity().getFilesDir().getPath() + "/parts.csv");

            FileOutputStream stream;
            // if file doesnt exists, then create it
            if (!file.exists()) {

                Log.d("EXISTS", "DNE");
                stream = new FileOutputStream(file);


            } else {
                Log.d("EXISTS", "EXISTS");
                stream = new FileOutputStream(file, true);

            }

            TextView name = (TextView) getView().findViewById(R.id.partNameDisplay);
            TextView vendor = (TextView) getView().findViewById(R.id.vendorDisplay);
            TextView price = (TextView) getView().findViewById(R.id.priceDisplay);
            TextView bench = (TextView) getView().findViewById(R.id.searchBenchmarkDisplay);
            CharSequence cs = name.getText() + "," + vendor.getText() + "," + price.getText() +  "," + bench.getText() + "@";
            String s = cs.toString();
            byte b[] = s.getBytes();
            stream.write(b);

            stream.close();
            stream.flush();

            for (int i = 0; i < 100; i++)
                Log.d("TEST", "SUCCESS");

            FileInputStream inputStream = new FileInputStream(file);


            int i = 0;
            while ((i = inputStream.read()) != -1) {

                char ch = (char) i;
                String str = String.valueOf(ch);
                Log.d("READ", str);

            }





                //      inputStream.close();
                //        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                //      BufferedWriter bw = new BufferedWriter(fw);
                //    bw.write(content);
                //  bw.close();


                Toast.makeText(getActivity(),
                        "Saved", Toast.LENGTH_LONG).show();


            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){


            View view = inflater.inflate(R.layout.search_part_info_display, container, false);
            mName = getArguments().getString("Name", "");
            mDescription = getArguments().getString("Description", "");
            mPrice = getArguments().getDouble("Price", 0);
            mImageResourceId = getArguments().getInt("ImageResource", 0);
            mVendor = getArguments().getString("Vendor", "");

            try {
                mBitmap = getArguments().getParcelable("BitmapImage");
            } catch (Exception e) {
                e.printStackTrace();
            }


            TextView partDisplay = view.findViewById(R.id.partNameDisplay);
            partDisplay.setText(mName);
            TextView descriptionDisplay = view.findViewById(R.id.descriptionDisplay);
            //descriptionDisplay.setText(mDescription);
            TextView priceDisplay = view.findViewById(R.id.priceDisplay);
            priceDisplay.setText(Double.toString(mPrice));
            TextView vendorDisplay = view.findViewById(R.id.vendorDisplay);
            vendorDisplay.setText(mVendor);
            ImageView imageDisplay = view.findViewById(R.id.imageDisplay);

            if (mBitmap != null) {
                imageDisplay.setImageBitmap(mBitmap);
            } else {
                imageDisplay.setImageResource(mImageResourceId);
            }


            Log.i("importantInfo", "test");
            PartAdapter adapter = new PartAdapter(getActivity(), -1, searchRelatedList);
            ListView listView = (ListView) view.findViewById(R.id.relatedParts);

            getBenchmark();
            TextView name = (TextView) view.findViewById(R.id.partNameDisplay);

            TextView bench = (TextView) view.findViewById(R.id.searchBenchmarkDisplay);



            for (int z = 0; z < 5; z++) {
                int csv = 0;
                switch (z) {
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

                        if (name.getText().toString().contains(tokens[3])) {
                            Log.d("READ", tokens[3]);

                            bench.setText(tokens[5]);

                        }

                    }
                } catch (IOException e) {
                    Log.wtf("MyActivity", "Error reading data file on line " + line, e);
                    e.printStackTrace();
                }
            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    SearchInfoPartDisplay searchInfoPartDisplay = new SearchInfoPartDisplay();
                    bundle.putString("Name", searchRelatedList.get(position).getmName());
                    bundle.putString("Description", searchRelatedList.get(position).getmDescription());
                    bundle.putInt("ImageResource", searchRelatedList.get(position).getmImageResourceId());
                    bundle.putDouble("Price", searchRelatedList.get(position).getmPrice());
                    bundle.putParcelable("BitmapImage", searchRelatedList.get(position).getmBitmap());
                    bundle.putString("Vendor", searchRelatedList.get(position).getmVendor());
                    searchInfoPartDisplay.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.screen_area, searchInfoPartDisplay, null);
                    transaction.addToBackStack(null);

                    transaction.commit();

                }
            });

            return view;
        }
    }




