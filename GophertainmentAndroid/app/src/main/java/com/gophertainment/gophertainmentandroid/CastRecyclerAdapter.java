package com.gophertainment.gophertainmentandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.model.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dakshsharma on 7/25/17.
 */

public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.CastViewHolder> {

    private final static String TAG = CastRecyclerAdapter.class.getSimpleName();
    private List<Cast> mCasts;
    Context mContext;

    public CastRecyclerAdapter(Context context, List<Cast> cast) {
        this.mCasts = cast;
        this.mContext = context;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_crew_cardview, parent, false);
        CastViewHolder ccvh = new CastViewHolder(view, mContext, mCasts);
        return ccvh;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        holder.castCrewName.setText(mCasts.get(position).getName());
        holder.castCrewJob.setText(mCasts.get(position).getCharacter());
        Picasso.with(mContext).load(getProfileImage(mCasts.get(position).getProfilePath())).into(holder.castCrewProfileImg);
    }

    @Override
    public int getItemCount() { return mCasts.size(); }

    public String getProfileImage(String path) {
        if(path != null) {
            return mContext.getResources().getString(R.string.poster_image_path) + path;
        } else {
            return mContext.getResources().getString(R.string.no_image_found);
        }
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder {

        ImageView castCrewProfileImg;
        TextView castCrewName;
        TextView castCrewJob;

        public CastViewHolder(View itemView, Context context, List<Cast> cast) {
            super(itemView);
            castCrewProfileImg = (ImageView) itemView.findViewById(R.id.castCrewProfileImage);
            castCrewName = (TextView) itemView.findViewById(R.id.castCrewName);
            castCrewJob = (TextView) itemView.findViewById(R.id.castCrewJobPosition);
        }
    }
}
