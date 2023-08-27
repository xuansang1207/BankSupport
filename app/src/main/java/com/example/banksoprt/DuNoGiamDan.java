package com.example.banksoprt;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class DuNoGiamDan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_du_no_giam_dan);
        EditText edtTienVay = (EditText)findViewById(R.id.edtTienVay);

        EditText edtThoiGian = (EditText)findViewById(R.id.edtThang);

        DungChung.setMinMaxValue(edtThoiGian,0,361);

        EditText edtLai = (EditText)findViewById(R.id.edtLaiSuat);
        TextView thongbao = (TextView) findViewById(R.id.thongBao);
        DungChung.addMinMaxValueListener(this, edtLai,0.0, 55.0, thongbao);

        DungChung.applyNumberFormatting(edtTienVay);

        View btnTinh = (View)findViewById(R.id.btnTinh);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        //tạo table trống nhìn cho đẹp khi mới vào
        for (int i = 0; i <= 12; i++) {
            TableRow tableRow = (TableRow) LayoutInflater.from(DuNoGiamDan.this).inflate(R.layout.table_row, null);
            ((TextView) tableRow.findViewById(R.id.numberTextView)).setText(String.valueOf(i));
            tableLayout.addView(tableRow);
        }

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DungChung.applyScaleAnimation(btnTinh);
                tableLayout.removeAllViews();

                if (edtLai.getText().toString().isEmpty() || edtLai.getText().toString().isEmpty() || edtThoiGian.getText().toString().isEmpty()) {
                    thongbao.setText("Vui lòng kiểm tra lại");
                }else {
                    thongbao.setText("");
                    long tienVay = DungChung.parseFormattedNumber( edtTienVay.getText().toString());
                    double lai = Double.parseDouble(edtLai.getText().toString());
                    int thoiGianVay = Integer.parseInt(edtThoiGian.getText().toString());
                    double soGocPhaiTra = 0.0;
                    double soTienGocConLai = tienVay;
                    int soLaiPhaiTra = 0;
                    double tongGocVaLai = 0.0;
                    int ky = thoiGianVay;
                    double laiHangThang = lai / 100.0 / 12;
                    long tonglai = 0;
                    DecimalFormat decimalFormat = new DecimalFormat("#,###,###");

                    for (int i = 0; i <= ky; i++) {
                        TableRow tableRow = (TableRow) LayoutInflater.from(DuNoGiamDan.this).inflate(R.layout.table_row, null);
                        ((TextView) tableRow.findViewById(R.id.numberTextView)).setText(String.valueOf(i));
                        ((TextView) tableRow.findViewById(R.id.nameTextView)).setText(decimalFormat.format(soTienGocConLai));
                        ((TextView) tableRow.findViewById(R.id.emailTextView)).setText(decimalFormat.format(soGocPhaiTra));
                        ((TextView) tableRow.findViewById(R.id.emailTextView1)).setText(decimalFormat.format(soLaiPhaiTra));
                        ((TextView) tableRow.findViewById(R.id.emailTextView2)).setText(decimalFormat.format(tongGocVaLai));
                        tableLayout.addView(tableRow);

                        //tính Số gốc phải trả trongg 1 tháng
                        soGocPhaiTra = tienVay / thoiGianVay;
                        //tính số lãi phải trả trong tháng
                        soLaiPhaiTra = (int) (laiHangThang * soTienGocConLai);
                        if (soLaiPhaiTra % 10 != 0) {
                            soLaiPhaiTra += 10 - (soLaiPhaiTra % 10);
                        }
                        tonglai = tonglai + soLaiPhaiTra;
                        //tính tổng gốc và lãi
                        tongGocVaLai = soGocPhaiTra + soLaiPhaiTra;
                        //tính số tiền sốc còn lại
                        soTienGocConLai -= soGocPhaiTra;
                        if (i == ky - 1) {
                            soTienGocConLai = 0;
                            tonglai=DungChung.roundLastThreeDigits(tonglai);
                        }
                    }
                    //dòng tính tổng
                    TableRow tableRow = (TableRow) LayoutInflater.from(DuNoGiamDan.this).inflate(R.layout.table_row1, null);
                    ((TextView) tableRow.findViewById(R.id.numberTextView)).setText("Tổng");
                    ((TextView) tableRow.findViewById(R.id.emailTextView)).setText(decimalFormat.format(tienVay));
                    ((TextView) tableRow.findViewById(R.id.emailTextView1)).setText(decimalFormat.format(tonglai));
                    ((TextView) tableRow.findViewById(R.id.emailTextView2)).setText(decimalFormat.format(tienVay + tonglai));
                    tableLayout.addView(tableRow);
                    //dòng cuối cùng của bảng
                    TableRow tableRow1 = (TableRow) LayoutInflater.from(DuNoGiamDan.this).inflate(R.layout.table_row2, null);
                    ((TextView) tableRow1.findViewById(R.id.ghi_chu)).setText("Nếu đóng lãi gốc và lãi đầy đủ");
                    tableLayout.addView(tableRow1);
                }
            }
        });
    }
}