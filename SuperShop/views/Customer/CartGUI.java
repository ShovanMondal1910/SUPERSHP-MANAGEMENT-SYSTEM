package views.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import models.Cart;
import models.Product;
import controllers.CartController;
import controllers.ProductController;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class CartGUI extends JFrame implements ActionListener {
    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JLabel itemCountLabel;
    private JButton checkoutButton;
    private JButton continueShoppingButton;
    private JButton clearCartButton;
    private JButton removeSelectedButton;
    private JButton updateQuantityButton;
    private CartController cartController;
    private ProductController productController;
    private List<Cart> cartItems;
    private String currentUserId; // This should be passed from login
    private CustomerGUI parentGUI;

    public CartGUI(CustomerGUI parent, String userId) {
        super("Shopping Cart - SuperShop");
        this.parentGUI = parent;
        this.currentUserId = userId;
        
        // Initialize controllers
        cartController = new CartController();
        productController = new ProductController();
        cartItems = new ArrayList<>();
        
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        this.setResizable(true);
        
        initializeComponents();
        loadCartItems();
        
        this.setVisible(true);
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content panel
        JPanel contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);
        
        // Footer panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(1000, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel titleLabel = new JLabel("Shopping Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("← Back to Products");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(44, 62, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(_ -> {
            dispose();
            parentGUI.setVisible(true);
        });
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(248, 249, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create cart table
        createCartTable();
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        
        // Create action panel
        JPanel actionPanel = createActionPanel();
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(actionPanel, BorderLayout.SOUTH);
        
        return contentPanel;
    }
    
    private void createCartTable() {
        String[] columnNames = {"Product", "Price", "Quantity", "Total", "Actions"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Only quantity column is editable
            }
        };
        
        cartTable = new JTable(tableModel);
        cartTable.setRowHeight(60);
        cartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 12));
        cartTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        cartTable.getTableHeader().setBackground(new Color(52, 73, 94));
        cartTable.getTableHeader().setForeground(Color.WHITE);
        
        // Set column widths
        cartTable.getColumnModel().getColumn(0).setPreferredWidth(300); // Product
        cartTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Price
        cartTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Quantity
        cartTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Total
        cartTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Actions
        
        // Set custom renderers
        cartTable.getColumnModel().getColumn(0).setCellRenderer(new ProductCellRenderer());
        cartTable.getColumnModel().getColumn(1).setCellRenderer(new PriceCellRenderer());
        cartTable.getColumnModel().getColumn(3).setCellRenderer(new PriceCellRenderer());
        cartTable.getColumnModel().getColumn(4).setCellRenderer(new ActionCellRenderer());
        cartTable.getColumnModel().getColumn(4).setCellEditor(new ActionCellEditor());
    }
    
    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        actionPanel.setBackground(new Color(236, 240, 241));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Update quantity button
        updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.setFont(new Font("Arial", Font.BOLD, 12));
        updateQuantityButton.setBackground(new Color(52, 152, 219));
        updateQuantityButton.setForeground(Color.WHITE);
        updateQuantityButton.addActionListener(this);
        actionPanel.add(updateQuantityButton);
        
        // Remove selected button
        removeSelectedButton = new JButton("Remove Selected");
        removeSelectedButton.setFont(new Font("Arial", Font.BOLD, 12));
        removeSelectedButton.setBackground(new Color(231, 76, 60));
        removeSelectedButton.setForeground(Color.WHITE);
        removeSelectedButton.addActionListener(this);
        actionPanel.add(removeSelectedButton);
        
        // Clear cart button
        clearCartButton = new JButton("Clear Cart");
        clearCartButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearCartButton.setBackground(new Color(149, 165, 166));
        clearCartButton.setForeground(Color.WHITE);
        clearCartButton.addActionListener(this);
        actionPanel.add(clearCartButton);
        
        return actionPanel;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(52, 73, 94));
        footerPanel.setPreferredSize(new Dimension(1000, 100));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        // Summary panel
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        summaryPanel.setBackground(new Color(52, 73, 94));
        
        itemCountLabel = new JLabel("Items: 0");
        itemCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        itemCountLabel.setForeground(Color.WHITE);
        
        totalLabel = new JLabel("Total: ৳0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(46, 204, 113));
        
        summaryPanel.add(itemCountLabel);
        summaryPanel.add(totalLabel);
        
        // Action buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(52, 73, 94));
        
        continueShoppingButton = new JButton("Continue Shopping");
        continueShoppingButton.setFont(new Font("Arial", Font.BOLD, 12));
        continueShoppingButton.setBackground(new Color(155, 89, 182));
        continueShoppingButton.setForeground(Color.WHITE);
        continueShoppingButton.addActionListener(this);
        
        checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        checkoutButton.setBackground(new Color(46, 204, 113));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(this);
        
        buttonPanel.add(continueShoppingButton);
        buttonPanel.add(checkoutButton);
        
        footerPanel.add(summaryPanel, BorderLayout.WEST);
        footerPanel.add(buttonPanel, BorderLayout.EAST);
        
        return footerPanel;
    }
    
    private void loadCartItems() {
        cartItems.clear();
        
        // Load cart items from database
        Cart[] cartArray = cartController.searchCartByUserId(currentUserId);
        if (cartArray != null) {
            for (Cart cart : cartArray) {
                if (cart != null) {
                    cartItems.add(cart);
                }
            }
        }
        
        refreshCartTable();
    }
    
    private void refreshCartTable() {
        tableModel.setRowCount(0);
        
        for (Cart cartItem : cartItems) {
            Product product = productController.searchProduct(cartItem.getProductId());
            if (product != null) {
                double total = product.getPrice() * cartItem.getQuantity();
                Object[] row = {product, product.getPrice(), cartItem.getQuantity(), total, "Remove"};
                tableModel.addRow(row);
            }
        }
        
        updateSummary();
    }
    
    private void updateSummary() {
        int itemCount = cartItems.size();
        double totalAmount = getTotalAmount();
        
        itemCountLabel.setText("Items: " + itemCount);
        totalLabel.setText("Total: ৳" + String.format("%.2f", totalAmount));
        
        // Enable/disable buttons based on cart state
        boolean hasItems = itemCount > 0;
        checkoutButton.setEnabled(hasItems);
        clearCartButton.setEnabled(hasItems);
        removeSelectedButton.setEnabled(hasItems);
        updateQuantityButton.setEnabled(hasItems);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateQuantityButton) {
            updateSelectedItemQuantity();
        } else if (e.getSource() == removeSelectedButton) {
            removeSelectedItem();
        } else if (e.getSource() == clearCartButton) {
            clearCart();
        } else if (e.getSource() == continueShoppingButton) {
            dispose();
            parentGUI.setVisible(true);
        } else if (e.getSource() == checkoutButton) {
            proceedToCheckout();
        }
    }
    
    private void updateSelectedItemQuantity() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(this, 
                "Enter new quantity:", "Update Quantity", JOptionPane.QUESTION_MESSAGE));
            
            if (newQuantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Cart cartItem = cartItems.get(selectedRow);
            Product product = productController.searchProduct(cartItem.getProductId());
            
            if (newQuantity > product.getAvailableQuantity()) {
                JOptionPane.showMessageDialog(this, 
                    "Requested quantity (" + newQuantity + ") exceeds available quantity (" + product.getAvailableQuantity() + ").", 
                    "Insufficient Stock", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Update quantity in database
            cartItem.setQuantity(newQuantity);
            cartController.updateCart(cartItem);
            
            refreshCartTable();
            JOptionPane.showMessageDialog(this, "Quantity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeSelectedItem() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to remove this item from your cart?", 
            "Confirm Removal", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            Cart cartItem = cartItems.get(selectedRow);
            removeCartItem(cartItem);
        }
    }
    
    private void removeCartItem(Cart cartItem) {
        cartController.deleteCart(cartItem.getCartId());
        loadCartItems();
    }
    
    private void clearCart() {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to clear your entire cart?", 
            "Confirm Clear Cart", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            for (Cart cartItem : cartItems) {
                cartController.deleteCart(cartItem.getCartId());
            }
            loadCartItems();
            JOptionPane.showMessageDialog(this, "Cart cleared successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void proceedToCheckout() {
        // TODO: Implement checkout functionality
        JOptionPane.showMessageDialog(this, 
            "Checkout functionality will be implemented in the next phase.", 
            "Checkout", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private double getTotalAmount() {
        double total = 0.0;
        for (Cart cartItem : cartItems) {
            Product product = productController.searchProduct(cartItem.getProductId());
            if (product != null) {
                total += product.getPrice() * cartItem.getQuantity();
            }
        }
        return total;
    }
    
    // Custom cell renderers
    private class ProductCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Product) {
                Product product = (Product) value;
                JPanel panel = new JPanel(new BorderLayout(5, 0));
                panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                
                // Product image (placeholder)
                JLabel imageLabel = new JLabel("📦");
                imageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setPreferredSize(new Dimension(50, 50));
                
                // Product details
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
                detailsPanel.setBackground(panel.getBackground());
                
                JLabel nameLabel = new JLabel(product.getProductName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
                
                JLabel categoryLabel = new JLabel(product.getCategory() + " • " + product.getBrand());
                categoryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                categoryLabel.setForeground(Color.GRAY);
                
                detailsPanel.add(nameLabel);
                detailsPanel.add(categoryLabel);
                
                panel.add(imageLabel, BorderLayout.WEST);
                panel.add(detailsPanel, BorderLayout.CENTER);
                
                return panel;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    
    private class PriceCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Double) {
                setText("৳" + String.format("%.2f", (Double) value));
                setHorizontalAlignment(JLabel.RIGHT);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    
    private class ActionCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            
            JButton removeBtn = new JButton("🗑");
            removeBtn.setFont(new Font("Arial", Font.PLAIN, 12));
            removeBtn.setPreferredSize(new Dimension(25, 25));
            removeBtn.setToolTipText("Remove item");
            
            panel.add(removeBtn);
            return panel;
        }
    }
    
    private class ActionCellEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton removeButton;
        
        public ActionCellEditor() {
            super(new JTextField());
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
            
            removeButton = new JButton("🗑");
            removeButton.setFont(new Font("Arial", Font.PLAIN, 12));
            removeButton.setPreferredSize(new Dimension(25, 25));
            removeButton.setToolTipText("Remove item");
            
            removeButton.addActionListener(_ -> {
                int row = cartTable.getSelectedRow();
                if (row != -1) {
                    removeSelectedItem();
                }
            });
            
            panel.add(removeButton);
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CartGUI(null, "TEST_USER");
        });
    }
} 