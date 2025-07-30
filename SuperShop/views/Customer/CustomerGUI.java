package views.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controllers.ProductController;
import models.Product;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel productGridPanel;
    private JScrollPane scrollPane;
    private JComboBox<String> categoryFilter;
    private JTextField searchField;
    private JButton searchButton;
    private JButton viewCartButton;
    private JButton logoutButton;
    private JButton gridViewButton;
    private JButton listViewButton;
    private JButton scrollToTopButton;
    private JButton scrollToBottomButton;
    private JButton clearFiltersButton;
    private JLabel scrollIndicator;
    private JLabel categoryStatsLabel;
    private List<Product> allProducts;
    private ProductController productController;

    public CustomerGUI() {
        super("SuperShop - Customer View");
        this.setSize(1400, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Initialize controllers
        productController = new ProductController();
        allProducts = new ArrayList<>();
        
        // Load all products
        loadProducts();
        
        // Initialize UI components
        initializeComponents();
        createProductGrid();
        
        this.setVisible(true);
    }
    
    private void loadProducts() {
        Product[] products = productController.getAllProduct();
        allProducts.clear();
        for (Product p : products) {
            if (p != null && "Available".equals(p.getStatus())) {
                allProducts.add(p);
            }
        }
    }
    
    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(1400, 80));
        
        JLabel titleLabel = new JLabel("Welcome to SuperShop - Browse Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        // Create control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        controlPanel.setBackground(new Color(236, 240, 241));
        controlPanel.setPreferredSize(new Dimension(1400, 60));
        
        // Search field
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        controlPanel.add(new JLabel("Search Products: "));
        controlPanel.add(searchField);
        
        // Search button
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchButton.addActionListener(this);
        controlPanel.add(searchButton);
        
        // Category filter with enhanced styling
        controlPanel.add(new JLabel("Category: "));
        categoryFilter = new JComboBox<>();
        categoryFilter.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryFilter.setPreferredSize(new Dimension(150, 30));
        categoryFilter.addItem("All Categories");
        
        // Get unique categories and sort them alphabetically
        List<String> categories = new ArrayList<>();
        for (Product p : allProducts) {
            if (!categories.contains(p.getCategory())) {
                categories.add(p.getCategory());
            }
        }
        java.util.Collections.sort(categories); // Sort categories alphabetically
        
        for (String category : categories) {
            categoryFilter.addItem(category);
        }
        
        // Add category count information
        categoryFilter.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    String category = value.toString();
                    if (!category.equals("All Categories")) {
                        int count = getProductCountByCategory(category);
                        setText(category + " (" + count + " items)");
                    } else {
                        setText(category + " (" + allProducts.size() + " items)");
                    }
                }
                return this;
            }
        });
        
        categoryFilter.addActionListener(this);
        categoryFilter.setToolTipText("Select a category to filter products. Shows product count for each category.");
        controlPanel.add(categoryFilter);
        
        // Clear filters button
        clearFiltersButton = new JButton("Clear Filters");
        clearFiltersButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearFiltersButton.setBackground(new Color(149, 165, 166));
        clearFiltersButton.setForeground(Color.WHITE);
        clearFiltersButton.addActionListener(this);
        controlPanel.add(clearFiltersButton);
        
        // View cart button
        viewCartButton = new JButton("View Cart");
        viewCartButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewCartButton.setBackground(new Color(46, 204, 113));
        viewCartButton.setForeground(Color.WHITE);
        viewCartButton.addActionListener(this);
        viewCartButton.setToolTipText("View shopping cart");
        controlPanel.add(viewCartButton);
        
        // View toggle buttons
        gridViewButton = new JButton("Grid View");
        gridViewButton.setFont(new Font("Arial", Font.BOLD, 12));
        gridViewButton.setBackground(new Color(52, 152, 219));
        gridViewButton.setForeground(Color.WHITE);
        gridViewButton.addActionListener(this);
        controlPanel.add(gridViewButton);
        
        listViewButton = new JButton("List View");
        listViewButton.setFont(new Font("Arial", Font.BOLD, 12));
        listViewButton.setBackground(new Color(155, 89, 182));
        listViewButton.setForeground(Color.WHITE);
        listViewButton.addActionListener(this);
        controlPanel.add(listViewButton);
        
        // Scroll navigation buttons
        scrollToTopButton = new JButton("↑ Top");
        scrollToTopButton.setFont(new Font("Arial", Font.BOLD, 10));
        scrollToTopButton.setBackground(new Color(52, 73, 94));
        scrollToTopButton.setForeground(Color.WHITE);
        scrollToTopButton.addActionListener(this);
        controlPanel.add(scrollToTopButton);
        
        scrollToBottomButton = new JButton("↓ Bottom");
        scrollToBottomButton.setFont(new Font("Arial", Font.BOLD, 10));
        scrollToBottomButton.setBackground(new Color(52, 73, 94));
        scrollToBottomButton.setForeground(Color.WHITE);
        scrollToBottomButton.addActionListener(this);
        controlPanel.add(scrollToBottomButton);
        
        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setBackground(new Color(231, 76, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(this);
        controlPanel.add(logoutButton);
        
        // Create product grid panel
        productGridPanel = new JPanel();
        productGridPanel.setLayout(new GridLayout(0, 4, 15, 15));
        productGridPanel.setBackground(new Color(248, 249, 250));
        productGridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create scroll pane with enhanced scrolling
        scrollPane = new JScrollPane(productGridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        
        // Enable mouse wheel scrolling
        scrollPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                int notches = e.getWheelRotation();
                int increment = verticalBar.getUnitIncrement() * 3;
                verticalBar.setValue(verticalBar.getValue() + (notches * increment));
            }
        });
        
        // Create scroll indicator
        scrollIndicator = new JLabel("Scroll: 0%");
        scrollIndicator.setFont(new Font("Arial", Font.BOLD, 10));
        scrollIndicator.setForeground(new Color(52, 73, 94));
        scrollIndicator.setBackground(new Color(236, 240, 241));
        scrollIndicator.setOpaque(true);
        scrollIndicator.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        
        // Add scroll listener to update indicator
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
                int value = scrollBar.getValue();
                int max = scrollBar.getMaximum() - scrollBar.getVisibleAmount();
                int percentage = max > 0 ? (value * 100) / max : 0;
                scrollIndicator.setText("Scroll: " + percentage + "%");
            }
        });
        
        // Create bottom panel for scroll indicator and category stats
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(236, 240, 241));
        bottomPanel.setPreferredSize(new Dimension(1400, 30));
        
        // Category statistics label
        categoryStatsLabel = new JLabel("Total Products: " + allProducts.size() + " | Categories: " + getUniqueCategoryCount());
        categoryStatsLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        categoryStatsLabel.setForeground(new Color(52, 73, 94));
        categoryStatsLabel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        
        bottomPanel.add(categoryStatsLabel, BorderLayout.WEST);
        bottomPanel.add(scrollIndicator, BorderLayout.EAST);
        
        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Add window listener for resize handling
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Refresh the product grid when window is resized
                SwingUtilities.invokeLater(() -> {
                    createProductGrid();
                });
            }
        });
    }
    
    private void createProductGrid() {
        productGridPanel.removeAll();
        
        String searchText = searchField.getText().toLowerCase();
        String selectedCategory = (String) categoryFilter.getSelectedItem();
        int displayedCount = 0;
        
        for (Product product : allProducts) {
            // Apply filters
            if (!selectedCategory.equals("All Categories") && !product.getCategory().equals(selectedCategory)) {
                continue;
            }
            
            if (!searchText.isEmpty() && 
                !product.getProductName().toLowerCase().contains(searchText) &&
                !product.getBrand().toLowerCase().contains(searchText) &&
                !product.getCategory().toLowerCase().contains(searchText)) {
                continue;
            }
            
            // Create product card
            JPanel productCard = createProductCard(product);
            productGridPanel.add(productCard);
            displayedCount++;
        }
        
        // Update category statistics
        updateCategoryStats(displayedCount, selectedCategory);
        
        productGridPanel.revalidate();
        productGridPanel.repaint();
    }
    
    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 400));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Product image
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(280, 200));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    viewProductDetails(product);
                }
            }
        });
        
        // Load product image
        ImageIcon productImage = loadProductImage(product.getImage());
        if (productImage != null) {
            Image scaledImage = productImage.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setText("No Image Available");
            imageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            imageLabel.setForeground(Color.GRAY);
        }
        
        // Product details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        
        // Product name
        JLabel nameLabel = new JLabel(product.getProductName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setMaximumSize(new Dimension(280, 25));
        
        // Brand
        JLabel brandLabel = new JLabel("Brand: " + product.getBrand());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        brandLabel.setForeground(Color.GRAY);
        brandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Category
        JLabel categoryLabel = new JLabel("Category: " + product.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        categoryLabel.setForeground(Color.GRAY);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Price (highlighted)
        JLabel priceLabel = new JLabel("Price: ৳" + String.format("%.2f", product.getPrice()) + " per " + product.getUnit());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(46, 204, 113));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Available quantity
        JLabel quantityLabel = new JLabel("Available: " + product.getAvailableQuantity() + " " + product.getUnit());
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        quantityLabel.setForeground(product.getAvailableQuantity() > 0 ? Color.GREEN : Color.RED);
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add to cart button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 12));
        addToCartButton.setBackground(new Color(52, 152, 219));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.setMaximumSize(new Dimension(120, 30));
        addToCartButton.setEnabled(product.getAvailableQuantity() > 0);
        addToCartButton.addActionListener(_ -> addToCart(product));
        
        // View details button
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewDetailsButton.setBackground(new Color(155, 89, 182));
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewDetailsButton.setMaximumSize(new Dimension(120, 30));
        viewDetailsButton.addActionListener(_ -> viewProductDetails(product));
        
        // Add components to details panel
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(brandLabel);
        detailsPanel.add(Box.createVerticalStrut(2));
        detailsPanel.add(categoryLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(priceLabel);
        detailsPanel.add(Box.createVerticalStrut(2));
        detailsPanel.add(quantityLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(addToCartButton);
        detailsPanel.add(Box.createVerticalStrut(5));
        detailsPanel.add(viewDetailsButton);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        // Add components to card
        card.add(imageLabel, BorderLayout.NORTH);
        card.add(detailsPanel, BorderLayout.CENTER);
        
        return card;
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
    
    private void addToCart(Product product) {
        // Open the add to cart popup
        new AddToCartPopup(this, product, getCurrentUserId());
    }
    
    private void viewProductDetails(Product product) {
        // Open the product details window
        new ProductDetailsGUI(product);
    }
    
    private int getProductCountByCategory(String category) {
        int count = 0;
        for (Product product : allProducts) {
            if (product.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }
    
    private int getUniqueCategoryCount() {
        List<String> categories = new ArrayList<>();
        for (Product product : allProducts) {
            if (!categories.contains(product.getCategory())) {
                categories.add(product.getCategory());
            }
        }
        return categories.size();
    }
    
    private void updateCategoryStats(int displayedCount, String selectedCategory) {
        if (selectedCategory.equals("All Categories")) {
            categoryStatsLabel.setText("Total Products: " + allProducts.size() + " | Categories: " + getUniqueCategoryCount() + " | Displayed: " + displayedCount);
        } else {
            categoryStatsLabel.setText("Category: " + selectedCategory + " | Products: " + displayedCount + " | Total Categories: " + getUniqueCategoryCount());
        }
    }
    
    private void smoothScrollTo(int targetValue) {
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        int currentValue = scrollBar.getValue();
        int difference = targetValue - currentValue;
        int steps = Math.abs(difference) / 10;
        
        if (steps == 0) return;
        
        Timer timer = new Timer(10, new ActionListener() {
            int currentStep = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep >= steps) {
                    scrollBar.setValue(targetValue);
                    ((Timer) e.getSource()).stop();
                    return;
                }
                
                int newValue = currentValue + (difference * currentStep / steps);
                scrollBar.setValue(newValue);
                currentStep++;
            }
        });
        timer.start();
    }
    
    private String getCurrentUserId() {
        // TODO: This should be passed from the login screen
        // For now, return a default user ID
        return "CUSTOMER001";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton || e.getSource() == categoryFilter) {
            createProductGrid();
        } else if (e.getSource() == clearFiltersButton) {
            searchField.setText("");
            categoryFilter.setSelectedItem("All Categories");
            createProductGrid();
        } else if (e.getSource() == gridViewButton) {
            productGridPanel.setLayout(new GridLayout(0, 4, 15, 15));
            createProductGrid();
        } else if (e.getSource() == listViewButton) {
            productGridPanel.setLayout(new GridLayout(0, 1, 5, 5));
            createProductGrid();
        } else if (e.getSource() == scrollToTopButton) {
            smoothScrollTo(0);
        } else if (e.getSource() == scrollToBottomButton) {
            smoothScrollTo(scrollPane.getVerticalScrollBar().getMaximum());
        } else if (e.getSource() == viewCartButton) {
            // Open cart GUI
            this.setVisible(false);
            new CartGUI(this, getCurrentUserId());
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to logout?", 
                "Logout", 
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // TODO: Navigate back to login screen
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerGUI();
        });
    }
}
