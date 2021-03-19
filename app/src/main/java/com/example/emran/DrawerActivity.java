package com.example.emran;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class DrawerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    CircleImageView ProfileImageView;
    TextView ProfileNameTextView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String user_id;
    String pic, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationView = findViewById(R.id.nav_viewId);
        View nabView = navigationView.inflateHeaderView(R.layout.drawer_header);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ProfileImageView = nabView.findViewById(R.id.ProPicId);
        ProfileNameTextView = nabView.findViewById(R.id.ProNameId);
//        ProfileImageView = findViewById(R.id.ProPicId);
//        ProfileNameTextView = findViewById(R.id.ProNameId);


        HomeFragment homeFragment = new HomeFragment();
        loadFragment(homeFragment);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.presidentItemId:
                        fragment = new PresidentFragment();
                        break;
                    case R.id.viceprecidentItemId:
                        fragment = new VicePresidentFragment();
                        break;
                    case R.id.secrateryIemId:
                        fragment = new SecrateryFragment();
                        break;
                    case R.id.memberItemId:
                        fragment = new MemberFragment();
                        break;
                    case R.id.generalMemberItemId:
                        fragment = new GeneralMemberFragment();
                        break;
                    case R.id.adminLoginItemId:
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        break;
                }if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayoutId, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

                //close navigation drawer
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
                menuItem.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

//    private void setPropic() {
//        Glide.with(this).load(pic).into(ProfileImageView);
//        ProfileNameTextView.setText(name);
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void loadFragment(Fragment fragment){
//        HomeFragment homeFragment = new HomeFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frameLayoutId,homeFragment).commit();
//
//    }


    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();

    }
}
