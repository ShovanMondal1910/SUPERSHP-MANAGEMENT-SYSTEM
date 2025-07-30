package views.Employee;

import javax.swing.*;
import java.awt.*;
import controllers.ProductController;
import models.Product;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class BuyForCustomerFrame extends JFrame {
    // Cart data and UI fields
    private final List<Object[]> cartData = new ArrayList<>();
    private final String[] columns = {"Product", "Qty", "Price", "VAT", "Discount", "Total"};
    private final JTable cartTable = new JTable(new Object[0][columns.length], columns);
    private final JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
    private final JLabel vatLabel = new JLabel("VAT: $0.00");
    private final JLabel discountLabel = new JLabel("Discount: $0.00");
    private final JLabel finalTotal = new JLabel("Final Total: $0.00");

    public BuyForCustomerFrame() {
        setTitle("Buy For Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Load products and categories
        ProductController productController = new ProductController();
        Product[] products = productController.getAllProduct();
        Set<String> categorySet = new LinkedHashSet<>();
        List<String> productList = new ArrayList<>();
        for (Product p : products) {
            if (p != null) {
                categorySet.add(p.getCategory());
                productList.add(p.getProductName());
            }
        }
        String[] categories = categorySet.toArray(new String[0]);
        String[] productNames = productList.toArray(new String[0]);

        // Selection Panel
        JPanel selectionPanel = new JPanel(new GridBagLayout());
        selectionPanel.setBorder(BorderFactory.createTitledBorder("Select Product Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        selectionPanel.add(new JLabel("Select Category:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> categoryComboBox = new JComboBox<>(categories.length > 0 ? categories : new String[]{"All Categories"});
        categoryComboBox.setPreferredSize(new Dimension(300, 30));
        categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        selectionPanel.add(categoryComboBox, gbc);
        gbc.gridx = 2;
        selectionPanel.add(new JLabel("Select Product:"), gbc);
        gbc.gridx = 3;
        JComboBox<String> productComboBox = new JComboBox<>(productNames.length > 0 ? productNames : new String[]{"Select a product"});
        productComboBox.setPreferredSize(new Dimension(300, 30));
        productComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        selectionPanel.add(productComboBox, gbc);

        // Update productComboBox when category changes
        categoryComboBox.addActionListener(_ -> {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            productComboBox.removeAllItems();
            for (Product p : products) {
                if (p != null && (selectedCategory.equals(p.getCategory()) || selectedCategory.equals("All Categories"))) {
                    productComboBox.addItem(p.getProductName());
                }
            }
            if (productComboBox.getItemCount() == 0) {
                productComboBox.addItem("Select a product");
            }
        });

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        selectionPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        quantitySpinner.setPreferredSize(new Dimension(250, 30));
        selectionPanel.add(quantitySpinner, gbc);
        gbc.gridx = 2;
        selectionPanel.add(new JLabel("VAT (%):"), gbc);
        gbc.gridx = 3;
        JSpinner vatSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
        vatSpinner.setPreferredSize(new Dimension(250, 30));
        selectionPanel.add(vatSpinner, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        selectionPanel.add(new JLabel("Discount (%):"), gbc);
        gbc.gridx = 1;
        JSpinner discountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        discountSpinner.setPreferredSize(new Dimension(250, 30));
        selectionPanel.add(discountSpinner, gbc);
        // Removed payment method label and combo box

        add(selectionPanel, BorderLayout.NORTH);

        // Actions Panel
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        Dimension smallBtn = new Dimension(150, 30);
        JButton btnAdd = new JButton("Add to Cart"); btnAdd.setPreferredSize(smallBtn);
        JButton btnCheckout = new JButton("Checkout"); btnCheckout.setPreferredSize(smallBtn);
        JButton btnClear = new JButton("Clear Cart"); btnClear.setPreferredSize(smallBtn);
        JButton btnRemove = new JButton("Remove Item"); btnRemove.setPreferredSize(smallBtn);
        JButton btnPreview = new JButton("Preview Receipt"); btnPreview.setPreferredSize(smallBtn);
        JButton btnBack = new JButton("Back"); btnBack.setPreferredSize(smallBtn);
        actionsPanel.add(btnAdd);
        actionsPanel.add(btnCheckout);
        actionsPanel.add(btnClear);
        actionsPanel.add(btnRemove);
        actionsPanel.add(btnPreview);
        actionsPanel.add(btnBack);
        add(actionsPanel, BorderLayout.CENTER);

        // Cart Table (Left)
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setPreferredSize(new Dimension(800, 600));
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        // Totals (Right)
        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
        totalsPanel.setBorder(BorderFactory.createTitledBorder("Totals"));
        totalsPanel.add(Box.createVerticalStrut(20));
        totalsPanel.add(subtotalLabel);
        totalsPanel.add(Box.createVerticalStrut(10));
        totalsPanel.add(vatLabel);
        totalsPanel.add(Box.createVerticalStrut(10));
        totalsPanel.add(discountLabel);
        totalsPanel.add(Box.createVerticalStrut(10));
        finalTotal.setFont(finalTotal.getFont().deriveFont(Font.BOLD, 18f));
        totalsPanel.add(finalTotal);
        totalsPanel.add(Box.createVerticalGlue());

        // Split Pane for Cart and Totals
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cartPanel, totalsPanel);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerLocation(950);
        splitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(splitPane, BorderLayout.SOUTH);

        // Add to Cart button logic
        btnAdd.addActionListener(_ -> {
            String selectedProduct = (String) productComboBox.getSelectedItem();
            if (selectedProduct == null || selectedProduct.equals("Select a product")) {
                JOptionPane.showMessageDialog(this, "Please select a product.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int quantity = (Integer) quantitySpinner.getValue();
            int vatPercent = (Integer) vatSpinner.getValue();
            int discountPercent = (Integer) discountSpinner.getValue();
            Product product = null;
            for (Product p : products) {
                if (p != null && p.getProductName().equals(selectedProduct)) {
                    product = p;
                    break;
                }
            }
            if (product == null) {
                JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double price = product.getPrice();
            // Check if product already in cart
            boolean found = false;
            for (Object[] row : cartData) {
                if (row[0].equals(selectedProduct)) {
                    // Update quantity and recalculate
                    int oldQty = Integer.parseInt(row[1].toString());
                    int newQty = oldQty + quantity;
                    double subtotal = price * newQty;
                    double vat = subtotal * vatPercent / 100.0;
                    double discount = subtotal * discountPercent / 100.0;
                    double total = subtotal + vat - discount;
                    row[1] = newQty;
                    row[2] = String.format("%.2f", price);
                    row[3] = String.format("%.2f", vat);
                    row[4] = String.format("%.2f", discount);
                    row[5] = String.format("%.2f", total);
                    found = true;
                    break;
                }
            }
            if (!found) {
                double subtotal = price * quantity;
                double vat = subtotal * vatPercent / 100.0;
                double discount = subtotal * discountPercent / 100.0;
                double total = subtotal + vat - discount;
                Object[] row = {selectedProduct, quantity, String.format("%.2f", price), String.format("%.2f", vat), String.format("%.2f", discount), String.format("%.2f", total)};
                cartData.add(row);
            }
            updateCartTableAndTotals();
        });

        btnClear.addActionListener(_ -> {
            cartData.clear();
            updateCartTableAndTotals();
        });

        btnRemove.addActionListener(_ -> {
            int selectedRow = cartTable.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < cartData.size()) {
                cartData.remove(selectedRow);
                updateCartTableAndTotals();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an item to remove from the cart.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPreview.addActionListener(_ -> {
            if (cartData.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart is empty.", "No Items", JOptionPane.WARNING_MESSAGE);
                return;
            }
            StringBuilder receipt = new StringBuilder();
            receipt.append("========= RECEIPT =========\n");
            receipt.append(String.format("%-20s %-6s %-8s %-8s %-10s %-10s\n", "Product", "Qty", "Price", "VAT", "Discount", "Total"));
            receipt.append("-------------------------------------------------------------\n");
            for (Object[] row : cartData) {
                receipt.append(String.format("%-20s %-6s %-8s %-8s %-10s %-10s\n",
                        row[0], row[1], row[2], row[3], row[4], row[5]));
            }
            receipt.append("-------------------------------------------------------------\n");
            receipt.append(subtotalLabel.getText()).append("\n");
            receipt.append(vatLabel.getText()).append("\n");
            receipt.append(discountLabel.getText()).append("\n");
            receipt.append(finalTotal.getText()).append("\n");
            receipt.append("===========================\n");
            JTextArea textArea = new JTextArea(receipt.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            JOptionPane.showMessageDialog(this, scrollPane, "Receipt Preview", JOptionPane.INFORMATION_MESSAGE);
        });

        btnCheckout.addActionListener(_ -> {
            // Prepare totals as strings
            String subtotal = subtotalLabel.getText();
            String vat = vatLabel.getText();
            String discount = discountLabel.getText();
            String finalTotalStr = finalTotal.getText();
            new CheckoutGUI(cartData, subtotal, vat, discount, finalTotalStr).setVisible(true);
            this.dispose();
        });

        btnBack.addActionListener(_ -> {
            new EmployeeGUI(null).setVisible(true);
            this.dispose();
        });
    }

    private void updateCartTableAndTotals() {
        // Update cart table
        Object[][] tableData = cartData.toArray(new Object[0][]);
        cartTable.setModel(new javax.swing.table.DefaultTableModel(tableData, columns));
        // Calculate totals
        double subtotal = 0, vat = 0, discount = 0, finalTotalVal = 0;
        for (Object[] row : cartData) {
            subtotal += Double.parseDouble(row[2].toString()) * Integer.parseInt(row[1].toString());
            vat += Double.parseDouble(row[3].toString());
            discount += Double.parseDouble(row[4].toString());
            finalTotalVal += Double.parseDouble(row[5].toString());
        }
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        vatLabel.setText(String.format("VAT: $%.2f", vat));
        discountLabel.setText(String.format("Discount: $%.2f", discount));
        finalTotal.setText(String.format("Final Total: $%.2f", finalTotalVal));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BuyForCustomerFrame().setVisible(true));
    }
}
