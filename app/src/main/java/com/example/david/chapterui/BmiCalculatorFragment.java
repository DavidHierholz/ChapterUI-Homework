package com.example.david.chapterui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by david on 22.02.18.
 */

public class BmiCalculatorFragment extends Fragment implements View.OnClickListener {

    private TextView textView;
    private EditText editTextHeight;
    private EditText editTextWeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bmi_calculator, null, false);

        textView = (TextView) view.findViewById(R.id.textView);
        editTextHeight = (EditText) view.findViewById(R.id.editTextHeight);
        editTextWeight = (EditText) view.findViewById(R.id.editTextWeight);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            double height = Double.parseDouble(editTextHeight.getText().toString());
            double weight = Double.parseDouble(editTextWeight.getText().toString());
            double result;
            if (height != 0) {
                result = weight / (height * height);
                result = Math.round(result);
                textView.setText("Your BMI is: " + (int) result);
            } else {
                textView.setText("Please enter valid values");
            }
        }
    }
}
