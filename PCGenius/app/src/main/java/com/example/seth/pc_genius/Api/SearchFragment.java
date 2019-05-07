package com.example.seth.pc_genius.Api;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.seth.pc_genius.Api.SearchPart;
import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;

import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.SavedPartsScreen.InfoPartDisplay;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    EditText searchProduct;
    public static List<Part> searchList;
    public static PartAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchList = new ArrayList<>();
        searchList.add(new Part("","",55,R.drawable.image_icon));
        Log.i("importantInfo","test");
         adapter = new PartAdapter(getActivity(), -1, searchList);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_search);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                InfoPartDisplay infoPartDisplay = new InfoPartDisplay();
                bundle.putString("Name", searchList.get(position).getmName());
                bundle.putString("Description",searchList.get(position).getmDescription());
                bundle.putInt("ImageResource", searchList.get(position).getmImageResourceId());
                bundle.putDouble("Price", searchList.get(position).getmPrice());
                infoPartDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoPartDisplay, null);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("importantInfo","test");
        getActivity().setTitle("Search");
        searchProduct =  view.findViewById(R.id.searchProduct);
        searchProduct.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (i == KeyEvent.KEYCODE_ENTER)) {
                    Log.i("importantInfo",searchProduct.getText().toString());
                    SearchPart searchPart = new SearchPart(searchProduct.getText().toString());

                    return true;
                }
                return false;
            }
        });
    }
}