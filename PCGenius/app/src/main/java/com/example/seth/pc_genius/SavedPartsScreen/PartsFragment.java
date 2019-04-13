package com.example.seth.pc_genius.SavedPartsScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.seth.pc_genius.MainActivity;
import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;

import java.util.ArrayList;
import java.util.List;

public class PartsFragment extends Fragment {
    List<Part> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         list = new ArrayList<>();
        PartsFragmentContents.initPartsListParts(list, getContext());

        PartAdapter adapter = new PartAdapter(getActivity(), -1, list);
        View view = inflater.inflate(R.layout.fragment_parts, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_parts);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new InfoPartDisplay();




            }
        });

        return view;

    }

}

