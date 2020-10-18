package com.leothelegion.calculatorandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate_Submit(View view){
        EditText operand1 = (EditText) findViewById(R.id.operand_1);
        EditText operand2 = (EditText) findViewById(R.id.operand_2);
        RadioGroup operators = (RadioGroup) findViewById(R.id.operators);
        TextView result = (TextView) findViewById(R.id.result);

        String selectedOperator = getSelectedRadioBtn(operators).getText().toString();
        float product = -1;
        try{
            product = calculate(
                    operand1.getText().toString(),
                    selectedOperator,
                    operand2.getText().toString());
        }catch (Exception e){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(e.getMessage());
            alert.setPositiveButton("Ok",null);
            alert.show();
            return;
        }


        result.setText(Float.toString(product));
    }

    float calculate(String operand1, String operator, String operand2) throws ParseException,ArithmeticException {
        if(operand1.isEmpty() || operand2.isEmpty()){
            throw new ParseException("Missing one or two numbers to do arithmetic on :(",0);
        }
        float num1 = Float.parseFloat(operand1);
        float num2 = Float.parseFloat(operand2);

        try{
            switch(operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 == 0f)
                        throw new ArithmeticException("You can't divide by zero..");
                    return num1 / num2;
            }
        }catch (Exception e){
            throw (e);
        }

        return -1;
    }


    RadioButton getSelectedRadioBtn(RadioGroup grp){
        int selectedRadioBtnID = grp.getCheckedRadioButtonId();
        View selectedRadioBtn = grp.findViewById(selectedRadioBtnID);
        int indexOfSelectedRadioBtn = grp.indexOfChild(selectedRadioBtn);
        return (RadioButton) grp.getChildAt(indexOfSelectedRadioBtn);
    }

}
