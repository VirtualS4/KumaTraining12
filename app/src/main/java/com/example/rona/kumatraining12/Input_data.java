package com.example.rona.kumatraining12;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Input_data extends AppCompatActivity {
    Context context;
    EditText nomor,nama,tanggal,kelamin,alamat;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        context=this;
        nomor = findViewById(R.id.nomor);
        nama =  findViewById(R.id.nama);
        tanggal =  findViewById(R.id.tanggal);
        kelamin =  findViewById(R.id.kelamin);
        alamat =  findViewById(R.id.Alamat);
        btn = findViewById(R.id.btn_simpan);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String tipe = bundle.getString("tipe");
        final OldBearHelp db =  new OldBearHelp(context);

        if(tipe.equals("Input")){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OldBear currentbear = new OldBear();

                    currentbear.setId(Integer.parseInt(nomor.getText().toString()));
                    currentbear.setNama(nama.getText().toString());
                    currentbear.setTanggal(tanggal.getText().toString());
                    currentbear.setKelamin(kelamin.getText().toString());
                    currentbear.setAlamat(alamat.getText().toString());
                    db.insert(currentbear);
                    Intent bearsense = new Intent(Input_data.this,BearMenu.class);
                    startActivity(bearsense);
                }
            });
        }else if(tipe.equals("Update")){
            btn.setText("Update");
            String id = bundle.getString("id");
            System.out.println(id);
            final OldBear oldBear = db.selectUserData(id);
            nomor.setText(oldBear.getId()+"");
            nama.setText(oldBear.getNama());
            tanggal.setText(oldBear.getTanggal());
            kelamin.setText(oldBear.getKelamin());
            alamat.setText(oldBear.getAlamat());

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OldBear currentbear = oldBear;
                    currentbear.setNama(nama.getText().toString());
                    currentbear.setTanggal(tanggal.getText().toString());
                    currentbear.setKelamin(kelamin.getText().toString());
                    currentbear.setAlamat(alamat.getText().toString());
                    db.update(currentbear);
                    Intent bearsense = new Intent(Input_data.this,BearMenu.class);
                    startActivity(bearsense);
                }
            });
            ;
        }

    }

}
