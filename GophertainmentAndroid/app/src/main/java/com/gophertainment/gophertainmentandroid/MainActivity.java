package com.gophertainment.gophertainmentandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gophertainment.gophertainmentandroid.model.JsonResult;
import com.gophertainment.gophertainmentandroid.model.MultiSearchResult;
import com.gophertainment.gophertainmentandroid.network.ApiInterface;
import com.gophertainment.gophertainmentandroid.network.GopherApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();


    private List<MultiSearchResult> mMultiSearchResults;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMultiSearchResults();

    }

    public void setUpUI() {
        final EditText searchET = (EditText) findViewById(R.id.searchField);
        final Button searchBtn = (Button) findViewById(R.id.search_button);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getMultiSearchResults(String searchString) {
        Map userString = new HashMap();
        userString.put("usersearchstring", searchString);

        mApiInterface = GopherApi.getApiClient().create(ApiInterface.class);
        Call<JsonResult> call = mApiInterface.getMultiSearchResult(userString);

        call.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                Log.i(TAG, "GOT RESPONSE");
                List<MultiSearchResult> results = ((JsonResult) response.body()).getResults();
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) { Log.e(TAG, "CALL WAS CANCELLED"); }
            }
        });
    }

}
