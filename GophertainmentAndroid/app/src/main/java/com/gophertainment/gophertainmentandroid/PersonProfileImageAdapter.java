package com.gophertainment.gophertainmentandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gophertainment.gophertainmentandroid.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dakshsharma on 7/29/17.
 */

public class PersonProfileImageAdapter extends RecyclerView.Adapter<PersonProfileImageAdapter.PersonProfileImageViewHolder> {

    private List<Profile> mProfileImages;
    private Context       ctx;

    public PersonProfileImageAdapter(Context context, List<Profile> profiles) {
        this.mProfileImages = profiles;
        this.ctx = context;
    }

    @Override
    public PersonProfileImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_profile_images, parent, false);
        return new PersonProfileImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonProfileImageViewHolder holder, int position) {
        Picasso.with(ctx).load(getProfileImage(mProfileImages.get(position).getFilePath())).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mProfileImages.size();
    }

    public String getProfileImage(String path) {
        if(path != null) {
            return ctx.getResources().getString(R.string.poster_image_path) + path;
        } else {
            return ctx.getResources().getString(R.string.no_image_found);
        }
    }

    public class PersonProfileImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;

        public PersonProfileImageViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.personProfileImagesView);
        }
    }
}
