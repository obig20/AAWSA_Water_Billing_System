package ui;

import javax.swing.*;
import service.PaymentService;

public class PaymentUI extends JFrame {
    private PaymentService paymentService = new PaymentService();

    public PaymentUI() {
        setTitle("Payment Processing");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Customer Code:")).setBounds(20, 20, 100, 25);
        JTextField codeField = new JTextField();
        codeField.setBounds(130, 20, 150, 25);
        add(codeField);

        JButton check = new JButton("Check Balance");
        check.setBounds(130, 60, 120, 30);
        add(check);

        JLabel balanceLabel = new JLabel("Balance: ");
        balanceLabel.setBounds(20, 100, 200, 25);
        add(balanceLabel);

        add(new JLabel("Payment Method:")).setBounds(20, 140, 100, 25);
        JComboBox<String> methodBox = new JComboBox<>(new String[]{"CBE", "Abissinia Bank", "TeleBirr", "Wash"});
        methodBox.setBounds(130, 140, 150, 25);
        add(methodBox);

        JButton pay = new JButton("Process Payment");
        pay.setBounds(130, 180, 150, 30);
        add(pay);

        check.addActionListener(e -> {
            double balance = paymentService.getOutstandingBalance(codeField.getText());
            balanceLabel.setText("Balance: " + balance + " ETB");
        });

        pay.addActionListener(e -> {
            String code = codeField.getText();
            double balance = paymentService.getOutstandingBalance(code);
            if (balance > 0) {
                boolean success = paymentService.processPayment(code, balance, (String) methodBox.getSelectedItem());
                if (success) {
                    String receipt = paymentService.generateReceipt(code, balance, (String) methodBox.getSelectedItem());
                    JOptionPane.showMessageDialog(this, receipt);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Payment failed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No outstanding balance.");
            }
        });

        setVisible(true);
    }
}