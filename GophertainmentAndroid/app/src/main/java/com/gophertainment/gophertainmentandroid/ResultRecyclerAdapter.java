package com.gophertainment.gophertainmentandroid;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.model.MultiSearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dakshsharma on 7/23/17.
 */

public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.ResultViewHolder> {

    private final static String TAG = ResultViewHolder.class.getSimpleName();
    private Context mContext;

    private List<MultiSearchResult> results;


    public ResultRecyclerAdapter(Context context, List<MultiSearchResult> results) {
        this.mContext = context;
        this.results = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_rowitem,parent,false);
        return new ResultViewHolder((view));
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.TitleName.setText(getResultTitleOrName(position));
        holder.MediaType.setImageResource(getResulMediaType(position));
        holder.Popularity.setText(String.format("%.1f",results.get(position).getPopularity()));
        holder.FirstReleaseDate.setText(firstReleaseDate(position));
        Picasso.with(mContext).load(getPosterImageUrl(position)).into(holder.PosterImg);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public String getPosterImageUrl(int position) {
        Resources res = mContext.getResources();
        String imgUrl = res.getString(R.string.poster_image_path);
        if (results.get(position).getPosterPath() != null) {
            return imgUrl + results.get(position).getPosterPath();
        } else if (results.get(position).getProfilePath() != null) {
            return imgUrl + results.get(position).getProfilePath();
        } else {
            return "https://assets.tmdb.org/assets/1c4aa0e7695a4eebe9a4d2c34a93bf34/images/no-poster-w600_and_h900_bestv2-v2.png";
        }
    }



    public String getResultTitleOrName(int position) {
        String movieTvActorName;
        if (results.get(position).getTitle() != null) {
            movieTvActorName = results.get(position).getTitle();
        } else if (results.get(position).getName() != null) {
            movieTvActorName = results.get(position).getName();
        } else {
            movieTvActorName = "N/A";
        }
        return movieTvActorName;
    }

    public int getResulMediaType(int position) {
        System.out.println(results.get(position).getMediaType());
        if (results.get(position).getMediaType().equalsIgnoreCase("movie")) {
            return R.drawable.ic_movie_color_icon;
        } else if (results.get(position).getMediaType().equalsIgnoreCase("tv")) {
            return R.drawable.ic_tvshow_color_icon;
        } else {
            return R.drawable.ic_profile_color_icon;
        }
    }

    public String firstReleaseDate(int position) {
        if (results.get(position).getReleaseDate() != null) {
            return results.get(position).getReleaseDate();
        } else if (results.get(position).getFirstAirDate() != null) {
            return results.get(position).getFirstAirDate();
        } else {
            return "N/A";
        }
    }


    public class ResultViewHolder extends RecyclerView.ViewHolder {

        ImageView PosterImg;
        ImageView MediaType;
        TextView  Popularity;
        TextView  TitleName;
        TextView  FirstReleaseDate;

        public ResultViewHolder(View itemView) {
            super(itemView);
            PosterImg = (ImageView) itemView.findViewById(R.id.resultPosterImage);
            MediaType = (ImageView) itemView.findViewById(R.id.resultMediaType);
            Popularity = (TextView) itemView.findViewById(R.id.resultPopularityTV);
            TitleName = (TextView) itemView.findViewById(R.id.resultTitleName);
            FirstReleaseDate = (TextView) itemView.findViewById(R.id.resultReleaseDate);
        }
    }

}


