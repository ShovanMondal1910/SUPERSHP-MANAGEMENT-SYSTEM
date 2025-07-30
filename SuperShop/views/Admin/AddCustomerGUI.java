package views.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

public class AddCustomerGUI extends JFrame implements ActionListener{
    private JLabel genaratedUserIDLabel,nameLabel,genderLabel,ageLabel,emailLabel,phoneLabel,addressLabel,securityQuestionLabel,securityAnswerLabel,passwordLabel,membershipTypeLabel,photoPathLabel;
    private JTextField genaratedUserIDField,nameField,emailField,phoneField,addressField,securityAnswerField;
    private JComboBox<String> genderComboBox,membershipTypeComboBox,securityQuestionComboBox;
    private JTextField ageField;
    private JTextField photoPathField;
    private JPasswordField passwordField;
    private JButton addCustomerButton,cancelButton,browseButton;
    private JPanel panel; 
    
    public AddCustomerGUI()
    {
        super("Add Customer");
        this.setSize(800, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.genaratedUserIDLabel = new JLabel("Genarated User ID: ");
        this.genaratedUserIDLabel.setBounds(100, 50, 200, 30);
        this.panel.add(this.genaratedUserIDLabel);

        this.genaratedUserIDField = createLineTextField();
        this.genaratedUserIDField.setBounds(250, 50, 300, 30);
        this.genaratedUserIDField.setEditable(false);
        this.genaratedUserIDField.setText(generateNextCustomerId());
        this.panel.add(this.genaratedUserIDField);
        // Move focus to nameField on Enter
        this.genaratedUserIDField.addActionListener(_ -> this.nameField.requestFocusInWindow());

        this.nameLabel = new JLabel("Name: ");
        this.nameLabel.setBounds(100, 100, 200, 30);
        this.panel.add(this.nameLabel);

        this.nameField = createLineTextField();
        this.nameField.setBounds(250, 100, 300, 30);
        this.panel.add(this.nameField);
        // Move focus to ageField on Enter
        this.nameField.addActionListener(_ -> this.ageField.requestFocusInWindow());

        this.genderLabel = new JLabel("Gender: ");
        this.genderLabel.setBounds(100, 150, 200, 30);
        this.panel.add(this.genderLabel);

        this.genderComboBox = createLineComboBox(new String[]{"Male", "Female"});
        this.genderComboBox.setBounds(250, 150, 300, 30);
        this.panel.add(this.genderComboBox);
        // Do not add Enter key focus for JComboBox

        this.ageLabel = new JLabel("Age: ");
        this.ageLabel.setBounds(100, 200, 200, 30);
        this.panel.add(this.ageLabel);
        
        this.ageField = createLineTextField();
        this.ageField.setBounds(250, 200, 300, 30);
        this.panel.add(this.ageField);
        // Move focus to emailField on Enter
        this.ageField.addActionListener(_ -> this.emailField.requestFocusInWindow());

        this.emailLabel = new JLabel("Email: ");
        this.emailLabel.setBounds(100, 250, 200, 30);
        this.panel.add(this.emailLabel);

        this.emailField = createLineTextField();
        this.emailField.setBounds(250, 250, 300, 30);
        this.panel.add(this.emailField);
        // Move focus to phoneField on Enter
        this.emailField.addActionListener(_ -> this.phoneField.requestFocusInWindow());

        this.phoneLabel = new JLabel("Phone: ");
        this.phoneLabel.setBounds(100, 300, 200, 30);
        this.panel.add(this.phoneLabel);

        this.phoneField = createLineTextField();
        this.phoneField.setBounds(250, 300, 300, 30);
        this.panel.add(this.phoneField);
        // Move focus to addressField on Enter
        this.phoneField.addActionListener(_ -> this.addressField.requestFocusInWindow());

        this.addressLabel = new JLabel("Address: ");
        this.addressLabel.setBounds(100, 350, 200, 30);
        this.panel.add(this.addressLabel);

        this.addressField = createLineTextField();
        this.addressField.setBounds(250, 350, 300, 30);
        this.panel.add(this.addressField);
        // Move focus to securityAnswerField on Enter
        this.addressField.addActionListener(_ -> this.securityAnswerField.requestFocusInWindow());

        this.securityQuestionLabel = new JLabel("Security Question: ");
        this.securityQuestionLabel.setBounds(100, 400, 200, 30);
        this.panel.add(this.securityQuestionLabel);

        this.securityQuestionComboBox = createLineComboBox(new String[]{"What is your Nickname?", "What is your favorite color?"});
        this.securityQuestionComboBox.setBounds(250, 400, 300, 30);
        this.panel.add(this.securityQuestionComboBox);
        // Do not add Enter key focus for JComboBox

        this.securityAnswerLabel = new JLabel("Security Answer: ");
        this.securityAnswerLabel.setBounds(100, 450, 200, 30);
        this.panel.add(this.securityAnswerLabel);

        this.securityAnswerField = createLineTextField();
        this.securityAnswerField.setBounds(250, 450, 300, 30);
        this.panel.add(this.securityAnswerField);
        // Move focus to passwordField on Enter
        this.securityAnswerField.addActionListener(_ -> this.passwordField.requestFocusInWindow());

        this.passwordLabel = new JLabel("Password: ");
        this.passwordLabel.setBounds(100, 500, 200, 30);
        this.panel.add(this.passwordLabel);

        this.passwordField = createLinePasswordField();
        this.passwordField.setBounds(250, 500, 300, 30);
        this.panel.add(this.passwordField);
        // Move focus to photoPathField on Enter
        this.passwordField.addActionListener(_ -> this.photoPathField.requestFocusInWindow());

        this.membershipTypeLabel = new JLabel("Membership Type: ");
        this.membershipTypeLabel.setBounds(100, 550, 200, 30);
        this.panel.add(this.membershipTypeLabel);

        this.membershipTypeComboBox = createLineComboBox(new String[]{"Basic", "Silver", "Gold", "Platinum","Diamond","VIP","Premium","Elite"});
        this.membershipTypeComboBox.setBounds(250, 550, 300, 30);
        this.panel.add(this.membershipTypeComboBox);
        // Do not add Enter key focus for JComboBox

        this.photoPathLabel = new JLabel("Photo Path: ");
        this.photoPathLabel.setBounds(100, 600, 200, 30);
        this.panel.add(this.photoPathLabel);

        this.photoPathField = createLineTextField();
        this.photoPathField.setBounds(250, 600, 300, 30);
        this.panel.add(this.photoPathField);
        // On Enter, trigger signupButton doClick
        this.photoPathField.addActionListener(_ -> this.addCustomerButton.doClick());

        this.browseButton = createAnimatedButton("Browse", new Color(70, 130, 180), new Color(100, 149, 237));
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

        this.addCustomerButton = createAnimatedButton("Add Customer", new Color(34, 139, 34), new Color(50, 205, 50));
        this.addCustomerButton.setBounds(150, 720, 200, 30);
        this.addCustomerButton.addActionListener(this);
        this.panel.add(this.addCustomerButton);

        this.cancelButton = createAnimatedButton("Cancel", new Color(220, 20, 60), new Color(255, 69, 0));
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

    private JTextField createLineTextField() {
        JTextField textField = new JTextField() {
            private boolean isFocused = false;
            private Color lineColor = new Color(220, 20, 60);
            private Color focusColor = new Color(255, 0, 0);
            
            {
                setOpaque(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setFont(new Font("Arial", Font.PLAIN, 14));
                
                addFocusListener(new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        isFocused = true;
                        repaint();
                    }
                    
                    @Override
                    public void focusLost(java.awt.event.FocusEvent e) {
                        isFocused = false;
                        repaint();
                    }
                });
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Set background to transparent
                g2d.setColor(new Color(0, 0, 0, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Draw text
                g2d.setColor(getForeground());
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(getText(), 5, textY);
                
                // Draw bottom line
                g2d.setColor(isFocused ? focusColor : lineColor);
                g2d.setStroke(new BasicStroke(isFocused ? 2.0f : 1.0f));
                g2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                
                g2d.dispose();
            }
        };
        return textField;
    }
    
    private JPasswordField createLinePasswordField() {
        JPasswordField passwordField = new JPasswordField() {
            private boolean isFocused = false;
            private Color lineColor = new Color(220, 20, 60);
            private Color focusColor = new Color(255, 0, 0);
            
            {
                setOpaque(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setFont(new Font("Arial", Font.PLAIN, 14));
                
                addFocusListener(new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        isFocused = true;
                        repaint();
                    }
                    
                    @Override
                    public void focusLost(java.awt.event.FocusEvent e) {
                        isFocused = false;
                        repaint();
                    }
                });
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Set background to transparent
                g2d.setColor(new Color(0, 0, 0, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Draw password dots
                g2d.setColor(getForeground());
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                
                char[] password = getPassword();
                String displayText = new String(password).replaceAll(".", "•");
                g2d.drawString(displayText, 5, textY);
                
                // Draw bottom line
                g2d.setColor(isFocused ? focusColor : lineColor);
                g2d.setStroke(new BasicStroke(isFocused ? 2.0f : 1.0f));
                g2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                
                g2d.dispose();
            }
        };
        return passwordField;
    }
    
    private JComboBox<String> createLineComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<String>(items) {
            private boolean isFocused = false;
            private Color lineColor = new Color(220, 20, 60);
            private Color focusColor = new Color(255, 0, 0);
            
            {
                setOpaque(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setFont(new Font("Arial", Font.PLAIN, 14));
                
                addFocusListener(new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        isFocused = true;
                        repaint();
                    }
                    
                    @Override
                    public void focusLost(java.awt.event.FocusEvent e) {
                        isFocused = false;
                        repaint();
                    }
                });
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Set background to transparent
                g2d.setColor(new Color(0, 0, 0, 0));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Draw selected text
                g2d.setColor(getForeground());
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                String selectedText = getSelectedItem() != null ? getSelectedItem().toString() : "";
                g2d.drawString(selectedText, 5, textY);
                
                // Draw bottom line
                g2d.setColor(isFocused ? focusColor : lineColor);
                g2d.setStroke(new BasicStroke(isFocused ? 2.0f : 1.0f));
                g2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                
                g2d.dispose();
            }
        };
        return comboBox;
    }

    private JButton createAnimatedButton(String text, Color normalColor, Color hoverColor) {
        JButton button = new JButton(text) {
            private boolean isHovered = false;
            private float alpha = 0.0f;
            
            {
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setForeground(Color.WHITE);
                setFont(new Font("Arial", Font.BOLD, 14));
                
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        isHovered = true;
                        animateHover();
                    }
                    
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        isHovered = false;
                        animateHover();
                    }
                });
            }
            
