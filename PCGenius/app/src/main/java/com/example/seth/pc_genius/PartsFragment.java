package com.example.seth.pc_genius;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<Part> list = new ArrayList<>();
        PartsFragmentContents.initPartsListParts(list, getContext());

        PartAdapter adapter = new PartAdapter(getActivity(), -1, list);
        View view = inflater.inflate(R.layout.fragment_parts, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_parts);

        listView.setAdapter(adapter);

        return view;
    }
}

