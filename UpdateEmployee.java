package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfname, tffname, tfdob, tfsalary, tfaddress, tfphone, tfemail, tfdesignation, tfcnic;
    JComboBox<String> cbeducation;
    JButton update, back;
    String empId;

    UpdateEmployee(String empId) {
        this.empId = empId;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        // Set up fields for name, father's name, etc. (same as AddEmployee)
        setupFormFields();

        // Load employee data based on the employee ID
        loadEmployeeData(empId);

        // Buttons
        update = new JButton("Update");
        update.setBounds(200, 550, 150, 40);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(400, 550, 150, 40);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(50, 50);
        setVisible(true);
    }

    public UpdateEmployee() {
        //TODO Auto-generated constructor stub
    }

    public UpdateEmployee(String empId2, String name, String phone, String email, String fatherName, String dob,
            String salary, String address, String education, String designation, String cnic) {
        //TODO Auto-generated constructor stub
    }

    private void setupFormFields() {
        // Add your form fields setup here, same as in the `AddEmployee` form
        // For simplicity, fields like tfname, tffname, etc. should be declared and initialized
        // as they were in the `AddEmployee` form.
    }

    private void loadEmployeeData(String empId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] employeeDetails = line.split(",");
                if (employeeDetails[0].equals(empId)) {
                    // Populate the form fields with employee data
                    tfname.setText(employeeDetails[1]);
                    tffname.setText(employeeDetails[4]);
                    tfdob.setText(employeeDetails[5]);
                    tfsalary.setText(employeeDetails[6]);
                    tfaddress.setText(employeeDetails[7]);
                    tfphone.setText(employeeDetails[2]);
                    tfemail.setText(employeeDetails[3]);
                    cbeducation.setSelectedItem(employeeDetails[8]);
                    tfdesignation.setText(employeeDetails[9]);
                    tfcnic.setText(employeeDetails[10]);
                    break;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            // Update employee details in the file
            updateEmployeeInFile();

            JOptionPane.showMessageDialog(null, "Employee updated successfully.");
            setVisible(false);
            new ViewEmployee(); // Or another page
        } else if (ae.getSource() == back) {
            setVisible(false);
            new ViewEmployee();
        }
    }

    private void updateEmployeeInFile() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] employeeDetails = line.split(",");
                if (employeeDetails[0].equals(empId)) {
                    // Update the employee details with the new form values
                    employeeDetails[1] = tfname.getText();
                    employeeDetails[2] = tfphone.getText();
                    employeeDetails[3] = tfemail.getText();
                    employeeDetails[4] = tffname.getText();
                    employeeDetails[5] = tfdob.getText();
                    employeeDetails[6] = tfsalary.getText();
                    employeeDetails[7] = tfaddress.getText();
                    employeeDetails[8] = (String) cbeducation.getSelectedItem();
                    employeeDetails[9] = tfdesignation.getText();
                    employeeDetails[10] = tfcnic.getText();
                }
                lines.add(String.join(",", employeeDetails));
            }

            reader.close();

            // Write the updated data back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("123456"); // Example ID
    }
}
