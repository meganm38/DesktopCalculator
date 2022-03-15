package calculator;

import Exceptions.InvalidEquation;
import Operation.CalculationPerformer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculator extends JFrame {
    private static final int GAP = 10;
    private JPanel controlPanel;
    private JLabel resultLabel;
    private JLabel equationLabel;
    private final CalculationPerformer calculationPerformer;

    public Calculator() {
        super("Calculator");
        calculationPerformer = new CalculationPerformer();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton fake = new JButton("0");
        Dimension dimension = fake.getPreferredSize();
        setSize(dimension.width * 4 + GAP * 5, 580);

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JPanel displayPanel = new JPanel(new GridLayout(2, 1));
        resultLabel = new JLabel("0", SwingConstants.RIGHT);
        resultLabel.setName("ResultLabel");
        resultLabel.setFont(new Font("Serif", Font.BOLD, 30));
        resultLabel.setBorder(new EmptyBorder(10, 20, 10, 20));

        equationLabel = new JLabel("", SwingConstants.RIGHT);
        equationLabel.setName("EquationLabel");
        equationLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        equationLabel.setForeground(Color.green.darker());
        equationLabel.setBorder(new EmptyBorder(10, 20, 10, 20));

        displayPanel.add(resultLabel);
        displayPanel.add(equationLabel);
        displayPanel.setPreferredSize(new Dimension(200, 150));

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, GAP, GAP));

        addButtonsToPanel();
        mainPanel.add(displayPanel);
        mainPanel.add(controlPanel);
        add(mainPanel);
    }


    public void addButtonsToPanel() {
        JButton btn1 = new JButton("1");
        btn1.setName("One");
        JButton btn2 = new JButton("2");
        btn2.setName("Two");
        JButton btn3 = new JButton("3");
        btn3.setName("Three");
        JButton btn4 = new JButton("4");
        btn4.setName("Four");
        JButton btn5 = new JButton("5");
        btn5.setName("Five");
        JButton btn6 = new JButton("6");
        btn6.setName("Six");
        JButton btn7 = new JButton("7");
        btn7.setName("Seven");
        JButton btn8 = new JButton("8");
        btn8.setName("Eight");
        JButton btn9 = new JButton("9");
        btn9.setName("Nine");
        JButton btn0 = new JButton("0");
        btn0.setName("Zero");

        JButton equals = new JButton("=");
        equals.setName("Equals");

        JButton plus = new JButton("\u002B");
        plus.setName("Add");
        JButton minus = new JButton("\u2212");
        minus.setName("Subtract");
        JButton multiply = new JButton("\u00D7");
        multiply.setName("Multiply");
        JButton divide = new JButton("\u00F7");
        divide.setName("Divide");

        JButton btnDecimal = new JButton(".");
        btnDecimal.setName("Dot");

        JButton btnClear = new JButton("C");
        btnClear.setName("Clear");

        JButton btnDelete = new JButton("Del");
        btnDelete.setName("Delete");

        JButton btnParentheses = new JButton("( )");
        btnParentheses.setName("Parentheses");

        JButton btnSquareRoot = new JButton("\u221A");
        btnSquareRoot.setName("SquareRoot");

        JButton btnPowerTwo = new JButton("x\u00b2");
        btnPowerTwo.setName("PowerTwo");

        JButton btnPowerY = new JButton("x\u02b8");
        btnPowerY.setName("PowerY");

        JButton btnPlusMinus = new JButton("\u00b1");
        btnPlusMinus.setName("PlusMinus");

        JButton btnCE = new JButton("CE");

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

        plus.addActionListener(e -> formatEquationLabel("+"));
        minus.addActionListener(e -> formatEquationLabel("-"));
        divide.addActionListener(e -> formatEquationLabel("\u00F7"));
        multiply.addActionListener(e -> formatEquationLabel("\u00D7"));
        btnSquareRoot.addActionListener(e -> equationLabel.setText(equationLabel.getText() + "\u221A("));
        btnPowerTwo.addActionListener(e -> {
            if (!equationLabel.getText().isEmpty()){
                equationLabel.setText(equationLabel.getText() + "^(2)");
            }
        });
        btnPowerY.addActionListener(e -> {
            if (!equationLabel.getText().isEmpty()) {
                equationLabel.setText(equationLabel.getText() + "^(");
            }
        });
        btnPlusMinus.addActionListener(e -> {
            String equation = equationLabel.getText();


            if (equation.startsWith("(-")) {
                equationLabel.setText(equation.substring(2));
            } else {
                equationLabel.setText("(-" + equationLabel.getText());

            }
        });

        equals.addActionListener(e -> {
            String equation = equationLabel.getText();
            try {
                calculationPerformer.checkValidEquation(equation);
                equationLabel.setForeground(Color.green.darker());
                resultLabel.setText(calculationPerformer.calculate(equation));
            } catch (InvalidEquation ex) {
                equationLabel.setForeground(Color.RED.darker());
            }
        });

        btnClear.addActionListener(e -> equationLabel.setText(""));

        btnDelete.addActionListener(e -> {
            String equation = equationLabel.getText();
            equationLabel.setText(equation.substring(0, equation.length() - 1));
        });

        btnDecimal.addActionListener(e -> {
            String lastInput = "";
            if (equationLabel.getText().length() > 0) {
                lastInput = equationLabel.getText().substring(equationLabel.getText().length() - 1);

            }
            if (lastInput.matches("\\d") || lastInput.equals("")) {
                equationLabel.setText(equationLabel.getText() + ".");
            } else {
                equationLabel.setText(equationLabel.getText() + "0.");
            }
        });

        btnParentheses.addActionListener(e -> {
            String equation = equationLabel.getText();
            int numLeft = 0;
            int numRight = 0;
            for (int i = 0; i < equation.length(); i++) {
                if (equation.charAt(i) == '(') {
                    numLeft++;
                }
                if (equation.charAt(i) == ')') {
                    numRight++;
                }
            }

            if (numLeft == numRight) {
                equationLabel.setText(equationLabel.getText() + "(");
            } else if (equation.substring(equation.length() - 1).matches("[+\\-\u00D7\u00F7(^]")) {
                equationLabel.setText(equationLabel.getText() + "(");
            } else {
                equationLabel.setText(equationLabel.getText() + ")");
            }
        });

        controlPanel.add(btnParentheses);
        controlPanel.add(btnCE);
        controlPanel.add(btnClear);
        controlPanel.add(btnDelete);

        controlPanel.add(btnPowerTwo);
        controlPanel.add(btnPowerY);
        controlPanel.add(btnSquareRoot);
        controlPanel.add(divide);

        controlPanel.add(btn7);
        controlPanel.add(btn8);
        controlPanel.add(btn9);
        controlPanel.add(multiply);

        controlPanel.add(btn4);
        controlPanel.add(btn5);
        controlPanel.add(btn6);
        controlPanel.add(minus);

        controlPanel.add(btn1);
        controlPanel.add(btn2);
        controlPanel.add(btn3);
        controlPanel.add(plus);

        controlPanel.add(btnPlusMinus);
        controlPanel.add(btn0);
        controlPanel.add(btnDecimal);
        controlPanel.add(equals);
    }

    private void formatEquationLabel(String operator) {
        String equation = equationLabel.getText();

        if (!equation.isEmpty()) {
            //replace the first operator with the second one if two operators are entered consecutively
            String lastChar = equation.substring(equation.length() - 1);
            if (lastChar.matches("([+\\-\u00D7\u00F7])")) {
                equation = equation.substring(0, equation.length() - 1) + operator;
            } else {
                equation = equation + operator;
            }

            //introduce leading or trailing zero if dot is entered without the integer part or decimal part
            int dotLastIndex = equation.lastIndexOf(".");
            if (dotLastIndex == 0 ||
                    (dotLastIndex != -1 &&
                            !equation.substring(dotLastIndex - 1, dotLastIndex).matches("\\d"))) {
                equation = equation.substring(0, dotLastIndex) + "0" + equation.substring(dotLastIndex);
            }
            dotLastIndex = equation.lastIndexOf(".");
            if (dotLastIndex != -1 &&
                    !equation.substring(dotLastIndex + 1, dotLastIndex + 2).matches("\\d")) {
                equation = equation.substring(0, dotLastIndex + 1) + "0" + equation.substring(dotLastIndex + 1);
            }
            equationLabel.setText(equation);
        }
    }
}
