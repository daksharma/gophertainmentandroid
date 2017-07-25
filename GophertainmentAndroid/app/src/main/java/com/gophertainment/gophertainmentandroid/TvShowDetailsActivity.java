package com.gophertainment.gophertainmentandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gophertainment.gophertainmentandroid.model.TvShow;
import com.gophertainment.gophertainmentandroid.network.ApiInterface;
import com.gophertainment.gophertainmentandroid.network.GopherApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailsActivity extends AppCompatActivity {

    private final static String TAG = TvShowDetailsActivity.class.getSimpleName();

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
        getTvShowDetails(63926);
    }

    public void getTvShowDetails(final int tvShowId) {
        Map userString = new HashMap();
        userString.put("tvshowsearchid", tvShowId);

        mApiInterface = GopherApi.getApiClient().create(ApiInterface.class);
        Call<TvShow> call = mApiInterface.getTvShowDetails(userString);

        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                Log.i(TAG, "GOT RESPONSE");
                TvShow tvShow = response.body();
                ActionBar ab = getSupportActionBar();
                ab.setTitle(tvShow.getName());
                //Picasso.with(getApplicationContext()).load(getBackdropImg(tvShow.getBackdropPath())).into(backDropImg);
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) { Log.e(TAG, "CALL WAS CANCELLED"); }
            }
        });
    }
}
