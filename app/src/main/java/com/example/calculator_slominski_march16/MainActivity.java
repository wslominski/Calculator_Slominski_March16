package com.example.calculator_slominski_march16;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Scriptable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_dot, btn_add, btn_subtract, btn_multiply, btn_divide, btn_equal, btn_clear,btn_percent, btn_parenthesis;
    TextView tv_input, tv_output;
    String process;
    ArrayList<Button> buttons;

    boolean checkBracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new ArrayList<>();

        buttons.add(btn_0);
        buttons.add(btn_1);
        buttons.add(btn_2);
        buttons.add(btn_3);
        buttons.add(btn_4);
        buttons.add(btn_5);
        buttons.add(btn_6);
        buttons.add(btn_7);
        buttons.add(btn_8);
        buttons.add(btn_9);
        buttons.add(btn_equal);



        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);

        btn_dot = findViewById(R.id.btn_dot);
        btn_add = findViewById(R.id.btn_add);
        btn_subtract = findViewById(R.id.btn_subtract);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_equal = findViewById(R.id.btn_equal);
        btn_clear = findViewById(R.id.btn_clear);
        btn_percent = findViewById(R.id.btn_percent);
        btn_parenthesis = findViewById(R.id.btn_parenthesis);

        tv_input = findViewById(R.id.tv_input);
        tv_output = findViewById(R.id.tv_output);



        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_input.setText("");
                tv_output.setText("");
            }
        });

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "9");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "+");
            }
        });

        btn_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "-");
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "x");
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "/");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + ".");
            }
        });

        btn_percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();
                tv_input.setText(process + "%");
            }
        });

        btn_parenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBracket){
                    process = tv_input.getText().toString();
                    tv_input.setText(process + ")");
                    checkBracket = false;
                } else {
                    process = tv_input.getText().toString();
                    tv_input.setText(process + "(");
                    checkBracket = true;
                }
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tv_input.getText().toString();

                process = process.replaceAll("%", "/100");
                process = process.replaceAll("x", "*");

                Context rhino = Context.enter();

                rhino.setOptimizationLevel(-1);

                String finalResult = "";

                try {
                    Scriptable scriptable = rhino.initSafeStandardObjects();
                    finalResult = rhino.evaluateString(scriptable, process, "javascript", 1, null).toString();

                } catch (Exception e) {
                    finalResult = "0";
                }

                tv_output.setText(finalResult);
            }
        });
    }
}
