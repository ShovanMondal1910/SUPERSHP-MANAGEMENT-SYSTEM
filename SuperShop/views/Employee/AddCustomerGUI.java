package views.Employee;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class AddCustomerGUI extends JFrame implements ActionListener{
    private JTextField genaratedCustomerIdField,customerNameField,customerAgeField,customerEmailField,customerPhoneField,customerAddressField,securityAnsField,passwordField;
    private JLabel genaratedCustomerIdLabel,customerNameLabel,customerAgeLabel,customerEmailLabel,customerPhoneLabel,customerAddressLabel,securityAnsLabel,passwordLabel,membershipTypeLabel;
    private JButton addCustomerButton,cancelButton;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> membershipTypeComboBox;
    private JPanel panel;
    private JLabel customerGenderLabel;
    public AddCustomerGUI(){
        super("Add Customer");
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.genaratedCustomerIdLabel = new JLabel("Genarated Customer ID:");
        this.genaratedCustomerIdLabel.setBounds(50, 15, 150, 30);
        this.panel.add(this.genaratedCustomerIdLabel);

        this.genaratedCustomerIdField = new JTextField();
        this.genaratedCustomerIdField.setBounds(210, 15, 200, 30);
        this.genaratedCustomerIdField.setEditable(false); // Make non-editable
        this.panel.add(this.genaratedCustomerIdField);
        // Enter on ID goes to Name
        this.genaratedCustomerIdField.addActionListener(_ -> this.customerNameField.requestFocusInWindow());

        this.customerNameLabel = new JLabel("Customer Name:");
        this.customerNameLabel.setBounds(50, 50, 150, 30);
        this.panel.add(this.customerNameLabel);

        this.customerNameField = new JTextField();
        this.customerNameField.setBounds(210, 50, 200, 30);
        this.panel.add(this.customerNameField);
        // Enter on Name goes to Age
        this.customerNameField.addActionListener(_ -> this.customerAgeField.requestFocusInWindow());

        this.customerGenderLabel = new JLabel("Customer Gender:");
        this.customerGenderLabel.setBounds(50, 90, 150, 30);
        this.panel.add(this.customerGenderLabel);

        this.genderComboBox = new JComboBox<String>(new String[]{"Male", "Female","Other"});
        this.genderComboBox.addActionListener(this);
        this.genderComboBox.setBounds(210, 90, 200, 30);
        this.panel.add(this.genderComboBox);

        this.genderComboBox = new JComboBox<String>(new String[]{"Male", "Female","Other"});
        this.genderComboBox.setBounds(210, 90, 200, 30);
        this.panel.add(this.genderComboBox);

        this.customerAgeLabel = new JLabel("Customer Age:");
        this.customerAgeLabel.setBounds(50, 130, 150, 30);
        this.panel.add(this.customerAgeLabel);

        this.customerAgeField = new JTextField();
        this.customerAgeField.setBounds(210, 130, 200, 30);
        this.panel.add(this.customerAgeField);
        // Enter on Age goes to Email
        this.customerAgeField.addActionListener(_ -> this.customerEmailField.requestFocusInWindow());

        this.customerEmailLabel = new JLabel("Customer Email:");
        this.customerEmailLabel.setBounds(50, 170, 150, 30);
        this.panel.add(this.customerEmailLabel);

        this.customerEmailField = new JTextField();
        this.customerEmailField.setBounds(210, 170, 200, 30);
        this.panel.add(this.customerEmailField);
        // Enter on Email goes to Phone
        this.customerEmailField.addActionListener(_ -> this.customerPhoneField.requestFocusInWindow());

        this.customerPhoneLabel = new JLabel("Customer Phone:");
        this.customerPhoneLabel.setBounds(50, 210, 150, 30);
        this.panel.add(this.customerPhoneLabel);

        this.customerPhoneField = new JTextField();
        this.customerPhoneField.setBounds(210, 210, 200, 30);
        this.panel.add(this.customerPhoneField);
        // Enter on Phone goes to Address
        this.customerPhoneField.addActionListener(_ -> this.customerAddressField.requestFocusInWindow());

        this.customerAddressLabel = new JLabel("Customer Address:");
        this.customerAddressLabel.setBounds(50, 250, 150, 30);
        this.panel.add(this.customerAddressLabel);

        this.customerAddressField = new JTextField();
        this.customerAddressField.setBounds(210, 250, 200, 30);
        this.panel.add(this.customerAddressField);
        // Enter on Address goes to SecurityAns
        this.customerAddressField.addActionListener(_ -> this.securityAnsField.requestFocusInWindow());

        this.securityAnsLabel = new JLabel("Security Answer:");
        this.securityAnsLabel.setBounds(50, 290, 150, 30);
        this.panel.add(this.securityAnsLabel);

        this.securityAnsField = new JTextField();
        this.securityAnsField.setBounds(210, 290, 200, 30);
        this.panel.add(this.securityAnsField);
        // Enter on SecurityAns goes to Password
        this.securityAnsField.addActionListener(_ -> this.passwordField.requestFocusInWindow());

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(50, 330, 150, 30);
        this.panel.add(this.passwordLabel);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(210, 330, 200, 30);
        this.panel.add(this.passwordField);
        // Enter on Password goes to Add Customer button (skipping membershipTypeComboBox)
        this.passwordField.addActionListener(_ -> this.addCustomerButton.doClick());

        this.membershipTypeLabel = new JLabel("Membership Type:");
        this.membershipTypeLabel.setBounds(50, 370, 150, 30);
        this.panel.add(this.membershipTypeLabel);

        this.membershipTypeComboBox = new JComboBox<String>(new String[]{"Regular", "Gold", "Silver", "Bronze","Platinum"});
        this.membershipTypeComboBox.setBounds(210, 370, 200, 30);
        this.panel.add(this.membershipTypeComboBox);

        this.addCustomerButton = new JButton("Add Customer");
        this.addCustomerButton.setBounds(100, 410, 200, 30);
        this.addCustomerButton.addActionListener(this);
        this.panel.add(this.addCustomerButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(320, 410, 200, 30);
        this.cancelButton.addActionListener(this);
        this.panel.add(this.cancelButton);
    
        this.add(this.panel);
        this.setVisible(true);
        generateCustomerID(); // Generate ID on form open
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.addCustomerButton){
            String customerId = this.genaratedCustomerIdField.getText();
            String name = this.customerNameField.getText();
            String gender = (String) this.genderComboBox.getSelectedItem();
            String age = this.customerAgeField.getText();
            String email = this.customerEmailField.getText();
            String phone = this.customerPhoneField.getText();
            String address = this.customerAddressField.getText();
            String securityAns = this.securityAnsField.getText();
            String password = this.passwordField.getText();
            String membershipType = (String) this.membershipTypeComboBox.getSelectedItem();

            // Save to Customers.txt (ID,Name,Gender,Age,Email,Phone,Address,SecurityAns,Password,MembershipType)
            try {
                java.io.FileWriter fw = new java.io.FileWriter("controllers/data/Customers.txt", true);
                fw.write(customerId + "," + name + "," + gender + "," + age + "," + email + "," + phone + "," + address + "," + securityAns + "," + password + "," + membershipType + "\n");
                fw.close();
                // Also add to Users.txt with role 3, SecurityAns, and password
                String usersPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Users.txt";
                java.io.FileWriter userFw = new java.io.FileWriter(usersPath, true);
                userFw.write(customerId + ",3," + securityAns + "," + password + "\n");
                userFw.close();
                JOptionPane.showMessageDialog(this, "Customer added successfully!");
                dispose();
                new CustomerListGUI().setVisible(true); // Redirect to checkout
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving customer: " + ex.getMessage());
            }
        }
        else if(e.getSource() == this.cancelButton){
            new EmployeeGUI(null).setVisible(true);
            dispose();
        }
    }

    // Generate unique customer ID like SignUpGUI
    private void generateCustomerID() {
        String prefix = "CUS";
        int digits = 6;
        String unique, customerID;
        do {
            unique = generateRandomDigits(digits);
            customerID = prefix + unique;
        } while (!isCustomerIDUnique(customerID));
        this.genaratedCustomerIdField.setText(customerID);
    }

    private String generateRandomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }

    private boolean isCustomerIDUnique(String customerID) {
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("controllers/data/Customers.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(customerID)) {
                    br.close();
                    return false;
                }
            }
            br.close();
        } catch (Exception e) {
            // If file doesn't exist, treat as unique
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        new AddCustomerGUI().setVisible(true);
    }
}
