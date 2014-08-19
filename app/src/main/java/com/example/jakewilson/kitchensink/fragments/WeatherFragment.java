package com.example.jakewilson.kitchensink.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jakewilson.kitchensink.R;
import com.example.jakewilson.kitchensink.clients.WeatherClient;
import com.example.jakewilson.kitchensink.interfaces.OnFragmentInteractionListener;
import com.example.jakewilson.kitchensink.models.WeatherResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WeatherFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    @InjectView(R.id.temperature)TextView temp;
    @InjectView(R.id.temperatureHigh)TextView high;
    @InjectView(R.id.temperatureLow)TextView low;


    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }
    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.inject(this, view);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WeatherClient.API_URL)
                .build();
        WeatherClient.WeatherInterface weather = restAdapter.create(WeatherClient.WeatherInterface.class);

        weather.currentWeather(new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {

                String current = String.valueOf(weatherResponse.getMain().getTemp().intValue());
                String humidity = String.valueOf(weatherResponse.getMain().getHumidity());
                String pressure = String.valueOf(weatherResponse.getMain().getPressure());

                temp.setText(String.format("%s: %s°", getResources().getString(R.string.current), current));
                high.setText(String.format("%s: %s", getResources().getString(R.string.humidity),humidity));
                low.setText(String.format("%s: %s", getResources().getString(R.string.pressure), pressure));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
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
}
