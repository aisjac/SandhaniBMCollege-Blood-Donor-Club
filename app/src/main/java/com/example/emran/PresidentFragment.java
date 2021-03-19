package com.example.emran;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PresidentFragment extends Fragment {

    MyAdapter myAdapter;
    List<PosoClass> listItem;
    RecyclerView recyclerView;

    ProgressDialog progressDialog;
    LinearLayout baseLayout;

    public PresidentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_president, container, false);

        baseLayout = view.findViewById(R.id.baseLinearLayoutId);

        recyclerView = view.findViewById(R.id.presidentRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listItem = new ArrayList<>();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        displayUser();


        return view;
    }

    public void displayUser(){
        FirebaseDatabase.getInstance().getReference().child("সভাপতি").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listItem.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot Snapshot: dataSnapshot.getChildren()){
                        PosoClass posoClass = Snapshot.getValue(PosoClass.class);
                        listItem.add(posoClass);
                    }

                    myAdapter = new MyAdapter(listItem,getContext());
                    myAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(myAdapter);

                    progressDialog.dismiss();
                }else {
                    progressDialog.dismiss();
                    baseLayout.setBackgroundResource(R.drawable.no_data_found);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
