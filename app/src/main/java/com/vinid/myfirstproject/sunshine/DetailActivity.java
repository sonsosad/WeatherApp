package com.vinid.myfirstproject.sunshine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinid.myfirstproject.sunshine.db.WeatherContract;
import com.vinid.myfirstproject.sunshine.db.WeatherDBHelper;

import static com.vinid.myfirstproject.sunshine.MainFragment.ROW_INDEX;

public class DetailActivity extends AppCompatActivity {

    static String WEATHER_INFO_ARG = "weather_info_arg";
    private static String TAG = "TAG";
    private WeatherInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWeatherInfoFromPreference();
        fillData();
    }

    private void getWeatherInfoFromPreference() {
        WeatherDBHelper dbHelper = new WeatherDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                WeatherContract.WeatherEntry.COLUMN_NAME_TYPE,
                WeatherContract.WeatherEntry.COLUMN_NAME_DATE,
                WeatherContract.WeatherEntry.COLUMN_NAME_MAX_TEMP,
                WeatherContract.WeatherEntry.COLUMN_NAME_MIN_TEMP
        };

        // Filter results WHERE "id" = 'inserted id'
        String selection = BaseColumns._ID + " = ?";
        long id = getIntent().getLongExtra(ROW_INDEX, 1);
        String[] selectionArgs = {"" + id};

        Cursor cursor = db.query(
                WeatherContract.WeatherEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            String weatherType = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_TYPE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_DATE));
            String maxTemp = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_MAX_TEMP));
            String minTemp = cursor.getString(cursor.getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_NAME_MIN_TEMP));
            info = new WeatherInfo(
                    WeatherType.valueOf(weatherType),
                    date,
                    maxTemp,
                    minTemp
            );
        }
        cursor.close();

        Log.e("TAG", cursor.toString());
    }

    private void fillData() {
        ImageView ivIcon = findViewById(R.id.ivIcon);
        ivIcon.setImageResource(info.getBigIconResId());

        TextView tvDate = findViewById(R.id.tvDate);
        tvDate.setText(info.getDate());

        TextView tvDescription = findViewById(R.id.tvDescription);
        tvDescription.setText(info.getDescriptionResId());

        TextView tvMaxTemp = findViewById(R.id.tvMaxTemp);
        tvMaxTemp.setText(info.getMaxTemp());

        TextView tvMinTemp = findViewById(R.id.tvMinTemp);
        tvMinTemp.setText(info.getMinTemp());
    }

    @Override
    public void onBackPressed() {
        info.setWeatherType(WeatherType.CLOUDS);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailActivity.WEATHER_INFO_ARG, info);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
