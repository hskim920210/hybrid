package com.tje.calculator_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] operators = new Button[4];
    String operator;
    double result;

    Button resetBtn;
    Button resultBtn;
    EditText N_1_Number;
    EditText N_2_Number;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        N_1_Number = findViewById(R.id.N_1_Number);
        N_2_Number = findViewById(R.id.N_2_Number);

        resultTextView = findViewById(R.id.resultTextView);

        resetBtn = findViewById(R.id.resetBtn);
        resultBtn = findViewById(R.id.resultBtn);
        operators[0] = findViewById(R.id.plusBtn);
        operators[1] = findViewById(R.id.minusBtn);
        operators[2] = findViewById(R.id.multipleBtn);
        operators[3] = findViewById(R.id.divideBtn);

        for(int i = 0 ; i < operators.length ; i++) {
            operators[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    operator = (String)((Button)view).getText();
                }
            });
        }

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(N_1_Number.getText().toString());
                int n2 = Integer.parseInt(N_2_Number.getText().toString());

                if(operator.equals("+")){
                    result = n1 + n2;
                } else if (operator.equals("-")) {
                    result = n1 + n2;
                } else if (operator.equals("*")) {
                    result = n1 * n2;
                } else if (operator.equals("/")) {
                    result = n1 / n2;
                }

                resultTextView.setText(n1+" "+operator+" "+n2+" = "+result);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTextView.setText("");
            }
        });


    }
}
