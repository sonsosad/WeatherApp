package com.vinid.myfirstproject.sunshine;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vinid.myfirstproject.sunshine.api.WeatherService;
import com.vinid.myfirstproject.sunshine.db.WeatherContract;
import com.vinid.myfirstproject.sunshine.db.WeatherDBHelper;
import com.vinid.myfirstproject.sunshine.models.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements WeatherAdapter.Callback {

    static final String ROW_INDEX = "row_index";

    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static final String CITY_ID = "524901";
    private static final String TOKEN = "b1a6b9d8867fa058c1a2f803e6244b14";

    private WeatherAdapter adapter;

    private ProgressBar progressBar;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        getServerData();
    }

    @Override
    public void onItemClick(int index, WeatherInfo weatherInfo) {
        long rowId = saveWeatherInfoToPreference(weatherInfo);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ROW_INDEX, rowId);
        startActivity(intent);
    }

    private void setupView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new WeatherAdapter(new ArrayList<WeatherInfo>(), this);
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);
    }

    private void getServerData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);

        Call<Data> call = weatherService.getWeatherInfoList("2.5", CITY_ID, TOKEN);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Data data = response.body();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private long saveWeatherInfoToPreference(WeatherInfo weatherInfo) {
        WeatherDBHelper dbHelper = new WeatherDBHelper(getActivity());

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_TYPE, weatherInfo.getWeatherType().toString());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_DATE, weatherInfo.getDate());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_MAX_TEMP, weatherInfo.getMaxTemp());
        values.put(WeatherContract.WeatherEntry.COLUMN_NAME_MIN_TEMP, weatherInfo.getMinTemp());

        // Insert the new row, returning the primary key value of the new row
        return db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, values);
    }
}
