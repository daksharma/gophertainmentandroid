package com.gophertainment.gophertainmentandroid;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.model.ShowSeason;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dakshsharma on 7/27/17.
 */

public class TVSeasonAdapter extends RecyclerView.Adapter<TVSeasonAdapter.TVSeasonViewHolder> {

    List<ShowSeason> mShowSeasonsList;
    Context          mContext;


    public TVSeasonAdapter(Context context, List<ShowSeason> results) {
        this.mContext = context;
        this.mShowSeasonsList = results;
    }

    @Override
    public TVSeasonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_crew_cardview, parent, false);
        TVSeasonViewHolder tvsvh = new TVSeasonViewHolder(view);
        return tvsvh;
    }

    @Override
    public void onBindViewHolder(TVSeasonViewHolder holder, int position) {
        holder.tvSeasonText.setText("Season " + mShowSeasonsList.get(position).getSeasonNumber());
        holder.tvEpisodeText.setText(mShowSeasonsList.get(position).getEpisodeCount() + " " +mContext.getResources().getString(R.string.episodesText));
        Picasso.with(mContext).load(getSeasonImg(mShowSeasonsList.get(position).getPosterPath())).into(holder.tvSeasonImg);
    }

    @Override
    public int getItemCount() {
        return mShowSeasonsList.size();
    }

    public String getSeasonImg(String imgPath) {
        Resources res    = mContext.getResources();
        String    imgUrl = res.getString(R.string.poster_image_path);
        return (imgPath != null) ?  imgUrl + imgPath : res.getString(R.string.no_image_found);
    }


    public class TVSeasonViewHolder extends RecyclerView.ViewHolder {

        ImageView tvSeasonImg;
        TextView  tvSeasonText;
        TextView  tvEpisodeText;

        public TVSeasonViewHolder(View itemView) {
            super(itemView);
            tvSeasonImg = (ImageView) itemView.findViewById(R.id.castCrewProfileImage);
            tvSeasonText = (TextView) itemView.findViewById(R.id.castCrewName);
            tvEpisodeText = (TextView) itemView.findViewById(R.id.castCrewJobPosition);
        }
    }
}
