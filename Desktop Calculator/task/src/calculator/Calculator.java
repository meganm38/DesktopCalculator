package calculator;

import Exceptions.InvalidEquation;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class Calculator extends JFrame {
    private JPanel controlPanel;
    private JTextComponent equationTextField;

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        initComponents();
        //setLayout(null); // sets absolute positioning of components
        setVisible(true);
    }

    public void initComponents() {
        equationTextField = new JTextField(20);
        equationTextField.setName("EquationTextField");
        equationTextField.setEditable(false);

        controlPanel = new JPanel();
        controlPanel.add(equationTextField);
        addButtonsToPanel();
        add(controlPanel);
    }

    public String calculate(String equation) {
        try {
            checkValidEquation(equation);
            String[] operands = equation.split("[+\\-*/]");
            String operator = equation.substring(operands[0].length(), operands[0].length() + 1);

            int result;
            switch (operator) {
                case "+":
                    result = Integer.parseInt(operands[0]) + Integer.parseInt(operands[1]);
                    break;
                case "-":
                    result = Integer.parseInt(operands[0]) - Integer.parseInt(operands[1]);
                    break;
                case "*":
                    result = Integer.parseInt(operands[0]) * Integer.parseInt(operands[1]);
                    break;
                default:
                    result = Integer.parseInt(operands[0]) / Integer.parseInt(operands[1]);
                    break;
            }
            return equation + "=" + result;
        } catch (InvalidEquation e) {
            return "Invalid input";
        }
    }

    public void checkValidEquation(String equation) throws InvalidEquation {
        //only supports integer addition now
        if (!equation.matches("(0|[1-9](\\d)*)([+\\-*/])(0|[1-9](\\d)*)")) {
            throw new InvalidEquation();
        }
    }

    public void addButtonsToPanel() {
        JButton btn1 = new JButton("1");
        btn1.setName("Zero");
        JButton btn2 = new JButton("2");
        btn2.setName("Two");
        JButton btn3 = new JButton("3");
        btn3.setName("Three");
        JButton plus = new JButton("+");
        plus.setName("Add");
        JButton btn4 = new JButton("4");
        btn4.setName("Four");
        JButton btn5 = new JButton("5");
        btn5.setName("Five");
        JButton btn6 = new JButton("6");
        btn6.setName("Six");
        JButton minus = new JButton("-");
        minus.setName("Subtract");
        JButton btn7 = new JButton("7");
        btn7.setName("Seven");
        JButton btn8 = new JButton("8");
        btn8.setName("Eight");
        JButton btn9 = new JButton("9");
        btn9.setName("Nine");
        JButton multiply = new JButton("*");
        multiply.setName("Multiply");
        JButton divide = new JButton("/");
        divide.setName("Divide");
        JButton btn0 = new JButton("0");
        btn0.setName("Zero");
        JButton equals = new JButton("=");
        equals.setName("Equals");

        btn0.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "0"));
        btn1.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "1"));
        btn2.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "2"));
        btn3.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "3"));
        btn4.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "4"));
        btn5.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "5"));
        btn6.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "6"));
        btn7.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "7"));
        btn8.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "8"));
        btn9.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "9"));
        plus.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "+"));
        minus.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "-"));
        divide.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "/"));
        multiply.addActionListener(e -> equationTextField.setText(equationTextField.getText() + "*"));
        equals.addActionListener(e -> {
            String equation = equationTextField.getText();
            equationTextField.setText(calculate(equation));
        });

        controlPanel.add(btn1);
        controlPanel.add(btn2);
        controlPanel.add(btn3);
        controlPanel.add(plus);
        controlPanel.add(btn4);
        controlPanel.add(btn5);
        controlPanel.add(btn6);
        controlPanel.add(minus);
        controlPanel.add(btn7);
        controlPanel.add(btn8);
        controlPanel.add(btn9);
        controlPanel.add(multiply);
        controlPanel.add(btn0);
        controlPanel.add(equals);
        controlPanel.add(divide);
    }
}
