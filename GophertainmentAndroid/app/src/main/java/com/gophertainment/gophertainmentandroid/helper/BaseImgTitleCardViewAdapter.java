package com.gophertainment.gophertainmentandroid.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.R;
import com.gophertainment.gophertainmentandroid.model.Cast;
import com.gophertainment.gophertainmentandroid.model.Crew;
import com.gophertainment.gophertainmentandroid.model.ShowSeason;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dakshsharma on 7/29/17.
 */

public class BaseImgTitleCardViewAdapter extends RecyclerView.Adapter<BaseImgTitleCardViewAdapter.BaseImgTitleCardViewHolder> {

    Context context;

    private List<Cast> casts;
    private List<Crew> crew;
    private List<ShowSeason> showSeasons;

    public BaseImgTitleCardViewAdapter(Context context, List<Cast> casts, List<Crew> crew, List<ShowSeason> showSeasons) {
        this.context = context;
        this.casts = casts;
        this.crew = crew;
        this.showSeasons = showSeasons;
    }

    @Override
    public BaseImgTitleCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_img_title_card_view, parent, false);
        return new BaseImgTitleCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseImgTitleCardViewHolder holder, int position) {
        if (casts != null) {
            holder.baseTitleTextView.setText(getCastNameOrTitle(position));
            holder.baseDescriptionTextView.setText(casts.get(position).getCharacter());
            Picasso.with(context).load(getCastProfileOrPoster(position)).into(holder.baseImgView);
        } else if (crew != null) {
            holder.baseTitleTextView.setText(getCrewTitleOrJob(position));
            holder.baseDescriptionTextView.setText(crew.get(position).getJob());
            Picasso.with(context).load(getCrewProfileOrPoster(position)).into(holder.baseImgView);
        } else if (showSeasons != null) {
            holder.baseTitleTextView.setText("Season " + getSeasonNumbers(position));
            holder.baseDescriptionTextView.setText(showSeasons.get(position).getEpisodeCount() + " Episodes");
            Picasso.with(context).load(getProfileImage(showSeasons.get(position).getPosterPath())).into(holder.baseImgView);
        }
    }

    @Override
    public int getItemCount() {
        if (casts != null) return casts.size();
        else if (crew != null) return crew.size();
        else if (showSeasons != null) return showSeasons.size();
        return 0;
    }

    public int getSeasonNumbers(int pos) {
        return (showSeasons.get(pos).getSeasonNumber() != null) ? showSeasons.get(pos).getSeasonNumber() : 0;
    }

    public String getCastNameOrTitle(int pos) {
        return (casts.get(pos).getName() != null) ? casts.get(pos).getName() : casts.get(pos).getTitle();
    }

    public String getCrewTitleOrJob(int pos) {
        return (crew.get(pos).getName() != null) ? crew.get(pos).getName() : crew.get(pos).getTitle();
    }

    public String getCastProfileOrPoster(int pos) {
        return (casts.get(pos).getProfilePath() != null) ? getProfileImage(casts.get(pos).getProfilePath()) :
                getProfileImage(casts.get(pos).getPosterPath());
    }

    public String getCrewProfileOrPoster(int pos) {
        return (crew.get(pos).getProfilePath() != null) ? getProfileImage(crew.get(pos).getProfilePath()) :
                getProfileImage(crew.get(pos).getPosterPath());
    }


    public String getProfileImage(String path) {
        return (path != null) ? context.getResources().getString(R.string.poster_image_path) + path :
                context.getResources().getString(R.string.no_image_found);

    }

    public class BaseImgTitleCardViewHolder extends RecyclerView.ViewHolder {

        ImageView baseImgView;
        TextView baseTitleTextView;
        TextView baseDescriptionTextView;

        public BaseImgTitleCardViewHolder(View itemView) {
            super(itemView);
            baseImgView = (ImageView) itemView.findViewById(R.id.baseCardViewImg);
            baseTitleTextView = (TextView) itemView.findViewById(R.id.baseCardTitleName);
            baseDescriptionTextView = (TextView) itemView.findViewById(R.id.baseCardJobPositionDescription);
        }
    }
}
