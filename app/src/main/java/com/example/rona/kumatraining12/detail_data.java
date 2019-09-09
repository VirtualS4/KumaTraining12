package com.example.rona.kumatraining12;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class detail_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        Context context=this;
        EditText nomor = findViewById(R.id.nomor);
        EditText nama =  findViewById(R.id.nama);
        EditText tanggal =  findViewById(R.id.tanggal);
        EditText kelamin =  findViewById(R.id.kelamin);
        EditText alamat =  findViewById(R.id.Alamat);
        Button btn = findViewById(R.id.btn_simpan);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final OldBearHelp db =  new OldBearHelp(context);

        String id = bundle.getString("id");
        final OldBear oldBear = db.selectUserData(id);
        nomor.setText(oldBear.getId()+"");
        nama.setText(oldBear.getNama());
        tanggal.setText(oldBear.getTanggal());
        kelamin.setText(oldBear.getKelamin());
        alamat.setText(oldBear.getAlamat());
        nomor.setFocusable(false);
        nomor.setFocusableInTouchMode(false);
        nama.setFocusable(false);
        nama.setFocusableInTouchMode(false);
        tanggal.setFocusable(false);
        tanggal.setFocusableInTouchMode(false);
        kelamin.setFocusable(false);
        kelamin.setFocusableInTouchMode(false);
        alamat.setFocusable(false);
        alamat.setFocusableInTouchMode(false);
    }
}
