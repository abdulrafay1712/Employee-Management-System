package employee.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewEmployee extends JFrame {

    JTable employeeTable;
    DefaultTableModel model;
    JButton updateButton, backButton;

    ViewEmployee() {
        // Create the main panel with null layout for layering
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, 900, 700);

        // Set full-page background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/view.jpg"));
        Image img = backgroundImage.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT); // Scale image
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 900, 700);
        mainPanel.add(backgroundLabel);

        // Title label
        JLabel titleLabel = new JLabel("View Employees");
        titleLabel.setBounds(300, 20, 300, 40); // Centered at the top
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(titleLabel); // Add to the background label

        // Table header
        String[] columns = {"Emp ID", "Name", "Phone", "Email", "Father's Name", "DOB", "Salary", "Address", "Education", "Designation", "CNIC"};
        model = new DefaultTableModel(columns, 0);
        employeeTable = new JTable(model);

        // Table scroll pane
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(50, 100, 800, 300);
        backgroundLabel.add(scrollPane);

        // Load employee data from file and populate the table
        loadEmployeeData();

        // Buttons
        updateButton = new JButton("Update Employee");
        updateButton.setBounds(250, 450, 150, 40);
        updateButton.addActionListener(e -> updateEmployee());
        backgroundLabel.add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(500, 450, 150, 40);
        backButton.addActionListener(e -> {
            setVisible(false);
            new Home(); // Go back to Home screen
        });
        backgroundLabel.add(backButton);

        // Add main panel to the frame
        add(mainPanel);

        setSize(900, 700); // Adjust size
        setLocation(50, 50);
        setLayout(null);
        setVisible(true);
    }

    // Method to load data from the file and display it in the table
    private void loadEmployeeData() {
        // Clear existing rows before reloading data
        model.setRowCount(0);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] employeeDetails = line.split(",");
                model.addRow(employeeDetails); // Add the data to the table
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle Update Employee button click
    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();

        if (selectedRow != -1) {
            String empId = (String) employeeTable.getValueAt(selectedRow, 0);
            String name = (String) employeeTable.getValueAt(selectedRow, 1);
            String phone = (String) employeeTable.getValueAt(selectedRow, 2);
            String email = (String) employeeTable.getValueAt(selectedRow, 3);
            String fatherName = (String) employeeTable.getValueAt(selectedRow, 4);
            String dob = (String) employeeTable.getValueAt(selectedRow, 5);
            String salary = (String) employeeTable.getValueAt(selectedRow, 6);
            String address = (String) employeeTable.getValueAt(selectedRow, 7);
            String education = (String) employeeTable.getValueAt(selectedRow, 8);
            String designation = (String) employeeTable.getValueAt(selectedRow, 9);
            String cnic = (String) employeeTable.getValueAt(selectedRow, 10);

            // Open UpdateEmployee window with the selected employee data
            new UpdateEmployee(empId, name, phone, email, fatherName, dob, salary, address, education, designation, cnic);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update.");
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
