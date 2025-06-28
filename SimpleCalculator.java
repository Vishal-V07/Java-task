# Java-task
Arttifai tech's intern task
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1, num2;
    private String operator;
    private String operationString="";
    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("[0-9]") || command.equals("0")) {
            display.setText(display.getText() + command);
            operationString+=command;
        } else if (command.equals("C")) {
            display.setText("");
            operator = "";
            num1 = num2 = 0;
            operationString="";
        } else if (command.equals("=")) {
            if (!operator.isEmpty() && !display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                calculate();
            }
        } else {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = command;
                operationString+=""+command+"";
                display.setText("");
            }
        }
    }
    private void calculate() {
        double result = 0;
        try {
            if (operator.equals("+")) result = num1 + num2;
            else if (operator.equals("-")) result = num1 - num2;
            else if (operator.equals("*")) result = num1 * num2;
            else if (operator.equals("/")) {
                if (num2 == 0) {
                    display.setText("Error");
                    return;
                }
                result = num1 / num2;
            }
            operationString+="="+String.valueOf(result);
            display.setText(operationString);
            operationString="";
        } catch (Throwable e) {
            display.setText("Error");
        }
    }
    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
