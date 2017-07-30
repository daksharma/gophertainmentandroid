package com.gophertainment.gophertainmentandroid;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.helper.BaseImgTitleCardViewAdapter;
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
    TextView                tvshowSeasons;
    TextView                tvshowEpisodes;
    Toolbar                 tvshowToolBar;
    CollapsingToolbarLayout tvshowCollapseToolBarLayout;

    private RecyclerView               mCastRecyclerView;
    private RecyclerView.Adapter       mCastAdapter;
    private RecyclerView.LayoutManager mCastLayoutManager;

    private RecyclerView mCrewRecyclerView;
    private RecyclerView.Adapter mCrewAdapter;
    private RecyclerView.LayoutManager mCrewLayoutManager;

    private RecyclerView mSeasonRecyclerView;
    private RecyclerView.Adapter mSeasonAdapter;
    private RecyclerView.LayoutManager mSeasonLayoutManager;

    private CardView tvshowCastCardView;
    private CardView tvshowCrewCardView;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
        setUpTvShowUI();
        String tvShowId = getApplicationContext().getString(R.string.tvShowId);
        int    tvShowID = getIntent().getIntExtra(tvShowId, 0);
        getTvShowDetails(tvShowID);
    }

    public void setUpTvShowUI() {
        tvshowBackdrop = (ImageView) findViewById(R.id.tvshowBackdrop);
        tvshowOverView = (TextView) findViewById(R.id.tvShowOverView);
        tvshowSeasons = (TextView) findViewById(R.id.numberOfSeasons);
        tvshowEpisodes = (TextView) findViewById(R.id.numberOfEpisodes);
        tvshowToolBar = (Toolbar) findViewById(R.id.tvshowToolBar);
        setSupportActionBar(tvshowToolBar);
        tvshowCollapseToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.tvshowCollapsingToolBar);

        tvshowCastCardView = (CardView) findViewById(R.id.tvshowCastCardView);
        tvshowCrewCardView = (CardView) findViewById(R.id.tvshowCrewCardView);

        mSeasonRecyclerView = (RecyclerView) findViewById(R.id.tvshowSeasonRecView);
        mSeasonRecyclerView.setHasFixedSize(true);
        mSeasonLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mSeasonRecyclerView.setLayoutManager(mSeasonLayoutManager);

        mCastRecyclerView = (RecyclerView) findViewById(R.id.tvshowCastRevView);
        mCastRecyclerView.setHasFixedSize(true);
        mCastLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCastRecyclerView.setLayoutManager(mCastLayoutManager);

        mCrewRecyclerView = (RecyclerView) findViewById(R.id.tvshowCrewRecView);
        mCrewRecyclerView.setHasFixedSize(true);
        mCrewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCrewRecyclerView.setLayoutManager(mCrewLayoutManager);

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
                tvshowSeasons.setText(tvShow.getNumberOfSeasons().toString());
                tvshowEpisodes.setText(tvShow.getNumberOfEpisodes().toString());
                Picasso.with(getApplicationContext()).load(getBackdropImg(tvShow.getBackdropPath())).into(tvshowBackdrop);

                if (tvShow.getSeasons() != null) {
                    mSeasonAdapter = new BaseImgTitleCardViewAdapter(getApplicationContext(), null, null, tvShow.getSeasons());
                    mSeasonRecyclerView.setAdapter(mSeasonAdapter);
                } else {
                    mSeasonRecyclerView.setVisibility(View.GONE);
                }

                if (tvShow.getCredits().getCast() != null) {
                    mCastAdapter = new BaseImgTitleCardViewAdapter(getApplicationContext(), tvShow.getCredits().getCast(), null, null);
                    mCastRecyclerView.setAdapter(mCastAdapter);
                } else {
                    tvshowCastCardView.setVisibility(View.GONE);
                }
                if (tvShow.getCredits().getCrew() != null) {
                    mCrewAdapter = new BaseImgTitleCardViewAdapter(getApplicationContext(), null, tvShow.getCredits().getCrew(), null);
                    mCrewRecyclerView.setAdapter(mCrewAdapter);
                } else {
                    tvshowCrewCardView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) {
                    Log.e(TAG, "CALL WAS CANCELLED");
                }
            }
        });
    }

    public String getBackdropImg(String bd) {
        return (bd != null) ? (getResources().getString(R.string.backdrop_image_path) + bd)
                : getResources().getString(R.string.no_image_found);
    }
}
