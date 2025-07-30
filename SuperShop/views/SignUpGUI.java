package views;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class SignUpGUI extends JFrame implements ActionListener{
   
    private JLabel genaratedUserIDLabel,nameLabel,genderLabel,ageLabel,emailLabel,phoneLabel,addressLabel,securityQuestionLabel,securityAnswerLabel,passwordLabel,membershipTypeLabel,photoPathLabel;
    private JTextField genaratedUserIDField,nameField,emailField,phoneField,addressField,securityAnswerField;
    private JComboBox<String> genderComboBox,membershipTypeComboBox,securityQuestionComboBox;
    private JTextField ageField;
    private JTextField photoPathField;
    private JPasswordField passwordField;
    private JButton signupButton,cancelButton,browseButton;
    private JPanel panel; 
    public SignUpGUI()
    {
        super("SignUp");
        this.setSize(800, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.genaratedUserIDLabel = new JLabel("Genarated User ID: ");
        this.genaratedUserIDLabel.setBounds(100, 50, 200, 30);
        this.panel.add(this.genaratedUserIDLabel);

        this.genaratedUserIDField = new JTextField();
        this.genaratedUserIDField.setBounds(250, 50, 300, 30);
        this.genaratedUserIDField.setEditable(false);
        this.genaratedUserIDField.setText(generateNextCustomerId());
        this.panel.add(this.genaratedUserIDField);
        // Move focus to nameField on Enter
        this.genaratedUserIDField.addActionListener(_ -> this.nameField.requestFocusInWindow());

        this.nameLabel = new JLabel("Name: ");
        this.nameLabel.setBounds(100, 100, 200, 30);
        this.panel.add(this.nameLabel);

        this.nameField = new JTextField();
        this.nameField.setBounds(250, 100, 300, 30);
        this.panel.add(this.nameField);
        // Move focus to ageField on Enter
        this.nameField.addActionListener(_ -> this.ageField.requestFocusInWindow());

        this.genderLabel = new JLabel("Gender: ");
        this.genderLabel.setBounds(100, 150, 200, 30);
        this.panel.add(this.genderLabel);

        this.genderComboBox = new JComboBox<String>(new String[]{"Male", "Female"});
        this.genderComboBox.setBounds(250, 150, 300, 30);
        this.panel.add(this.genderComboBox);
        // Do not add Enter key focus for JComboBox

        this.ageLabel = new JLabel("Age: ");
        this.ageLabel.setBounds(100, 200, 200, 30);
        this.panel.add(this.ageLabel);
        
        this.ageField = new JTextField();
        this.ageField.setBounds(250, 200, 300, 30);
        this.panel.add(this.ageField);
        // Move focus to emailField on Enter
        this.ageField.addActionListener(_ -> this.emailField.requestFocusInWindow());

        this.emailLabel = new JLabel("Email: ");
        this.emailLabel.setBounds(100, 250, 200, 30);
        this.panel.add(this.emailLabel);

        this.emailField = new JTextField();
        this.emailField.setBounds(250, 250, 300, 30);
        this.panel.add(this.emailField);
        // Move focus to phoneField on Enter
        this.emailField.addActionListener(_ -> this.phoneField.requestFocusInWindow());

        this.phoneLabel = new JLabel("Phone: ");
        this.phoneLabel.setBounds(100, 300, 200, 30);
        this.panel.add(this.phoneLabel);

        this.phoneField = new JTextField();
        this.phoneField.setBounds(250, 300, 300, 30);
        this.panel.add(this.phoneField);
        // Move focus to addressField on Enter
        this.phoneField.addActionListener(_ -> this.addressField.requestFocusInWindow());

        this.addressLabel = new JLabel("Address: ");
        this.addressLabel.setBounds(100, 350, 200, 30);
        this.panel.add(this.addressLabel);

        this.addressField = new JTextField();
        this.addressField.setBounds(250, 350, 300, 30);
        this.panel.add(this.addressField);
        // Move focus to securityAnswerField on Enter
        this.addressField.addActionListener(_ -> this.securityAnswerField.requestFocusInWindow());

        this.securityQuestionLabel = new JLabel("Security Question: ");
        this.securityQuestionLabel.setBounds(100, 400, 200, 30);
        this.panel.add(this.securityQuestionLabel);

        this.securityQuestionComboBox = new JComboBox<String>(new String[]{"What is your Nickname?", "What is your favorite color?"});
        this.securityQuestionComboBox.setBounds(250, 400, 300, 30);
        this.panel.add(this.securityQuestionComboBox);
        // Do not add Enter key focus for JComboBox

        this.securityAnswerLabel = new JLabel("Security Answer: ");
        this.securityAnswerLabel.setBounds(100, 450, 200, 30);
        this.panel.add(this.securityAnswerLabel);

        this.securityAnswerField = new JTextField();
        this.securityAnswerField.setBounds(250, 450, 300, 30);
        this.panel.add(this.securityAnswerField);
        // Move focus to passwordField on Enter
        this.securityAnswerField.addActionListener(_ -> this.passwordField.requestFocusInWindow());

        this.passwordLabel = new JLabel("Password: ");
        this.passwordLabel.setBounds(100, 500, 200, 30);
        this.panel.add(this.passwordLabel);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(250, 500, 300, 30);
        this.panel.add(this.passwordField);
        // Move focus to photoPathField on Enter
        this.passwordField.addActionListener(_ -> this.photoPathField.requestFocusInWindow());

        this.membershipTypeLabel = new JLabel("Membership Type: ");
        this.membershipTypeLabel.setBounds(100, 550, 200, 30);
        this.panel.add(this.membershipTypeLabel);

        this.membershipTypeComboBox = new JComboBox<String>(new String[]{"Basic", "Silver", "Gold", "Platinum","Diamond","VIP","Premium","Elite"});
        this.membershipTypeComboBox.setBounds(250, 550, 300, 30);
        this.panel.add(this.membershipTypeComboBox);
        // Do not add Enter key focus for JComboBox

        this.photoPathLabel = new JLabel("Photo Path: ");
        this.photoPathLabel.setBounds(100, 600, 200, 30);
        this.panel.add(this.photoPathLabel);

        this.photoPathField = new JTextField();
        this.photoPathField.setBounds(250, 600, 300, 30);
        this.panel.add(this.photoPathField);
        // On Enter, trigger signupButton doClick
        this.photoPathField.addActionListener(_ -> this.signupButton.doClick());

        this.browseButton = new JButton("Browse");
        this.browseButton.setBounds(570, 600, 100, 30);
        this.browseButton.addActionListener(this);
        this.panel.add(this.browseButton);

        this.photoPathField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == photoPathField){
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(photoPathField);
                }
            }
        }); 

        this.signupButton = new JButton("Sign Up");
        this.signupButton.setBounds(150, 720, 200, 30);
        this.signupButton.addActionListener(this);
        this.panel.add(this.signupButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(400, 720, 200, 30);
        this.cancelButton.addActionListener(this);
        this.panel.add(this.cancelButton);
        
        
        this.add(this.panel);
        this.setVisible(true);
    }

    private static String generateNextCustomerId() {
        String fileName = "controllers/data/Customers.txt";
        String prefix = "CUS";
        int maxId = 0;
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].startsWith(prefix)) {
                    String numStr = parts[0].substring(prefix.length());
                    try {
                        int num = Integer.parseInt(numStr);
                        if (num > maxId) maxId = num;
                    } catch (NumberFormatException ignored) {}
                }
            }
            br.close();
        } catch (Exception e) {
            // If file does not exist or is empty, start from 1
        }
        return prefix + String.format("%06d", maxId + 1);
    }

    private void saveCustomer(models.Customer customer) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("controllers/data/Customers.txt", true);
            fw.write(customer.toStringCustomer());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.signupButton){
            String userID = this.genaratedUserIDField.getText();
            String name = this.nameField.getText();
            String gender = (String)this.genderComboBox.getSelectedItem();
            String ageText = this.ageField.getText();
            String email = this.emailField.getText();
            String phone = this.phoneField.getText();
            String address = this.addressField.getText();
            String securityQuestion = (String)this.securityQuestionComboBox.getSelectedItem();
            String securityAnswer = this.securityAnswerField.getText();
            String password = new String(this.passwordField.getPassword());
            String membershipType = (String)this.membershipTypeComboBox.getSelectedItem();
            String photoPath = this.photoPathField.getText();

            if(name.isEmpty() || ageText.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || securityAnswer.isEmpty() || password.isEmpty() || membershipType.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid age.");
                return;
            }
            models.Customer customer = new models.Customer(userID, name, gender, age, email, phone, address, securityQuestion, securityAnswer, password, membershipType, photoPath);
            saveCustomer(customer);
            // Save to Users.txt with required fields
            models.User user = new models.User(userID, 3, securityAnswer, password, photoPath);
            controllers.UserController userController = new controllers.UserController();
            userController.insertUser(user);
            int result = JOptionPane.showConfirmDialog(this, "Customer signed up successfully!\nGo to login page?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                new LoginGUI().setVisible(true);
            } // else stay on signup
        } else if (e.getSource() == this.browseButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.photoPathField.setText(selectedFile.getAbsolutePath());
            }
        }
        else if(e.getSource() == this.cancelButton){
            new LoginGUI().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SignUpGUI signUpGUI = new SignUpGUI();
        signUpGUI.setVisible(true);
    }
}
