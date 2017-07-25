package com.gophertainment.gophertainmentandroid;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class MovieDetails extends AppCompatActivity {
    private final static String TAG = MovieDetails.class.getSimpleName();
    ActionBar ab = getSupportActionBar();

    ImageView backDropImg;
    TextView movieTitle;
    TextView movieTagline;
    TextView movieOverview;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setUpMovieDetailUI();
        int movieID = getIntent().getIntExtra("movieId", 0);
        getMovieDetail(movieID);

    }

    public void setUpMovieDetailUI() {
        backDropImg = (ImageView) findViewById(R.id.movieBackDropImage);
        movieTitle = (TextView) findViewById(R.id.movieTitleText);
        movieTagline = (TextView) findViewById(R.id.movieTaglineText);
        movieOverview = (TextView) findViewById(R.id.movieOverViewText);
    }


    public void getMovieDetail(final int movieId) {
        Map userString = new HashMap();
        userString.put("moviesearchid", movieId);

        mApiInterface = GopherApi.getApiClient().create(ApiInterface.class);
        Call<Movie> call = mApiInterface.getMovieDetails(userString);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.i(TAG, "GOT RESPONSE");
                Movie     movieDetails = response.body();
                ab.setTitle(movieDetails.getTitle());
                movieTitle.setText(movieDetails.getTitle());
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
        Resources res    = getResources();
        String    imgUrl = res.getString(R.string.poster_image_path);
        if (bd != null) {
            return imgUrl + bd;
        } else {
            return res.getString(R.string.no_image_found);
        }
    }

}
