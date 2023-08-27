package com.example.banksoprt;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DungChung {
    //thêm dấu . khi nhập ố
    public static void applyNumberFormatting(final EditText editText) {
        final DecimalFormat decimalFormat = new DecimalFormat("#,###");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                editText.removeTextChangedListener(this);
                if (!s.toString().isEmpty()) {
                    String rawValue = s.toString().replaceAll("[^0-9]", "");
                    try {
                        String formattedValue = decimalFormat.format(Double.parseDouble(rawValue));
                        editText.setText(formattedValue);
                        editText.setSelection(formattedValue.length());
                    } catch (NumberFormatException e) {
                        // Handle invalid input
                    }
                }
                editText.addTextChangedListener(this);
            }
        });
    }

    //lại bỏ dấu .
    public static long parseFormattedNumber(String formattedNumber) {
        // Loại bỏ dấu phân tách hàng nghìn và chuyển đổi thành số
        try {
            formattedNumber = formattedNumber.replace(".", "");
            return Long.parseLong(formattedNumber);
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu chuỗi không hợp lệ
            return 0; // Hoặc bất kỳ giá trị mặc định bạn muốn
        }
    }
    //hiệu ứng buton
    public static void applyScaleAnimation(View view) {
        Animation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(scaleAnimation);
    }
    //setminmax
    public static void setMinMaxValue(EditText editText, int minValue, int maxValue) {
        InputFilter minMaxValueFilter = (source, start, end, dest, dstart, dend) -> {
            try {
                String input = dest.subSequence(0, dstart).toString() + source.subSequence(start, end) + dest.subSequence(dend, dest.length()).toString();
                int value = Integer.parseInt(input);
                if (value >= minValue && value <= maxValue) {
                    return null;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return "";
        };

        InputFilter[] filters = new InputFilter[] { minMaxValueFilter };
        editText.setFilters(filters);
    }
    //đang nghiên cứu thêm
    public static void addMinMaxValueListener(Context context, EditText editText, double minValue, double maxValue, TextView thongbao) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String input = editable.toString();
                    double value = Double.parseDouble(input);
                    if (value < minValue || value > maxValue) {
                        // Hiển thị thông báo cho người dùng
                        thongbao.setText("Lãi chi cao kinh vậy");
                    }else {
                        thongbao.setText("");
                    }
                } catch (NumberFormatException e) {
                    // Xử lý nếu không thể chuyển đổi thành số
                }
            }
        });
    }
    public static long roundLastThreeDigits(long number) {
        long roundingFactor = 1000; // 10^3
        return (number / roundingFactor) * roundingFactor;
    }
}
