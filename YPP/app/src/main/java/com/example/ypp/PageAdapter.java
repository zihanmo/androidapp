package com.example.ypp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PageAdapter extends FragmentStatePagerAdapter {
    private String selectedFile;


    //Default constructor
    public PageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    /**
     * It is used to retrieve a page and put it into the view pager.
     * @param position the option on the menu bar
     * @return fragment a page's constructor
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new Friends();
                break;
            case 1:
                fragment = new Forum();
                break;
            case 2:
                fragment = new Activity();
                break;
            case 3:
                fragment = new Me();
                break;
        }

        return fragment;
    }


    /**
     * Get item position
     * @param object Fragment
     * @return POSITION_NONE 0 which is used with notifyDataSetChanged(), which aims to refresh
     * all fragments
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    /**
     * Add a file's name.
     * @param fileName the name of a file
     */
    public void addFile(String fileName) {
        this.selectedFile = fileName;
    }


    /**
     * Get the total number of options on the menu bar.
     * @return 3
     */
    @Override
    public int getCount() {
        return 4;
    }


    /**
     * Used to set up the title of each option on the menu bar.
     * @param position The option on the menu bar
     * @return title The name of the option
     */
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0:
                title = "Chatting";
                break;
            case 1:
                title = "Forum";
                break;
            case 2:
                title = "Help";
                break;
            case 3:
                title = "Me";
                break;
        }
        return title;
    }

}
