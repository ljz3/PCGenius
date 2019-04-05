package com.example.seth.pc_genius;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<Part> list = new ArrayList<>();
        HomeFragmentContents.initFoodsList(list, getContext());

        PartAdapter adapter = new PartAdapter(getActivity(), -1, list);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_home);

        listView.setAdapter(adapter);

        return view;
    }


}

