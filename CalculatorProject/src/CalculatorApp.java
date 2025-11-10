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

        setTitle("🧮 Κομπιουτεράκι - Ανανεωμένο UI");
        setSize(420, 600);
        getContentPane().setBackground(new Color(45, 45, 45)); // σκούρο φόντο
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Πεδίο εμφάνισης
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Κουμπιά αριθμών και πράξεων (μοντέρνο UI)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(60, 60, 60));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
            button.setForeground(Color.WHITE);

            if (text.equals("C")) {
                button.setBackground(new Color(200, 70, 70)); // κόκκινο
            } else if (text.equals("=")) {
                button.setBackground(new Color(70, 130, 180)); // μπλε
            } else if (text.matches("[/*\\-+]")) {
                button.setBackground(new Color(90, 90, 90)); // πιο σκούρο για πράξεις
            } else {
                button.setBackground(new Color(100, 100, 100)); // γκρι για αριθμούς
            }

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
                String[] parts = display.getText().split("[\\+\\-\\*/]");
                if (parts.length < 2) return;

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
                            display.setText("Σφάλμα: Διαίρεση με 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Σφάλμα");
            }
        }

        // Τελεστές (+, -, *, /)
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
