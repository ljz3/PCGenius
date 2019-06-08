package com.example.seth.pc_genius.HomeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.seth.pc_genius.Api.BestDeals;
import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.SavedPartsScreen.InfoPartDisplay;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static List<Part> bestDealsList = new ArrayList<>();
    public static PartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Home");



        adapter = new PartAdapter(getActivity(), -1, bestDealsList);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_home);


        BestDeals bestDeals = new BestDeals();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                InfoPartDisplay infoPartDisplay = new InfoPartDisplay();
                bundle.putString("Name", bestDealsList.get(position).getmName());
                bundle.putString("Description", bestDealsList.get(position).getmDescription());
                bundle.putInt("ImageResource", bestDealsList.get(position).getmImageResourceId());
                bundle.putDouble("Price", bestDealsList.get(position).getmPrice());
                bundle.putString("Vendor", bestDealsList.get(position).getmVendor());
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

