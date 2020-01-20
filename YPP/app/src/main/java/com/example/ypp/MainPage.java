package com.example.ypp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;


public class MainPage extends FragmentActivity {

    private int[] tabIcons = {
            R.drawable.friends,
            R.drawable.forum,
            R.drawable.activity,
            R.drawable.me
    };

    //navigation bar elements
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        //link the navigation layout with this interface
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpap);
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // check that it is the SecondActivity with an OK result
//        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // get String data from Intent
//                fileName = data.getStringExtra(Intent.EXTRA_TEXT);
//                pageAdapter.addFile(fileName);
//
//                //notify the adapter to update the content
//                pageAdapter.notifyDataSetChanged();
//            }
//        }
//    }

}
