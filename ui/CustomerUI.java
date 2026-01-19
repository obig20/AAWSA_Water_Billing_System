package ui;
import javax.swing.*;
import dao.CustomerDAO;
import model.*;
import service.BillingService;

public class CustomerUI extends JFrame {
    JTextField id=new JTextField(), reading=new JTextField(), acc=new JTextField();
    JTextArea area=new JTextArea();
    Bill bill;

    public CustomerUI(){
        setTitle("Customer Payment");
        setSize(500,450);
        setLayout(null);

        add(new JLabel("Customer ID")).setBounds(20,20,100,25);
        id.setBounds(140,20,200,25); add(id);

        add(new JLabel("Meter Count")).setBounds(20,60,100,25);
        reading.setBounds(140,60,200,25); add(reading);

        JButton calc=new JButton("Calculate");
        calc.setBounds(140,100,200,30); add(calc);

        area.setBounds(20,140,440,120); add(area);

        add(new JLabel("Bank/Birr No")).setBounds(20,270,100,25);
        acc.setBounds(140,270,200,25); add(acc);

        JButton pay=new JButton("Confirm Payment");
        pay.setBounds(140,310,200,30); add(pay);

        calc.addActionListener(e->calculate());
        pay.addActionListener(e->pay());

        setVisible(true);
    }

    void calculate(){
        try{
            BillingService billingService = new BillingService();
            billingService.recordMeterReading(id.getText(), Double.parseDouble(reading.getText()));
            Customer c = new CustomerDAO().getCustomerById(id.getText());
            area.setText("Outstanding Balance: " + c.getBalance() + " Birr");
        }catch(Exception e){e.printStackTrace();}
    }

    void pay(){
        JOptionPane.showMessageDialog(this,"Payment Successful");
    }
}
