package com.android.penyiramotomatis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Input_Tanaman extends AppCompatActivity {
    EditText nama_tanaman;
    EditText kiri_kering;
    EditText tengah_kering;
    EditText kanan_kering;
    EditText kiri_lembab;
    EditText tengah_lembab;
    EditText kanan_lembab;
    EditText kiri_basah;
    EditText tengah_basah;
    EditText kanan_basah;
    Button simpan_tanaman;

    DatabaseReference databaseTanaman;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tanaman);

        databaseTanaman = FirebaseDatabase.getInstance().getReference("Tanaman");


        nama_tanaman= (EditText) findViewById(R.id.nama_tanaman);
        kiri_kering = (EditText) findViewById(R.id.kiri_kering);
        kiri_kering.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        tengah_kering = (EditText) findViewById(R.id.tengah_kering);
        tengah_kering.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        kanan_kering = (EditText) findViewById(R.id.kanan_kering);
        kanan_kering.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        kiri_lembab = (EditText) findViewById(R.id.kiri_lembab);
        kiri_lembab.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        tengah_lembab = (EditText) findViewById(R.id.tengah_lembab);
        tengah_lembab.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        kanan_lembab = (EditText) findViewById(R.id.kanan_lembab);
        kanan_lembab.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        kiri_basah = (EditText) findViewById(R.id.kiri_basah);
        kiri_basah.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        tengah_basah = (EditText) findViewById(R.id.tengah_basah);
        tengah_basah.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        kanan_basah = (EditText) findViewById(R.id.kanan_basah);
        kanan_basah.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        simpan_tanaman = (Button) findViewById(R.id.simpan_tanaman);

        simpan_tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_tanaman();
            }
        });
    }

    private void input_tanaman(){
        String tanaman_nama = nama_tanaman.getText().toString().trim();
        Integer kering_kiri = Integer.parseInt(kiri_kering.getText().toString());
        Integer kering_tengah = Integer.parseInt(tengah_kering.getText().toString());
        Integer kering_kanan = Integer.parseInt(kanan_kering.getText().toString());
        Integer lembab_kiri = Integer.parseInt(kiri_lembab.getText().toString());
        Integer lembab_tengah = Integer.parseInt(tengah_lembab.getText().toString());
        Integer lembab_kanan = Integer.parseInt(kanan_lembab.getText().toString());
        Integer basah_kiri = Integer.parseInt(kiri_basah.getText().toString());
        Integer basah_tengah = Integer.parseInt(tengah_basah.getText().toString());
        Integer basah_kanan = Integer.parseInt(kanan_basah.getText().toString());

        if (!TextUtils.isEmpty(tanaman_nama)&&kering_kiri>0&&kering_tengah>0&&kering_kanan>0&&
                lembab_kiri>0&&lembab_tengah>0&&lembab_kanan>0&&basah_kiri>0&&basah_tengah>0
                &&basah_kanan>0){
            String id = databaseTanaman.push().getKey();
            Tanaman tanaman = new Tanaman(id,tanaman_nama,kering_kiri,kering_tengah,kering_kanan,lembab_kiri,lembab_tengah,lembab_kanan,basah_kiri,basah_tengah,basah_kanan);
            databaseTanaman.child(id).setValue(tanaman);
            Toast.makeText(this,"TANAMAN BERHASIL DIMASUKAN",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Input_Tanaman.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Nilai Harus Diisi dan lebih dari 0 dan maksimal 99", Toast.LENGTH_LONG).show();
        }

    }
}
