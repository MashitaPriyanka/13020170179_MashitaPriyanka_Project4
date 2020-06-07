package com.example.projek4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    private TextView judul,desc,genre,durasi,sutradara,tahun;
    private ImageView photo;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    String getjudul,getdesc,getgenre,getdurasi,getsutradara,gettahun;
    int getphoto;
    String[] nama;
    TypedArray photocast;
    private ArrayList<FilmDetails> data = new ArrayList<>();

    public void prepare(){
        switch (getjudul){
            case "The Avenger":
                nama = getResources().getStringArray(R.array.avengers1);
                photocast = getResources().obtainTypedArray(R.array.castavenger1);
                break;

            case "Avengers: Infinity War":
                nama = getResources().getStringArray(R.array.avenger3);
                photocast = getResources().obtainTypedArray(R.array.castavenger3);
                break;
            case "Captain Marvel":
                nama = getResources().getStringArray(R.array.captainmarvel);
                photocast = getResources().obtainTypedArray(R.array.castcaptainmarvel);
                break;
            case "Avenger: Endgame":
                nama = getResources().getStringArray(R.array.avenger4);
                photocast = getResources().obtainTypedArray(R.array.castavenger4);
                break;

        }
    }

    public void addItem(){
        for (int i=0;i<nama.length;i++){
            FilmDetails film = new FilmDetails(nama[i],photocast.getResourceId(i,-1));
            data.add(film);
        }
    }

    public void getData(){
        prepare();
        addItem();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView = findViewById(R.id.cast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        FilmAdapterDetails filmAdapterDetails = new FilmAdapterDetails(this, data);
        recyclerView.setAdapter(filmAdapterDetails);
        filmAdapterDetails.notifyDataSetChanged();

        toolbar = findViewById(R.id.toolbar);
        judul = findViewById(R.id.judul);
        desc = findViewById(R.id.desc);
        genre = findViewById(R.id.genre);
        durasi = findViewById(R.id.durasi);
        sutradara = findViewById(R.id.sutradara);
        tahun = findViewById(R.id.tahun);
        photo = findViewById(R.id.photo);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getjudul = getIntent().getStringExtra("judul");
        getdesc = getIntent().getStringExtra("desc");
        getgenre = getIntent().getStringExtra("genre");
        getdurasi = getIntent().getStringExtra("durasi");
        getsutradara = getIntent().getStringExtra("sutradara");
        gettahun = getIntent().getStringExtra("tahun");
        getphoto = getIntent().getIntExtra("photo",0);

        getData();

        judul.setText(getjudul);
        desc.setText(getdesc);
        genre.setText(getgenre);
        durasi.setText(getdurasi);
        sutradara.setText(getsutradara);
        tahun.setText(gettahun);
        photo.setImageResource(getphoto);

    }
}
