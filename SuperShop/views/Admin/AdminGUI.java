package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import models.User;
    
public class AdminGUI extends JFrame implements ActionListener{
   
    private JButton addEmployeeButton,EmployeeListButton,removeEmployeeButton,editEmployeeButton,addCustomerButton,CustomerListButton,addAdminButton,AdminListButton,editAdminButton;
    private JButton addProductButton,editProductButton,ProductListButton;
    private JButton InvoiceListButton,InvoiceDetailsButton,LowStockProductButton,TopSellingProductButton;
    private JButton saleReportButton,ExpenseReportButton,RevenueReportButton,ProfitReportButton;
    private JPanel panel;
    private User user;
    
    public AdminGUI(User user)
    {
        super("Admin Dashboard");
        this.user = user;
        this.setSize(1400, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(new Color(248, 249, 250)); // Light gray background

        // Title
        JLabel titleLabel = new JLabel("ADMIN DASHBOARD");
        titleLabel.setBounds(50, 20, 1300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.panel.add(titleLabel);

        // Section Headers
        createSectionLabel("EMPLOYEE MANAGEMENT", 50, 80);
        createSectionLabel("CUSTOMER MANAGEMENT", 50, 280);
        createSectionLabel("ADMIN MANAGEMENT", 50, 480);
        createSectionLabel("PRODUCT MANAGEMENT", 50, 680);
        createSectionLabel("REPORTS & ANALYTICS", 700, 80);

        // Employee Management Section
        this.addEmployeeButton = createStyledButton("Add Employee", 50, 120, 200, 40, new Color(52, 152, 219), new Color(41, 128, 185));
        this.EmployeeListButton = createStyledButton("Employee List", 270, 120, 200, 40, new Color(46, 204, 113), new Color(39, 174, 96));
        this.removeEmployeeButton = createStyledButton("Remove Employee", 50, 170, 200, 40, new Color(231, 76, 60), new Color(192, 57, 43));
        this.editEmployeeButton = createStyledButton("Edit Employee", 270, 170, 200, 40, new Color(155, 89, 182), new Color(142, 68, 173));

        // Customer Management Section
        this.addCustomerButton = createStyledButton("Add Customer", 50, 320, 200, 40, new Color(241, 196, 15), new Color(243, 156, 18));
        this.CustomerListButton = createStyledButton("Customer List", 270, 320, 200, 40, new Color(26, 188, 156), new Color(22, 160, 133));

        // Admin Management Section
        this.addAdminButton = createStyledButton("Add Admin", 50, 520, 200, 40, new Color(52, 73, 94), new Color(44, 62, 80));
        this.AdminListButton = createStyledButton("Admin List", 270, 520, 200, 40, new Color(149, 165, 166), new Color(127, 140, 141));
        this.editAdminButton = createStyledButton("Edit Admin", 50, 570, 200, 40, new Color(211, 84, 0), new Color(186, 74, 0));

        // Product Management Section
        this.addProductButton = createStyledButton("Add Product", 50, 720, 200, 40, new Color(142, 68, 173), new Color(123, 31, 162));
        this.editProductButton = createStyledButton("Edit Product", 270, 720, 200, 40, new Color(39, 174, 96), new Color(46, 204, 113));
        this.ProductListButton = createStyledButton("Product List", 50, 770, 200, 40, new Color(230, 126, 34), new Color(211, 84, 0));
        this.InvoiceListButton = createStyledButton("Invoice List", 270, 770, 200, 40, new Color(52, 152, 219), new Color(41, 128, 185));
        this.InvoiceDetailsButton = createStyledButton("Invoice Details", 50, 820, 200, 40, new Color(46, 204, 113), new Color(39, 174, 96));
        this.LowStockProductButton = createStyledButton("Low Stock Alert", 270, 820, 200, 40, new Color(231, 76, 60), new Color(192, 57, 43));
        this.TopSellingProductButton = createStyledButton("Top Selling", 50, 870, 200, 40, new Color(155, 89, 182), new Color(142, 68, 173));

        // Reports Section
        this.saleReportButton = createStyledButton("Sales Report", 700, 120, 250, 50, new Color(52, 152, 219), new Color(41, 128, 185));
        this.ExpenseReportButton = createStyledButton("Expense Report", 700, 180, 250, 50, new Color(231, 76, 60), new Color(192, 57, 43));
        this.RevenueReportButton = createStyledButton("Revenue Report", 700, 240, 250, 50, new Color(46, 204, 113), new Color(39, 174, 96));
        this.ProfitReportButton = createStyledButton("Profit Report", 700, 300, 250, 50, new Color(155, 89, 182), new Color(142, 68, 173));

        // Logout Button
        createStyledButton("Logout", 1250, 30, 120, 35, new Color(231, 76, 60), new Color(192, 57, 43));
    
        this.add(this.panel);
        this.setVisible(true);
    }

    private JLabel createSectionLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 400, 30);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(52, 73, 94));
        this.panel.add(label);
        return label;
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height, Color backgroundColor, Color hoverColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Create rounded rectangle
                int arc = 20;
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                
                // Draw text
                g2.setColor(getForeground());
                g2.setFont(getFont());
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), textX, textY);
                
                g2.dispose();
            }
        };
        
        button.setBounds(x, y, width, height);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        
        // Add animated hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            private javax.swing.Timer timer;
            private Color startColor;
            private Color endColor;
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startColor = backgroundColor;
                endColor = hoverColor;
                animateColorChange(button, startColor, endColor, 150);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startColor = hoverColor;
                endColor = backgroundColor;
                animateColorChange(button, startColor, endColor, 150);
            }
            
            private void animateColorChange(JButton btn, Color from, Color to, int duration) {
                if (timer != null) {
                    timer.stop();
                }
                
                timer = new javax.swing.Timer(16, new java.awt.event.ActionListener() {
                    private long startTime = System.currentTimeMillis();
                    
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        long elapsed = System.currentTimeMillis() - startTime;
                        float progress = Math.min((float) elapsed / duration, 1.0f);
                        
                        // Easing function for smooth animation
                        progress = (float) (1 - Math.pow(1 - progress, 3));
                        
                        Color currentColor = interpolateColor(from, to, progress);
                        btn.setBackground(currentColor);
                        btn.repaint();
                        
                        if (progress >= 1.0f) {
                            timer.stop();
                        }
                    }
                });
                timer.start();
            }
            
            private Color interpolateColor(Color from, Color to, float ratio) {
                int red = (int) (from.getRed() + (to.getRed() - from.getRed()) * ratio);
                int green = (int) (from.getGreen() + (to.getGreen() - from.getGreen()) * ratio);
                int blue = (int) (from.getBlue() + (to.getBlue() - from.getBlue()) * ratio);
                return new Color(red, green, blue);
            }
        });
        
        this.panel.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.addEmployeeButton) {
            this.dispose(); 
            new AddEmployeeGUI(); 
        }
        if (e.getSource() == this.EmployeeListButton) {
            this.dispose(); 
            new EmployeeListGUI().setVisible(true); 
        }
        if (e.getSource() == this.removeEmployeeButton) {
            this.dispose(); 
            new RemoveEmployeeGUI().setVisible(true); 
        }
        if (e.getSource() == this.editEmployeeButton) {
            this.dispose(); 
            new EditEmployeeGUI().setVisible(true); 
        }
        if (e.getSource() == this.addCustomerButton) {
            this.dispose(); 
            new AddCustomerGUI().setVisible(true); 
        }
        if (e.getSource() == this.CustomerListButton) {
            this.dispose(); 
            new CustomerListGUI().setVisible(true); 
        }
        if (e.getSource() == this.addAdminButton) {
            new AddAdminGUI().setVisible(true); 
        }
        if (e.getSource() == this.AdminListButton) {
            this.dispose(); 
            new AdminListGUI().setVisible(true); 
        }
        if (e.getSource() == this.editAdminButton) {
            this.dispose(); 
            new EditAdminGUI().setVisible(true); 
        }
        if (e.getSource() == this.addProductButton) {
            this.dispose(); 
            new AddProductGUI().setVisible(true); 
        }
        if (e.getSource() == this.editProductButton) {
            this.dispose(); 
            new EditProductGUI().setVisible(true); 
        }
        if (e.getSource() == this.ProductListButton) {
            this.dispose(); 
            new ProductListGUI().setVisible(true); 
        }
        if (e.getSource() == this.InvoiceListButton) {
            this.dispose(); 
            new InvoiceListGUI().setVisible(true);
        }
        if (e.getSource() == this.InvoiceDetailsButton) {
            this.dispose(); 
            new InvoiceDetailsGUI().setVisible(true); 
        }
        if (e.getSource() == this.LowStockProductButton) {
            this.dispose(); 
            new LowStockGUI().setVisible(true); 
        }
        if (e.getSource() == this.TopSellingProductButton) {
            this.dispose();
            new TopSellingProductGUI().setVisible(true); 
        }
         if (e.getSource() == this.saleReportButton) {
            new SalesReportGUI(user).setVisible(true);
        }
        if (e.getSource() == this.ExpenseReportButton) {
            new ExpenseReportGUI(user).setVisible(true);
        }
        if (e.getSource() == this.RevenueReportButton) {
            new RevenueReportGUI(user).setVisible(true);
        }
        if (e.getSource() == this.ProfitReportButton) {
            new ProfitReportGUI(user).setVisible(true);
        }
        if (e.getSource() instanceof JButton && ((JButton)e.getSource()).getText().equals("Logout")) {
            this.dispose();
            new views.LoginGUI().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new AdminGUI(null).setVisible(true);
    }
}
