package com.example.fadii.bloodbank.navigationScreens.favoritePk;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fadii.bloodbank.MAIN.ArticlesPK.ArticleDatum;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.Article_Detail.DiseasepreventActivity;

import java.util.ArrayList;
import java.util.List;

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.myViewHolder3> {
    Context mContext3;
    ArrayList<FavouriteDatum> mdata3;

    public favouriteAdapter(Context mContext3, ArrayList<FavouriteDatum> mdata3) {
        this.mContext3 = mContext3;
        this.mdata3 = mdata3;
    }

    @NonNull
    @Override
    public myViewHolder3 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext3).inflate(R.layout.item_favorite, viewGroup, false);
        myViewHolder3 vHolder3 = new myViewHolder3(v);
        return vHolder3;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder3 myViewHolder3, int position) {

        final FavouriteDatum articleDatum = mdata3.get(position);
        myViewHolder3.header_txt.setText(mdata3.get(position).getTitle());
        Glide.with(mContext3).load(articleDatum.getThumbnailFullPath())
                .apply(new RequestOptions().override(400, 200))
                .into(myViewHolder3.article_image);

        myViewHolder3.favouritVIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext3, DiseasepreventActivity.class);
                mContext3.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata3.size();
    }


    public class myViewHolder3 extends RecyclerView.ViewHolder {

        private TextView header_txt;
        private ImageView article_image;
        private LinearLayout favouritVIEW;

        public myViewHolder3(@NonNull View itemView) {
            super(itemView);

            header_txt = itemView.findViewById(R.id.favourite_headerID);
            article_image = itemView.findViewById(R.id.favourite_imageID);
            favouritVIEW = itemView.findViewById(R.id.fav_ViewID);

        }
    }

}
