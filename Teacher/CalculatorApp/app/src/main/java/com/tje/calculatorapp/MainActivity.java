package com.tje.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    TextView outputTextView;
    Button [] numbers = new Button[11];
    Button [] operators = new Button[4];
    Button outputBtn;

    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbers[0] = findViewById(R.id.button0);
        numbers[1] = findViewById(R.id.button1);
        numbers[2] = findViewById(R.id.button2);
        numbers[3] = findViewById(R.id.button3);
        numbers[4] = findViewById(R.id.button4);
        numbers[5] = findViewById(R.id.button5);
        numbers[6] = findViewById(R.id.button6);
        numbers[7] = findViewById(R.id.button7);
        numbers[8] = findViewById(R.id.button8);
        numbers[9] = findViewById(R.id.button9);
        numbers[10] = findViewById(R.id.button_dot);

        operators[0] = findViewById(R.id.button_plus);
        operators[1] = findViewById(R.id.button_minus);
        operators[2] = findViewById(R.id.button_mul);
        operators[3] = findViewById(R.id.button_div);

        outputBtn = findViewById(R.id.button_eq);

        outputTextView = findViewById(R.id.outputTextView);

        setEvents();
    }

    private void setEvents() {
        for( int i = 0 ; i < numbers.length ; i++ ) {
            numbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String prevOutputText = (String) outputTextView.getText();
                    String btnText = (String) ((Button)view).getText();
                    outputTextView.setText(prevOutputText + btnText);
                }
            });
        }

        for( int i = 0 ; i < operators.length ; i++ ) {
            operators[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String prevOutputText = (String) outputTextView.getText();
                    String btnText = (String) ((Button)view).getText();
                    outputTextView.setText(prevOutputText + " " + btnText + " ");
                }
            });
        }

        outputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orginalText = (String) outputTextView.getText();
                StringTokenizer st = new StringTokenizer(orginalText);

                if( st.countTokens() < 3 ) {
                    Toast.makeText(getApplicationContext(),
                            "숫자 연산자 숫자 형태로 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                double lValue = Double.parseDouble(st.nextToken());
                String operator = st.nextToken().trim();
                double rValue = Double.parseDouble(st.nextToken());

                if( operator.equals("+") )
                    result = lValue + rValue;
                else if( operator.equals("") )
                    result = lValue - rValue;
                else if( operator.equals("*") )
                    result = lValue * rValue;
                else if( operator.equals("/") )
                    result = lValue / rValue;

                outputTextView.setText(orginalText + " = " + result);
            }
        });
    }
}








