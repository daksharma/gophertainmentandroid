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

import com.gophertainment.gophertainmentandroid.model.Movie;
import com.gophertainment.gophertainmentandroid.network.ApiInterface;
import com.gophertainment.gophertainmentandroid.network.GopherApi;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    private final static String TAG = MovieDetailsActivity.class.getSimpleName();

    private ImageView backDropImg;
    private TextView movieTagline;
    private TextView movieOverview;
    private TextView movieReleaseDate;


    private Toolbar                 movieDetailToolBar;
    private CollapsingToolbarLayout movieDetailCollapseToolBarLayout;

    private RecyclerView               mCastRecyclerView;
    private RecyclerView.Adapter       mCastAdapter;
    private RecyclerView.LayoutManager mCastLayoutManager;

    private RecyclerView mCrewRecyclerView;
    private RecyclerView.Adapter mCrewAdapter;
    private RecyclerView.LayoutManager mCrewLayoutManager;

    private CardView movieCastCardView;
    private CardView movieCrewCardView;

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
        movieReleaseDate = (TextView) findViewById(R.id.movieReleaseDate);

        movieCastCardView = (CardView) findViewById(R.id.movieCastCardView);
        movieCrewCardView = (CardView) findViewById(R.id.movieCrewCardView);

        movieDetailToolBar = (Toolbar) findViewById(R.id.movieDetailToolBar);
        setSupportActionBar(movieDetailToolBar);
        movieDetailCollapseToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.movieDetailCollapsingToolBar);

        mCastRecyclerView = (RecyclerView) findViewById(R.id.movieCastRecView);
        mCastRecyclerView.setHasFixedSize(true);
        mCastLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCastRecyclerView.setLayoutManager(mCastLayoutManager);

        mCrewRecyclerView = (RecyclerView) findViewById(R.id.movieCrewRecView);
        mCrewRecyclerView.setHasFixedSize(true);
        mCrewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCrewRecyclerView.setLayoutManager(mCrewLayoutManager);
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
                movieReleaseDate.setText(formatReleaseDate(movieDetails.getReleaseDate()));
                Picasso.with(getApplicationContext()).load(getBackdropImg(movieDetails.getBackdropPath())).into(backDropImg);
                if (movieDetails.getMovieCredit().getCast() != null) {
                    mCastAdapter = new CastRecyclerAdapter(getApplicationContext(), movieDetails.getMovieCredit().getCast());
                    mCastRecyclerView.setAdapter(mCastAdapter);
                } else {
                    movieCastCardView.setVisibility(View.GONE);
                }
                if (movieDetails.getMovieCredit().getCrew() != null) {
                    mCrewAdapter = new CrewRecyclerAdapter(getApplicationContext(), movieDetails.getMovieCredit().getCrew());
                    mCrewRecyclerView.setAdapter(mCrewAdapter);
                } else {
                    movieCrewCardView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) { Log.e(TAG, "CALL WAS CANCELLED"); }
            }
        });
    }

    public String formatReleaseDate(String d) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(d);
            sdf = new SimpleDateFormat("E, MMM d, yyyy");
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public String getBackdropImg(String bd) {
        return (bd != null) ? getResources().getString(R.string.backdrop_image_path) + bd
                : getResources().getString(R.string.no_image_found);
    }

}
