package com.pensive.android.romplanuib;

/**
 * Created by EddiStat on 31.10.2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pensive.android.romplanuib.util.DownloadAndStoreData;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    DownloadAndStoreData dl = new DownloadAndStoreData();

    private static final String ARG_SECTION_NUMBER = "section_number";

    public RecentFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecentFragment newInstance(int sectionNumber) {
        RecentFragment fragment = new RecentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent, container, false);

        return rootView;
    }
}