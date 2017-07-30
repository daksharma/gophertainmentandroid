package com.gophertainment.gophertainmentandroid;

import android.content.Context;
import android.content.Intent;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_card_view,parent,false);
        ResultViewHolder rvh = new ResultViewHolder(view, mContext, results);
        return rvh;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.TitleName.setText(results.get(position).getTitleOrName());
        holder.MediaType.setImageResource(getResulMediaType(results.get(position).getMediaType()));

        //TODO: fix popularity text size in xml
        holder.Popularity.setText(String.format("%.1f",results.get(position).getPopularity()));

//        holder.FirstReleaseDate.setText(firstReleaseDate(position));
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
            return res.getString(R.string.no_image_found);
        }
    }


    public int getResulMediaType(String mediaType) {
        if (mediaType.equalsIgnoreCase("movie")) {
            return R.drawable.ic_movie_color_icon;
        } else if (mediaType.equalsIgnoreCase("tv")) {
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


    public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        List<MultiSearchResult> mSearchResults;
        Context ctx;
        ImageView PosterImg;
        ImageView MediaType;
        TextView  Popularity;
        TextView  TitleName;
        TextView  FirstReleaseDate;

        public ResultViewHolder(View itemView, Context context, List<MultiSearchResult> results) {
            super(itemView);
            this.ctx = context;
            this.mSearchResults = results;
            itemView.setOnClickListener(this);
            this.PosterImg = (ImageView) itemView.findViewById(R.id.resultCVImage);
            this.MediaType = (ImageView) itemView.findViewById(R.id.resultMediaType);
            this.Popularity = (TextView) itemView.findViewById(R.id.resultRatingNumber);
            this.TitleName = (TextView) itemView.findViewById(R.id.resultNameTitleTV);
            this.FirstReleaseDate = (TextView) itemView.findViewById(R.id.resultReleaseDate);
        }

        @Override
        public void onClick(View v) { routeDetailType(results.get(getAdapterPosition())); }

        public void routeDetailType(MultiSearchResult res) {
            Intent intent;
            if (res.getMediaType().equalsIgnoreCase("movie")) {
                intent = new Intent(this.ctx, MovieDetailsActivity.class);
                intent.putExtra(this.ctx.getString(R.string.movieId), res.getID());
                this.ctx.startActivity(intent);
            } else if (res.getMediaType().equalsIgnoreCase("tv")) {
                intent = new Intent(this.ctx, TvShowDetailsActivity.class);
                intent.putExtra(this.ctx.getString(R.string.tvShowId), res.getID());
                this.ctx.startActivity(intent);
            } else {
                intent = new Intent(this.ctx, PersonDetailActivity.class);
                intent.putExtra(this.ctx.getString(R.string.personId), res.getID());
                intent.putExtra("personName", res.getName());
                this.ctx.startActivity(intent);
            }
        }
    }

}


