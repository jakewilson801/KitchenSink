package com.example.jakewilson.kitchensink.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.jakewilson.kitchensink.R;
import com.example.jakewilson.kitchensink.interfaces.OnFragmentInteractionListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PageOneFragment extends Fragment implements ActionBar.OnNavigationListener {

    private OnFragmentInteractionListener mListener;
    @InjectView(R.id.title)TextView title;

    public static PageOneFragment newInstance(){
        PageOneFragment fragment = new PageOneFragment();
        return fragment;
    }
    public PageOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_one, container, false);
        ActionBar ab = getActivity().getActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.items, android.R.layout.simple_spinner_dropdown_item);
        ab.setListNavigationCallbacks(mSpinnerAdapter, this);
        ButterKnife.inject(this, view);
        return view;
    }

    public void showResults(String args){
        title.setText(args);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {

        if(getView() == null)
            return false;
        switch(itemPosition){
            case 0:
                getView().setBackgroundColor(Color.rgb(255,0,0));
                break;
            case 1:
                getView().setBackgroundColor(Color.rgb(0,255,0));
                break;
            case 2:
                getView().setBackgroundColor(Color.rgb(0,0,255));
                break;
        }
        return false;
    }
}
