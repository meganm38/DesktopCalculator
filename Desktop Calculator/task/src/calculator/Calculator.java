package calculator;

import Exceptions.InvalidEquation;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Calculator extends JFrame {

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        initComponents();
        setLayout(null); // sets absolute positioning of components
        setVisible(true);
    }

    public void initComponents() {
        JTextComponent EquationTextField = new JTextField();
        EquationTextField.setName("EquationTextField");
        EquationTextField.setBounds(30, 30, 150, 20);
        add(EquationTextField);

        JButton solve = new JButton();
        solve.setName("Solve");
        solve.setBounds(100, 200, 50, 20);
        add(solve);

        solve.addActionListener(e -> {
            String equation = EquationTextField.getText();
            EquationTextField.setText(calculate(equation));
        });


    }

    public String calculate(String equation) {
        try {
            checkValidEquation(equation);
            String[] operands = equation.split("\\+");
            int result = Integer.parseInt(operands[0]) + Integer.parseInt(operands[1]);
            return equation + "=" + result;
        } catch (InvalidEquation e) {
            return "Invalid input";
        }
    }

    public boolean checkValidEquation(String equation) throws InvalidEquation {
        //only supports integer addition now
        if (equation.matches("(0|[1-9](\\d)*)([+\\-])(0|[1-9](\\d)*)")) {
            return true;
        } else {
            throw new InvalidEquation();
        }
    }
}
