package ui;

import javax.swing.*;
import service.BillingService;

public class MeterReadingUI extends JFrame {
    private BillingService billingService = new BillingService();

    public MeterReadingUI() {
        setTitle("Meter Reading");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Customer ID:")).setBounds(20, 20, 80, 25);
        JTextField customerIdField = new JTextField();
        customerIdField.setBounds(110, 20, 150, 25);
        add(customerIdField);

        add(new JLabel("Current Reading:")).setBounds(20, 60, 100, 25);
        JTextField readingField = new JTextField();
        readingField.setBounds(130, 60, 130, 25);
        add(readingField);

        JButton submit = new JButton("Submit");
        submit.setBounds(110, 100, 100, 30);
        add(submit);

        submit.addActionListener(e -> {
            try {
                String customerId = customerIdField.getText();
                double reading = Double.parseDouble(readingField.getText());
                billingService.recordMeterReading(customerId, reading);
                JOptionPane.showMessageDialog(this, "Meter reading recorded and bill generated.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}