package com.tje.imagebuttontestapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private RadioGroup petRadioGroup;
    private ImageView imageView;
    private RadioButton selectedPetBtn;
    private String selectedPet;
    private ToggleButton toggleBtn;
    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        petRadioGroup = findViewById(R.id.petRadioGroup);
        imageView = findViewById(R.id.imageView);

        toggleBtn = findViewById(R.id.toggleBtn);
        sw = findViewById(R.id.switch1);

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isOn = toggleBtn.getText().toString();
                Toast t = Toast.makeText(getApplicationContext(), isOn, Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                t.show();
            }
        });

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw.isChecked()) {
                    Toast t = Toast.makeText(getApplicationContext(), "On", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    t.show();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "Off", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    t.show();
                }
            }
        });

        petRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedPetBtn = findViewById(i);
                selectedPet = selectedPetBtn.getText().toString();
                showDialog();
                Toast t = Toast.makeText(getApplicationContext(), selectedPet + "를 선택하셨네요 !", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                t.show();
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("선택하신 애완동물은 '" +
                selectedPet + "' 입니다.\n사진을 띄울까요?");
        builder.setTitle("선택 확인");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(selectedPet.equals("강아지")){
                    imageView.setImageResource(R.drawable.dog);
                } else if (selectedPet.equals("고양이")){
                    imageView.setImageResource(R.drawable.cat);
                }
            }
        });
        builder.setNegativeButton("아니오", null);
        builder.create().show();
    }
}
