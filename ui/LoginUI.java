package ui;

import javax.swing.*;
import model.User;
import service.AuthService;

public class LoginUI extends JFrame {
    private AuthService authService = new AuthService();
    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();

    public LoginUI() {
        setTitle("AAWSA Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Username:")).setBounds(20, 20, 80, 25);
        userField.setBounds(110, 20, 150, 25);
        add(userField);

        add(new JLabel("Password:")).setBounds(20, 60, 80, 25);
        passField.setBounds(110, 60, 150, 25);
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(110, 100, 150, 30);
        add(loginBtn);

        loginBtn.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        User user = authService.login(userField.getText(), new String(passField.getPassword()));
        if (user != null) {
            dispose();
            if ("ADMIN".equals(user.getRole())) {
                new AdminDashboard();
            } else if ("CLERK".equals(user.getRole())) {
                new ClerkDashboard();
            } else if ("CUSTOMER".equals(user.getRole())) {
                new CustomerPaymentUI();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }
}
