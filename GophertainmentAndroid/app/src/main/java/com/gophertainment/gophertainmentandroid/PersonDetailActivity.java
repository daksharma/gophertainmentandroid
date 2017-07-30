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
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.helper.BaseImgTitleCardViewAdapter;
import com.gophertainment.gophertainmentandroid.helper.DateFormatter;
import com.gophertainment.gophertainmentandroid.model.Person;
import com.gophertainment.gophertainmentandroid.network.ApiInterface;
import com.gophertainment.gophertainmentandroid.network.GopherApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dakshsharma on 7/29/17.
 */

public class PersonDetailActivity extends AppCompatActivity {

    private static final String TAG = PersonDetailActivity.class.getSimpleName();

    private ApiInterface mApiInterface;

    private Toolbar mPersonDetailToolBar;
    private CollapsingToolbarLayout mPersonCollapsingToolBarLayout;

    private TextView mPersonBiography;
    private TextView mPersonBirthDay;
    private TextView mPersonBirthPlace;


    private RecyclerView mPersonProfileImagesRecyclerView;
    private RecyclerView.Adapter mPersonProfileImagesAdapter;
    private RecyclerView.LayoutManager mPersonProfileImagesLayoutManager;


    private RecyclerView mPersonActingRecView;
    private RecyclerView.Adapter mPersonActingAdapter;
    private RecyclerView.LayoutManager mPersonActingLayoutManager;

    private RecyclerView mPersonBehindSceneRecView;
    private RecyclerView.Adapter mPersonBehindSceneAdapter;
    private RecyclerView.LayoutManager mPersonBehindSceneLayoutManager;

    private CardView mPersonActingCardView;
    private CardView mPersonBehindSceneCardView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        setPersonDetailUI();

        String personId = getApplicationContext().getString(R.string.personId);
        int pId = getIntent().getIntExtra(personId, 0);
        String personName = getIntent().getStringExtra("personName");
        mPersonCollapsingToolBarLayout.setTitle(personName);
        getPersonDetails(pId);
    }

    public void setPersonDetailUI() {
        mPersonDetailToolBar = (Toolbar) findViewById(R.id.personDetailToolbar);
        setSupportActionBar(mPersonDetailToolBar);
        mPersonCollapsingToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.personDetailCollapseToolBar);

        mPersonBiography = (TextView) findViewById(R.id.personDetailBiography);
        mPersonBirthDay = (TextView) findViewById(R.id.personBirthDay);
        mPersonBirthPlace = (TextView) findViewById(R.id.personBirthPlace);

        mPersonActingCardView = (CardView) findViewById(R.id.personActingCardView);
        mPersonBehindSceneCardView = (CardView) findViewById(R.id.personBehindSceneCardView);

        // Person Profile Images
        mPersonProfileImagesRecyclerView = (RecyclerView) findViewById(R.id.personProfileImagesRecView);
        mPersonProfileImagesRecyclerView.setHasFixedSize(true);
        mPersonProfileImagesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mPersonProfileImagesRecyclerView.setLayoutManager(mPersonProfileImagesLayoutManager);

        // Person Acting Works
        mPersonActingRecView = (RecyclerView) findViewById(R.id.personActingCareerRecView);
        mPersonActingRecView.setHasFixedSize(true);
        mPersonActingLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mPersonActingRecView.setLayoutManager(mPersonActingLayoutManager);

        // Person Behind The Scene Works
        mPersonBehindSceneRecView = (RecyclerView) findViewById(R.id.personBehindTheScreenRecView);
        mPersonBehindSceneRecView.setHasFixedSize(true);
        mPersonBehindSceneLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mPersonBehindSceneRecView.setLayoutManager(mPersonBehindSceneLayoutManager);
    }

    public void getPersonDetails(int personId) {
        Map userString = new HashMap();
        userString.put(getApplicationContext().getString(R.string.personsearchid), personId);

        mApiInterface = GopherApi.getApiClient().create(ApiInterface.class);
        Call<Person> call = mApiInterface.getPersonDetails(userString);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.i(TAG, "GOT RESPONSE");
                Person p = response.body();
                if (p.getBiography() != null) { mPersonBiography.setText(p.getBiography()); }
                if (p.getPlaceOfBirth() != null) { mPersonBirthPlace.setText(p.getPlaceOfBirth()); }
                mPersonBirthDay.setText(DateFormatter.getReleaseDateFormat(p.getBirthday()));

                if (p.getImages().getProfiles() != null) {
                    mPersonProfileImagesAdapter = new PersonProfileImageAdapter(getApplicationContext(), p.getImages().getProfiles());
                    mPersonProfileImagesRecyclerView.setAdapter(mPersonProfileImagesAdapter);
                }

                if (p.getCredits().getCast() != null) {
                    mPersonActingAdapter = new BaseImgTitleCardViewAdapter(getApplicationContext(), p.getCredits().getCast(), null, null);
                    mPersonActingRecView.setAdapter(mPersonActingAdapter);
                } else {
                    mPersonActingCardView.setVisibility(View.GONE);
                }

                if (p.getCredits().getCrew() != null) {
                    mPersonBehindSceneAdapter = new BaseImgTitleCardViewAdapter(getApplicationContext(), null, p.getCredits().getCrew(), null);
                    mPersonBehindSceneRecView.setAdapter(mPersonBehindSceneAdapter);
                } else {
                    mPersonBehindSceneCardView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.toString());
                if (call.isCanceled()) { Log.e(TAG, "CALL WAS CANCELLED"); }
            }
        });
    }
}
