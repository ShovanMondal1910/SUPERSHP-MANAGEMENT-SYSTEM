package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import controllers.ProductController;
import models.Product;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class ProductListGUI extends JFrame implements ActionListener{
    private JButton addProductButton,removeProductButton,backButton;
    private JTable productTable;
    private JScrollPane scrollPane;
    private JPanel panel;

    public ProductListGUI()
    {
        super("Product List");
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        this.panel = new JPanel();
        this.panel.setBounds(0,0,1200,1000);
        this.panel.setLayout(null);

        this.addProductButton = new JButton("Add Product");
        this.addProductButton.setBounds(550,20,200,30);
        this.addProductButton.addActionListener(this);
        this.panel.add(this.addProductButton);

        this.removeProductButton = new JButton("Remove Product");
        this.removeProductButton.setBounds(800,20,200,30);
        this.removeProductButton.addActionListener(this);
        this.panel.add(this.removeProductButton);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(1050,20,100,30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        // Table columns
        String[] columnNames = {"Photo", "Product ID", "Name", "Category", "Price", "Available Qty", "Unit", "Details", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return ImageIcon.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Load product data
        ProductController pc = new ProductController();
        Product[] products = pc.getAllProduct();
        for (Product p : products) {
            if (p != null) {
                ImageIcon icon = null;
                String imgPath = p.getImage();
                if (imgPath != null && !imgPath.isEmpty()) {
                    File imgFile = new File(imgPath);
                    if (imgFile.exists()) {
                        try {
                            // Try multiple approaches to load the image
                            java.awt.image.BufferedImage originalImage = loadImageWithMultipleFormats(imgFile);
                            if (originalImage != null) {
                                Image scaledImg = originalImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                                icon = new ImageIcon(scaledImg);
                            } else {
                                icon = createDefaultIcon();
                            }
                        } catch (Exception e) {
                            // If image loading fails, create a default placeholder
                            icon = createDefaultIcon();
                        }
                    } else {
                        // If file doesn't exist, create a default placeholder
                        icon = createDefaultIcon();
                    }
                } else {
                    // If no image path, create a default placeholder
                    icon = createDefaultIcon();
                }
                model.addRow(new Object[]{
                    icon,
                    p.getProductId(),
                    p.getProductName(),
                    p.getCategory(),
                    String.valueOf(p.getPrice()),
                    String.valueOf(p.getAvailableQuantity()),
                    p.getUnit(),
                    p.getDetails(),
                    p.getStatus()
                });
            }
        }

        this.productTable = new JTable(model);
        this.productTable.setRowHeight(60);
        this.scrollPane = new JScrollPane(this.productTable);
        this.scrollPane.setBounds(10,70,1170,885);
        this.panel.add(this.scrollPane);

        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            new AdminGUI(null).setVisible(true);
            dispose();
        }
        else if(e.getSource() == addProductButton){
            new AddProductGUI().setVisible(true);
            dispose();
        }
        else if(e.getSource() == removeProductButton){
            int selectedRow = productTable.getSelectedRow();
            if(selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a product to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String productId = productTable.getValueAt(selectedRow, 1).toString();
            ProductController pc = new ProductController();
            pc.delateProduct(productId);
            ((DefaultTableModel)productTable.getModel()).removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Product removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private java.awt.image.BufferedImage loadImageWithMultipleFormats(File imgFile) {
        // Method 1: Try ImageIO.read() - supports most formats including webp, png, jpg, gif, bmp
        try {
            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(imgFile);
            if (image != null) {
                return image;
            }
        } catch (Exception e) {
            // Continue to next method
        }
        
        // Method 2: Try ImageIcon for formats that ImageIO might not support
        try {
            ImageIcon icon = new ImageIcon(imgFile.getPath());
            if (icon.getImage() != null) {
                // Convert Image to BufferedImage
                java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(
                    icon.getIconWidth(), icon.getIconHeight(), java.awt.image.BufferedImage.TYPE_INT_RGB);
                java.awt.Graphics2D g2d = bufferedImage.createGraphics();
                g2d.drawImage(icon.getImage(), 0, 0, null);
                g2d.dispose();
                return bufferedImage;
            }
        } catch (Exception e) {
            // Continue to next method
        }
        
        // Method 3: Try with explicit format detection
        try {
            String fileName = imgFile.getName().toLowerCase();
            String format = null;
            
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                format = "JPEG";
            } else if (fileName.endsWith(".png")) {
                format = "PNG";
            } else if (fileName.endsWith(".gif")) {
                format = "GIF";
            } else if (fileName.endsWith(".bmp")) {
                format = "BMP";
            } else if (fileName.endsWith(".webp")) {
                format = "WEBP";
            }
            
            if (format != null) {
                java.io.FileInputStream fis = new java.io.FileInputStream(imgFile);
                java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(fis);
                fis.close();
                if (image != null) {
                    return image;
                }
            }
        } catch (Exception e) {
            // Continue to next method
        }
        
        // Method 4: Try with Toolkit for system-specific image loading
        try {
            java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
            java.awt.Image image = toolkit.getImage(imgFile.getPath());
            if (image != null) {
                // Convert to BufferedImage
                java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(
                    image.getWidth(null), image.getHeight(null), java.awt.image.BufferedImage.TYPE_INT_RGB);
                java.awt.Graphics2D g2d = bufferedImage.createGraphics();
                g2d.drawImage(image, 0, 0, null);
                g2d.dispose();
                return bufferedImage;
            }
        } catch (Exception e) {
            // All methods failed
        }
        
        return null;
    }
    
    private ImageIcon createDefaultIcon() {
        // Create a simple placeholder icon
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(60, 60, java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g2d = img.createGraphics();
        g2d.setColor(java.awt.Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, 60, 60);
        g2d.setColor(java.awt.Color.DARK_GRAY);
        g2d.drawString("No Image", 5, 35);
        g2d.dispose();
        return new ImageIcon(img);
    }
    
    public static void main(String[] args) {
        ProductListGUI productListGUI = new ProductListGUI();
        productListGUI.setVisible(true);
    }
    
}
