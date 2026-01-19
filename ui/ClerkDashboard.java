package ui;

import javax.swing.*;

public class ClerkDashboard extends JFrame {
    public ClerkDashboard() {
        setTitle("Clerk Dashboard");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addCustomer = new JButton("Add Customer");
        addCustomer.setBounds(50, 50, 150, 30);
        add(addCustomer);

        JButton meterReading = new JButton("Meter Reading");
        meterReading.setBounds(50, 100, 150, 30);
        add(meterReading);

        JButton billing = new JButton("Billing");
        billing.setBounds(50, 150, 150, 30);
        add(billing);

        JButton payment = new JButton("Payment");
        payment.setBounds(50, 200, 150, 30);
        add(payment);

        addCustomer.addActionListener(e -> new AddCustomerUI());
        meterReading.addActionListener(e -> new MeterReadingUI());
        billing.addActionListener(e -> new BillingUI());
        payment.addActionListener(e -> new PaymentUI());

        setVisible(true);
    }
}