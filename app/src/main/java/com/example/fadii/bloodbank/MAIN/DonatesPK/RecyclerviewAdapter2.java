package com.example.fadii.bloodbank.MAIN.DonatesPK;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fadii.bloodbank.R;
import com.example.fadii.bloodbank.mainUIPK.DonationDetail.DonatordetailsActivity;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class RecyclerviewAdapter2 extends RecyclerView.Adapter<RecyclerviewAdapter2.MyviewHolder2> {

    Context mcontext2;
    List<DonationDatum> mData2;

    public RecyclerviewAdapter2(Context mcontext2, List<DonationDatum> mData2) {
        this.mcontext2 = mcontext2;
        this.mData2 = mData2;
    }

    @NonNull
    @Override
    public MyviewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(mcontext2).inflate(R.layout.item_reqdonation,parent,false);


        MyviewHolder2 vHolder = new MyviewHolder2(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder2 holder2, final int position) {

        holder2.donatorName.setText(mData2.get(position).getPatientName());
        holder2.hospital.setText(mData2.get(position).getHospitalName());
        holder2.bloodtext.setText(mData2.get(position).getBloodType());
        holder2.city.setText(mData2.get(position).getCity().getName());
        holder2.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = mData2.get(position).getPhone();

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+phone));
                if (ActivityCompat.checkSelfPermission(mcontext2, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    requestPermission();
                }
                else {
                    startActivity(mcontext2,i,null);
                }
            }

            private void requestPermission(){
                ActivityCompat.requestPermissions((Activity) mcontext2,new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });

        holder2.Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mcontext2,DonatordetailsActivity.class);
                startActivity(mcontext2,intent,null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData2.size();
    }

    public static class MyviewHolder2 extends RecyclerView.ViewHolder {

        private TextView donatorName;
        private TextView hospital;
        private TextView city;
        private TextView bloodtext;
        private Button call;
        private Button Detail;

        public MyviewHolder2(@NonNull View itemView) {
            super(itemView);

            donatorName = itemView.findViewById(R.id.donator_nameID);
            hospital = itemView.findViewById(R.id.hospital_nameID);
            city = itemView.findViewById(R.id.city_nameID);
            bloodtext = itemView.findViewById(R.id.text_bloodID);
            call = itemView.findViewById(R.id.contact_Button);
            Detail = itemView.findViewById(R.id.detailrequest_ButtonID);
        }
    }
}
