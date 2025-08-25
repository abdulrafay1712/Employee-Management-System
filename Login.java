package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JCheckBox showPasswordCheckBox;  // Checkbox to toggle password visibility

    Login() {
        setTitle("Employee Management System - Login");

        // Set layout and size
        setLayout(null);
        setSize(600, 350);
        setLocationRelativeTo(null); // Center the frame

        // Background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/backg1.jpeg"));
        Image img = backgroundImage.getImage().getScaledInstance(600, 350, Image.SCALE_DEFAULT); // Scale to fit window size
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 600, 350); // Position it to cover the entire window
        add(backgroundLabel);

        // Title Label (Centered at the top)
        JLabel titleLabel = new JLabel("Login to Your Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        titleLabel.setBounds(100, 20, 400, 40);
        backgroundLabel.add(titleLabel);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setBounds(50, 80, 100, 30);
        backgroundLabel.add(usernameLabel);

        // Username Field
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBounds(150, 80, 200, 30);
        backgroundLabel.add(usernameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setBounds(50, 130, 100, 30);
        backgroundLabel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(150, 130, 200, 30);
        backgroundLabel.add(passwordField);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        showPasswordCheckBox.setBounds(150, 170, 200, 25);
        showPasswordCheckBox.setBackground(new Color(255, 255, 255)); // Match background color
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });
        backgroundLabel.add(showPasswordCheckBox);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 102, 204)); // Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(150, 200, 200, 35);
        loginButton.addActionListener(this);
        backgroundLabel.add(loginButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ActionListener for the login button
    public void actionPerformed(ActionEvent ae) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check if the credentials are correct
        if (username.equals("management") && password.equals("admin123")) {
            // If credentials are correct, show Home page
            new Home(); // Open Home page
            setVisible(false); // Close Login page
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        new Login(); // Show Login page
    }
}
