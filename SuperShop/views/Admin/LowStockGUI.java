package views.Admin;

import controllers.ProductController;
import models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LowStockGUI extends JFrame implements ActionListener {
    private JTextField quantityField;
    private JButton searchButton;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private JLabel titleLabel, quantityLabel;
    private JButton backButton;

    public LowStockGUI() {
        super("Low Stock Products");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(248, 249, 250));

        titleLabel = new JLabel("Low Stock Products");
        titleLabel.setBounds(0, 20, 900, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(231, 76, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        quantityLabel = new JLabel("Show products with quantity less than or equal to:");
        quantityLabel.setBounds(60, 80, 350, 30);
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(quantityLabel);

        quantityField = new JTextField("10");
        quantityField.setBounds(400, 80, 60, 30);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(quantityField);

        searchButton = new JButton("Search");
        searchButton.setBounds(480, 80, 100, 30);
        searchButton.setBackground(new Color(231, 76, 60));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(this);
        panel.add(searchButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(30, 20, 80, 30);
        backButton.setBackground(new Color(52, 73, 94));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        panel.add(backButton);

        // Table setup
        String[] columns = {"Product ID", "Name", "Category", "Brand", "Price", "Quantity", "Status", "Unit"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(tableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        productTable.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(40, 140, 800, 380);
        panel.add(scrollPane);

        this.add(panel);
        this.setVisible(true);
        loadLowStockProducts(10); // Default load
    }

    private void loadLowStockProducts(int maxQuantity) {
        tableModel.setRowCount(0);
        ProductController pc = new ProductController();
        Product[] products = pc.getAllProduct();
        for (Product p : products) {
            if (p != null && p.getAvailableQuantity() <= maxQuantity) {
                tableModel.addRow(new Object[]{
                        p.getProductId(),
                        p.getProductName(),
                        p.getCategory(),
                        p.getBrand(),
                        p.getPrice(),
                        p.getAvailableQuantity(),
                        p.getStatus(),
                        p.getUnit()
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                int qty = Integer.parseInt(quantityField.getText().trim());
                loadLowStockProducts(qty);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            AdminGUI adminGUI = new AdminGUI(null);
            adminGUI.setVisible(true);
            this.dispose();
        }
    }

    // For testing standalone
    public static void main(String[] args) {
        new LowStockGUI();
    }
}
