package views.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import models.Product;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class ProductDetailsGUI extends JFrame implements ActionListener {
    private Product product;
    private JLabel productImageLabel;
    private JLabel productNameLabel;
    private JLabel brandLabel;
    private JLabel categoryLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel detailsLabel;
    private JLabel unitLabel;
    private JButton addToCartButton;
    private JButton backButton;
    private JButton viewAllProductsButton;
    private JPanel mainPanel;

    public ProductDetailsGUI(Product product) {
        super("Product Details - " + product.getProductName());
        this.product = product;
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        initializeComponents();
        loadProductDetails();
        
        this.setVisible(true);
    }
    
    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        
        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(800, 60));
        
        JLabel titleLabel = new JLabel("Product Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Back button
        backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(231, 76, 60));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        headerPanel.add(backButton, BorderLayout.EAST);
        
        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(20, 20));
        contentPanel.setBackground(new Color(248, 249, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel for image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        productImageLabel = new JLabel();
        productImageLabel.setPreferredSize(new Dimension(350, 350));
        productImageLabel.setHorizontalAlignment(JLabel.CENTER);
        productImageLabel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        imagePanel.add(productImageLabel, BorderLayout.CENTER);
        
        // Right panel for details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Product name
        productNameLabel = new JLabel();
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        productNameLabel.setForeground(new Color(44, 62, 80));
        productNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        productNameLabel.setMaximumSize(new Dimension(350, 40));
        
        // Brand
        brandLabel = new JLabel();
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        brandLabel.setForeground(new Color(52, 73, 94));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Category
        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        categoryLabel.setForeground(new Color(52, 73, 94));
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Unit
        unitLabel = new JLabel();
        unitLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        unitLabel.setForeground(new Color(52, 73, 94));
        unitLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Price (highlighted)
        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        priceLabel.setForeground(new Color(46, 204, 113));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Available quantity
        quantityLabel = new JLabel();
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Details
        detailsLabel = new JLabel();
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsLabel.setForeground(new Color(127, 140, 141));
        detailsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Add to cart button
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 16));
        addToCartButton.setBackground(new Color(52, 152, 219));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addToCartButton.setMaximumSize(new Dimension(200, 40));
        addToCartButton.addActionListener(this);
        
        // View all products button
        viewAllProductsButton = new JButton("View All Products");
        viewAllProductsButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewAllProductsButton.setBackground(new Color(46, 204, 113));
        viewAllProductsButton.setForeground(Color.WHITE);
        viewAllProductsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewAllProductsButton.setMaximumSize(new Dimension(200, 35));
        viewAllProductsButton.addActionListener(this);
        
        // Add components to details panel
        detailsPanel.add(productNameLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(brandLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(categoryLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(unitLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(quantityLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(detailsLabel);
        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(addToCartButton);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(viewAllProductsButton);
        
        // Add panels to content panel
        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(detailsPanel, BorderLayout.CENTER);
        
        // Create scrollable content panel
        JScrollPane contentScrollPane = new JScrollPane(contentPanel);
        contentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        
        // Enable mouse wheel scrolling for content
        contentScrollPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar verticalBar = contentScrollPane.getVerticalScrollBar();
                int notches = e.getWheelRotation();
                int increment = verticalBar.getUnitIncrement() * 3;
                verticalBar.setValue(verticalBar.getValue() + (notches * increment));
            }
        });
        
        // Add to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentScrollPane, BorderLayout.CENTER);
        
        this.add(mainPanel);
    }
    
    private void loadProductDetails() {
        // Load product image
        ImageIcon productImage = loadProductImage(product.getImage());
        if (productImage != null) {
            Image scaledImage = productImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            productImageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            productImageLabel.setText("No Image Available");
            productImageLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            productImageLabel.setForeground(Color.GRAY);
        }
        
        // Set product details
        productNameLabel.setText(product.getProductName());
        brandLabel.setText("Brand: " + product.getBrand());
        categoryLabel.setText("Category: " + product.getCategory());
        unitLabel.setText("Unit: " + product.getUnit());
        priceLabel.setText("৳" + String.format("%.2f", product.getPrice()) + " per " + product.getUnit());
        
        // Set quantity with color coding
        if (product.getAvailableQuantity() > 0) {
            quantityLabel.setText("Available: " + product.getAvailableQuantity() + " " + product.getUnit());
            quantityLabel.setForeground(new Color(46, 204, 113));
            addToCartButton.setEnabled(true);
        } else {
            quantityLabel.setText("Out of Stock");
            quantityLabel.setForeground(new Color(231, 76, 60));
            addToCartButton.setEnabled(false);
            addToCartButton.setText("Out of Stock");
        }
        
        // Set details
        if (product.getDetails() != null && !product.getDetails().isEmpty()) {
            detailsLabel.setText("Details: " + product.getDetails());
        } else {
            detailsLabel.setText("No additional details available.");
        }
    }
    
    private ImageIcon loadProductImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        
        File imgFile = new File(imagePath);
        if (!imgFile.exists()) {
            return null;
        }
        
        try {
            // Try multiple approaches to load the image
            java.awt.image.BufferedImage originalImage = loadImageWithMultipleFormats(imgFile);
            if (originalImage != null) {
                return new ImageIcon(originalImage);
            }
        } catch (Exception e) {
            // Image loading failed
        }
        
        return null;
    }
    
    private java.awt.image.BufferedImage loadImageWithMultipleFormats(File imgFile) {
        // Method 1: Try ImageIO.read() - supports most formats
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
        
        // Method 3: Try with Toolkit for system-specific image loading
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
    
    private String getCurrentUserId() {
        // TODO: This should be passed from the parent CustomerGUI
        // For now, return a default user ID
        return "CUSTOMER001";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
        } else if (e.getSource() == addToCartButton) {
            if (product.getAvailableQuantity() > 0) {
                // Open the add to cart popup
                new AddToCartPopup(this, product, getCurrentUserId());
            }
        } else if (e.getSource() == viewAllProductsButton) {
            // Open the main customer GUI
            new CustomerGUI();
            dispose();
        }
    }
    
    public static void main(String[] args) {
        // For testing purposes
        SwingUtilities.invokeLater(() -> {
            // Create a sample product for testing
            Product testProduct = new Product(
                "TEST001", 
                "Sample Product", 
                "Test Category", 
                "Test Brand", 
                99.99, 
                10, 
                "This is a sample product for testing the details view.", 
                "", 
                "Available", 
                "piece"
            );
            new ProductDetailsGUI(testProduct);
        });
    }
} 