package views;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
    
public class ForgetPassword extends JFrame implements ActionListener{
    private JLabel UserIDLabel,securityQuestionLabel,securityAnswerLabel,newPasswordLabel,confirmPasswordLabel;
    private JTextField UserIDField,securityAnswerField;
    private JPasswordField newPasswordField,confirmPasswordField;
    private JButton submitButton,cancelButton;
    private JPanel panel;

    public ForgetPassword()
    {
        super("Forget Password");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
     
        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.UserIDLabel = new JLabel("User ID:");
        this.UserIDLabel.setBounds(100, 50, 150, 30);
        this.panel.add(this.UserIDLabel);

        this.UserIDField = new JTextField();
        this.UserIDField.setBounds(250, 50, 200, 30);
        this.panel.add(this.UserIDField);

        this.securityQuestionLabel = new JLabel("Security Question :                  Your Nickname?  ");
        this.securityQuestionLabel.setBounds(100, 100, 500, 30);
        this.panel.add(this.securityQuestionLabel);

        this.securityAnswerLabel = new JLabel("Security Answer: ");
        this.securityAnswerLabel.setBounds(100, 150, 500, 30);
        this.panel.add(this.securityAnswerLabel);

        this.securityAnswerField = new JTextField();
        this.securityAnswerField.setBounds(250, 150, 200, 30);
        this.panel.add(this.securityAnswerField);

        this.newPasswordLabel = new JLabel("New Password:");
        this.newPasswordLabel.setBounds(100, 200, 150, 30);
        this.panel.add(this.newPasswordLabel);
        
        this.newPasswordField = new JPasswordField();
        this.newPasswordField.setBounds(250, 200, 200, 30);
        this.panel.add(this.newPasswordField);

        this.confirmPasswordLabel = new JLabel("Confirm Password:");
        this.confirmPasswordLabel.setBounds(100, 250, 150, 30);
        this.panel.add(this.confirmPasswordLabel);

        this.confirmPasswordField = new JPasswordField();
        this.confirmPasswordField.setBounds(250, 250, 200, 30);
        this.panel.add(this.confirmPasswordField);

        this.submitButton = new JButton("Submit");
        this.submitButton.setBounds(100, 300, 150, 30);
        this.submitButton.addActionListener(this);
        this.panel.add(this.submitButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(300, 300, 150, 30);
        this.cancelButton.addActionListener(this);
        this.panel.add(this.cancelButton);

    
        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.submitButton){
            String userId = this.UserIDField.getText().trim();
            String securityAns = this.securityAnswerField.getText().trim();
            String newPassword = new String(this.newPasswordField.getPassword()).trim();
            String confirmPassword = new String(this.confirmPasswordField.getPassword()).trim();

            if (userId.isEmpty() || securityAns.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controllers.FileIO fio = new controllers.FileIO();
            String usersPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Users.txt";
            String[] users = fio.readFile(usersPath);
            
            // Find user and determine their role
            int userRole = -1;
            int userIndex = -1;
            for (int i = 0; i < users.length; i++) {
                if (users[i] == null) continue;
                String[] parts = users[i].split(",");
                if (parts.length >= 4) {
                    String fileUserId = parts[0].trim();
                    if (userId.equals(fileUserId)) {
                        userRole = Integer.parseInt(parts[1].trim());
                        userIndex = i;
                        break;
                    }
                }
            }
            
            if (userIndex == -1) {
                JOptionPane.showMessageDialog(this, "User ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean passwordUpdated = false;
            
            // Update password based on user role
            if (userRole == 1) { // Admin
                passwordUpdated = updateAdminPassword(userId, securityAns, newPassword, fio);
            } else if (userRole == 2) { // Employee
                passwordUpdated = updateEmployeePassword(userId, securityAns, newPassword, fio);
            } else if (userRole == 3) { // Customer
                passwordUpdated = updateCustomerPassword(userId, securityAns, newPassword, fio);
            }
            
            if (passwordUpdated) {
                // Update password in Users.txt as well
                String[] parts = users[userIndex].split(",");
                parts[3] = newPassword;
                users[userIndex] = String.join(",", parts);
                fio.writeFile(usersPath, users);
                
                JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginGUI().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "User ID or Security Answer is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == this.cancelButton){
            new LoginGUI().setVisible(true);
            dispose();
        }
    }
    
    private boolean updateAdminPassword(String userId, String securityAns, String newPassword, controllers.FileIO fio) {
        String adminsPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Admins.txt";
        String[] admins = fio.readFile(adminsPath);
        
        for (int i = 0; i < admins.length; i++) {
            if (admins[i] == null) continue;
            String[] parts = admins[i].split(",");
            if (parts.length >= 8) {
                String adminId = parts[0].trim();
                String securityAnswer = parts[7].trim();
                if (userId.equals(adminId) && securityAns.equals(securityAnswer)) {
                    parts[8] = newPassword; // Update password field
                    admins[i] = String.join(",", parts);
                    fio.writeFile(adminsPath, admins);
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean updateEmployeePassword(String userId, String securityAns, String newPassword, controllers.FileIO fio) {
        String employeesPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Employees.txt";
        String[] employees = fio.readFile(employeesPath);
        
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) continue;
            String[] parts = employees[i].split(",");
            if (parts.length >= 8) {
                String empId = parts[0].trim();
                String securityAnswer = parts[7].trim();
                if (userId.equals(empId) && securityAns.equals(securityAnswer)) {
                    parts[8] = newPassword; // Update password field
                    employees[i] = String.join(",", parts);
                    fio.writeFile(employeesPath, employees);
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean updateCustomerPassword(String userId, String securityAns, String newPassword, controllers.FileIO fio) {
        String usersPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Users.txt";
        String[] users = fio.readFile(usersPath);
        
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) continue;
            String[] parts = users[i].split(",");
            if (parts.length >= 4) {
                String fileUserId = parts[0].trim();
                String fileSecurityAns = parts[2].trim();
                if (userId.equals(fileUserId) && securityAns.equals(fileSecurityAns)) {
                    parts[3] = newPassword;
                    users[i] = String.join(",", parts);
                    fio.writeFile(usersPath, users);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        ForgetPassword forgetPassword = new ForgetPassword();
        forgetPassword.setVisible(true);
    }
}
