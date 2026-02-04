package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String expression = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View.OnClickListener inputListener = v -> {
            String value = ((Button) v).getText().toString();
            expression += value;
            binding.display.setText(expression);};

        binding.Click0.setOnClickListener(inputListener);
        binding.Click1.setOnClickListener(inputListener);
        binding.Click2.setOnClickListener(inputListener);
        binding.Click3.setOnClickListener(inputListener);
        binding.Click4.setOnClickListener(inputListener);
        binding.Click5.setOnClickListener(inputListener);
        binding.Click6.setOnClickListener(inputListener);
        binding.Click7.setOnClickListener(inputListener);
        binding.Click8.setOnClickListener(inputListener);
        binding.Click9.setOnClickListener(inputListener);
        binding.Decimal.setOnClickListener(inputListener);

        View.OnClickListener opListener = v -> {
            String op = ((Button) v).getText().toString();
            expression += " " + op + " ";
            binding.display.setText(expression);};

        binding.Plus.setOnClickListener(opListener);
        binding.Minus.setOnClickListener(opListener);
        binding.Multiply.setOnClickListener(opListener);
        binding.Divide.setOnClickListener(opListener);
        binding.Clear.setOnClickListener(v -> {
            expression = "";
            binding.display.setText("0");
        });
        binding.Equals.setOnClickListener(v -> {
            try {
                double result = evaluate(expression);
                expression = removeZeros(result);
                binding.display.setText(expression);
            } catch (Exception e) {
                binding.display.setText("Error");
                expression = "";
            }
        });
    }
    private double evaluate(String exp) {
        String[] parts = exp.split(" ");
        double result = Double.parseDouble(parts[0]);
        for (int i = 1; i < parts.length; i += 2) {
            String op = parts[i];
            double next = Double.parseDouble(parts[i + 1]);
            switch (op) {
                case "+": result += next; break;
                case "-": result -= next; break;
                case "×": result *= next; break;
                case "÷": result /= next; break;
            }
        }
        return result;
    }
    private String removeZeros(double value) {
        if (value == (long) value)
            return String.format("%d", (long) value);
        else
            return String.valueOf(value);
    }
}
