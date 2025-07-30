package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import controllers.EmployeeController;
import models.Employee;

public class RemoveEmployeeGUI extends JFrame implements ActionListener{
    private JLabel userIdLabel,nameLabel;
    private JTextField userIdField,nameField;
    private JButton removeButton,cancelButton,searchButton;
    private JPanel panel;
    private EmployeeController employeeController = new EmployeeController();
    private Employee foundEmployee = null;
    private JLabel genderLabel, ageLabel, emailLabel, phoneLabel, addressLabel, nidLabel, designationLabel, salaryLabel;
    private JTextField genderField, ageField, emailField, phoneField, addressField, nidField, designationField, salaryField;

    public RemoveEmployeeGUI(){ 
        super("Remove Employee");
        this.setSize(500,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.userIdLabel = new JLabel("User ID: ");
        this.userIdLabel.setBounds(50,20,150,30);
        this.panel.add(this.userIdLabel);

        this.userIdField = new JTextField();
        this.userIdField.setBounds(150,20,200,30);
        this.panel.add(this.userIdField);

        this.nameLabel = new JLabel("   Name: ");
        this.nameLabel.setBounds(50,60,150,30);
        this.panel.add(this.nameLabel);

        this.nameField = new JTextField();
        this.nameField.setBounds(150,60,200,30);    
        this.panel.add(this.nameField);

        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(150,450,200,30);    
        this.searchButton.addActionListener(this);
        this.searchButton.setBackground(new java.awt.Color(52, 152, 219)); // Blue
        this.searchButton.setForeground(java.awt.Color.WHITE);
        this.searchButton.setFocusPainted(false);
        this.addAnimatedButtonEffect(this.searchButton, new java.awt.Color(41, 128, 185));
        this.panel.add(this.searchButton);

        this.removeButton = new JButton("Remove Employee");
        this.removeButton.setBounds(150,500,200,30);
        this.removeButton.addActionListener(this);
        this.removeButton.setBackground(new java.awt.Color(231, 76, 60)); // Red
        this.removeButton.setForeground(java.awt.Color.WHITE);
        this.removeButton.setFocusPainted(false);
        this.addAnimatedButtonEffect(this.removeButton, new java.awt.Color(192, 57, 43));
        this.panel.add(this.removeButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(150,550,200,30);
        this.cancelButton.addActionListener(this);
        this.cancelButton.setBackground(new java.awt.Color(46, 204, 113)); // Green
        this.cancelButton.setForeground(java.awt.Color.WHITE);
        this.cancelButton.setFocusPainted(false);
        this.addAnimatedButtonEffect(this.cancelButton, new java.awt.Color(39, 174, 96));
        this.panel.add(this.cancelButton);

        this.genderLabel = new JLabel("Gender: ");
        this.genderLabel.setBounds(50,100,150,30);
        this.panel.add(this.genderLabel);
        this.genderField = new JTextField();
        this.genderField.setBounds(150,100,200,30);
        this.genderField.setEditable(false);
        this.panel.add(this.genderField);

        this.ageLabel = new JLabel("Age: ");
        this.ageLabel.setBounds(50,140,150,30);
        this.panel.add(this.ageLabel);
        this.ageField = new JTextField();
        this.ageField.setBounds(150,140,200,30);
        this.ageField.setEditable(false);
        this.panel.add(this.ageField);

        this.emailLabel = new JLabel("Email: ");
        this.emailLabel.setBounds(50,180,150,30);
        this.panel.add(this.emailLabel);
        this.emailField = new JTextField();
        this.emailField.setBounds(150,180,200,30);
        this.emailField.setEditable(false);
        this.panel.add(this.emailField);

        this.phoneLabel = new JLabel("Phone: ");
        this.phoneLabel.setBounds(50,220,150,30);
        this.panel.add(this.phoneLabel);
        this.phoneField = new JTextField();
        this.phoneField.setBounds(150,220,200,30);
        this.phoneField.setEditable(false);
        this.panel.add(this.phoneField);

        this.addressLabel = new JLabel("Address: ");
        this.addressLabel.setBounds(50,260,150,30);
        this.panel.add(this.addressLabel);
        this.addressField = new JTextField();
        this.addressField.setBounds(150,260,200,30);
        this.addressField.setEditable(false);
        this.panel.add(this.addressField);

        this.nidLabel = new JLabel("NID: ");
        this.nidLabel.setBounds(50,300,150,30);
        this.panel.add(this.nidLabel);
        this.nidField = new JTextField();
        this.nidField.setBounds(150,300,200,30);
        this.nidField.setEditable(false);
        this.panel.add(this.nidField);

        this.designationLabel = new JLabel("Designation: ");
        this.designationLabel.setBounds(50,340,150,30);
        this.panel.add(this.designationLabel);
        this.designationField = new JTextField();
        this.designationField.setBounds(150,340,200,30);
        this.designationField.setEditable(false);
        this.panel.add(this.designationField);

        this.salaryLabel = new JLabel("Salary: ");
        this.salaryLabel.setBounds(50,380,150,30);
        this.panel.add(this.salaryLabel);
        this.salaryField = new JTextField();
        this.salaryField.setBounds(150,380,200,30);
        this.salaryField.setEditable(false);
        this.panel.add(this.salaryField);

        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.searchButton){
            String userId = userIdField.getText().trim();
            if(userId.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
                return;
            }
            foundEmployee = employeeController.searchEmployee(userId);
            if(foundEmployee != null){
                nameField.setText(displayOrDefault(foundEmployee.getName()));
                genderField.setText(displayOrDefault(foundEmployee.getGender()));
                ageField.setText(foundEmployee.getAge() == 0 ? "Not Provided" : String.valueOf(foundEmployee.getAge()));
                emailField.setText(displayOrDefault(foundEmployee.getEmail()));
                phoneField.setText(displayOrDefault(foundEmployee.getPhoneNo()));
                addressField.setText(displayOrDefault(foundEmployee.getAddress()));
                nidField.setText(displayOrDefault(foundEmployee.getNID()));
                designationField.setText(displayOrDefault(foundEmployee.getDesignation()));
                salaryField.setText(foundEmployee.getSalary() == 0.0 ? "Not Provided" : String.valueOf(foundEmployee.getSalary()));
            } else {
                nameField.setText("");
                genderField.setText("");
                ageField.setText("");
                emailField.setText("");
                phoneField.setText("");
                addressField.setText("");
                nidField.setText("");
                designationField.setText("");
                salaryField.setText("");
                JOptionPane.showMessageDialog(this, "Employee not found.");
            }
        }
        else if(e.getSource() == this.removeButton){
            String userId = userIdField.getText().trim();
            if(userId.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
                return;
            }
            if(foundEmployee == null || !userId.equals(foundEmployee.getUserId())){
                JOptionPane.showMessageDialog(this, "Please search and select a valid employee before removing.");
                return;
            }
            employeeController.deleteEmployee(userId);
            JOptionPane.showMessageDialog(this, "Employee removed successfully.");
            userIdField.setText("");
            nameField.setText("");
            genderField.setText("");
            ageField.setText("");
            emailField.setText("");
            phoneField.setText("");
            addressField.setText("");
            nidField.setText("");
            designationField.setText("");
            salaryField.setText("");
            foundEmployee = null;
        }
        else if (e.getSource() == this.cancelButton){
            new AdminGUI(null).setVisible(true);
            dispose();
        }
    }

    private String displayOrDefault(String value) {
        return (value == null || value.trim().isEmpty()) ? "Not Provided" : value;
    }

    // Add this method to the class for button animation and hover effect
    private void addAnimatedButtonEffect(final JButton button, final java.awt.Color hoverColor) {
        final java.awt.Color originalColor = button.getBackground();
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(hoverColor);
                button.setSize(button.getWidth() + 10, button.getHeight() + 5);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(originalColor);
                button.setSize(200, 30); // Reset to original size
            }
        });
    }

    public static void main(String[] args) {
        new RemoveEmployeeGUI().setVisible(true);
    }
}
