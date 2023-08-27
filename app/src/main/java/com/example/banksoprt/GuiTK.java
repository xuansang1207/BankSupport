package com.example.banksoprt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class GuiTK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_tk);
        TextView soTienVay =(TextView)findViewById(R.id._11111);
        TextView Kyhan =(TextView)findViewById(R.id._2222);
        TextView laiHangThang =(TextView)findViewById(R.id._3333);
        TextView tongLai =(TextView)findViewById(R.id._4444);
        TextView TongTra =(TextView)findViewById(R.id._5555);
        EditText edtTienVay = (EditText)findViewById(R.id.edtTienVay);

        EditText edtThoiGian = (EditText)findViewById(R.id.edtThang);

        DungChung.setMinMaxValue(edtThoiGian,0,361);

        EditText edtLai = (EditText)findViewById(R.id.edtLaiSuat);
        TextView thongbao = (TextView) findViewById(R.id.thongBao);
        DungChung.addMinMaxValueListener(this, edtLai,0.0, 55.0, thongbao);

        DungChung.applyNumberFormatting(edtTienVay);

        View btnTinh = (View)findViewById(R.id.btnTinh);
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DungChung.applyScaleAnimation(btnTinh);
                if (edtLai.getText().toString().isEmpty() || edtLai.getText().toString().isEmpty() || edtThoiGian.getText().toString().isEmpty()) {
                    thongbao.setText("Vui lòng kiểm tra lại");
                }else {
                DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
                long tienVay = DungChung.parseFormattedNumber( edtTienVay.getText().toString());
                double lai = Double.parseDouble(edtLai.getText().toString());
                int thoiGianVay = Integer.parseInt(edtThoiGian.getText().toString());
                double laiThang = lai / 100.0 / 12;
                soTienVay.setText(decimalFormat.format(tienVay));
                Kyhan.setText(String.valueOf(thoiGianVay));
                laiHangThang.setText(decimalFormat.format(tienVay*laiThang));
                tongLai.setText(decimalFormat.format(tienVay*lai/100.00));
                TongTra.setText(decimalFormat.format((tienVay*lai/100.00)+tienVay));
            }
            }
        });

    }
}