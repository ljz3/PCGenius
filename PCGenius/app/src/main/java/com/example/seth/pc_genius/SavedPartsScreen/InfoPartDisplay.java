package com.example.seth.pc_genius.SavedPartsScreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seth.pc_genius.MainActivity;
import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

public class InfoPartDisplay{
    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;

    public InfoPartDisplay(){
        Log.i("info","work1");


    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("info","work2");
        setContentView(R.layout.fragment_settings);
    }
}
