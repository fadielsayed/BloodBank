package com.example.fadii.bloodbank.MAIN.ArticlesPK;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.Article_Detail.DiseasepreventActivity;
import com.example.fadii.bloodbank.registerPK.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewAdapter1 extends RecyclerView.Adapter<RecyclerviewAdapter1.myViewHolder1> {

    Context mContext;
    List<ArticleDatum> mdata;


    public RecyclerviewAdapter1(Context mContext, List<ArticleDatum> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public myViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false);
        myViewHolder1 vHolder = new myViewHolder1(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder1 holder1, final int position) {
        final ArticleDatum articleDatum = mdata.get(position);
        holder1.header_txt.setText(mdata.get(position).getTitle());
        Glide.with(mContext).load(articleDatum.getThumbnailFullPath())
                .apply(new RequestOptions().override(400, 200))
                .into(holder1.article_image);

        if (articleDatum.isIs_favourite()) {
            holder1.favcheckbox.setChecked(true);
        } else {
            holder1.favcheckbox.setChecked(false);
        }

        holder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DiseasepreventActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder1.favcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("isChecked", "is checked");

                if (holder1.favcheckbox.isChecked()) {
                    boolean check = addToFavourite(mdata.get(position).getId());

                    if (check) {
                        Log.i("isChecked", "start");
                        mdata.get(position).setIs_favourite(true);
                        notifyDataSetChanged();
                    }
                } else {

                    boolean check = addToFavourite(mdata.get(position).getId());

                    if (check) {
                        Log.i("isChecked", "start");
                        mdata.get(position).setIs_favourite(false);
                        notifyDataSetChanged();

                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class myViewHolder1 extends RecyclerView.ViewHolder

    {

        private TextView header_txt;
        private ImageView article_image;
        private LinearLayout linearLayout;
        private CheckBox favcheckbox;

        public myViewHolder1(@NonNull View itemView) {
        super(itemView);

        header_txt = itemView.findViewById(R.id.article_headerID);
        article_image = itemView.findViewById(R.id.article_imageID);
        linearLayout = itemView.findViewById(R.id.postViewID);
        favcheckbox = itemView.findViewById(R.id.checkedBT);

    }
    }


    public Boolean addToFavourite(int id) {
        final boolean[] isChecked = {false};
        Call<ArticleUser> call = RetrofitClient.getInstance().getApi()
                .addFavourite(id, "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
        call.enqueue(new Callback<ArticleUser>() {
            @Override
            public void onResponse(Call<ArticleUser> call, Response<ArticleUser> response) {
                ArticleUser user = response.body();

//                Boolean data = user.getData().getData().get(0).isIs_favourite();

                if (user.getStatus() == 1) {
                    isChecked[0] = true;


                } else {
                    isChecked[0] = false;
                }
            }

            @Override
            public void onFailure(Call<ArticleUser> call, Throwable t) {
                Log.i("isChecked", "error  " + t.getMessage());
                isChecked[0] = false;
            }
        });

        return isChecked[0];
    }
}
