package com.example.AndroidTest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatActivity;

public class sy3_2AlterDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showCustomAlertDialog();
    }

    private void showCustomAlertDialog() {

        LayoutInflater inflater= LayoutInflater.from(this);

        View dialogView=inflater.inflate(R.layout.sy3_alter_dialog,null);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setCancelable(true);

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}
