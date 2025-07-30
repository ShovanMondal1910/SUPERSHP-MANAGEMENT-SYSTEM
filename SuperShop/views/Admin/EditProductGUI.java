package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import models.Product;
import controllers.ProductController;

public class EditProductGUI extends JFrame implements ActionListener{
     
    private JLabel ProductIdLabel,productNameLabel,categoryLabel,brandLabel,priceLabel,vailableQuantityLabel,detailsLabel,imageLabel,statusLabel,unitLabel;
    private JTextField ProductIdField,productNameField,brandField,priceField,vailableQuantityField,detailsField,imageField;
    private JComboBox<String> categoryComboBox,unitComboBox,statusComboBox;
    private JButton searchButton,editButton,backButton;
    private JPanel panel;
    public EditProductGUI() {
        super("Edit Product");
        this.setSize(800, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.ProductIdLabel = new JLabel("Product ID: ");
        this.ProductIdLabel.setBounds(130, 50, 200, 30);
        this.panel.add(this.ProductIdLabel);

        this.ProductIdField = new JTextField();
        this.ProductIdField.setBounds(330, 50, 300, 30);
        this.panel.add(this.ProductIdField);

        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(640, 50, 90, 29);
        this.searchButton.addActionListener(this);
        this.panel.add(this.searchButton);

        
        this.productNameLabel = new JLabel("Product Name: ");
        this.productNameLabel.setBounds(130, 100, 200, 30);
        this.panel.add(this.productNameLabel);

        this.productNameField = new JTextField();
        this.productNameField.setBounds(330, 100, 300, 30);
        this.panel.add(this.productNameField);
        // Enter key moves to brand field (skipping categoryComboBox)
        this.productNameField.addActionListener(_ -> this.brandField.requestFocusInWindow());

        this.categoryLabel = new JLabel("Category: ");
        this.categoryLabel.setBounds(130, 150, 200, 30);
        this.panel.add(this.categoryLabel);

        this.categoryComboBox = new JComboBox<String>(new String[]{"Vegetables","Fruits","Grocery","Fish","Meat","Electronics", "Clothing", "Books", "Furniture", "Medicine", "Tea & Coffee","Beverages","Bakery","Dairy","Personal Care","Household","Pet Supplies","Baby","Office Supplies","Sports","Toys","Automotive"});
        this.categoryComboBox.setBounds(330, 150, 300, 30);
        this.panel.add(this.categoryComboBox);

        this.brandLabel = new JLabel("Brand: ");
        this.brandLabel.setBounds(130, 200, 200, 30);
        this.panel.add(this.brandLabel);

        this.brandField = new JTextField();
        this.brandField.setBounds(330, 200, 300, 30);
        this.panel.add(this.brandField);
        // Enter key moves to price field
        this.brandField.addActionListener(_ -> this.priceField.requestFocusInWindow());

        this.priceLabel = new JLabel("Price: ");
        this.priceLabel.setBounds(130, 250, 200, 30);
        this.panel.add(this.priceLabel);

        this.priceField = new JTextField();
        this.priceField.setBounds(330, 250, 300, 30);
        this.panel.add(this.priceField);
        // Enter key moves to available quantity field
        this.priceField.addActionListener(_ -> this.vailableQuantityField.requestFocusInWindow());

        this.vailableQuantityLabel = new JLabel("Available Quantity: ");
        this.vailableQuantityLabel.setBounds(130, 300, 200, 30);
        this.panel.add(this.vailableQuantityLabel);

        this.vailableQuantityField = new JTextField();
        this.vailableQuantityField.setBounds(330, 300, 300, 30);
        this.panel.add(this.vailableQuantityField);

        this.vailableQuantityField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void updateStatus() {
                String text = vailableQuantityField.getText();
                try {
                    int qty = Integer.parseInt(text);
                    if (qty <= 10 && qty > 0) {
                        statusComboBox.setSelectedItem("Limited");
                    }
                    else if(qty == 0){
                        statusComboBox.setSelectedItem("Out of Stock");
                    }
                    else{
                        statusComboBox.setSelectedItem("Available");
                    }
                } catch (NumberFormatException ex) {
                    // Ignore invalid input
                }
            }
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateStatus(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateStatus(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateStatus(); }
        });

        this.detailsLabel = new JLabel("Details: ");
        this.detailsLabel.setBounds(130, 350, 200, 30);
        this.panel.add(this.detailsLabel);

        this.detailsField = new JTextField();
        this.detailsField.setBounds(330, 350, 300, 30);
        this.panel.add(this.detailsField);
        // Enter key moves to image field (skipping statusComboBox)
        this.detailsField.addActionListener(_ -> this.imageField.requestFocusInWindow());

        this.imageLabel = new JLabel("Image: ");
        this.imageLabel.setBounds(130, 400, 200, 30);
        this.panel.add(this.imageLabel);

        this.imageField = new JTextField();
        this.imageField.setBounds(330, 400, 200, 30);
        this.panel.add(this.imageField);
        // Enter key triggers edit button (skipping unitComboBox)
        this.imageField.addActionListener(_ -> this.editButton.doClick());

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(540, 400, 90, 30);
        this.panel.add(browseButton);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                        int result = fileChooser.showOpenDialog(EditProductGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    imageField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        this.statusLabel = new JLabel("Status: ");
        this.statusLabel.setBounds(130, 450, 200, 30);
        this.panel.add(this.statusLabel);

        this.statusComboBox = new JComboBox<String>(new String[]{"Available", "Unavailable","Limited","Out of Stock"});
        this.statusComboBox.setBounds(330, 450, 300, 30);
        this.panel.add(this.statusComboBox);

        this.unitLabel = new JLabel("Unit: ");
        this.unitLabel.setBounds(130, 500, 200, 30);
        this.panel.add(this.unitLabel);

        this.unitComboBox = new JComboBox<String>(new String[]{"Piece", "Kilogram", "Liter", "Meter"});
        this.unitComboBox.setBounds(330, 500, 300, 30);
        this.panel.add(this.unitComboBox);

        this.editButton = new JButton("Save Changes");
        this.editButton.setBounds(130, 600, 200, 30);
        this.editButton.addActionListener(this); 
        this.panel.add(this.editButton);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(360, 600, 200, 30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchButton){
            String productId = ProductIdField.getText();
            ProductController pc = new ProductController();
            Product product = pc.searchProduct(productId);
            if(product != null){
                productNameField.setText(product.getProductName());
                categoryComboBox.setSelectedItem(product.getCategory());
                brandField.setText(product.getBrand());
                priceField.setText(String.valueOf(product.getPrice()));
                vailableQuantityField.setText(String.valueOf(product.getAvailableQuantity()));
                detailsField.setText(product.getDetails());
                imageField.setText(product.getImage());
                statusComboBox.setSelectedItem(product.getStatus());
                unitComboBox.setSelectedItem(product.getUnit());
            }
            else{
                JOptionPane.showMessageDialog(this, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == editButton){
            try {
                String productId = ProductIdField.getText();
                String productName = productNameField.getText();
                String category = (String) categoryComboBox.getSelectedItem();
                String brand = brandField.getText();
                double price = Double.parseDouble(priceField.getText());
                int availableQuantity = Integer.parseInt(vailableQuantityField.getText());
                String details = detailsField.getText();
                String image = imageField.getText();
                String status = (String) statusComboBox.getSelectedItem();
                String unit = (String) unitComboBox.getSelectedItem();

                Product updatedProduct = new Product(productId, productName, category, brand, price, availableQuantity, details, image, status, unit);
                ProductController pc = new ProductController();
                pc.updateProduct(updatedProduct);
                JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == backButton){
            dispose();
            new AdminGUI(null).setVisible(true);
        }
    }
    public static void main(String[] args) {
        EditProductGUI ep = new EditProductGUI();
        ep.setVisible(true);
    }
}