package views.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import models.Product;
import models.Cart;
import controllers.CartController;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddToCartPopup extends JDialog implements ActionListener {
    private Product product;
    private JLabel productImageLabel;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel availableLabel;
    private JSpinner quantitySpinner;
    private JButton addToCartButton;
    private JButton cancelButton;
    private JLabel totalPriceLabel;
    private int selectedQuantity = 1;
    private String userId;

    public AddToCartPopup(JFrame parent, Product product, String userId) {
        super(parent, "Add to Cart - " + product.getProductName(), true);
        this.product = product;
        this.userId = userId;
        
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        
        initializeComponents();
        loadProductDetails();
        
        this.setVisible(true);
    }
    
    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel("Add to Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Close button
        JButton closeButton = new JButton("×");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(new Color(231, 76, 60));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        closeButton.addActionListener(_ -> dispose());
        headerPanel.add(closeButton, BorderLayout.EAST);
        
        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Product image
        productImageLabel = new JLabel();
        productImageLabel.setPreferredSize(new Dimension(200, 150));
        productImageLabel.setMaximumSize(new Dimension(200, 150));
        productImageLabel.setHorizontalAlignment(JLabel.CENTER);
        productImageLabel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        productImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Product name
        productNameLabel = new JLabel();
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productNameLabel.setForeground(new Color(44, 62, 80));
        productNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productNameLabel.setMaximumSize(new Dimension(350, 25));
        
        // Price
        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(46, 204, 113));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Available quantity
        availableLabel = new JLabel();
        availableLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        availableLabel.setForeground(Color.GRAY);
        availableLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Quantity selection panel
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantityPanel.setBackground(Color.WHITE);
        quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Create spinner for quantity selection
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, product.getAvailableQuantity(), 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        quantitySpinner.setPreferredSize(new Dimension(80, 30));
        
        // Add spinner change listener to update total price
        quantitySpinner.addChangeListener(_ -> {
            selectedQuantity = (Integer) quantitySpinner.getValue();
            updateTotalPrice();
        });
        
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
        
        // Total price label
        totalPriceLabel = new JLabel();
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel.setForeground(new Color(46, 204, 113));
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 14));
        addToCartButton.setBackground(new Color(46, 204, 113));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setPreferredSize(new Dimension(120, 35));
        addToCartButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(100, 35));
        cancelButton.addActionListener(this);
        
        buttonPanel.add(addToCartButton);
        buttonPanel.add(cancelButton);
        
        // Add components to content panel
        contentPanel.add(productImageLabel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(productNameLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(priceLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(availableLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(quantityPanel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(totalPriceLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buttonPanel);
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        this.add(mainPanel);
        
        // Add keyboard shortcuts
        this.getRootPane().registerKeyboardAction(
            _ -> dispose(), 
            "Cancel", 
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        this.getRootPane().registerKeyboardAction(
            _ -> {
                if (addToCartButton.isEnabled()) {
                    addToCartButton.doClick();
                }
            }, 
            "Add to Cart", 
            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), 
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        // Set initial total price
        updateTotalPrice();
    }
    
    private void loadProductDetails() {
        // Load product image
        ImageIcon productImage = loadProductImage(product.getImage());
        if (productImage != null) {
            Image scaledImage = productImage.getImage().getScaledInstance(180, 130, Image.SCALE_SMOOTH);
            productImageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            productImageLabel.setText("No Image");
            productImageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            productImageLabel.setForeground(Color.GRAY);
        }
        
        // Set product details
        productNameLabel.setText(product.getProductName());
        priceLabel.setText("Price: ৳" + String.format("%.2f", product.getPrice()) + " per " + product.getUnit());
        availableLabel.setText("Available: " + product.getAvailableQuantity() + " " + product.getUnit());
        
        // Set spinner maximum value
        SpinnerNumberModel model = (SpinnerNumberModel) quantitySpinner.getModel();
        model.setMaximum(product.getAvailableQuantity());
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
    
    private void updateTotalPrice() {
        double totalPrice = product.getPrice() * selectedQuantity;
        totalPriceLabel.setText("Total: ৳" + String.format("%.2f", totalPrice));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToCartButton) {
            // Add to cart functionality
            addToCart();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
    
    private void addToCart() {
        try {
            // Create a new cart item
            Cart cartItem = new Cart();
            cartItem.setCartId(generateCartId());
            cartItem.setUserId(getCurrentUserId()); // This should be passed from parent
            cartItem.setProductId(product.getProductId());
            cartItem.setQuantity(selectedQuantity);
            cartItem.setPrice(product.getPrice());
            cartItem.setVAT(0.0); // Default VAT
            cartItem.setDiscount("0%"); // Default discount
            cartItem.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            cartItem.setTotalPrice(product.getPrice() * selectedQuantity);
            cartItem.setStatus("Active");
            
            // Add to cart using CartController
            CartController cartController = new CartController();
            cartController.insertCart(cartItem);
            
            JOptionPane.showMessageDialog(this, 
                "Added " + selectedQuantity + " " + product.getUnit() + " of " + product.getProductName() + " to cart!\n" +
                "Total: ৳" + String.format("%.2f", product.getPrice() * selectedQuantity), 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error adding item to cart: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String generateCartId() {
        return "CART" + System.currentTimeMillis();
    }
    
    private String getCurrentUserId() {
        return userId;
    }
    
    // Getter methods for cart integration
    public Product getProduct() {
        return product;
    }
    
    public int getSelectedQuantity() {
        return selectedQuantity;
    }
    
    public double getTotalPrice() {
        return product.getPrice() * selectedQuantity;
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
                "This is a sample product for testing the add to cart popup.", 
                "", 
                "Available", 
                "piece"
            );
            new AddToCartPopup(null, testProduct, "TEST_USER");
        });
    }
} 