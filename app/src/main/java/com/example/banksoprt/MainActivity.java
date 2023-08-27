package com.example.banksoprt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View btnTk = (View)findViewById(R.id.btn_GuiTK);
        View btn_lai = (View)findViewById(R.id.btn_tinhlai);
        View btn_tragop = (View)findViewById(R.id.btn_tragop);
        View btn_duno = (View)findViewById(R.id.btnDuno);

        btn_duno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DungChung.applyScaleAnimation(btn_duno);
                Intent act = new Intent(MainActivity.this, DuNoGiamDan.class);
                startActivity(act);
            }
        });

        btn_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DungChung.applyScaleAnimation(btn_lai);
                Intent act = new Intent(MainActivity.this, Lai.class);
                startActivity(act);
            }
        });
        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DungChung.applyScaleAnimation(btnTk);
                Intent act = new Intent(MainActivity.this, GuiTK.class);
                startActivity(act);
            }
        });
        btn_tragop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DungChung.applyScaleAnimation(btn_tragop);
                Intent act = new Intent(MainActivity.this, DuNoGoc.class);
                startActivity(act);
            }
        });

    }
}