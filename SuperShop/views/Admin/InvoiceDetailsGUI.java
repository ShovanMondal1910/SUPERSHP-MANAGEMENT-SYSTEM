package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class InvoiceDetailsGUI extends JFrame implements ActionListener{
    private JLabel invoiceLabel;
    private JTextField invoiceField;
    private JButton backButton;
    private JButton searchButton;
    private JPanel panel;
    
    // Results display components
    private JTextArea resultArea;
    private JScrollPane scrollPane;
   // private JLabel customerInfoLabel;
   // private JLabel paymentInfoLabel;
    private JLabel productsLabel;
    
    public InvoiceDetailsGUI()
    {
        super("Invoice Details");
        this.setSize(800, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.invoiceLabel = new JLabel("Invoice ID: ");
        this.invoiceLabel.setBounds(30, 10, 100, 30);
        this.panel.add(this.invoiceLabel);

        this.invoiceField = new JTextField();
        this.invoiceField.setBounds(120, 10, 300, 30);
        this.panel.add(this.invoiceField);

        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(450, 10, 100, 30);
        this.searchButton.addActionListener(this);
        this.panel.add(this.searchButton);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(650, 10, 100, 30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        // Products Label
        this.productsLabel = new JLabel("Products Purchased:");
        this.productsLabel.setBounds(30, 40, 200, 30);
        this.productsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.panel.add(this.productsLabel);

        // Results area
        this.resultArea = new JTextArea();
        this.resultArea.setEditable(false);
        this.resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.scrollPane = new JScrollPane(this.resultArea);
        this.scrollPane.setBounds(30, 70, 720, 750);
        this.panel.add(this.scrollPane);

        this.add(this.panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            searchInvoice();
        } else if (e.getSource() == this.backButton) {
            this.dispose();
            new AdminGUI(null).setVisible(true);
        }
    }

    private void searchInvoice() {
        String invoiceId = this.invoiceField.getText().trim();
        if (invoiceId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Invoice ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Search for all invoice entries with this ID
        List<String> invoiceEntries = searchAllInvoiceEntries(invoiceId);
        if (invoiceEntries.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invoice not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Parse first invoice entry for basic info
        String[] firstEntry = invoiceEntries.get(0).split(",");
        String date = firstEntry[1];
        String customerId = firstEntry[2];
        String paymentMethod = firstEntry[7];

        // Get customer information
        String customerInfo = getCustomerInfo(customerId);
        
        // Get all products information
        List<String> productsInfo = getAllProductsInfo(invoiceEntries);

        // Display results
        displayResults(invoiceId, date, customerInfo, paymentMethod, productsInfo);
    }

    private List<String> searchAllInvoiceEntries(String invoiceId) {
        List<String> entries = new ArrayList<>();
        try {
            File file = new File("controllers/data/InvoiceList.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                if (line.startsWith(invoiceId + ",")) {
                    entries.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return entries;
    }

    private String getCustomerInfo(String customerId) {
        try {
            File file = new File("controllers/data/Customers.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length >= 10) {
                    // Try exact match first
                    if (parts[0].equals(customerId)) {
                        scanner.close();
                        return "ID: " + parts[0] + "\nName: " + parts[1] + "\nGender: " + parts[2] + 
                               "\nAge: " + parts[3] + "\nEmail: " + parts[4] + "\nPhone: " + parts[5] + 
                               "\nAddress: " + parts[6] + "\nMembership: " + parts[9];
                    }
                    // Try partial match for different ID formats
                    String customerIdShort = customerId.replaceAll("^0+", ""); // Remove leading zeros
                    String partsIdShort = parts[0].replaceAll("^0+", ""); // Remove leading zeros
                    if (customerIdShort.equals(partsIdShort) || parts[0].contains(customerId) || customerId.contains(parts[0])) {
                        scanner.close();
                        return "ID: " + parts[0] + "\nName: " + parts[1] + "\nGender: " + parts[2] + 
                               "\nAge: " + parts[3] + "\nEmail: " + parts[4] + "\nPhone: " + parts[5] + 
                               "\nAddress: " + parts[6] + "\nMembership: " + parts[9];
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "Customer not found";
    }

    private List<String> getAllProductsInfo(List<String> invoiceEntries) {
        List<String> productsInfo = new ArrayList<>();
        
        for (String entry : invoiceEntries) {
            String[] parts = entry.split(",");
            if (parts.length >= 8) {
                String productName = parts[3];
                String quantity = parts[4];
                String price = parts[5];
                String total = parts[6];
                
                String productInfo = getProductInfo(productName);
                productsInfo.add("Product: " + productName + "\nQuantity: " + quantity + 
                               "\nUnit Price: $" + price + "\nTotal: $" + total + "\n" + productInfo + "\n");
            }
        }
        
        return productsInfo;
    }

    private String getProductInfo(String productName) {
        try {
            File file = new File("controllers/data/Products.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length >= 10 && parts[1].equals(productName)) {
                    scanner.close();
                    return "ID: " + parts[0] + "\nName: " + parts[1] + "\nCategory: " + parts[2] + 
                           "\nBrand: " + parts[3] + "\nPrice: " + parts[4] + "\nUnit: " + parts[9];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "Product not found";
    }

    private void displayResults(String invoiceId, String date, String customerInfo, String paymentMethod, 
                              List<String> productsInfo) {
        StringBuilder result = new StringBuilder();
        result.append("        INVOICE DETAILS\n");
        result.append("===============\n");
        result.append("Invoice ID: ").append(invoiceId).append("\n");
        result.append("Date: ").append(date).append("\n");
        
        result.append("CUSTOMER INFORMATION\n");
        result.append("====================\n");
        result.append(customerInfo).append("\n\n");
        
        result.append("PAYMENT INFORMATION\n");
        result.append("===================\n");
        result.append("Payment Method: ").append(paymentMethod).append("\n\n");
        
        result.append("PRODUCTS PURCHASED\n");
        result.append("==================\n");
        
        double subtotal = 0.0;
        for (int i = 0; i < productsInfo.size(); i++) {
            result.append("Product ").append(i + 1).append(":\n");
            result.append("----------------\n");
            result.append(productsInfo.get(i));
            
            // Extract total from the product info
            String[] lines = productsInfo.get(i).split("\n");
            for (String line : lines) {
                if (line.startsWith("Total: $")) {
                    try {
                        subtotal += Double.parseDouble(line.substring(8));
                    } catch (NumberFormatException e) {
                        // Ignore parsing errors
                    }
                    break;
                }
            }
        }
        
        // Calculate pricing details
        double taxRate = 0.15; // 15% tax rate
        double taxAmount = subtotal * taxRate;
        double discount = 0.0; // No discount by default
        double grandTotal = subtotal + taxAmount - discount;
        
        result.append("\nPRICING DETAILS\n");
        result.append("===============\n");
        result.append("Subtotal:                    $").append(String.format("%.2f", subtotal)).append("\n");
        result.append("Tax (").append((int)(taxRate * 100)).append("%):                    $").append(String.format("%.2f", taxAmount)).append("\n");
        result.append("Discount:                    $").append(String.format("%.2f", discount)).append("\n");
        result.append("----------------------------------------\n");
        result.append("GRAND TOTAL:                 $").append(String.format("%.2f", grandTotal)).append("\n");
        
        this.resultArea.setText(result.toString());
    }

    public static void main(String[] args) {
        InvoiceDetailsGUI invoiceDetailsGUI = new InvoiceDetailsGUI();
        invoiceDetailsGUI.setVisible(true);
    }
}
