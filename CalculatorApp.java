

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

