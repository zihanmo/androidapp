package com.example.ypp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class Forum extends Fragment {

    private EditText searchBar;
    private ListView listView;
    private ArrayAdapter<String> adapter; //list view adapter

    private List<String> patientListUnsorted;
    private List<String> patientList; //list for storing all patients and sections
    private List<String> idList;
    private List<String> sectionList; //list for storing all sections
    private String userInput; //user input in the search bar


    // Required empty public constructor
    public Forum() {
        patientListUnsorted = new ArrayList<>();
        patientList = new ArrayList<>();
        sectionList = new ArrayList<>();
        idList = new ArrayList<>();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forum_layout, container, false);

        RelativeLayout relativeLayout3 = view.findViewById(R.id.activity3);
        RelativeLayout relativeLayout2 = view.findViewById(R.id.activity2);
        RelativeLayout relativeLayout1 = view.findViewById(R.id.activity1);


        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Post.class);
                startActivity(intent);
            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Post2.class);
                startActivity(intent);
            }
        });

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Post3.class);
                startActivity(intent);
            }
        });

        return view;
    }


    /**
     * Trigger event when a user typed something in the search bar
     */
    private void searchBarCapture() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int j, int k) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int j, int k) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}