            private void animateHover() {
                Timer timer = new Timer(20, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isHovered && alpha < 1.0f) {
                            alpha += 0.1f;
                            repaint();
                        } else if (!isHovered && alpha > 0.0f) {
                            alpha -= 0.1f;
                            repaint();
                        } else {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });
                timer.start();
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Create gradient effect
                Color currentColor = interpolateColor(normalColor, hoverColor, alpha);
                GradientPaint gradient = new GradientPaint(0, 0, currentColor, getWidth(), getHeight(), 
                    currentColor.brighter());
                
                g2d.setPaint(gradient);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                
                // Add shadow effect
                if (isHovered) {
                    g2d.setColor(new Color(0, 0, 0, 50));
                    g2d.fill(new RoundRectangle2D.Float(2, 2, getWidth()-4, getHeight()-4, 15, 15));
                }
                
                // Draw text
                g2d.setColor(getForeground());
                FontMetrics fm = g2d.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2d.drawString(getText(), textX, textY);
                
                g2d.dispose();
            }
            
            private Color interpolateColor(Color c1, Color c2, float ratio) {
                float[] comp1 = c1.getRGBComponents(null);
                float[] comp2 = c2.getRGBComponents(null);
                float[] result = new float[4];
                for (int i = 0; i < 4; i++) {
                    result[i] = comp1[i] + (comp2[i] - comp1[i]) * ratio;
                }
                return new Color(result[0], result[1], result[2], result[3]);
            }
        };
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.addCustomerButton){
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
                new AdminGUI(null).setVisible(true);
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
            new AdminGUI(null).setVisible(true);
            dispose();
        }
    }
    public static void main(String[] args) {
        AddCustomerGUI addCustomerGUI = new AddCustomerGUI();
        addCustomerGUI.setVisible(true);
    }
}