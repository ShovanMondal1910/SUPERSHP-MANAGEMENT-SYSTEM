package views.Admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import controllers.AdminController;
import models.Admin;

public class EditAdminGUI extends JFrame implements ActionListener {
    private JLabel AdminIDLabel, NameLabel, GenderLabel, AgeLabel, EmailLabel, PhoneLabel, AddressLabel, SecurityQuestionLabel, SecurityAnsLabel, PasswordLabel, AdminTypeLabel;
    private JTextField AdminIDField, NameField, AgeField, EmailField, PhoneField, AddressField, SecurityAnsField, PasswordField;
    private JButton SearchButton, UpdateButton, BackButton;
    private JComboBox<String> GenderComboBox, AdminTypeComboBox;
    private JPanel mainPanel, searchPanel, personalPanel, contactPanel, securityPanel, adminPanel, buttonPanel;
    private JScrollPane scrollPane;
    private AdminController adminController;
    private Admin currentAdmin;

    public EditAdminGUI() {
        this.setTitle("Edit Administrator");
        this.setSize(700, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.adminController = new AdminController();
    
        // Main panel with organized layout
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout(15, 15));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Title
        JLabel titleLabel = new JLabel("Edit Administrator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        this.mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        
        // Search Panel
        createSearchPanel();
        GridBagConstraints searchConstraints = new GridBagConstraints();
        searchConstraints.gridx = 0;
        searchConstraints.gridy = 0;
        searchConstraints.gridwidth = 2;
        searchConstraints.fill = GridBagConstraints.HORIZONTAL;
        searchConstraints.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(this.searchPanel, searchConstraints);

        // Personal Information Panel
        createPersonalPanel();
        GridBagConstraints personalConstraints = new GridBagConstraints();
        personalConstraints.gridx = 0;
        personalConstraints.gridy = 1;
        personalConstraints.fill = GridBagConstraints.HORIZONTAL;
        personalConstraints.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(this.personalPanel, personalConstraints);

        // Contact Information Panel
        createContactPanel();
        GridBagConstraints contactConstraints = new GridBagConstraints();
        contactConstraints.gridx = 0;
        contactConstraints.gridy = 2;
        contactConstraints.fill = GridBagConstraints.HORIZONTAL;
        contactConstraints.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(this.contactPanel, contactConstraints);

        // Security Information Panel
        createSecurityPanel();
        GridBagConstraints securityConstraints = new GridBagConstraints();
        securityConstraints.gridx = 0;
        securityConstraints.gridy = 3;
        securityConstraints.fill = GridBagConstraints.HORIZONTAL;
        securityConstraints.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(this.securityPanel, securityConstraints);

        // Admin Configuration Panel
        createAdminPanel();
        GridBagConstraints adminConstraints = new GridBagConstraints();
        adminConstraints.gridx = 0;
        adminConstraints.gridy = 4;
        adminConstraints.fill = GridBagConstraints.HORIZONTAL;
        adminConstraints.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(this.adminPanel, adminConstraints);

        // Buttons Panel
        createButtonPanel();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 5;
        buttonConstraints.gridwidth = 2;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new Insets(25, 0, 0, 0);
        contentPanel.add(this.buttonPanel, buttonConstraints);

        this.mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Create scroll pane
        this.scrollPane = new JScrollPane(this.mainPanel);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(this.scrollPane);
        
        // Setup keyboard shortcuts
        setupKeyboardShortcuts();
        
        this.setVisible(true);
    }

    private void createSearchPanel() {
        this.searchPanel = new JPanel();
        this.searchPanel.setLayout(new GridBagLayout());
        this.searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Search Administrator", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        this.AdminIDLabel = new JLabel("Admin ID:");
        this.AdminIDLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.AdminIDField = new JTextField(20);
        this.AdminIDField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.SearchButton = new JButton("Search");
        this.SearchButton.setFont(new Font("Arial", Font.BOLD, 14));
        this.SearchButton.setPreferredSize(new Dimension(100, 35));
        this.SearchButton.addActionListener(this);
        
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 15);
        this.searchPanel.add(this.AdminIDLabel, labelConstraints);
        
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 15);
        this.searchPanel.add(this.AdminIDField, fieldConstraints);
        
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 2;
        buttonConstraints.gridy = 0;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);
        this.searchPanel.add(this.SearchButton, buttonConstraints);
    }

    private void createPersonalPanel() {
        this.personalPanel = new JPanel();
        this.personalPanel.setLayout(new GridBagLayout());
        this.personalPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        this.NameLabel = new JLabel("Full Name:");
        this.NameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.NameField = new JTextField(25);
        this.NameField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.GenderLabel = new JLabel("Gender:");
        this.GenderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.GenderComboBox = new JComboBox<String>(new String[]{"Male", "Female", "Other"});
        this.GenderComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        this.AgeLabel = new JLabel("Age:");
        this.AgeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.AgeField = new JTextField(12);
        this.AgeField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Name
        GridBagConstraints nameLabelConstraints = new GridBagConstraints();
        nameLabelConstraints.gridx = 0;
        nameLabelConstraints.gridy = 0;
        nameLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.personalPanel.add(this.NameLabel, nameLabelConstraints);
        
        GridBagConstraints nameFieldConstraints = new GridBagConstraints();
        nameFieldConstraints.gridx = 1;
        nameFieldConstraints.gridy = 0;
        nameFieldConstraints.gridwidth = 3;
        nameFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        nameFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.personalPanel.add(this.NameField, nameFieldConstraints);

        // Gender and Age
        GridBagConstraints genderLabelConstraints = new GridBagConstraints();
        genderLabelConstraints.gridx = 0;
        genderLabelConstraints.gridy = 1;
        genderLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.personalPanel.add(this.GenderLabel, genderLabelConstraints);
        
        GridBagConstraints genderComboConstraints = new GridBagConstraints();
        genderComboConstraints.gridx = 1;
        genderComboConstraints.gridy = 1;
        genderComboConstraints.insets = new Insets(10, 10, 10, 15);
        this.personalPanel.add(this.GenderComboBox, genderComboConstraints);
        
        GridBagConstraints ageLabelConstraints = new GridBagConstraints();
        ageLabelConstraints.gridx = 2;
        ageLabelConstraints.gridy = 1;
        ageLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.personalPanel.add(this.AgeLabel, ageLabelConstraints);
        
        GridBagConstraints ageFieldConstraints = new GridBagConstraints();
        ageFieldConstraints.gridx = 3;
        ageFieldConstraints.gridy = 1;
        ageFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.personalPanel.add(this.AgeField, ageFieldConstraints);
    }

    private void createContactPanel() {
        this.contactPanel = new JPanel();
        this.contactPanel.setLayout(new GridBagLayout());
        this.contactPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Contact Information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        this.EmailLabel = new JLabel("Email:");
        this.EmailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.EmailField = new JTextField(25);
        this.EmailField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.PhoneLabel = new JLabel("Phone:");
        this.PhoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.PhoneField = new JTextField(25);
        this.PhoneField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.AddressLabel = new JLabel("Address:");
        this.AddressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.AddressField = new JTextField(25);
        this.AddressField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Email
        GridBagConstraints emailLabelConstraints = new GridBagConstraints();
        emailLabelConstraints.gridx = 0;
        emailLabelConstraints.gridy = 0;
        emailLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.contactPanel.add(this.EmailLabel, emailLabelConstraints);
        
        GridBagConstraints emailFieldConstraints = new GridBagConstraints();
        emailFieldConstraints.gridx = 1;
        emailFieldConstraints.gridy = 0;
        emailFieldConstraints.gridwidth = 3;
        emailFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        emailFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.contactPanel.add(this.EmailField, emailFieldConstraints);

        // Phone
        GridBagConstraints phoneLabelConstraints = new GridBagConstraints();
        phoneLabelConstraints.gridx = 0;
        phoneLabelConstraints.gridy = 1;
        phoneLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.contactPanel.add(this.PhoneLabel, phoneLabelConstraints);
        
        GridBagConstraints phoneFieldConstraints = new GridBagConstraints();
        phoneFieldConstraints.gridx = 1;
        phoneFieldConstraints.gridy = 1;
        phoneFieldConstraints.gridwidth = 3;
        phoneFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        phoneFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.contactPanel.add(this.PhoneField, phoneFieldConstraints);

        // Address
        GridBagConstraints addressLabelConstraints = new GridBagConstraints();
        addressLabelConstraints.gridx = 0;
        addressLabelConstraints.gridy = 2;
        addressLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.contactPanel.add(this.AddressLabel, addressLabelConstraints);
        
        GridBagConstraints addressFieldConstraints = new GridBagConstraints();
        addressFieldConstraints.gridx = 1;
        addressFieldConstraints.gridy = 2;
        addressFieldConstraints.gridwidth = 3;
        addressFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        addressFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.contactPanel.add(this.AddressField, addressFieldConstraints);
    }

    private void createSecurityPanel() {
        this.securityPanel = new JPanel();
        this.securityPanel.setLayout(new GridBagLayout());
        this.securityPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Security Information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        this.SecurityQuestionLabel = new JLabel("Security Question: What is your Nickname?");
        this.SecurityQuestionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.SecurityAnsLabel = new JLabel("Security Answer:");
        this.SecurityAnsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.SecurityAnsField = new JTextField(25);
        this.SecurityAnsField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.PasswordLabel = new JLabel("Password:");
        this.PasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.PasswordField = new JTextField(25);
        this.PasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Security Question
        GridBagConstraints questionLabelConstraints = new GridBagConstraints();
        questionLabelConstraints.gridx = 0;
        questionLabelConstraints.gridy = 0;
        questionLabelConstraints.gridwidth = 4;
        questionLabelConstraints.insets = new Insets(10, 10, 10, 10);
        this.securityPanel.add(this.SecurityQuestionLabel, questionLabelConstraints);

        // Security Answer
        GridBagConstraints securityAnsLabelConstraints = new GridBagConstraints();
        securityAnsLabelConstraints.gridx = 0;
        securityAnsLabelConstraints.gridy = 1;
        securityAnsLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.securityPanel.add(this.SecurityAnsLabel, securityAnsLabelConstraints);
        
        GridBagConstraints securityAnsFieldConstraints = new GridBagConstraints();
        securityAnsFieldConstraints.gridx = 1;
        securityAnsFieldConstraints.gridy = 1;
        securityAnsFieldConstraints.gridwidth = 3;
        securityAnsFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        securityAnsFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.securityPanel.add(this.SecurityAnsField, securityAnsFieldConstraints);

        // Password
        GridBagConstraints passwordLabelConstraints = new GridBagConstraints();
        passwordLabelConstraints.gridx = 0;
        passwordLabelConstraints.gridy = 2;
        passwordLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.securityPanel.add(this.PasswordLabel, passwordLabelConstraints);
        
        GridBagConstraints passwordFieldConstraints = new GridBagConstraints();
        passwordFieldConstraints.gridx = 1;
        passwordFieldConstraints.gridy = 2;
        passwordFieldConstraints.gridwidth = 3;
        passwordFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        passwordFieldConstraints.insets = new Insets(10, 10, 10, 10);
        this.securityPanel.add(this.PasswordField, passwordFieldConstraints);
    }

    private void createAdminPanel() {
        this.adminPanel = new JPanel();
        this.adminPanel.setLayout(new GridBagLayout());
        this.adminPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Admin Configuration", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        this.AdminTypeLabel = new JLabel("Admin Type:");
        this.AdminTypeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.AdminTypeComboBox = new JComboBox<String>(new String[]{"Developer","Manager","Sales","Marketing","HR","IT","Finance","Customer Service","Accounting","Inventory","Security","Other"});
        this.AdminTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Admin Type
        GridBagConstraints adminTypeLabelConstraints = new GridBagConstraints();
        adminTypeLabelConstraints.gridx = 0;
        adminTypeLabelConstraints.gridy = 0;
        adminTypeLabelConstraints.insets = new Insets(10, 10, 10, 15);
        this.adminPanel.add(this.AdminTypeLabel, adminTypeLabelConstraints);
        
        GridBagConstraints adminTypeComboConstraints = new GridBagConstraints();
        adminTypeComboConstraints.gridx = 1;
        adminTypeComboConstraints.gridy = 0;
        adminTypeComboConstraints.gridwidth = 3;
        adminTypeComboConstraints.fill = GridBagConstraints.HORIZONTAL;
        adminTypeComboConstraints.insets = new Insets(10, 10, 10, 10);
        this.adminPanel.add(this.AdminTypeComboBox, adminTypeComboConstraints);

    }

    private void createButtonPanel() {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        this.buttonPanel.setBorder(BorderFactory.createEtchedBorder());

        this.UpdateButton = new JButton("Update Administrator");
        this.UpdateButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.UpdateButton.setPreferredSize(new Dimension(200, 45));
        this.UpdateButton.addActionListener(this);
        this.UpdateButton.setEnabled(false); // Disabled until admin is loaded
        
        this.BackButton = new JButton("Back");
        this.BackButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.BackButton.setPreferredSize(new Dimension(150, 45));
        this.BackButton.addActionListener(this);
        
        this.buttonPanel.add(this.UpdateButton);
        this.buttonPanel.add(this.BackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.SearchButton) {
            searchAdmin();
        }
        if (e.getSource() == this.UpdateButton) {
            updateAdminData();
        }
        if (e.getSource() == this.BackButton) {
            goToAdminGUI();
        }
    }

    // Add keyboard shortcuts
    private void setupKeyboardShortcuts() {
        // Enter key in Admin ID field triggers search
        this.AdminIDField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchAdmin();
                }
            }
        });

        // Ctrl+S for save/update
        this.getRootPane().registerKeyboardAction(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (UpdateButton.isEnabled()) {
                        updateAdminData();
                    }
                }
            },
            "Save",
            KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );

        // Escape key to go back
        this.getRootPane().registerKeyboardAction(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToAdminGUI();
                }
            },
            "Back",
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    private void searchAdmin() {
        String adminId = this.AdminIDField.getText().trim();
        
        if (adminId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Admin ID!", "Error", JOptionPane.ERROR_MESSAGE);
            this.AdminIDField.requestFocus();
            return;
        }

        // Show loading indicator
        this.SearchButton.setText("Searching...");
        this.SearchButton.setEnabled(false);
        
        // Use SwingWorker for background processing
        SwingWorker<Admin, Void> worker = new SwingWorker<Admin, Void>() {
            @Override
            protected Admin doInBackground() throws Exception {
                return adminController.searchAdmin(adminId);
            }
            
            @Override
            protected void done() {
                try {
                    currentAdmin = get();
                    
                    if (currentAdmin == null) {
                        JOptionPane.showMessageDialog(EditAdminGUI.this, 
                            "Admin not found with ID: " + adminId, "Error", JOptionPane.ERROR_MESSAGE);
                        clearForm();
                    } else {
                        // Load admin data into form fields
                        loadAdminData();
                        UpdateButton.setEnabled(true);
                        JOptionPane.showMessageDialog(EditAdminGUI.this, 
                            "Admin found! You can now edit the information.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    String errorMessage = "Error searching for admin: " + ex.getMessage();
                    if (ex instanceof NumberFormatException) {
                        errorMessage = "Error: Invalid data format in admin records. Please check the data file.";
                    }
                    JOptionPane.showMessageDialog(EditAdminGUI.this, 
                        errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    SearchButton.setText("Search");
                    SearchButton.setEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void loadAdminData() {
        if (this.currentAdmin == null) return;

        // Load basic information
        this.NameField.setText(this.currentAdmin.getName());
        this.AgeField.setText(String.valueOf(this.currentAdmin.getAge()));
        this.EmailField.setText(this.currentAdmin.getEmail());
        this.PhoneField.setText(this.currentAdmin.getPhoneNo());
        this.AddressField.setText(this.currentAdmin.getAddress());
        this.SecurityAnsField.setText(this.currentAdmin.getSecurityAns());
        this.PasswordField.setText(this.currentAdmin.getPassword());

        // Set gender combo box
        String gender = this.currentAdmin.getGender();
        if (gender != null) {
            if (gender.equalsIgnoreCase("Male")) {
                this.GenderComboBox.setSelectedIndex(0);
            } else if (gender.equalsIgnoreCase("Female")) {
                this.GenderComboBox.setSelectedIndex(1);
            } else {
                this.GenderComboBox.setSelectedIndex(2);
            }
        }

        // Set admin type combo box
        String adminType = this.currentAdmin.getAdminType();
        if (adminType != null) {
            String[] adminTypes = {"Developer","Manager","Sales","Marketing","HR","IT","Finance","Customer Service","Accounting","Inventory","Security","Other"};
            for (int i = 0; i < adminTypes.length; i++) {
                if (adminTypes[i].equals(adminType)) {
                    this.AdminTypeComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }

    }

    private void updateAdminData() {
        if (this.currentAdmin == null) {
            JOptionPane.showMessageDialog(this, "Please search for an admin first!", "Error", JOptionPane.ERROR_MESSAGE);
            this.AdminIDField.requestFocus();
            return;
        }

        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to update this admin's information?", 
            "Confirm Update", JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Get all the data from form fields
            String name = this.NameField.getText().trim();
            String ageStr = this.AgeField.getText().trim();
            String email = this.EmailField.getText().trim();
            String phone = this.PhoneField.getText().trim();
            String address = this.AddressField.getText().trim();
            String securityAnswer = this.SecurityAnsField.getText().trim();
            String password = this.PasswordField.getText().trim();
            String adminType = (String) this.AdminTypeComboBox.getSelectedItem();
            String gender = (String) this.GenderComboBox.getSelectedItem();
            
            // Validate required fields
            if (name.isEmpty() || ageStr.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate age
            int age;
            try {
                age = Integer.parseInt(ageStr);
                if (age <= 0 || age > 150) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                    this.AgeField.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                this.AgeField.requestFocus();
                return;
            }

            // Show loading indicator
            this.UpdateButton.setText("Updating...");
            this.UpdateButton.setEnabled(false);
            
            // Use SwingWorker for background processing
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Update the admin object
                    currentAdmin.setName(name);
                    currentAdmin.setGender(gender);
                    currentAdmin.setAge(age);
                    currentAdmin.setEmail(email);
                    currentAdmin.setPhoneNo(phone);
                    currentAdmin.setAddress(address);
                    currentAdmin.setSecurityAns(securityAnswer);
                    currentAdmin.setPassword(password);
                    currentAdmin.setAdminType(adminType);

                    // Update in the system
                    adminController.updateAdmin(currentAdmin);
                    return null;
                }
                
                @Override
                protected void done() {
                    try {
                        get(); // Check for exceptions
                        JOptionPane.showMessageDialog(EditAdminGUI.this, 
                            "Admin updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(EditAdminGUI.this, 
                            "Error updating data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        UpdateButton.setText("Update Administrator");
                        UpdateButton.setEnabled(true);
                    }
                }
            };
            worker.execute();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        this.AdminIDField.setText("");
        this.NameField.setText("");
        this.AgeField.setText("");
        this.EmailField.setText("");
        this.PhoneField.setText("");
        this.AddressField.setText("");
        this.SecurityAnsField.setText("");
        this.PasswordField.setText("");
        this.GenderComboBox.setSelectedIndex(0);
        this.AdminTypeComboBox.setSelectedIndex(0);
        this.UpdateButton.setEnabled(false);
        this.currentAdmin = null;
    }

    private void goToAdminGUI() {
        // Check if there are unsaved changes
        if (this.currentAdmin != null && this.UpdateButton.isEnabled()) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "You have unsaved changes. Are you sure you want to go back?", 
                "Unsaved Changes", JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        this.dispose();
        new AdminGUI(currentAdmin);
    }

    public static void main(String[] args) {
        EditAdminGUI editAdminGUI = new EditAdminGUI();
        editAdminGUI.setVisible(true);
    }
}
