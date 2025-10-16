package com.example.AndroidTest;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class sy3_3xml_menu extends AppCompatActivity {

    private TextView textView;
    private int currentTextColor = 0xFF000000; // 默认黑色
    private int currentFontSize = 16; // 默认中等大小

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sy3_menulayout);

        textView = findViewById(R.id.your_text_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sy3_main_menu, menu);
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // 设置字体大小的初始选中状态
//        MenuItem fontSmall = menu.findItem(R.id.font_small);
//        MenuItem fontMid = menu.findItem(R.id.font_mid);
//        MenuItem fontLarge = menu.findItem(R.id.font_large);
//
//        if (fontSmall != null) fontSmall.setChecked(currentFontSize == 12);
//        if (fontMid != null) fontMid.setChecked(currentFontSize == 16);
//        if (fontLarge != null) fontLarge.setChecked(currentFontSize == 20);
//
//        // 设置字体颜色的初始选中状态
//        MenuItem fontRed = menu.findItem(R.id.font_color_red);
//        MenuItem fontBlack = menu.findItem(R.id.font_color_black);
//
//        if (fontRed != null) fontRed.setChecked(currentTextColor == 0xFFFF0000);
//        if (fontBlack != null) fontBlack.setChecked(currentTextColor == 0xFF000000);
//
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ordinary_menu) {
            showToast("普通菜单项");
            return true;
        } else if (id == R.id.font_small) {
            changeFontSize(10);
            item.setChecked(true);
            showToast("小");
            return true;
        } else if (id == R.id.font_mid) {
            changeFontSize(16);
            item.setChecked(true);
            showToast("中");
            return true;
        } else if (id == R.id.font_large) {
            changeFontSize(20);
            item.setChecked(true);
            showToast("大");
            return true;
        } else if (id == R.id.font_color_red) {
            changeTextColor(0xFFFF0000);
            item.setChecked(true);
            showToast("红色");
            return true;
        } else if (id == R.id.font_color_black) {
            changeTextColor(0xFF000000);
            item.setChecked(true);
            showToast("黑色");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void changeFontSize(int size) {
        currentFontSize = size;
        if (textView != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    private void changeTextColor(int color) {
        currentTextColor = color;
        if (textView != null) {
            textView.setTextColor(color);
        }
    }
}