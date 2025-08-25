package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice cEmpId;
    JButton delete, back;
    JLabel lblname, lblphone, lblemail;

    RemoveEmployee() {

        // Set full-page background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image img = backgroundImage.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT); // Scale the image to fit the window
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 900, 700);

        // Add the background label first
        setLayout(null);
        add(backgroundLabel);

        // Font for title and other components
        Font titleFont = new Font("Serif", Font.BOLD, 28);
        Font boldFont = new Font("Serif", Font.BOLD, 18);

        // Add a title to the window
        JLabel title = new JLabel("Remove Employee");
        title.setBounds(300, 10, 300, 40); // Center-aligned at the top
        title.setForeground(Color.BLACK); // Title text color
        title.setFont(titleFont); // Apply title font
        title.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(title);

        // Set the layout and add components
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(300, 70, 150, 30);
        labelempId.setForeground(Color.BLUE);
        labelempId.setFont(boldFont); // Apply bold font
        backgroundLabel.add(labelempId);

        cEmpId = new Choice();
        cEmpId.setBounds(450, 70, 150, 30);
        cEmpId.setFont(boldFont); // Apply font to Choice
        backgroundLabel.add(cEmpId);

        loadEmployeeDetails();

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(300, 120, 150, 30);
        labelname.setForeground(Color.BLUE);
        labelname.setFont(boldFont); // Apply bold font
        backgroundLabel.add(labelname);

        lblname = new JLabel();
        lblname.setBounds(450, 120, 200, 30);
        lblname.setForeground(Color.BLUE);
        lblname.setFont(boldFont); // Apply bold font
        backgroundLabel.add(lblname);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(300, 170, 150, 30);
        labelphone.setForeground(Color.BLUE);
        labelphone.setFont(boldFont); // Apply bold font
        backgroundLabel.add(labelphone);

        lblphone = new JLabel();
        lblphone.setBounds(450, 170, 200, 30);
        lblphone.setForeground(Color.BLUE);
        lblphone.setFont(boldFont); // Apply bold font
        backgroundLabel.add(lblphone);

        JLabel labelemail = new JLabel("Date Of Birth");
        labelemail.setBounds(300, 220, 150, 30);
        labelemail.setForeground(Color.BLUE);
        labelemail.setFont(boldFont); // Apply bold font
        backgroundLabel.add(labelemail);

        lblemail = new JLabel();
        lblemail.setBounds(450, 220, 200, 30);
        lblemail.setForeground(Color.BLUE);
        lblemail.setFont(boldFont); // Apply bold font
        backgroundLabel.add(lblemail);

        cEmpId.addItemListener(e -> updateEmployeeDetails(cEmpId.getSelectedItem()));

        delete = new JButton("Delete");
        delete.setBounds(300, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setFont(boldFont); // Apply bold font
        delete.addActionListener(this);
        backgroundLabel.add(delete);

        back = new JButton("Back");
        back.setBounds(450, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(boldFont); // Apply bold font
        back.addActionListener(this);
        backgroundLabel.add(back);

        setSize(900, 700);
        setLocation(50, 50);
        setVisible(true);
    }

    private void loadEmployeeDetails() {
        cEmpId.removeAll(); // Refresh Choice box
        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                cEmpId.add(details[0]); // Add Employee ID to Choice
            }
            br.close();
            if (cEmpId.getItemCount() > 0) {
                updateEmployeeDetails(cEmpId.getSelectedItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEmployeeDetails(String empId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(empId)) {
                    lblname.setText(details[1]);
                    lblphone.setText(details[2]);
                    lblemail.setText(details[3]);
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeEmployee(String empId) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] details = currentLine.split(",");
                if (!details[0].equals(empId)) {
                    lines.add(currentLine); // Add all lines except the one with empId
                }
            }
            reader.close();

            // Write updated lines back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            String empId = cEmpId.getSelectedItem();
            removeEmployee(empId);
            JOptionPane.showMessageDialog(null, "Employee " + empId + " deleted successfully");
            loadEmployeeDetails(); // Refresh data
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
