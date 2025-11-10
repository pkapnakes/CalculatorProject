import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField display;
    private double num1, num2, result;
    private char operator;

    public CalculatorApp() {
        setTitle("Κομπιουτεράκι");
        setSize(400, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // μία γραμμή παραπάνω

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "√", "^", "%"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        }
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        }
        else if (command.equals("√")) {
            try {
                double value = Double.parseDouble(display.getText());
                if (value < 0) {
                    display.setText("Σφάλμα: Αρνητικός αριθμός");
                } else {
                    result = Math.sqrt(value);
                    display.setText(String.valueOf(result));
                }
            } catch (Exception ex) {
                display.setText("Σφάλμα");
            }
        }
        else if (command.equals("=")) {
            try {
                String[] parts = display.getText().split("[\\+\\-\\*/\\^%]");
                if (parts.length < 2) return;

                num1 = Double.parseDouble(parts[0]);
                num2 = Double.parseDouble(parts[1]);

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            display.setText("Σφάλμα: Διαίρεση με 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    case '^': result = Math.pow(num1, num2); break;
                    case '%': result = (num1 * num2) / 100; break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Σφάλμα");
            }
        }
        else {
            if (!display.getText().isEmpty()) {
                operator = command.charAt(0);
                display.setText(display.getText() + operator);
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
