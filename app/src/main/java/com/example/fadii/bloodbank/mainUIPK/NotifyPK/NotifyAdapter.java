package com.example.fadii.bloodbank.mainUIPK.NotifyPK;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fadii.bloodbank.R;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.myNotifyViewHolder> {

    Context mContext4;
    List<Notify_Datum> mdata4;

    public NotifyAdapter(Context mContext4, List<Notify_Datum> mdata4) {
        this.mContext4 = mContext4;
        this.mdata4 = mdata4;
    }

    @NonNull
    @Override
    public myNotifyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext4).inflate(R.layout.notify_item, viewGroup, false);
        myNotifyViewHolder vHolder4 = new myNotifyViewHolder(v);
        return vHolder4;
    }

    @Override
    public void onBindViewHolder(@NonNull myNotifyViewHolder myNotifyViewHolder, int i) {
        final Notify_Datum notify_datum = mdata4.get(i);

        int r =notify_datum.getPivot().getIsRead();

        if (r == 0){
            Glide.with(mContext4).load(R.mipmap.readnotify).into(myNotifyViewHolder.notify_image);
        } else {
            Glide.with(mContext4).load(R.drawable.notify).into(myNotifyViewHolder.notify_image);
        }
        myNotifyViewHolder.notify_Text.setText(mdata4.get(i).getTitle());
       myNotifyViewHolder.notify_Date.setText(mdata4.get(i).getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return mdata4.size();
    }

    public class myNotifyViewHolder extends RecyclerView.ViewHolder {

        private TextView notify_Text;
        private ImageView notify_image;
        private TextView notify_Date;

        public myNotifyViewHolder(@NonNull View itemView) {
            super(itemView);

            notify_Text = itemView.findViewById(R.id.notifyTEXT);
            notify_image = itemView.findViewById(R.id.notifyImage);
            notify_Date = itemView.findViewById(R.id.dateTEXT);
        }
    }
}
