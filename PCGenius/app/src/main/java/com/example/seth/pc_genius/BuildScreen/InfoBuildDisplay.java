package com.example.seth.pc_genius.BuildScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.SavedPartsScreen.InfoPartDisplay;

import java.util.ArrayList;

import static com.example.seth.pc_genius.BuildScreen.BuildsFragment.buildList;


public class InfoBuildDisplay extends Fragment{
    ArrayList<Part> partList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     Log.i("info","infoBuild built");
        View view = inflater.inflate(R.layout.build_info_display, container, false);
            partList = buildList.get(getArguments().getInt("Position", -1)).getmParts();
        PartAdapter adapter = new PartAdapter(getActivity(), -1, partList);

        ListView listView = (ListView) view.findViewById(R.id.listSavedParts);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                InfoPartDisplay infoPartDisplay = new InfoPartDisplay();
                bundle.putString("Name", partList.get(position).getmName());
                bundle.putString("Description", partList.get(position).getmDescription());
                bundle.putInt("ImageResource", partList.get(position).getmImageResourceId());
                bundle.putDouble("Price", partList.get(position).getmPrice());
                infoPartDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoPartDisplay, null);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        return view;
    }
}
