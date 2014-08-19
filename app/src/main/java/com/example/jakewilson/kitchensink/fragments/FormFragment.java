package com.example.jakewilson.kitchensink.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.doomonafireball.betterpickers.timepicker.TimePickerBuilder;
import com.doomonafireball.betterpickers.timepicker.TimePickerDialogFragment;
import com.example.jakewilson.kitchensink.MyActivity;
import com.example.jakewilson.kitchensink.R;
import com.example.jakewilson.kitchensink.interfaces.OnFragmentInteractionListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private MyActivity mContext;

    @InjectView(R.id.time_btn)Button time;
    @InjectView(R.id.date_btn)Button date;
    @InjectView(R.id.name_edit)EditText name;
    @InjectView(R.id.food_spinner)Spinner food;
    @InjectView(R.id.number_btn) Button number;
    @InjectView(R.id.number_text)TextView numberText;
    @InjectView(R.id.date_text) TextView dateText;
    @InjectView(R.id.time_text) TextView timeText;
    @InjectView(R.id.name_text) TextView nameText;

    Map<String,String> dataMap = new HashMap<String,String>();


    @OnClick(R.id.time_btn)
    void selectTime() {
        TimePickerBuilder tpb = new TimePickerBuilder()
                .setFragmentManager(mContext.getSupportFragmentManager()).addTimePickerDialogHandler(new TimePickerDialogFragment.TimePickerDialogHandler() {
                    @Override
                    public void onDialogTimeSet(int reference, int hourOfDay, int minute) {
                        timeText.setText("" +( hourOfDay - 12) + ":" + ( minute > 0 ? minute: "00"  ));
                        dataMap.put("time",( hourOfDay - 12) + ":" + ( minute > 0 ? minute: "00"  ));
                    }
                })
                .setStyleResId(R.style.BetterPickersDialogFragment);
        tpb.show();
    }



    @OnClick(R.id.date_btn)
    void selectDate() {
        DatePickerBuilder dpb = new DatePickerBuilder()
                .setFragmentManager(mContext.getSupportFragmentManager())
                .addDatePickerDialogHandler(new DatePickerDialogFragment.DatePickerDialogHandler() {
                    @Override
                    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth){
                        String date = String.format("%d/%d/%d", monthOfYear + 1, dayOfMonth, year);
                        dateText.setText(date);
                        dataMap.put("date", date);
                    }
                })
                .setStyleResId(R.style.BetterPickersDialogFragment);

        dpb.show();
    }


    @OnClick(R.id.number_btn)
    void selectNumber() {
        NumberPickerBuilder npb = new NumberPickerBuilder()
                .setFragmentManager(mContext.getSupportFragmentManager()).addNumberPickerDialogHandler(new NumberPickerDialogFragment.NumberPickerDialogHandler() {
                    @Override
                    public void onDialogNumberSet(int reference, int number, double decimal, boolean isNegative, double fullNumber) {
                        numberText.setText(String.valueOf(fullNumber));
                        dataMap.put("number", String.valueOf(fullNumber));

                    }
                })
                .setStyleResId(R.style.BetterPickersDialogFragment);
        npb.show();

    }



    @OnClick(R.id.save)void saveData(){

        dataMap.put("name", name.getText().toString());
        dataMap.put("food", food.getSelectedItem().toString());

        String display = new String();
        for(String key : dataMap.keySet()){
            display += (String.format(" %s: %s \n", key, dataMap.get(key)));
        }

        if (mListener != null) {
            mListener.onFragmentInteraction(display);
        }
    }




    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();
        return fragment;
    }

    public FormFragment() {
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_form, container, false);

        ButterKnife.inject(this, view);
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.foods, R.layout.food);
        food.setAdapter(mSpinnerAdapter);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (MyActivity) activity;
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


}
