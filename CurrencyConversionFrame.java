import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*Business P11.25  Write a program with a graphical interface that allows the user to convert an amount
 of money between U.S. dollars (USD), euro (EUR), and British pounds (GBP). The
 user interface should have the following elements: a text box to enter the amount to
 be converted, two combo boxes to allow the user to select the currencies, a button
 to make the conversion, and a label to show the result. Display a warning if the user
 does not choose different currencies. Use the following conversion rates:
 1 EUR is equal to 1.42 USD.
 1 GBP is equal to 1.64 USD.
 1 GBP is equal to 1.13 EUR.*/

public class CurrencyConversionFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;
    private JLabel fromCurrencyLabel;
    private JLabel toCurrencyLabel;
    private JTextField currencyAmountField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JButton converseButton;
    private JLabel resultLabel;
    private JLabel conversedAmountLabel;
    private static final double EUR_TO_USD = 1.42;
    private static final double GBP_TO_USD = 1.64;
    private static final double GBP_TO_EUR = 1.13;

    public CurrencyConversionFrame() {
        this.createComponents();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        super.setVisible(true);
    }

    private void createComponents() {
        final int FIELD_WIDTH = 10;
        this.currencyAmountField = new JTextField(FIELD_WIDTH);
        this.createLabels();
        this.createComboBoxes();
        this.converseButton = this.createConverseButton();

        JPanel topPanel = new JPanel();
        topPanel.add(this.fromCurrencyLabel);
        topPanel.add(this.fromCurrencyComboBox);
        topPanel.add(this.toCurrencyLabel);
        topPanel.add(this.toCurrencyComboBox);
        super.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(this.currencyAmountField);
        centerPanel.add(this.converseButton);
        centerPanel.add(this.resultLabel);
        centerPanel.add(this.conversedAmountLabel);
        super.add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createConverseButton() {
        JButton button = new JButton("Converse");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                double balance = 0;
                try {
                    balance = Double.parseDouble(currencyAmountField.getText());
                    if (balance < 0) {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: Currency amount must be a positive number",
                            "Invalid currency amount", JOptionPane.ERROR_MESSAGE);
                }

                String fromCurrency = "" + fromCurrencyComboBox.getSelectedItem();
                String toCurrency = "" + toCurrencyComboBox.getSelectedItem();
                if (balance == 0) {
                    return;
                } else if (fromCurrency.equals(toCurrency)) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Same currencies to convert from and convert to selected",
                            "Invalid currency conversion", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (fromCurrency.equals("EUR")) {
                        if (toCurrency.equals("USD")) {
                            conversedAmountLabel.setText(String.format("%.2f", balance * EUR_TO_USD));
                        } else {
                            conversedAmountLabel.setText(String.format("%.2f", balance / GBP_TO_EUR));
                        }
                    } else if (fromCurrency.equals("GBP")) {
                        if (toCurrency.equals("USD")) {
                            conversedAmountLabel.setText(String.format("%.2f", balance * GBP_TO_USD));
                        } else {
                            conversedAmountLabel.setText(String.format("%.2f", balance * GBP_TO_EUR));
                        }
                    } else if (fromCurrency.equals("USD")) {
                        if (toCurrency.equals("EUR")) {
                            conversedAmountLabel.setText(String.format("%.2f", balance / EUR_TO_USD));
                        } else {
                            conversedAmountLabel.setText(String.format("%.2f", balance / GBP_TO_USD));
                        }
                    }
                }
            }
        });
        return button;
    }

    private void createLabels() {
        this.fromCurrencyLabel = new JLabel("From:");
        this.toCurrencyLabel = new JLabel("To:");
        this.resultLabel = new JLabel("Conversed:");
        this.conversedAmountLabel = new JLabel("0.00");
    }

    private void createComboBoxes() {
        this.fromCurrencyComboBox = new JComboBox<String>();
        this.fromCurrencyComboBox.setEditable(false);
        this.fromCurrencyComboBox.addItem("USD");
        this.fromCurrencyComboBox.addItem("GBP");
        this.fromCurrencyComboBox.addItem("EUR");
        this.toCurrencyComboBox = new JComboBox<String>();
        this.toCurrencyComboBox.setEditable(false);
        this.toCurrencyComboBox.addItem("USD");
        this.toCurrencyComboBox.addItem("GBP");
        this.toCurrencyComboBox.addItem("EUR");
    }

    public static void main(String[] args) {
        JFrame testFrame = new CurrencyConversionFrame();
    }
}