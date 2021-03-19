package com.example.emran;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class DatabaseActivity extends AppCompatActivity {

    CircleImageView circleImageView;
    EditText session,msNo,fullname,phone,department,lastDonation,desigType;
    Button addButton;
    DatePickerDialog datePickerDialog;

    Uri uri;
    String imageUrl;

    String[] bloodGroopArray,designationArray;
    Spinner bloodgroupSpinner,designationSpinner;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        circleImageView = findViewById(R.id.profileImageId);
        session = findViewById(R.id.sessionEditTextId);
        msNo = findViewById(R.id.msEditTextId);
        fullname = findViewById(R.id.fullnameEditTextId);
        phone = findViewById(R.id.phoneEditTextId);
        department = findViewById(R.id.departmentEditTextId);
        lastDonation = findViewById(R.id.lastDonationEditTextId);
        designationSpinner = findViewById(R.id.designationSpinnerId);
        bloodgroupSpinner = findViewById(R.id.bloodGroupSpinnerId);
        desigType = findViewById(R.id.designationTypeEditTextId);
        addButton = findViewById(R.id.addButtonId);

        designationArray = getResources().getStringArray(R.array.Designation);
        bloodGroopArray = getResources().getStringArray(R.array.Blood_Group);

        ArrayAdapter<String> desigAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,designationArray);
        designationSpinner.setAdapter(desigAdapter);
        
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,bloodGroopArray);
        bloodgroupSpinner.setAdapter(bloodAdapter);

        lastDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker datePicker = new DatePicker(DatabaseActivity.this);
                int currentDay = datePicker.getDayOfMonth();
                int currentMonth = datePicker.getMonth();
                int currentYear = datePicker.getYear();

                datePickerDialog = new DatePickerDialog(DatabaseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        lastDonation.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },currentYear,currentMonth,currentDay);
                datePickerDialog.show();

            }
        });


       mAuth  = FirebaseAuth.getInstance();
       if (mAuth == null){
           Intent intent = new Intent(DatabaseActivity.this,LoginActivity.class);
           startActivity(intent);
           finish();
       }else {
           Toast.makeText(this, "Welcome Admin to the Database !", Toast.LENGTH_SHORT).show();
       }

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker,1);
            }
        });



       addButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (uri != null){

                   uploadImages();

               }else {
                   Toast.makeText(DatabaseActivity.this, "Pic Image", Toast.LENGTH_SHORT).show();
               }
           }
       });


    }


    private void uploadImages() {
        if (uri != null){
            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReference().child("Post Image").child(uri.getLastPathSegment());

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            UploadTask image_path = storageReference.putFile(uri);
            image_path.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    Uri urlImage = uriTask.getResult();
                    imageUrl = urlImage.toString();
                    uploadPost();
                    progressDialog.dismiss();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DatabaseActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }else {
            Toast.makeText(DatabaseActivity.this, "Select Image", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadPost() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

//        String postkeyid = FirebaseDatabase.getInstance().getReference().child("Post").child(user_id).push().getKey();

        String sessn = session.getText().toString().trim();
        String msno = msNo.getText().toString().trim();
        String name = fullname.getText().toString().trim();
        String phn = phone.getText().toString().trim();
        String dept = department.getText().toString().trim();
        String lastdonate = lastDonation.getText().toString().trim();
        final String dgntn = designationSpinner.getSelectedItem().toString();
        String blood = bloodgroupSpinner.getSelectedItem().toString();
        String desiType = desigType.getText().toString().trim();

        if (!sessn.equals("") && !msno.equals("") && !name.equals("") && !phn.equals("") && !dept.equals("") && !lastdonate.equals("") && !dgntn.equals("") && !blood.equals("") && !desiType.equals("")) {

            PosoClass posoClass = new PosoClass(imageUrl,sessn, msno, name, phn, dept, lastdonate, blood, dgntn, desiType);

            FirebaseDatabase.getInstance().getReference().child(dgntn).push().setValue(posoClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(DatabaseActivity.this, dgntn + " added Successfully !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DatabaseActivity.this, DrawerActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DatabaseActivity.this, "Connection Error !", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Toast.makeText(DatabaseActivity.this, "Any field can't be empty !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            uri = data.getData();
            circleImageView.setImageURI(uri);
        }else {
            Toast.makeText(this, "You haven't picked images", Toast.LENGTH_SHORT).show();
        }
    }



}
