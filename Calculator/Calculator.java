import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Scientific Calculator Application
 * 
 * A feature-rich calculator GUI application built with Java Swing that supports:
 * - Basic arithmetic operations (+, -, *, /)
 * - Scientific functions (sin, cos, tan, log, ln, sqrt)
 * - Advanced calculations (factorial, power, percentage)
 * - Mathematical constants (π, e)
 * 
 * The calculator features a modern dark theme UI with color-coded buttons for different operation types.
 * 
 * @author Developer
 * @version 2.0
 */
public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;
    private String currentInput = "";  // Tracks current number being entered

    /**
     * Initializes and constructs the calculator GUI with display panel and button grid
     */
    public Calculator() {
        setTitle("Scientific Calculator");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 44, 52));

        // Display Panel Setup
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(new Color(40, 44, 52));
        displayPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        displayPanel.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Segoe UI", Font.BOLD, 32));
        display.setBackground(new Color(61, 66, 76));
        display.setForeground(new Color(200, 200, 200));
        display.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        display.setPreferredSize(new Dimension(0, 60));
        displayPanel.add(display, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);

        // Button Grid Panel Setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 6, 8, 8));
        panel.setBackground(new Color(40, 44, 52));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "sin", "cos", "tan", "π", "e", "C",
            "log", "ln", "x^y", "√", "%", "AC",
            "7", "8", "9", "/", "!", "(",
            "4", "5", "6", "*", ")", "",
            "1", "2", "3", "-", ".", "",
            "0", "0", "=", "+", "±", "",
            "DEL", "", "", "", "", ""
        };

        // Initialize buttons with appropriate styling and event handling
        for (String text : buttons) {
            if (!text.isEmpty()) {
                JButton button = new JButton(text);
                button.addActionListener(this);
                button.setFont(new Font("Segoe UI", Font.BOLD, 16));
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Apply color coding based on button function
                if (text.matches("[+\\-*/]")) {
                    // Operator buttons - Red
                    button.setBackground(new Color(255, 107, 107));
                    button.setForeground(Color.WHITE);
                } else if (text.equals("=")) {
                    // Equals button - Green
                    button.setBackground(new Color(76, 175, 80));
                    button.setForeground(Color.WHITE);
                } else if (text.matches("sin|cos|tan|log|ln|√|!|x\\^y|π|e|±")) {
                    // Scientific functions - Blue
                    button.setBackground(new Color(66, 133, 244));
                    button.setForeground(Color.WHITE);
                } else if (text.matches("C|AC|DEL")) {
                    // Clear/Delete buttons - Dark Red
                    button.setBackground(new Color(244, 67, 54));
                    button.setForeground(Color.WHITE);
                } else if (text.matches("%|\\(|\\)")) {
                    // Percentage and Parentheses - Purple
                    button.setBackground(new Color(156, 39, 176));
                    button.setForeground(Color.WHITE);
                } else {
                    // Number buttons - Dark Gray
                    button.setBackground(new Color(80, 87, 100));
                    button.setForeground(new Color(200, 200, 200));
                }

                button.setOpaque(true);
                panel.add(button);
            } else {
                panel.add(new JLabel());
            }
        }

        add(panel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Handles all button click events and routes them to appropriate calculation or operation methods
     * 
     * @param e the action event triggered by button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            currentInput += command;
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals(".")) {
            if (!currentInput.contains(".")) {
                if (startNewNumber) {
                    currentInput = "0.";
                    display.setText("0.");
                    startNewNumber = false;
                } else {
                    currentInput += ".";
                    display.setText(display.getText() + ".");
                }
            }
        } else if (command.matches("[+\\-*/]")) {
            if (!operator.isEmpty() && !currentInput.isEmpty()) {
                // Chain calculation: perform previous operation before moving to next
                secondNumber = Double.parseDouble(currentInput);
                calculate();
                firstNumber = Double.parseDouble(display.getText().split(" ")[0]);
            } else if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
            }
            
            operator = command;
            currentInput = "";
            display.setText(display.getText() + " " + command + " ");
            startNewNumber = true;
        } else if (command.equals("x^y")) {
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operator = "^";
                currentInput = "";
                display.setText(display.getText() + " x^y ");
                startNewNumber = true;
            }
        } else if (command.equals("=")) {
            if (!operator.isEmpty() && !currentInput.isEmpty()) {
                secondNumber = Double.parseDouble(currentInput);
                calculate();
                currentInput = "";
                operator = "";
            }
        } else if (command.equals("√")) {
            performScientificOperation("sqrt");
        } else if (command.equals("sin")) {
            performScientificOperation("sin");
        } else if (command.equals("cos")) {
            performScientificOperation("cos");
        } else if (command.equals("tan")) {
            performScientificOperation("tan");
        } else if (command.equals("log")) {
            performScientificOperation("log");
        } else if (command.equals("ln")) {
            performScientificOperation("ln");
        } else if (command.equals("!")) {
            performScientificOperation("factorial");
        } else if (command.equals("%")) {
            performScientificOperation("percent");
        } else if (command.equals("π")) {
            display.setText(String.valueOf(Math.PI));
            currentInput = String.valueOf(Math.PI);
            startNewNumber = true;
        } else if (command.equals("e")) {
            display.setText(String.valueOf(Math.E));
            currentInput = String.valueOf(Math.E);
            startNewNumber = true;
        } else if (command.equals("±")) {
            if (!currentInput.isEmpty()) {
                double num = Double.parseDouble(currentInput);
                currentInput = String.valueOf(-num);
                display.setText(currentInput);
            }
        } else if (command.equals("(")) {
            currentInput += "(";
            display.setText(display.getText() + "(");
            startNewNumber = false;
        } else if (command.equals(")")) {
            currentInput += ")";
            display.setText(display.getText() + ")");
        } else if (command.equals("C")) {
            display.setText("");
            currentInput = "";
            startNewNumber = true;
        } else if (command.equals("AC")) {
            display.setText("");
            firstNumber = 0;
            secondNumber = 0;
            currentInput = "";
            operator = "";
            startNewNumber = true;
        } else if (command.equals("DEL")) {
            String current = display.getText();
            if (!current.isEmpty()) {
                display.setText(current.substring(0, current.length() - 1));
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                }
            }
        }
    }

    /**
     * Executes scientific operations like trigonometric, logarithmic, and other advanced functions
     * Includes error handling for invalid inputs (negative logarithms, etc.)
     * 
     * @param operation the name of the operation to perform
     */
    private void performScientificOperation(String operation) {
        try {
            if (currentInput.isEmpty()) return;
            double num = Double.parseDouble(currentInput);
            double result = 0;

            switch (operation) {
                case "sqrt":
                    if (num < 0) {
                        display.setText("Error: Negative √");
                        startNewNumber = true;
                        currentInput = "";
                        return;
                    }
                    result = Math.sqrt(num);
                    break;
                case "sin":
                    result = Math.sin(Math.toRadians(num));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(num));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(num));
                    break;
                case "log":
                    if (num <= 0) {
                        display.setText("Error: log(x>0)");
                        startNewNumber = true;
                        currentInput = "";
                        return;
                    }
                    result = Math.log10(num);
                    break;
                case "ln":
                    if (num <= 0) {
                        display.setText("Error: ln(x>0)");
                        startNewNumber = true;
                        currentInput = "";
                        return;
                    }
                    result = Math.log(num);
                    break;
                case "factorial":
                    if (num < 0 || num != (int) num) {
                        display.setText("Error: !n≥0");
                        startNewNumber = true;
                        currentInput = "";
                        return;
                    }
                    result = factorial((int) num);
                    break;
                case "percent":
                    result = num / 100;
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            startNewNumber = true;
        } catch (Exception e) {
            display.setText("Error");
            startNewNumber = true;
            currentInput = "";
        }
    }

    /**
     * Calculates the factorial of a given number
     * 
     * @param n the number to calculate factorial for
     * @return the factorial result
     */
    private double factorial(int n) {
        if (n < 0) return Double.NaN;
        if (n == 0 || n == 1) return 1;
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Performs the selected arithmetic or scientific operation on two operands
     * Supports +, -, *, /, and power operations
     */
    private void calculate() {
        try {
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error: Div by 0");
                        operator = "";
                        startNewNumber = true;
                        currentInput = "";
                        return;
                    }
                    break;
                case "^":
                    result = Math.pow(firstNumber, secondNumber);
                    break;
            }
            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            firstNumber = result;
            startNewNumber = true;
        } catch (Exception e) {
            display.setText("Error");
            startNewNumber = true;
            currentInput = "";
        }
    }

    /**
     * Application entry point
     * Creates and displays the calculator GUI
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
