package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> poll, answ1,answ2,answ3,answ4,answ5, vreme;
    String usname;
    DBHelper DB;
    RecViewAdapter adapter;
    Double longitude, lattitude;
    FusedLocationProviderClient fusedLocationProviderClient;
    public static final int REQUEST_CODR = 100;
    Button buttonMyPolls, buttonLogOutUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        FindLocation();
        buttonMyPolls = findViewById(R.id.buttonMyPolls);
        buttonLogOutUser = findViewById(R.id.buttonLogOutUser);

        buttonMyPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), MyPolls.class);
                startActivity(intent);
            }
        });

        buttonLogOutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String uname = intent.getStringExtra("uname");

        DB = new DBHelper(this);
        poll = new ArrayList<>();
        answ1 = new ArrayList<>();
        answ2 = new ArrayList<>();
        answ3 = new ArrayList<>();
        answ4 = new ArrayList<>();
        answ5 = new ArrayList<>();
        vreme = new ArrayList<>();
        usname = new String();

        recyclerView = findViewById(R.id.recView);
        adapter = new RecViewAdapter(this,poll,answ1,answ2,answ3,answ4,answ5,vreme,usname,longitude,lattitude);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void AskforPermission() {
        ActivityCompat.requestPermissions(UserActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        }, REQUEST_CODR);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODR){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                FindLocation();
            }else{
                Toast.makeText(UserActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void displaydata(){
        Cursor cursor = DB.getData();
        if(cursor.getCount()==0){
            Toast.makeText(UserActivity.this, "Nema aktivni glasanja", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while(cursor.moveToNext()){
                poll.add(cursor.getString(1));
                answ1.add(cursor.getString(2));
                answ2.add(cursor.getString(3));
                answ3.add(cursor.getString(4));
                answ4.add(cursor.getString(5));
                answ5.add(cursor.getString(6));
                vreme.add(cursor.getString(7));
            }
        }
    }

    public void FindLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null) {
                        Geocoder geocoder = new Geocoder(UserActivity.this, Locale.getDefault());
                        List<Address> addressList = null;
                        try {
                            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            lattitude = addressList.get(0).getLatitude();
                            longitude = addressList.get(0).getLongitude();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else{
            AskforPermission();
        }
    }
}