package com.example.ypp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.view.View;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class Activity extends Fragment {

    private EditText searchBar;
    private ListView listView;
    private ArrayAdapter<String> adapter; //list view adapter
    private  Boolean joined = false;

    // Required empty public constructor
    public Activity() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_layout, container, false);

        final RelativeLayout mainlayout = view.findViewById(R.id.acdetail);
        RelativeLayout relativeLayout1 = view.findViewById(R.id.activity1);
        RelativeLayout pop = view.findViewById(R.id.backg);
        RelativeLayout container1 = view.findViewById(R.id.container1);
        ImageButton back = view.findViewById(R.id.closejoin);
        final Button join = view.findViewById(R.id.buttonjoin);
        final TextView numb = view.findViewById(R.id.joinnumber);
        ImageButton addHelp= view.findViewById((R.id.addActivity));

        join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!joined) {
                    numb.setText("9");
                    join.setText("Cancel");
                    joined = true;
                } else {
                    numb.setText("8");
                    join.setText("Help");
                    joined = false;
                }
            }
        });
        addHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainlayout.setVisibility(View.VISIBLE);
            }
        });


        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainlayout.setVisibility(View.VISIBLE);
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainlayout.setVisibility(View.GONE);
            }
        });

        container1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainlayout.setVisibility(View.GONE);
            }
        });


        return view;
    }

    private void openMainPage() {
        Intent intent = new Intent(getActivity(), AddHelp.class);
        startActivity(intent);
    }


}
