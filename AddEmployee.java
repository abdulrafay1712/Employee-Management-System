package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField tfname, tffname, tfdob, tfsalary, tfaddress, tfphone, tfemail, tfdesignation, tfcnic;
    JComboBox<String> cbeducation;
    JLabel lblempId;
    JButton add, back;

    AddEmployee() {
        setTitle("Add Employee");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // Center the frame

        // Set full-page background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/ADDemployee.jpeg"));
        Image img = backgroundImage.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT); // Scale the image to fit the window
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 900, 700);
        add(backgroundLabel);

        // Create a panel to center-align form elements
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null); // Use absolute positioning for flexibility
        formPanel.setOpaque(false); // Make the panel transparent
        formPanel.setBounds(150, 100, 600, 500); // Position the panel in the center of the background

        // Heading
        JLabel heading = new JLabel("Add Employee Details", SwingConstants.CENTER);
        heading.setBounds(100, 10, 400, 40);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        formPanel.add(heading);

        // Labels and text fields
        JLabel labelname = new JLabel("Name:");
        labelname.setBounds(50, 70, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        labelname.setForeground(Color.WHITE);
        formPanel.add(labelname);

        tfname = new JTextField();
        tfname.setBounds(250, 70, 300, 30);
        formPanel.add(tfname);

        JLabel labelfname = new JLabel("Father's Name:");
        labelfname.setBounds(50, 120, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        labelfname.setForeground(Color.WHITE);
        formPanel.add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(250, 120, 300, 30);
        formPanel.add(tffname);

        JLabel labeldob = new JLabel("Date of Birth:");
        labeldob.setBounds(50, 170, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        labeldob.setForeground(Color.WHITE);
        formPanel.add(labeldob);

        tfdob = new JTextField("yyyy-MM-dd");
        tfdob.setBounds(250, 170, 300, 30);
        formPanel.add(tfdob);

        JLabel labelsalary = new JLabel("Salary:");
        labelsalary.setBounds(50, 220, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        labelsalary.setForeground(Color.WHITE);
        formPanel.add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(250, 220, 300, 30);
        formPanel.add(tfsalary);

        JLabel labeladdress = new JLabel("Address:");
        labeladdress.setBounds(50, 270, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        labeladdress.setForeground(Color.WHITE);
        formPanel.add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(250, 270, 300, 30);
        formPanel.add(tfaddress);

        JLabel labelphone = new JLabel("Phone:");
        labelphone.setBounds(50, 320, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        labelphone.setForeground(Color.WHITE);
        formPanel.add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(250, 320, 300, 30);
        formPanel.add(tfphone);

        JLabel labelempId = new JLabel("Employee ID:");
        labelempId.setBounds(50, 370, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        labelempId.setForeground(Color.WHITE);
        formPanel.add(labelempId);

        lblempId = new JLabel("" + (new Random().nextInt(999999)));
        lblempId.setBounds(250, 370, 300, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        lblempId.setForeground(Color.WHITE);
        formPanel.add(lblempId);

        // Buttons
        add = new JButton("Add Details");
        add.setBounds(150, 420, 120, 40);
        add.setBackground(new Color(0, 102, 204));
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        formPanel.add(add);

        back = new JButton("Back");
        back.setBounds(300, 420, 120, 40);
        back.setBackground(new Color(0, 102, 204));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        formPanel.add(back);

        backgroundLabel.add(formPanel); // Add formPanel to the background
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String empId = lblempId.getText();
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = tfdob.getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt", true));
                bw.write(empId + "," + name + "," + fname + "," + dob + "," + salary + "," + address + "," + phone);
                bw.newLine();
                bw.close();
                JOptionPane.showMessageDialog(null, "Employee added successfully.");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
