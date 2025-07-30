package views.Employee;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class RemoveCustomerGUI extends JFrame implements ActionListener{
    private JButton backButton;
    private JTextField customerIdField;
    private JLabel customerIdLabel;
    private JButton removeButton;
    private JPanel panel;

    public RemoveCustomerGUI()
    {
        super("Remove Customer");
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setBounds(0,0,600,400);
        this.panel.setLayout(null); 

        this.customerIdLabel = new JLabel("Customer ID : ");
        this.customerIdLabel.setBounds(50,40,100,30);
        this.panel.add(this.customerIdLabel);
        this.customerIdField = new JTextField();
        this.customerIdField.setBounds(150,40,300,30);
        this.panel.add(this.customerIdField);

        this.removeButton = new JButton("Remove");  
        this.removeButton.setBounds(40,120,200,30);
        this.removeButton.addActionListener(this);
        this.panel.add(this.removeButton);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(260,120,200,30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            new EmployeeGUI(null).setVisible(true);
            dispose();
        }
        else if(e.getSource() == removeButton){
            String customerId = customerIdField.getText().trim();
            if (customerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Customer ID.");
                return;
            }
            boolean found = false;
            // Remove from Customers.txt
            try {
                java.io.File inputFile = new java.io.File("controllers/data/Customers.txt");
                java.io.File tempFile = new java.io.File("controllers/data/Customers_temp.txt");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(inputFile));
                java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(tempFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(customerId)) {
                        found = true;
                        continue; // skip this line
                    }
                    writer.write(line + System.lineSeparator());
                }
                writer.close();
                reader.close();
                if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                    JOptionPane.showMessageDialog(this, "Error updating Customers.txt file.");
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error removing customer: " + ex.getMessage());
                return;
            }
            // Remove from Users.txt
            try {
                String usersPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Users.txt";
                java.io.File inputFile = new java.io.File(usersPath);
                java.io.File tempFile = new java.io.File(System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Users_temp.txt");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(inputFile));
                java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(tempFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(customerId)) {
                        continue; // skip this line
                    }
                    writer.write(line + System.lineSeparator());
                }
                writer.close();
                reader.close();
                if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                    JOptionPane.showMessageDialog(this, "Error updating Users.txt file.");
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error removing user: " + ex.getMessage());
                return;
            }
            if (found) {
                JOptionPane.showMessageDialog(this, "Customer removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Customer ID not found.");
            }
            new CustomerListGUI().setVisible(true);
            dispose();
        }
    }
    public static void main(String[] args) {
        RemoveCustomerGUI removeCustomerGUI = new RemoveCustomerGUI();
        removeCustomerGUI.setVisible(true);
    }
}
