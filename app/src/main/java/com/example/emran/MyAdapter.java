package com.example.emran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<PosoClass> MyItem;
    Context context;

    public MyAdapter(List<PosoClass> myItem, Context context) {
        MyItem = myItem;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.display_sample,viewGroup,false);
        MyViewHolder MVH = new MyViewHolder(view);
        return MVH;


    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final PosoClass itemPosition = MyItem.get(i);

//        myViewHolder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));


        myViewHolder.session.setText(itemPosition.getSession());
        myViewHolder.name.setText(itemPosition.getFullName());
        myViewHolder.phone.setText(itemPosition.getPhoneNumber());
        myViewHolder.dept.setText(itemPosition.getDepartmentName());
        myViewHolder.desig.setText(itemPosition.getDesignation());
        myViewHolder.desigType.setText(itemPosition.getDesignationType());
        myViewHolder.msN.setText(itemPosition.getMS_No());
        myViewHolder.blood.setText(itemPosition.getBloodGroup());
        myViewHolder.lastDonate.setText(itemPosition.getLastDonation());

        Glide.with(context)
                .load(itemPosition.getImageUrl())
                .into(myViewHolder.imageView);

//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Good Job !", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return MyItem.size();
    }
}
