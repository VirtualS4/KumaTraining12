package com.example.rona.kumatraining12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BearMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear_menu);
        initbtn((Button) findViewById(R.id.btn_lat1),Beartivity.class);

        initbtn((Button) findViewById(R.id.btn_lat2),Input_data.class,((Button) findViewById(R.id.btn_lat2)).getText().toString());
        initbtn((Button) findViewById(R.id.btn_lat22),Input_data.class,((Button) findViewById(R.id.btn_lat22)).getText().toString());
        initbtn((Button) findViewById(R.id.btn_lat23),detail_data.class,((Button) findViewById(R.id.btn_lat23)).getText().toString());

    }

    private void initbtn(Button btn, final Class bearclass){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bearsense = new Intent(BearMenu.this,bearclass);
                startActivity(bearsense);
            }
        });
    }

    private void initbtn(Button btn, final Class bearclass,final String tipe){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ((EditText) findViewById(R.id.id_bear)).getText().toString();
                Intent bearsense = new Intent(BearMenu.this,bearclass);
                bearsense.putExtra("tipe",tipe);
                bearsense.putExtra("id",id);
                startActivity(bearsense);
            }
        });
    }
}
