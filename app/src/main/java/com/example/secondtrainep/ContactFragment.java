package com.example.secondtrainep;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;




public class ContactFragment extends Fragment {

    private TextView resultTextView;
    private StringBuilder currentNumber;
    private double result = 0;
    private String operator = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resultTextView = view.findViewById(R.id.resultTextView);
        currentNumber = new StringBuilder();

        Button button0 = view.findViewById(R.id.button0);
        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);
        Button button4 = view.findViewById(R.id.button4);
        Button button5 = view.findViewById(R.id.button5);
        Button button6 = view.findViewById(R.id.button6);
        Button button7 = view.findViewById(R.id.button7);
        Button button8 = view.findViewById(R.id.button8);
        Button button9 = view.findViewById(R.id.button9);
        Button buttonDot = view.findViewById(R.id.buttonDot);
        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        Button buttonSubtract = view.findViewById(R.id.buttonSubtract);
        Button buttonMultiply = view.findViewById(R.id.buttonMultiply);
        Button buttonDivide = view.findViewById(R.id.buttonDivide);
        Button buttonEquals = view.findViewById(R.id.buttonEquals);
        Button buttonClear = view.findViewById(R.id.buttonClear);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentNumber.append(button.getText().toString());
                updateResult();
            }
        };

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operator = button.getText().toString();
                result = Double.parseDouble(currentNumber.toString());
                currentNumber.setLength(0);
            }
        };

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() == 0) {
                    currentNumber.append("0.");
                } else if (!currentNumber.toString().contains(".")) {
                    currentNumber.append(".");
                }
                updateResult();
            }
        });

        buttonAdd.setOnClickListener(operatorClickListener);
        buttonSubtract.setOnClickListener(operatorClickListener);
        buttonMultiply.setOnClickListener(operatorClickListener);
        buttonDivide.setOnClickListener(operatorClickListener);

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator.isEmpty()) {
                    return;
                }
                double secondNumber = Double.parseDouble(currentNumber.toString());
                switch (operator) {
                    case "+":
                        result += secondNumber;
                        break;
                    case "-":
                        result -= secondNumber;
                        break;
                    case "*":
                        result *= secondNumber;
                        break;
                    case "/":
                        if (secondNumber == 0) {
                            resultTextView.setText("Error");
                            result = 0;
                        } else {
                            result /= secondNumber;
                        }
                        break;
                }
                currentNumber.setLength(0);
                currentNumber.append(result);
                operator = "";
                updateResult();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber.setLength(0);
                result = 0;
                operator = "";
                updateResult();
            }
        });
    }

    private void updateResult() {
        resultTextView.setText(currentNumber.toString());
    }
}
