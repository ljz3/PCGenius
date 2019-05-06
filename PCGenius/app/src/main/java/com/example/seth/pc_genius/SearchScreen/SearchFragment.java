package com.example.seth.pc_genius.SearchScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seth.pc_genius.Api.SearchPart;
import com.example.seth.pc_genius.R;

public class SearchFragment extends Fragment {
    EditText searchProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("importantInfo","test");
        return inflater.inflate(R.layout.fragment_search, container, false);
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