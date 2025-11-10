import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField display;
    private double num1, num2, result;
    private char operator;

    public CalculatorApp() {
        // Ρυθμίσεις παραθύρου
        setTitle("Κομπιουτεράκι");
        setSize(400, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Πεδίο εμφάνισης
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Κουμπιά αριθμών και πράξεων
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
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

        // Αν πατηθεί αριθμός
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        }

        // Καθαρισμός (C)
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        }

        // Υπολογισμός (=)
        else if (command.equals("=")) {
            try {
                // Έλεγχος για σωστή έκφραση (π.χ. "3+5")
                if (display.getText().isEmpty() || !display.getText().matches(".*[\\+\\-\\*/].*")) {
                    display.setText("Σφάλμα: Άκυρη πράξη");
                    return;
                }

                String[] parts = display.getText().split("[\\+\\-\\*/]");
                if (parts.length < 2) {
                    display.setText("Σφάλμα: Ελλιπής είσοδος");
                    return;
                }

                num1 = Double.parseDouble(parts[0]);
                num2 = Double.parseDouble(parts[1]);

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            display.setText("Σφάλμα: Διαίρεση με 0!");
                            return; // Διακόπτουμε τον υπολογισμό
                        }
                        result = num1 / num2;
                        break;
                }

                display.setText(String.valueOf(result));

            } catch (Exception ex) {
                display.setText("Σφάλμα!");
            }
        }

        // Τελεστές (+, -, *, /)
        else {
            if (!display.getText().isEmpty() && !display.getText().endsWith("+") &&
                    !display.getText().endsWith("-") && !display.getText().endsWith("*") &&
                    !display.getText().endsWith("/")) {
                operator = command.charAt(0);
                display.setText(display.getText() + operator);
            }
        }
    }


    public static void main(String[] args) {
        new CalculatorApp();
    }
}
