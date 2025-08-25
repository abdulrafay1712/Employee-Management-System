package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton view, add, remove;

    Home() {
        setLayout(null);

        // Set background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        // Heading for the home screen
        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(360, 20, 400, 40); // Adjusted to be centered
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(heading);

        // Buttons for actions
        add = new JButton("Add Employee");
        add.setBounds(480, 100, 150, 40); // Centered horizontally
        add.addActionListener(this);
        image.add(add);

        view = new JButton("View Employees");
        view.setBounds(480, 160, 150, 40); // Centered horizontally and below Add
        view.addActionListener(this);
        image.add(view);

        remove = new JButton("Remove Employee");
        remove.setBounds(480, 220, 150, 40); // Centered horizontally and below View
        remove.addActionListener(this);
        image.add(remove);

        // Frame settings
        setSize(1120, 630);
        setLocation(0, 40);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            // Hide Home and open AddEmployee
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            // Hide Home and open ViewEmployee
            setVisible(false);
            new ViewEmployee(); // Pass necessary data if required
        } else if (ae.getSource() == remove) {
            // Hide Home and open RemoveEmployee
            setVisible(false);
            new RemoveEmployee(); // Pass necessary data if required
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
