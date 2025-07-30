package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import controllers.EmployeeController;
import models.Employee;
import java.awt.Color;
import java.awt.Font;

public class EditEmployeeGUI extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel userIdLabel, nameLabel, emailLabel, phoneLabel, addressLabel, nidLabel, designationLabel, salaryLabel, genderLabel, ageLabel, passwordLabel, securityQuestionLabel, securityAnsLabel;
   private AnimatedTextField userIdField, nameField, emailField, phoneField, addressField, nidField, designationField, salaryField, securityAnsField;
   private JPasswordField passwordField;
    private JComboBox<String> genderCombo, securityQuestionCombo;
   private JSpinner ageSpinner;
   private JButton saveButton, clearButton, backButton;
   private JButton choosePhotoButton;
   private JLabel photoPreviewLabel;
   private String selectedPhotoPath = null;
    private EmployeeController employeeController = new EmployeeController();
    private JButton searchButton;

    public EditEmployeeGUI() {
        this(null);
    }

    public EditEmployeeGUI(String userId) {
    super("Edit Employee");
    this.setSize(800, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("Edit Employee");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(30, 90, 180));
        titleLabel.setBounds(200, 30, 400, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.panel.add(titleLabel);

        this.userIdLabel = new JLabel("User ID: ");
        this.userIdLabel.setBounds(120, 100, 100, 30);
        this.panel.add(this.userIdLabel);

        this.userIdField = new AnimatedTextField();
        this.userIdField.setBounds(200, 100, 200, 30); // Adjusted width for search button
        this.panel.add(this.userIdField);

        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(410, 100, 90, 30);
        this.searchButton.setBackground(new Color(70, 130, 180));
        this.searchButton.setForeground(Color.WHITE);
        this.searchButton.addActionListener(_ -> searchEmployeeById());
        this.panel.add(this.searchButton);

        this.nameLabel = new JLabel("Name");
        this.nameLabel.setBounds(120, 150, 100, 30);
        this.panel.add(this.nameLabel);

        this.nameField = new AnimatedTextField();
        this.nameField.setBounds(200, 150, 300, 30);
        this.panel.add(this.nameField);

        this.genderLabel = new JLabel("Gender: ");
        this.genderLabel.setBounds(120, 200, 100, 30);
        this.panel.add(this.genderLabel);

        this.genderCombo = new JComboBox<String>(new String[]{"Male", "Female"});
        this.genderCombo.setBounds(200, 200, 300, 30);
        this.panel.add(this.genderCombo);

        this.ageLabel = new JLabel("Age: ");
        this.ageLabel.setBounds(120, 250, 100, 30);
        this.panel.add(this.ageLabel);

        this.ageSpinner = new JSpinner(new javax.swing.SpinnerNumberModel(18, 18, 100, 1));
        this.ageSpinner.setBounds(200, 250, 300, 30);
        this.panel.add(this.ageSpinner);

        this.emailLabel = new JLabel("Email: ");
        this.emailLabel.setBounds(120, 300, 100, 30);
        this.panel.add(this.emailLabel);

        this.emailField = new AnimatedTextField();
        this.emailField.setBounds(200, 300, 300, 30);
        this.panel.add(this.emailField);

        this.phoneLabel = new JLabel("Phone: ");
        this.phoneLabel.setBounds(120, 350, 100, 30);
        this.panel.add(this.phoneLabel);

        this.phoneField = new AnimatedTextField();
        this.phoneField.setBounds(200, 350, 300, 30);
        this.panel.add(this.phoneField);

        this.addressLabel = new JLabel("Address: ");
        this.addressLabel.setBounds(120, 400, 100, 30);
        this.panel.add(this.addressLabel);

        this.addressField = new AnimatedTextField();
        this.addressField.setBounds(200, 400, 300, 30);
        this.panel.add(this.addressField);

        this.nidLabel = new JLabel("NID: ");
        this.nidLabel.setBounds(120, 450, 100, 30);
        this.panel.add(this.nidLabel);

        this.nidField = new AnimatedTextField();
        this.nidField.setBounds(200, 450, 300, 30);
        this.panel.add(this.nidField);

        this.designationLabel = new JLabel("Designation: ");
        this.designationLabel.setBounds(80, 500, 150, 30);
        this.panel.add(this.designationLabel);

        this.designationField = new AnimatedTextField();
        this.designationField.setBounds(200, 500, 300, 30);
        this.panel.add(this.designationField);

        this.salaryLabel = new JLabel("Salary: ");
        this.salaryLabel.setBounds(120, 550, 100, 30);
        this.panel.add(this.salaryLabel);

        this.salaryField = new AnimatedTextField();
        this.salaryField.setBounds(200, 550, 300, 30);
        this.panel.add(this.salaryField);

        this.passwordLabel = new JLabel("Password: ");
        this.passwordLabel.setBounds(80, 600, 100, 30);
        this.panel.add(this.passwordLabel);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(200, 600, 300, 30);
        this.panel.add(this.passwordField);

        this.securityQuestionLabel = new JLabel("Security Question: ");
        this.securityQuestionLabel.setBounds(50, 650, 170, 30);
        this.panel.add(this.securityQuestionLabel);

        this.securityQuestionCombo = new JComboBox<String>(new String[]{"What is your Nickname?", "What is your pet's name?", "What is your favorite color?"});
        this.securityQuestionCombo.setBounds(200, 650, 300, 30);
        this.panel.add(this.securityQuestionCombo);

        this.securityAnsLabel = new JLabel("Security Answer: ");
        this.securityAnsLabel.setBounds(50, 700, 150, 30);
        this.panel.add(this.securityAnsLabel);

        this.securityAnsField = new AnimatedTextField();
        this.securityAnsField.setBounds(200, 700, 300, 30);
        this.panel.add(this.securityAnsField);

        this.saveButton = new JButton("Save Changes");
        this.saveButton.setBounds(120, 750, 200, 30);
        this.saveButton.setBackground(new Color(34, 139, 34));
        this.saveButton.setForeground(Color.WHITE);
        this.saveButton.addActionListener(this);
        this.panel.add(this.saveButton);

        this.clearButton = new JButton("Clear All");
        this.clearButton.setBounds(400, 750, 200, 30);
        this.clearButton.setBackground(new Color(255, 140, 0));
        this.clearButton.setForeground(Color.WHITE);
        this.clearButton.addActionListener(this);
        this.panel.add(this.clearButton);

        this.backButton = new JButton("Back to Admin");
        this.backButton.setBounds(250, 800, 200, 30);
        this.backButton.setBackground(new Color(70, 130, 180));
        this.backButton.setForeground(Color.WHITE);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        photoPreviewLabel = new JLabel();
        photoPreviewLabel.setBounds(550, 80, 120, 120);
        photoPreviewLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(180,200,240), 2));
        photoPreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoPreviewLabel.setVerticalAlignment(SwingConstants.CENTER);
        photoPreviewLabel.setText("No Photo");
        this.panel.add(photoPreviewLabel);

        choosePhotoButton = new JButton("Choose Photo");
        choosePhotoButton.setBounds(550, 210, 120, 30);
        choosePhotoButton.setBackground(new Color(70, 130, 180));
        choosePhotoButton.setForeground(Color.WHITE);
        choosePhotoButton.addActionListener(_ -> choosePhoto());
        this.panel.add(choosePhotoButton);

        addButtonAnimation(saveButton, new Color(34, 139, 34));
        addButtonAnimation(clearButton, new Color(255, 140, 0));
        addButtonAnimation(backButton, new Color(70, 130, 180));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 18);
        Color labelColor = new Color(40, 40, 60);
        userIdLabel.setFont(labelFont);
        userIdLabel.setForeground(labelColor);
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(labelColor);
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(labelColor);
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(labelColor);
        nidLabel.setFont(labelFont);
        nidLabel.setForeground(labelColor);
        designationLabel.setFont(labelFont);
        designationLabel.setForeground(labelColor);
        salaryLabel.setFont(labelFont);
        salaryLabel.setForeground(labelColor);
        genderLabel.setFont(labelFont);
        genderLabel.setForeground(labelColor);
        ageLabel.setFont(labelFont);
        ageLabel.setForeground(labelColor);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(labelColor);
        securityQuestionLabel.setFont(labelFont);
        securityQuestionLabel.setForeground(labelColor);
        securityAnsLabel.setFont(labelFont);
        securityAnsLabel.setForeground(labelColor);

        Font comboFont = new Font("Segoe UI", Font.PLAIN, 16);
        Color comboBg = new Color(245, 250, 255);
        Color comboFg = new Color(30, 90, 180);
        javax.swing.border.Border roundedBorder = new javax.swing.border.LineBorder(new Color(180, 200, 240), 2, true);
        genderCombo.setFont(comboFont);
        genderCombo.setBackground(comboBg);
        genderCombo.setForeground(comboFg);
        genderCombo.setBorder(roundedBorder);
        securityQuestionCombo.setFont(comboFont);
        securityQuestionCombo.setBackground(comboBg);
        securityQuestionCombo.setForeground(comboFg);
        securityQuestionCombo.setBorder(roundedBorder);

        setupEnterKeyNavigation();
        this.add(panel);
    this.setVisible(true);

        if (userId != null) {
            loadEmployee(userId);
        }
    }

    private void loadEmployee(String userId) {
        Employee emp = employeeController.searchEmployee(userId);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        userIdField.setText(emp.getUserId());
        nameField.setText(emp.getName());
        genderCombo.setSelectedItem(emp.getGender());
        ageSpinner.setValue(emp.getAge());
        emailField.setText(emp.getEmail());
        phoneField.setText(emp.getPhoneNo());
        addressField.setText(emp.getAddress());
        nidField.setText(emp.getNID());
        designationField.setText(emp.getDesignation());
        salaryField.setText(String.valueOf(emp.getSalary()));
        // Password and security answer are not stored in Employee, so leave blank
        passwordField.setText("");
        securityAnsField.setText("");
        // Photo
        selectedPhotoPath = emp.getPhotoPath();
        if (selectedPhotoPath != null && !selectedPhotoPath.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(new ImageIcon(selectedPhotoPath).getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH));
                photoPreviewLabel.setIcon(icon);
                photoPreviewLabel.setText("");
            } catch (Exception ex) {
                photoPreviewLabel.setIcon(null);
                photoPreviewLabel.setText("No Photo");
            }
        } else {
            photoPreviewLabel.setIcon(null);
            photoPreviewLabel.setText("No Photo");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveEmployee();
        } else if (e.getSource() == clearButton) {
            clearForm();
        } else if (e.getSource() == backButton) {
            dispose();
            new AdminGUI(null).setVisible(true);
        }
    }

    private void searchEmployeeById() {
        String userId = userIdField.getText().trim();
        if (userId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        loadEmployee(userId);
    }

    private void saveEmployee() {
        if (!validateForm()) {
            return;
        }
        try {
            String userId = userIdField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String nid = nidField.getText().trim();
            String designation = designationField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            String gender = (String) genderCombo.getSelectedItem();
            int age = (Integer) ageSpinner.getValue();
            String password = new String(passwordField.getPassword());
            String securityAns = securityAnsField.getText().trim();
            Employee employee = new Employee(userId, name, gender, age, email, phone, address, 2, securityAns, password, nid, designation, salary, selectedPhotoPath != null ? selectedPhotoPath : "");
            employeeController.updateEmployee(employee);
            JOptionPane.showMessageDialog(this, "Employee updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid salary amount!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter employee name!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (emailField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter phone number!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (addressField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (nidField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter NID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (designationField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter designation!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (salaryField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter salary!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (securityAnsField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter security answer!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        nidField.setText("");
        designationField.setText("");
        salaryField.setText("");
        passwordField.setText("");
        securityAnsField.setText("");
        genderCombo.setSelectedIndex(0);
        ageSpinner.setValue(18);
        selectedPhotoPath = null;
        photoPreviewLabel.setIcon(null);
        photoPreviewLabel.setText("No Photo");
    }

    private void addButtonAnimation(final JButton button, final Color baseColor) {
        button.setBackground(baseColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(brighten(baseColor, 0.15f));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(darken(baseColor, 0.15f));
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
            }
        });
    }

    private Color brighten(Color color, float fraction) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int i = (int)(1.0/(1.0-fraction));
        if ( r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i);
        }
        if ( r > 0 && r < i ) r = i;
        if ( g > 0 && g < i ) g = i;
        if ( b > 0 && b < i ) b = i;
        return new Color(Math.min((int)(r/(1.0-fraction)), 255),
                         Math.min((int)(g/(1.0-fraction)), 255),
                         Math.min((int)(b/(1.0-fraction)), 255));
    }

    private Color darken(Color color, float fraction) {
        int r = (int)Math.max(color.getRed() * (1.0 - fraction), 0);
        int g = (int)Math.max(color.getGreen() * (1.0 - fraction), 0);
        int b = (int)Math.max(color.getBlue() * (1.0 - fraction), 0);
        return new Color(r, g, b);
    }

    private void setupEnterKeyNavigation() {
        AnimatedTextField[] textFields = {
            userIdField, nameField, emailField, phoneField, addressField,
            nidField, designationField, salaryField, securityAnsField
        };
        for (int i = 0; i < textFields.length; i++) {
            final int currentIndex = i;
            final int nextIndex = (i + 1) % textFields.length;
            textFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (nextIndex < textFields.length) {
                            textFields[nextIndex].requestFocus();
                        } else {
                            if (textFields[currentIndex] == nameField) {
                                genderCombo.requestFocus();
                            } else {
                                passwordField.requestFocus();
                            }
                        }
                    }
                }
            });
        }
        genderCombo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ageSpinner.requestFocus();
                }
            }
        });
        ageSpinner.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    emailField.requestFocus();
                }
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    securityAnsField.requestFocus();
                }
            }
        });
    }

    private void choosePhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Employee Photo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "bmp"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            selectedPhotoPath = selectedFile.getAbsolutePath();
            try {
                ImageIcon icon = new ImageIcon(new ImageIcon(selectedPhotoPath).getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH));
                photoPreviewLabel.setIcon(icon);
                photoPreviewLabel.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to load photo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                selectedPhotoPath = null;
                photoPreviewLabel.setIcon(null);
                photoPreviewLabel.setText("No Photo");
            }
        }
    }

    public static void main(String[] args) {
        // For demo/testing, you can pass a userId to load
        new EditEmployeeGUI().setVisible(true);
    }
}

