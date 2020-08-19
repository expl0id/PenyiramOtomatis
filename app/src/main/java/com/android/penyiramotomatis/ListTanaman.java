package com.android.penyiramotomatis;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.renderscript.Sampler;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListTanaman extends AppCompatActivity {
    EditText nama_tanaman;
    DatabaseReference databaseTanaman;

    DatabaseReference idTanaman;
    DatabaseReference namaTanaman;
    DatabaseReference keringKiri;
    DatabaseReference keringTengah;
    DatabaseReference keringKanan;
    DatabaseReference lembabKiri;
    DatabaseReference lembabTengah;
    DatabaseReference lembabKanan;
    DatabaseReference basahKiri;
    DatabaseReference basahTengah;
    DatabaseReference basahKanan;
    DatabaseReference rest;

    ListView listViewTanaman;
    List<Tanaman> tanamanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanaman_list);
        databaseTanaman = FirebaseDatabase.getInstance().getReference("Tanaman");
        listViewTanaman = findViewById(R.id.listViewTanaman);
        tanamanList = new ArrayList<>();
        nama_tanaman= (EditText) findViewById(R.id.nama_tanaman);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTanaman.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tanamanList.clear();
                for (DataSnapshot tanamanSnapshot : dataSnapshot.getChildren()){
                    Tanaman tanaman = tanamanSnapshot.getValue(Tanaman.class);
                    tanamanList.add(tanaman);
                }
                list adapter = new list(ListTanaman.this, tanamanList);
                listViewTanaman.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        listViewTanaman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tanaman tanaman = tanamanList.get(position);
                showUpdateDialog(
                        tanaman.getNama_tanaman(),
                        tanaman.getId_tanaman(),
                        tanaman.getKanan_basah().toString(),
                        tanaman.getTengah_basah().toString(),
                        tanaman.getKiri_basah().toString(),
                        tanaman.getKanan_lembab().toString(),
                        tanaman.getTengah_lembab().toString(),
                        tanaman.getKiri_lembab().toString(),
                        tanaman.getKanan_kering().toString(),
                        tanaman.getTengah_kering().toString(),
                        tanaman.getKiri_kering().toString()
                );
                return;
            }
        });
    }

    private void showUpdateDialog (


            final String tanamanNama,
            final String tanamanId,
            final String getKanan_basah,
            final String getTengah_basah,
            final String getKiri_basah,
            final String getKanan_lembab,
            final String getTengah_lembab,
            final String getKiri_lembab,
            final String getKanan_kering,
            final String getTengah_kering,
            final String getKiri_kering

    )
    {
        Log.d("id tanaman : ",tanamanId);
        Log.d("nama tanaman : ",tanamanNama);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_tanaman,null);

        dialogBuilder.setView(dialogView);
        final EditText edit_nama_tanaman = dialogView.findViewById(R.id.edit_nama_tanaman);
        final EditText update_kiri_kering = dialogView.findViewById(R.id.update_kiri_kering);
        final EditText update_tengah_kering = dialogView.findViewById(R.id.update_tengah_kering);
        final EditText update_kanan_kering = dialogView.findViewById(R.id.update_kanan_kering);
        final EditText update_kiri_lembab = dialogView.findViewById(R.id.update_kiri_lembab);
        final EditText update_tengah_lembab = dialogView.findViewById(R.id.update_tengah_lembab);
        final EditText update_kanan_lembab = dialogView.findViewById(R.id.update_kanan_lembab);
        final EditText update_kiri_basah = dialogView.findViewById(R.id.update_kiri_basah);
        final EditText update_tengah_basah = dialogView.findViewById(R.id.update_tengah_basah);
        final EditText update_kanan_basah = dialogView.findViewById(R.id.update_kanan_basah);
        final Button update_tanaman = dialogView.findViewById(R.id.update_tanaman);
        final Button delete_tanaman = dialogView.findViewById(R.id.delete_tanaman);
        final Button set_tanaman = dialogView.findViewById(R.id.btn_set);


        edit_nama_tanaman.setText(tanamanId);
        edit_nama_tanaman.setText(tanamanNama);
        update_kiri_kering.setText(getKiri_kering);
        update_tengah_kering.setText(getTengah_kering);
        update_kanan_kering.setText(getKanan_kering);
        update_kiri_lembab.setText(getKiri_lembab);
        update_tengah_lembab.setText(getTengah_lembab);
        update_kanan_lembab.setText(getKanan_lembab);
        update_kiri_basah.setText(getKiri_basah);
        update_tengah_basah.setText(getTengah_basah);
        update_kanan_basah.setText(getKanan_basah);

        update_kiri_kering.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_tengah_kering.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_kanan_kering.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_kiri_lembab.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_tengah_lembab.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_kanan_lembab.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_kiri_basah.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_tengah_basah.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;
        update_kanan_basah.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        set_tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTanaman    = FirebaseDatabase.getInstance().getReference("id_tanaman");
                namaTanaman  = FirebaseDatabase.getInstance().getReference("nama_tanaman");
                keringKiri   = FirebaseDatabase.getInstance().getReference("kering_kiri");
                keringTengah = FirebaseDatabase.getInstance().getReference("kering_tengah");
                keringKanan  = FirebaseDatabase.getInstance().getReference("kering_kanan");
                lembabKiri   = FirebaseDatabase.getInstance().getReference("lembab_kiri");
                lembabTengah = FirebaseDatabase.getInstance().getReference("lembab_tengah");
                lembabKanan  = FirebaseDatabase.getInstance().getReference("lembab_kanan");
                basahKiri    = FirebaseDatabase.getInstance().getReference("basah_kiri");
                basahTengah  = FirebaseDatabase.getInstance().getReference("basah_tengah");
                basahKanan   = FirebaseDatabase.getInstance().getReference("basah_kanan");
                rest         = FirebaseDatabase.getInstance().getReference("rest");

                idTanaman.setValue      (tanamanId);
                namaTanaman.setValue    (tanamanNama);
                keringKiri.setValue     (Integer.parseInt(getKiri_kering));
                keringTengah.setValue   (Integer.parseInt(getTengah_kering));
                keringKanan.setValue    (Integer.parseInt(getKanan_kering));
                lembabKiri.setValue     (Integer.parseInt(getKiri_lembab));
                lembabTengah.setValue   (Integer.parseInt(getTengah_lembab));
                lembabKanan.setValue    (Integer.parseInt(getKanan_lembab));
                basahKiri.setValue      (Integer.parseInt(getKiri_basah));
                basahTengah.setValue    (Integer.parseInt(getTengah_basah));
                basahKanan.setValue     (Integer.parseInt(getKanan_basah));
                rest.setValue(1);
                Intent intent = new Intent(ListTanaman.this,ListTanaman.class);
                startActivity(intent);
                Toast.makeText(ListTanaman.this,"Tanaman "+tanamanNama+" berhasil diterapkan",Toast.LENGTH_LONG).show();
                finish();
            }

        });

        update_tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  nama            = edit_nama_tanaman.getText().toString();
                Integer kering_kiri     = Integer.parseInt(update_kiri_kering.getText().toString());
                Integer kering_tengah   = Integer.parseInt(update_tengah_kering.getText().toString());
                Integer kering_kanan    = Integer.parseInt(update_kanan_kering.getText().toString());
                Integer lembab_kiri     = Integer.parseInt(update_kiri_lembab.getText().toString());
                Integer lembab_tengah   = Integer.parseInt(update_tengah_lembab.getText().toString());
                Integer lembab_kanan    = Integer.parseInt(update_kanan_lembab.getText().toString());
                Integer basah_kiri      = Integer.parseInt(update_kiri_basah.getText().toString());
                Integer basah_tengah    = Integer.parseInt(update_tengah_basah.getText().toString());
                Integer basah_kanan     = Integer.parseInt(update_kanan_basah.getText().toString());


                if (TextUtils.isEmpty(nama)){
                    edit_nama_tanaman.setError("Nama tidak boleh kosong!");
                    return;
                }

                updateTanaman   (tanamanId,nama,kering_kiri,kering_tengah,kering_kanan,
                                lembab_kiri,lembab_tengah,lembab_kanan,
                                basah_kiri,basah_tengah,basah_kanan);
                alertDialog.dismiss();
            }
        });
        delete_tanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTanaman(tanamanId);
            }
        });

    }


    private void deleteTanaman(String tanamanId) {
        DatabaseReference databaseTanaman = FirebaseDatabase.getInstance().getReference("Tanaman").child(tanamanId);
        databaseTanaman.removeValue();
        Toast.makeText(this,"Berhasil Terhapus",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ListTanaman.this,ListTanaman.class);
        startActivity(intent);
        finish();
        return;
    }

    private boolean updateTanaman(String id, String nama, Integer kering_kiri, Integer kering_tengah, Integer kering_kanan,
                                  Integer lembab_kiri, Integer lembab_tengah, Integer lembab_kanan,
                                  Integer basah_kiri, Integer basah_tengah, Integer basah_kanan)
    {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tanaman").child(id);
    Tanaman tanaman = new Tanaman(id,nama,kering_kiri,kering_tengah,kering_kanan,lembab_kiri,lembab_tengah,lembab_kanan,basah_kiri,basah_tengah,basah_kanan);
    databaseReference.setValue(tanaman);

    Toast.makeText(this,"Sukses Terupdate", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ListTanaman.this,ListTanaman.class);
        startActivity(intent);
        finish();
    return false;

    }
}
