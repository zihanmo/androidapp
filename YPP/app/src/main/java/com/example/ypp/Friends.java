package com.example.ypp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class Friends extends Fragment {

    private String name;
    private TextView numbers;
    private int numberOF = 0;
    private EditText searchBar;
    private RelativeLayout rl1;
    private Boolean rl1Visible = true;
    private RelativeLayout rl2;
    private Boolean rl2Visible = true;
    private RelativeLayout rl3;
    private Boolean rl3Visible = true;

    private ListView listView;
    private ListView listViewFriends;

    private ImageButton addButton;
    private ArrayAdapter<String> adapter; //list view adapter

    String groups[] = {
            "Tom                                             ",
            "Jake                                                                           ",
            "Amy                                                           "};

    public List<String> groupsFriends;
    private List<String> patientList; //list for storing all patients and sections
    private List<String> idList;
    private List<String> sectionList; //list for storing all sections
    private String userInput; //user input in the search bar


    // Required empty public constructor
    public Friends() {
        patientList = new ArrayList<>();
        sectionList = new ArrayList<>();
        idList = new ArrayList<>();

        if (Globals.groupsFriend == null) {
            Globals.groupsFriend = new ArrayList<>();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends_layout, container, false);

        numbers = view.findViewById(R.id.numbers);
        numbers.setText(Globals.numbers + "/" + Globals.numbers);

        listView = view.findViewById(R.id.list1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, groups);
        listView.setAdapter(arrayAdapter);

        listViewFriends = view.findViewById(R.id.list2);
        ArrayAdapter<String> arrayAdapterF = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, Globals.groupsFriend);
        listViewFriends.setAdapter(arrayAdapterF);

        addButton = view.findViewById(R.id.addFriend);

        rl1 = view.findViewById(R.id.group1);
        rl2 = view.findViewById(R.id.group2);
        rl3 = view.findViewById(R.id.group3);

        rl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (rl1Visible) {
                    listView.setVisibility(View.GONE);
                    rl1Visible = false;
                    listView.deferNotifyDataSetChanged();
                } else {
                    listView.setVisibility(View.VISIBLE);
                    rl1Visible = true;
                    listView.deferNotifyDataSetChanged();
                }
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (rl2Visible) {
                    listViewFriends.setVisibility(View.GONE);
                    rl2Visible = false;
                    listViewFriends.deferNotifyDataSetChanged();
                } else {
                    listViewFriends.setVisibility(View.VISIBLE);
                    rl2Visible = true;
                    listViewFriends.deferNotifyDataSetChanged();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogPopup(v);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFile = listView.getItemAtPosition(position).toString();
                String c;
                if (selectedFile.charAt(0) == 'B') {
                    c = "BNE Chatting room1";
                } else if (selectedFile.charAt(0) == 'G') {
                    c = "Girls";
                } else {
                    c = "Secret Space";
                }
                Intent intent = new Intent(getActivity(), Chatting.class);
                intent.putExtra("SELECTED_CHANNEL", c);
                startActivity(intent);
            }
        });

        listViewFriends.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFile = listViewFriends.getItemAtPosition(position).toString();
                Intent intent = new Intent(getActivity(), ChattingFriend.class);
                intent.putExtra("SELECTED_CHANNEL", selectedFile);
                startActivity(intent);
            }
        });

        return view;
    }

    private void dialogPopup(View view) {
        AlertDialog.Builder message = new AlertDialog.Builder(view.getContext());
        message.setTitle("Add a friend");
        message.setMessage("Enter your friend's name here");

        final EditText input = new EditText(view.getContext());
        message.setView(input);
        message.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                name = input.getText().toString();
                Globals.numbers++;
                Toast.makeText(getContext(), "Friend Added", Toast.LENGTH_SHORT).show();
                numbers.setText(Globals.numbers + "/" + Globals.numbers);
                Globals.groupsFriend.add(name);
            }
        });

        message.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = message.create();
        dialog.show();
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
