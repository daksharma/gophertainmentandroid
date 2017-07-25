package com.gophertainment.gophertainmentandroid;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.model.TvShow;
import com.gophertainment.gophertainmentandroid.network.ApiInterface;
import com.gophertainment.gophertainmentandroid.network.GopherApi;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailsActivity extends AppCompatActivity {

    private final static String TAG = TvShowDetailsActivity.class.getSimpleName();

    ImageView               tvshowBackdrop;
    TextView                tvshowOverView;
    Toolbar                 tvshowToolBar;
    CollapsingToolbarLayout tvshowCollapseToolBarLayout;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
        setUpTvShowUI();
        String tvShowId = getApplicationContext().getString(R.string.tvShowId);
        int tvShowID = getIntent().getIntExtra(tvShowId, 0);
        getTvShowDetails(tvShowID);
    }

    public void setUpTvShowUI() {
        tvshowBackdrop = (ImageView) findViewById(R.id.tvshowBackdrop);
        tvshowOverView = (TextView) findViewById(R.id.tvShowOverView);
        tvshowToolBar = (Toolbar) findViewById(R.id.tvshowToolBar);
        setSupportActionBar(tvshowToolBar);
        tvshowCollapseToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.tvshowCollapsingToolBar);

    }

    public void getTvShowDetails(final int tvShowId) {
        Map userString = new HashMap();
        userString.put(getApplicationContext().getString(R.string.tvshowsearchid), tvShowId);

        mApiInterface = GopherApi.getApiClient().create(ApiInterface.class);
        Call<TvShow> call = mApiInterface.getTvShowDetails(userString);

        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                Log.i(TAG, "GOT RESPONSE");
                TvShow tvShow = response.body();
                tvshowCollapseToolBarLayout.setTitle(tvShow.getName());
                tvshowOverView.setText(tvShow.getOverview());
                Picasso.with(getApplicationContext()).load(getBackdropImg(tvShow.getBackdropPath())).into(tvshowBackdrop);
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) { Log.e(TAG, "CALL WAS CANCELLED"); }
            }
        });
    }

    public String getBackdropImg(String bd) {
        return (bd != null) ? (getResources().getString(R.string.backdrop_image_path) + bd)
                : getResources().getString(R.string.no_image_found);
    }
}
