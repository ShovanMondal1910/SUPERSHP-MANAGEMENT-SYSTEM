package views.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddAdminGUI extends JFrame implements ActionListener {
    private JLabel GenaratedUserIDLabel,NameLabel,GenderLabel,AgeLabel,EmailLabel,PhoneLabel,AddressLabel,SecurityQuestionLabel,SecurityAnsLabel,PasswordLabel,AdminTypeLabel,PhotoPathLabel;
    private JTextField GenaratedUserIDField,NameField,AgeField,EmailField,PhoneField,AddressField,SecurityAnsField,PasswordField,PhotoPathField;
    private JButton AddButton,BackButton;
    private JComboBox<String> AdminTypeComboBox, genderComboBox;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JLabel photoDisplayArea;

    public AddAdminGUI()
    {
        this.setTitle("Add Admin");
        this.setSize(800, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Main panel with null layout for absolute positioning
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(new Color(245, 247, 250));

        // Title
        JLabel titleLabel = new JLabel("Add New Administrator");
        titleLabel.setBounds(50, 30, 800, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(titleLabel);

        // Personal Information Section
        JLabel personalInfoLabel = new JLabel("Personal Information");
        personalInfoLabel.setBounds(50, 90, 300, 30);
        personalInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        personalInfoLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(personalInfoLabel);

        // Generated User ID
        this.GenaratedUserIDLabel = new JLabel("Generated Admin ID:");
        this.GenaratedUserIDLabel.setBounds(50, 130, 200, 30);
        this.GenaratedUserIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.GenaratedUserIDLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.GenaratedUserIDLabel);
        
        this.GenaratedUserIDField = new JTextField();
        this.GenaratedUserIDField.setBounds(250, 130, 300, 35);
        this.GenaratedUserIDField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.GenaratedUserIDField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.GenaratedUserIDField.setOpaque(false);
        this.GenaratedUserIDField.setBackground(new Color(0, 0, 0, 0));
        this.GenaratedUserIDField.setForeground(new Color(52, 73, 94));
        this.GenaratedUserIDField.setEditable(false); // Make non-editable
        this.GenaratedUserIDField.setText(generateNextAdminId()); // Generate and set admin ID
        this.GenaratedUserIDField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    NameField.requestFocus();
                }
            }
        });
        this.panel.add(this.GenaratedUserIDField);
        
        // Animated underline for Generated User ID
        JPanel underline1 = new JPanel();
        underline1.setBounds(250, 165, 300, 2);
        underline1.setBackground(new Color(52, 152, 219));
        this.panel.add(underline1);

        // Name
        this.NameLabel = new JLabel("Full Name:");
        this.NameLabel.setBounds(50, 175, 200, 30);
        this.NameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.NameLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.NameLabel);
        
        this.NameField = new JTextField();
        this.NameField.setBounds(250, 175, 300, 35);
        this.NameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.NameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.NameField.setOpaque(false);
        this.NameField.setBackground(new Color(0, 0, 0, 0));
        this.NameField.setForeground(new Color(52, 73, 94));
        this.NameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AgeField.requestFocus();
                }
            }
        });
        this.panel.add(this.NameField);
        
        // Animated underline for Name
        JPanel underline2 = new JPanel();
        underline2.setBounds(250, 210, 300, 2);
        underline2.setBackground(new Color(52, 152, 219));
        this.panel.add(underline2);

        // Gender and Age side by side
        this.GenderLabel = new JLabel("Gender:");
        this.GenderLabel.setBounds(50, 220, 200, 30);
        this.GenderLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.GenderLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.GenderLabel);
        
        JComboBox<String> genderComboBox = new JComboBox<String>(new String[]{"Male", "Female", "Other"});
        genderComboBox.setBounds(250, 220, 140, 35);
        genderComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.panel.add(genderComboBox);
        
        // Store reference to gender combo box
        this.genderComboBox = genderComboBox;

        this.AgeLabel = new JLabel("Age:");
        this.AgeLabel.setBounds(410, 220, 100, 30);
        this.AgeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.AgeLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.AgeLabel);
        
        this.AgeField = new JTextField();
        this.AgeField.setBounds(470, 220, 80, 35);
        this.AgeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.AgeField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.AgeField.setOpaque(false);
        this.AgeField.setBackground(new Color(0, 0, 0, 0));
        this.AgeField.setForeground(new Color(52, 73, 94));
        this.AgeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    EmailField.requestFocus();
                }
            }
        });
        this.panel.add(this.AgeField);
        
        // Animated underline for Age
        JPanel underline3 = new JPanel();
        underline3.setBounds(470, 255, 80, 2);
        underline3.setBackground(new Color(52, 152, 219));
        this.panel.add(underline3);

        // Contact Information Section
        JLabel contactInfoLabel = new JLabel("Contact Information");
        contactInfoLabel.setBounds(50, 275, 300, 30);
        contactInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contactInfoLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(contactInfoLabel);

        // Email
        this.EmailLabel = new JLabel("Email:");
        this.EmailLabel.setBounds(50, 315, 200, 30);
        this.EmailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.EmailLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.EmailLabel);

        this.EmailField = new JTextField();
        this.EmailField.setBounds(250, 315, 300, 35);
        this.EmailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.EmailField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.EmailField.setOpaque(false);
        this.EmailField.setBackground(new Color(0, 0, 0, 0));
        this.EmailField.setForeground(new Color(52, 73, 94));
        this.EmailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    PhoneField.requestFocus();
                }
            }
        });
        this.panel.add(this.EmailField);
        
        // Animated underline for Email
        JPanel underline4 = new JPanel();
        underline4.setBounds(250, 350, 300, 2);
        underline4.setBackground(new Color(52, 152, 219));
        this.panel.add(underline4);

        // Phone
        this.PhoneLabel = new JLabel("Phone:");
        this.PhoneLabel.setBounds(50, 360, 200, 30);
        this.PhoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.PhoneLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.PhoneLabel);
        
        this.PhoneField = new JTextField();    
        this.PhoneField.setBounds(250, 360, 300, 35);
        this.PhoneField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.PhoneField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.PhoneField.setOpaque(false);
        this.PhoneField.setBackground(new Color(0, 0, 0, 0));
        this.PhoneField.setForeground(new Color(52, 73, 94));
        this.PhoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AddressField.requestFocus();
                }
            }
        });
        this.panel.add(this.PhoneField);
        
        // Animated underline for Phone
        JPanel underline5 = new JPanel();
        underline5.setBounds(250, 395, 300, 2);
        underline5.setBackground(new Color(52, 152, 219));
        this.panel.add(underline5);

        // Address
        this.AddressLabel = new JLabel("Address:");
        this.AddressLabel.setBounds(50, 405, 200, 30);
        this.AddressLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.AddressLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.AddressLabel);
        
        this.AddressField = new JTextField();
        this.AddressField.setBounds(250, 405, 300, 35);
        this.AddressField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.AddressField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.AddressField.setOpaque(false);
        this.AddressField.setBackground(new Color(0, 0, 0, 0));
        this.AddressField.setForeground(new Color(52, 73, 94));
        this.AddressField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SecurityAnsField.requestFocus();
                }
            }
        });
        this.panel.add(this.AddressField);
        
        // Animated underline for Address
        JPanel underline6 = new JPanel();
        underline6.setBounds(250, 440, 300, 2);
        underline6.setBackground(new Color(52, 152, 219));
        this.panel.add(underline6);

        // Security Information Section
        JLabel securityInfoLabel = new JLabel("Security Information");
        securityInfoLabel.setBounds(50, 460, 300, 30);
        securityInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        securityInfoLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(securityInfoLabel);

        // Security Question
        this.SecurityQuestionLabel = new JLabel("Security Question: What is your  Nickname?");
        this.SecurityQuestionLabel.setBounds(50, 500, 400, 30);
        this.SecurityQuestionLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.SecurityQuestionLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.SecurityQuestionLabel);

        // Security Answer
        this.SecurityAnsLabel = new JLabel("Security Answer:");
        this.SecurityAnsLabel.setBounds(50, 545, 200, 30);
        this.SecurityAnsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.SecurityAnsLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.SecurityAnsLabel);

        this.SecurityAnsField = new JTextField();
        this.SecurityAnsField.setBounds(250, 545, 300, 35);
        this.SecurityAnsField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.SecurityAnsField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.SecurityAnsField.setOpaque(false);
        this.SecurityAnsField.setBackground(new Color(0, 0, 0, 0));
        this.SecurityAnsField.setForeground(new Color(52, 73, 94));
        this.SecurityAnsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    PasswordField.requestFocus();
                }
            }
        });
        this.panel.add(this.SecurityAnsField);
        
        // Animated underline for Security Answer
        JPanel underline7 = new JPanel();
        underline7.setBounds(250, 580, 300, 2);
        underline7.setBackground(new Color(52, 152, 219));
        this.panel.add(underline7);

        // Password
        this.PasswordLabel = new JLabel("Password:");
        this.PasswordLabel.setBounds(50, 590, 200, 30);
        this.PasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.PasswordLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.PasswordLabel);
        
        this.PasswordField = new JTextField();
        this.PasswordField.setBounds(250, 590, 300, 35);
        this.PasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.PasswordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.PasswordField.setOpaque(false);
        this.PasswordField.setBackground(new Color(0, 0, 0, 0));
        this.PasswordField.setForeground(new Color(52, 73, 94));
        this.PasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AdminTypeComboBox.requestFocus();
                }
            }
        });
        this.panel.add(this.PasswordField);
        
        // Animated underline for Password
        JPanel underline8 = new JPanel();
        underline8.setBounds(250, 625, 300, 2);
        underline8.setBackground(new Color(52, 152, 219));
        this.panel.add(underline8);

        // Admin Configuration Section
        JLabel adminConfigLabel = new JLabel("Admin Configuration");
        adminConfigLabel.setBounds(50, 645, 300, 30);
        adminConfigLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        adminConfigLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(adminConfigLabel);

        // Admin Type
        this.AdminTypeLabel = new JLabel("Admin Type:");
        this.AdminTypeLabel.setBounds(50, 685, 200, 30);
        this.AdminTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.AdminTypeLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.AdminTypeLabel);

        this.AdminTypeComboBox = new JComboBox<String>(new String[]{"Developer","Manager","Sales","Marketing","HR","IT","Finance","Customer Service","Accounting","Inventory","Security","Other"});
        this.AdminTypeComboBox.setBounds(250, 685, 300, 35);
        this.AdminTypeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.AdminTypeComboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    PhotoPathField.requestFocus();
                }
            }
        });
        this.panel.add(this.AdminTypeComboBox);

        // Photo Path and Browse Button (side by side)
        this.PhotoPathLabel = new JLabel("Photo Path:");
        this.PhotoPathLabel.setBounds(50, 730, 200, 30);
        this.PhotoPathLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.PhotoPathLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.PhotoPathLabel);

        this.PhotoPathField = new JTextField();
        this.PhotoPathField.setBounds(250, 730, 180, 35);
        this.PhotoPathField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.PhotoPathField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.PhotoPathField.setOpaque(false);
        this.PhotoPathField.setBackground(new Color(0, 0, 0, 0));
        this.PhotoPathField.setForeground(new Color(52, 73, 94));
        this.PhotoPathField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AddButton.requestFocus();
                }
            }
        });
        this.panel.add(this.PhotoPathField);
        
        // Animated underline for Photo Path
        JPanel underline9 = new JPanel();
        underline9.setBounds(250, 765, 180, 2);
        underline9.setBackground(new Color(52, 152, 219));
        this.panel.add(underline9);

        // Browse Button for Photo
        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(450, 730, 80, 35);
        browseButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        browseButton.setForeground(Color.WHITE);
        browseButton.setBackground(new Color(52, 152, 219));
        browseButton.setBorderPainted(false);
        browseButton.setFocusPainted(false);
        browseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add action listener for browse button
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif", "bmp"));
                
                int result = fileChooser.showOpenDialog(AddAdminGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    PhotoPathField.setText(selectedFilePath);
                    
                    // Load and display the image
                    try {
                        ImageIcon originalIcon = new ImageIcon(selectedFilePath);
                        Image originalImage = originalIcon.getImage();
                        
                        // Resize image to fit the display area (150x150)
                        Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        
                        photoDisplayArea.setIcon(resizedIcon);
                        photoDisplayArea.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AddAdminGUI.this, 
                            "Error loading image: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        this.panel.add(browseButton);

        // Photo Display Area (top right)
        JLabel photoDisplayLabel = new JLabel("Photo Preview:");
        photoDisplayLabel.setBounds(570, 90, 200, 30);
        photoDisplayLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        photoDisplayLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(photoDisplayLabel);

        this.photoDisplayArea = new JLabel();
        this.photoDisplayArea.setBounds(570, 120, 150, 150);
        this.photoDisplayArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        this.photoDisplayArea.setBackground(new Color(245, 245, 245));
        this.photoDisplayArea.setOpaque(true);
        this.photoDisplayArea.setHorizontalAlignment(JLabel.CENTER);
        this.photoDisplayArea.setVerticalAlignment(JLabel.CENTER);
        this.photoDisplayArea.setText("No Photo Selected");
        this.photoDisplayArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        this.photoDisplayArea.setForeground(new Color(150, 150, 150));
        this.panel.add(this.photoDisplayArea);

        // Buttons
        this.AddButton = new JButton("Add Administrator");
        this.AddButton.setBounds(150, 800, 200, 40);
        this.AddButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.AddButton.setForeground(Color.WHITE);
        this.AddButton.setBackground(new Color(46, 204, 113));
        this.AddButton.setBorderPainted(false);
        this.AddButton.setFocusPainted(false);
        this.AddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.AddButton.addActionListener(this);
        this.panel.add(this.AddButton);

        this.BackButton = new JButton("Back");
        this.BackButton.setBounds(380, 800, 120, 40);
        this.BackButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.BackButton.setForeground(Color.WHITE);
        this.BackButton.setBackground(new Color(231, 76, 60));
        this.BackButton.setBorderPainted(false);
        this.BackButton.setFocusPainted(false);
        this.BackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.BackButton.addActionListener(this);
        this.panel.add(this.BackButton);

        // Create scroll pane
        this.scrollPane = new JScrollPane(this.panel);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(this.scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.AddButton) {
            saveAdminData();
        }
        if (e.getSource() == this.BackButton) {
            goToAdminGUI();
        }
    }

    private void saveAdminData() {
        try {
            // Get all the data from form fields
            String generatedID = this.GenaratedUserIDField.getText();
            String name = this.NameField.getText();
            String age = this.AgeField.getText();
            String email = this.EmailField.getText();
            String phone = this.PhoneField.getText();
            String address = this.AddressField.getText();
            String securityAnswer = this.SecurityAnsField.getText();
            String password = this.PasswordField.getText();
            String adminType = (String) this.AdminTypeComboBox.getSelectedItem();
            String photoPath = this.PhotoPathField.getText();
            
            // Validate required fields
            if (generatedID.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get gender from combo box
            String gender = (String) this.genderComboBox.getSelectedItem();
            
            // Create admin data line for Admins.txt
            String adminData = generatedID + "," + name + ","+ gender +"," + age + "," + email + "," + phone + "," + 
                             address + "," + securityAnswer + "," + password + "," + adminType + "," + photoPath;
            
            // Create user data line for Users.txt with format: ID,Role,SecurityAnswer,Password,PhotoPath
            // Role 1 = Admin
            String userData = generatedID + ",1," + securityAnswer + "," + password + "," + photoPath;
            
            // Save to Admins.txt
            FileWriter adminWriter = new FileWriter("controllers/data/Admins.txt", true);
            adminWriter.write(adminData + "\n");
            adminWriter.close();
            
            // Save to Users.txt
            FileWriter userWriter = new FileWriter("controllers/data/Users.txt", true);
            userWriter.write(userData + "\n");
            userWriter.close();
            
            JOptionPane.showMessageDialog(this, "Admin added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        this.GenaratedUserIDField.setText(generateNextAdminId()); // Regenerate admin ID
        this.NameField.setText("");
        this.AgeField.setText("");
        this.EmailField.setText("");
        this.PhoneField.setText("");
        this.AddressField.setText("");
        this.SecurityAnsField.setText("");
        this.PasswordField.setText("");
        this.PhotoPathField.setText("");
        this.AdminTypeComboBox.setSelectedIndex(0);
        this.photoDisplayArea.setIcon(null);
        this.photoDisplayArea.setText("No Photo Selected");
    }

    private void goToAdminGUI() {
        this.dispose();
        new AdminGUI(null);
    }

    private String generateNextAdminId() {
        String prefix = "ADM";
        int maxId = 0;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("controllers/data/Admins.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
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
            reader.close();
        } catch (IOException e) {
            // If file doesn't exist or is empty, start from 1
        }
        return prefix + String.format("%04d", maxId + 1);
    }

    public static void main(String[] args) {
        AddAdminGUI addAdminGUI = new AddAdminGUI();
        addAdminGUI.setVisible(true);
    }
}
