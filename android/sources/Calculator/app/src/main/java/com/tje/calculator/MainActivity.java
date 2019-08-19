package com.tje.calculator;

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
    Button cBtn;
    Button backBtn;
    double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbers[0] = findViewById(R.id.num_0Btn);
        numbers[1] = findViewById(R.id.num_1Btn);
        numbers[2] = findViewById(R.id.num_2Btn);
        numbers[3] = findViewById(R.id.num_3Btn);
        numbers[4] = findViewById(R.id.num_4Btn);
        numbers[5] = findViewById(R.id.num_5Btn);
        numbers[6] = findViewById(R.id.num_6Btn);
        numbers[7] = findViewById(R.id.num_7Btn);
        numbers[8] = findViewById(R.id.num_8Btn);
        numbers[9] = findViewById(R.id.num_9Btn);
        numbers[10] = findViewById(R.id.dotBtn);

        operators[0] = findViewById(R.id.plusBtn);
        operators[1] = findViewById(R.id.minusBtn);
        operators[2] = findViewById(R.id.multipleBtn);
        operators[3] = findViewById(R.id.divideBtn);

        outputBtn = findViewById(R.id.equalBtn);
        outputTextView = findViewById(R.id.outputTextView);

        // back버튼 아직 처리안함
        cBtn = findViewById(R.id.cBtn);
        backBtn = findViewById(R.id.backBtn);

        setEvents();

    }

    private void setEvents() {
        for( int i = 0 ; i < numbers.length ; i++ ) {
            numbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String prevOutputText = (String)outputTextView.getText();
                    String btnText = (String)((Button)view).getText();
                    if(prevOutputText.equals("0")) {
                        outputTextView.setText(btnText);
                        return;
                    } else if(prevOutputText.equals("0") && btnText.equals("0")) {
                        outputTextView.setText(btnText);
                        return;
                    }
                    outputTextView.setText( prevOutputText + btnText );
                }
            });
        }

        for( int i = 0 ; i < operators.length ; i++ ) {
            operators[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String prevOutputText = (String)outputTextView.getText();
                    String btnText = (String)((Button)view).getText();
                    outputTextView.setText( prevOutputText + " " + btnText + " " );
                }
            });
        }

        outputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originalText = (String)outputTextView.getText();
                // ST는 기본으로 공백을 잘라준다.
                StringTokenizer st = new StringTokenizer(originalText);

                if( st.countTokens() < 3 ) {
                    Toast.makeText(getApplicationContext(), "숫자 연산자 숫자 형태로 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double lValue = Double.parseDouble(st.nextToken());
                String operator = st.nextToken().trim();
                double rValue = Double.parseDouble(st.nextToken());

                if(operator.equals("+")){
                    result = lValue + rValue;
                } else if (operator.equals("-")) {
                    result = lValue + rValue;
                } else if (operator.equals("*")) {
                    result = lValue * rValue;
                } else if (operator.equals("/")) {
                    result = lValue / rValue;
                }

                outputTextView.setText(originalText + " = " + result);
            }
        });
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputTextView.setText("0");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originalText = (String)outputTextView.getText();
                String outputText = originalText.substring(0, originalText.length()-1);
                if(outputText.length() == 0) {
                    outputTextView.setText("0");
                    return;
                }
                outputTextView.setText(outputText);
            }
        });
    }
}
