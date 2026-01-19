package ui;
import javax.swing.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard(){
        setTitle("Admin Dashboard");
        setSize(400,300);
        JButton add=new JButton("Add Customer");
        JButton bill=new JButton("Billing");
        add(add); add(bill);
        add.addActionListener(e->new AddCustomerUI());
        bill.addActionListener(e->new BillingUI());
        setVisible(true);
    }
}
