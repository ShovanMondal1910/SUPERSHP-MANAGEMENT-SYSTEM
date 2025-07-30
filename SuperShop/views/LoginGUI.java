package views;
import javax.swing.*;
import java.awt.event.*;
import controllers.FileIO;
import java.io.File;
import models.User;
import views.Admin.AdminGUI;
import views.Employee.EmployeeGUI;
import views.Customer.CustomerGUI;


public class LoginGUI extends JFrame implements ActionListener{
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton,signupButton,forgetPasswordButton;
    private JButton cancelButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel panel;
 
    public LoginGUI() {
        super("Login");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.usernameLabel = new JLabel("Username:");
        this.usernameLabel.setBounds(50, 50, 100, 30);
        this.panel.add(this.usernameLabel);

        this.usernameField = new JTextField();
        this.usernameField.setBounds(150, 50, 200, 30);
        this.panel.add(this.usernameField);

        // Pressing Enter in usernameField focuses passwordField
        this.usernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(50, 100, 100, 30);
        this.panel.add(this.passwordLabel);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(150, 100, 200, 30);
        this.panel.add(this.passwordField);

        // Pressing Enter in passwordField triggers login
        this.passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        this.loginButton = new JButton("Login");
        this.loginButton.setBounds(100, 200, 100, 30);
        this.loginButton.addActionListener(this);
        this.panel.add(this.loginButton);

        this.signupButton = new JButton("Signup");
        this.signupButton.setBounds(250, 200, 100, 30);
        this.signupButton.addActionListener(this);
        this.panel.add(this.signupButton);

        this.forgetPasswordButton = new JButton("Forget Password");
        this.forgetPasswordButton.setBounds(30, 250, 200, 30);
        this.forgetPasswordButton.addActionListener(this);
        this.panel.add(this.forgetPasswordButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(250, 250, 100, 30);
        this.cancelButton.addActionListener(this);
        this.panel.add(this.cancelButton);

        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.loginButton){
            String username = this.usernameField.getText();
            String password = new String(this.passwordField.getPassword());
            FileIO fio = new FileIO();
            String usersPath = System.getProperty("user.dir") + File.separator + "controllers" + File.separator + "data" + File.separator + "Users.txt";
            String[] users = fio.readFile(usersPath);
            boolean loginSuccessful = false;
            
            for(String userData : users){
                String[] parts = userData.split(",");
                if(parts[0].equals(username) && parts[3].equals(password)){
                    loginSuccessful = true;
                    User user = new User().formUser(userData);
                    
                    if(user.getRole() == 1){
                        new AdminGUI(user).setVisible(true);
                        dispose();
                    }
                    else if(user.getRole() == 2){
                        new EmployeeGUI(user).setVisible(true);
                        dispose();
                    }
                    else{
                        new CustomerGUI().setVisible(true);
                        dispose();
                    }
                    break;
                }
            }
            
            if(!loginSuccessful){
                JOptionPane.showMessageDialog(this, "Login failed. Invalid username or password.");
            }
        }
        else if(e.getSource() == this.signupButton){
            new SignUpGUI().setVisible(true);
            dispose();
        }
        else if(e.getSource() == this.forgetPasswordButton){
            new ForgetPassword().setVisible(true);
            dispose();
        }
        else if(e.getSource() == this.cancelButton){
            dispose();
        }
    }

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
    }
}
