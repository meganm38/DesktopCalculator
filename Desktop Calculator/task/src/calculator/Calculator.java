package calculator;

import Exceptions.InvalidEquation;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private static final int GAP = 10;
    private JPanel controlPanel;
    private JLabel resultLabel;
    private JLabel equationLabel;
    private JPanel displayPanel;
    private JPanel mainPanel;

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton fake = new JButton("0");
        Dimension dimension = fake.getPreferredSize();
        setSize(dimension.width * 4 + GAP * 5, 480);

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComponents() {
        mainPanel = new JPanel(new GridLayout(2, 1));
        displayPanel = new JPanel(new GridLayout(2, 1));
        resultLabel = new JLabel("0", SwingConstants.RIGHT);
        resultLabel.setName("ResultLabel");
        resultLabel.setFont(new Font("Serif", Font.BOLD, 30));

        equationLabel = new JLabel("", SwingConstants.RIGHT);
        equationLabel.setName("EquationLabel");
        equationLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        equationLabel.setForeground(Color.blue);

        displayPanel.add(resultLabel);
        displayPanel.add(equationLabel);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, GAP, GAP));

        addButtonsToPanel();
        mainPanel.add(displayPanel);
        mainPanel.add(controlPanel);
        add(mainPanel);
    }

    public String calculate(String equation) {
        try {
            checkValidEquation(equation);
            String[] operands = equation.split("[+\\-x/]");
            String operator = equation.substring(operands[0].length(), operands[0].length() + 1);

            int result;
            switch (operator) {
                case "+":
                    result = Integer.parseInt(operands[0]) + Integer.parseInt(operands[1]);
                    break;
                case "-":
                    result = Integer.parseInt(operands[0]) - Integer.parseInt(operands[1]);
                    break;
                case "x":
                    result = Integer.parseInt(operands[0]) * Integer.parseInt(operands[1]);
                    break;
                default:
                    result = Integer.parseInt(operands[0]) / Integer.parseInt(operands[1]);
                    break;
            }
            return result + "";
        } catch (InvalidEquation e) {
            return "Invalid input";
        }
    }

    public void checkValidEquation(String equation) throws InvalidEquation {
        //only supports integer addition now
        if (!equation.matches("((0|[1-9](\\d)*)([+\\-x/])(0|[1-9](\\d)*))+")) {
            throw new InvalidEquation();
        }
    }

    public void addButtonsToPanel() {
        JButton btn1 = new JButton("1");
        btn1.setName("One");
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

        JButton btnDecimal = new JButton(".");
        btnDecimal.setName("Dot");

        JButton btnClear = new JButton("C");
        btnClear.setName("Clear");

        JButton btnDelete = new JButton("Del");
        btnDelete.setName("Delete");

        btn0.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "0"));
        btn1.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "1"));
        btn2.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "2"));
        btn3.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "3"));
        btn4.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "4"));
        btn5.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "5"));
        btn6.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "6"));
        btn7.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "7"));
        btn8.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "8"));
        btn9.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "9"));
        plus.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "+"));
        minus.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "-"));
        divide.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "/"));
        multiply.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "x"));
        equals.addActionListener(e -> {
            String equation = equationLabel.getText();
            resultLabel.setText(calculate(equation));
        });
        btnClear.addActionListener(e -> equationLabel.setText(""));
        btnDelete.addActionListener(e -> {
            String equation = equationLabel.getText();
            equationLabel.setText(equation.substring(0, equation.length() - 1));
        });
        btnDecimal.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "."));

        JLabel fake = new JLabel();
        fake.setPreferredSize(btn0.getPreferredSize());
        JLabel fake1 = new JLabel();
        fake1.setPreferredSize(btn0.getPreferredSize());
        controlPanel.add(fake);
        controlPanel.add(fake1);
        controlPanel.add(btnClear);
        controlPanel.add(btnDelete);
        controlPanel.add(btn7);
        controlPanel.add(btn8);
        controlPanel.add(btn9);
        controlPanel.add(divide);
        controlPanel.add(btn4);
        controlPanel.add(btn5);
        controlPanel.add(btn6);
        controlPanel.add(multiply);
        controlPanel.add(btn1);
        controlPanel.add(btn2);
        controlPanel.add(btn3);
        controlPanel.add(plus);
        controlPanel.add(btnDecimal);
        controlPanel.add(btn0);
        controlPanel.add(equals);
        controlPanel.add(minus);
    }
}
