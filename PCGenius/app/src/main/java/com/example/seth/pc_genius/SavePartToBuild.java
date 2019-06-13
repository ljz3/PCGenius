package com.example.seth.pc_genius;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seth.pc_genius.BuildObject.Build;
import com.example.seth.pc_genius.BuildObject.BuildsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SavePartToBuild extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        final BuildsAdapter adapter = new BuildsAdapter(getActivity(), -1, com.example.seth.pc_genius.BuildScreen.BuildsFragment.buildList);
        View view = inflater.inflate(R.layout.save_to_build, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.list_add_build);
        listView.setAdapter(adapter);
        return view;
    }

}
