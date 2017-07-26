package com.gophertainment.gophertainmentandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gophertainment.gophertainmentandroid.model.Crew;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dakshsharma on 7/25/17.
 */

public class CrewRecyclerAdapter extends RecyclerView.Adapter<CrewRecyclerAdapter.CrewViewHolder> {

    private List<Crew> mCrews;
    Context mContext;


    public CrewRecyclerAdapter(Context context, List<Crew> results) {
        this.mContext = context;
        this.mCrews = results;
    }

    @Override
    public CrewRecyclerAdapter.CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_crew_cardview, parent, false);
        CrewViewHolder cvh = new CrewViewHolder(view, mContext, mCrews);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CrewViewHolder holder, int position) {
        holder.crewName.setText(mCrews.get(position).getName());
        holder.crewJob.setText(mCrews.get(position).getJob());
        Picasso.with(mContext).load(getProfileImage(mCrews.get(position).getProfilePath())).into(holder.crewProfileImg);
    }

    @Override
    public int getItemCount() { return mCrews.size(); }

    public String getProfileImage(String path) {
        if(path != null) {
            return mContext.getResources().getString(R.string.poster_image_path) + path;
        } else {
            return mContext.getResources().getString(R.string.no_image_found);
        }
    }

    public class CrewViewHolder extends RecyclerView.ViewHolder {


        ImageView crewProfileImg;
        TextView crewName;
        TextView crewJob;


        public CrewViewHolder(View itemView, Context context, List<Crew> crewList) {
            super(itemView);
            this.crewProfileImg = (ImageView) itemView.findViewById(R.id.castCrewProfileImage);
            this.crewName = (TextView) itemView.findViewById(R.id.castCrewName);
            this.crewJob = (TextView) itemView.findViewById(R.id.castCrewJobPosition);

        }
    }
}
