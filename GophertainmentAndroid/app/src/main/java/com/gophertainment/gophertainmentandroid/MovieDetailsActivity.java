package com.gophertainment.gophertainmentandroid;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.model.Movie;
import com.gophertainment.gophertainmentandroid.network.ApiInterface;
import com.gophertainment.gophertainmentandroid.network.GopherApi;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    private final static String TAG = MovieDetailsActivity.class.getSimpleName();

    ImageView backDropImg;
    TextView movieTagline;
    TextView movieOverview;

    Toolbar                 movieDetailToolBar;
    CollapsingToolbarLayout movieDetailCollapseToolBarLayout;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setUpMovieDetailUI();
        String movieId = getApplicationContext().getString(R.string.movieId);
        int movieID = getIntent().getIntExtra(movieId, 0);
        getMovieDetail(movieID);

    }

    public void setUpMovieDetailUI() {
        backDropImg = (ImageView) findViewById(R.id.movieBackDropImage);
        movieTagline = (TextView) findViewById(R.id.movieTaglineText);
        movieOverview = (TextView) findViewById(R.id.movieOverViewText);

        movieDetailToolBar = (Toolbar) findViewById(R.id.movieDetailToolBar);
        setSupportActionBar(movieDetailToolBar);
        movieDetailCollapseToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.movieDetailCollapsingToolBar);
    }


    public void getMovieDetail(final int movieId) {
        Map userString = new HashMap();
        userString.put(getApplicationContext().getString(R.string.moviesearchid), movieId);

        mApiInterface = GopherApi.getApiClient().create(ApiInterface.class);
        Call<Movie> call = mApiInterface.getMovieDetails(userString);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.i(TAG, "GOT RESPONSE");
                Movie     movieDetails = response.body();
                movieDetailCollapseToolBarLayout.setTitle(movieDetails.getTitle());
                movieTagline.setText((movieDetails.getTagline() != null) ? movieDetails.getTagline() : "");
                movieOverview.setText((movieDetails.getOverview() != null) ? movieDetails.getOverview() : "");
                Picasso.with(getApplicationContext()).load(getBackdropImg(movieDetails.getBackdropPath())).into(backDropImg);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) { Log.e(TAG, "CALL WAS CANCELLED"); }
            }
        });
    }

    public String getBackdropImg(String bd) {
        return (bd != null) ? getResources().getString(R.string.backdrop_image_path) + bd
                : getResources().getString(R.string.no_image_found);
    }

}
