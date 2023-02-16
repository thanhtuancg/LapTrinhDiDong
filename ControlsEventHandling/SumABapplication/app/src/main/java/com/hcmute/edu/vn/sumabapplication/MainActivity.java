package com.hcmute.edu.vn.sumabapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaodienappsum);
        EditText chuoi1 = (EditText)findViewById(R.id.inputA);
        EditText chuoi2 = (EditText)findViewById(R.id.inputB);
        EditText kq = (EditText) findViewById(R.id.hienthi);
        Button tinhtoan = (Button) findViewById(R.id.btsum);
        tinhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp1 = chuoi1.getText().toString();
                String temp2 = chuoi2.getText().toString();
                if(temp1.length() == 0 || temp2.length() == 0) {
                    kq.setText("");
                    return;
                }
                int so1 = Integer.parseInt(temp1);
                int so2 = Integer.parseInt(temp2);

                int tong = so1 + so2;
                kq.setText(String.valueOf(tong));

            }
        });



    }
}