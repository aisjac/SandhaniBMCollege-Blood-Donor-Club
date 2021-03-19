package com.example.emran;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView session,name,phone,dept,desig,desigType,msN,blood,lastDonate;
    CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardViewId);

        imageView = itemView.findViewById(R.id.imageViewId);
        session = itemView.findViewById(R.id.sessionTextViewId);
        name = itemView.findViewById(R.id.nameTextViewId);
        phone = itemView.findViewById(R.id.phoneTextViewId);
        dept = itemView.findViewById(R.id.departmentTextViewId);
        desig = itemView.findViewById(R.id.desigTextViewId);
        desigType = itemView.findViewById(R.id.desigTypeTextViewId);
        msN = itemView.findViewById(R.id.msNumberTextViewId);
        blood = itemView.findViewById(R.id.bloodgroupTextViewId);
        lastDonate = itemView.findViewById(R.id.lastDonationTextViewId);


    }
}
