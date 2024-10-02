import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {

    private JComboBox<String> fromUnitDropdown, toUnitDropdown;
    private JTextField inputField, outputField;
    private JButton convertButton;
    private Map<String, Double> conversionRates;

    public Main() {
        setTitle("Converter Tool");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2));

        String[] units = {"Celsius", "Fahrenheit", "Second", "Minute", "Kilograms", "Grams", "Milligram", "Meters", "Feet", "Kg/m3", "lbs/ft3", "Kilometers", "Miles"};
        fromUnitDropdown = new JComboBox<>(units);
        add(new JLabel("From Unit:"));
        add(fromUnitDropdown);

        toUnitDropdown = new JComboBox<>(units);
        add(new JLabel("To Unit:"));
        add(toUnitDropdown);

        inputField = new JTextField();
        add(new JLabel("Enter Value:"));
        add(inputField);

        outputField = new JTextField();
        outputField.setEditable(false);
        add(new JLabel("Converted Value:"));
        add(outputField);

        convertButton = new JButton("Convert");
        add(convertButton);
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        conversionRates = new HashMap<>();
        conversionRates.put("Celsius_Fahrenheit", 1.8);
        conversionRates.put("Fahrenheit_Celsius", 0.5555555555555556);
        conversionRates.put("Second_Minute", 0.016666666666666666);
        conversionRates.put("Minute_Second", 60.0);
        conversionRates.put("Kilograms_Grams", 1000.0);
        conversionRates.put("Grams_Kilograms", 0.001);
        conversionRates.put("Milligram_Grams", 0.001);
        conversionRates.put("Grams_Milligram", 1000.0);
        conversionRates.put("Meters_Feet", 3.28084);
        conversionRates.put("Feet_Meters", 0.3048);
        conversionRates.put("Kg/m3_lbs/ft3", 0.06242796);
        conversionRates.put("lbs/ft3_Kg/m3", 16.018463);
        conversionRates.put("Kilometers_Miles", 0.621371);
        conversionRates.put("Miles_Kilometers", 1.609344);

        setVisible(true);
    }

    private void performConversion() {
        String fromUnit = (String) fromUnitDropdown.getSelectedItem();
        String toUnit = (String) toUnitDropdown.getSelectedItem();
        double inputValue;
        try {
            inputValue = Double.parseDouble(inputField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            return;
        }

        double outputValue = 0;
        String conversionKey = fromUnit + "_" + toUnit;
        if (conversionRates.containsKey(conversionKey)) {
            outputValue = inputValue * conversionRates.get(conversionKey);
        } else {
            JOptionPane.showMessageDialog(this, "Unknown conversion type.");
            return;
        }

        outputField.setText(String.format("%.2f", outputValue));
    }

    public static void main(String[] args) {
        new Main();
    }
}