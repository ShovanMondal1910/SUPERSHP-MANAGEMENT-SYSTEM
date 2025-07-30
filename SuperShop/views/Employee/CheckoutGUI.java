package views.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllers.CustomerController;
import models.Customer;
import controllers.FileIO;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import controllers.ProductController;
import models.Product;

public class CheckoutGUI extends JFrame implements ActionListener {
   private JLabel customerLabel,CustomerIDLabel,CustomerNameLabel,CustomerPhoneLabel,CustomerEmailLabel,PaymentMethodLabel,PaymentByLabel;
   private JTextField customerField,CustomerIDField,CustomerNameField,CustomerPhoneField,CustomerEmailField,PaymentByField;
   private JButton searchButton,addCustomerButton,PrintReceiptButton,BackButton;
   private JComboBox<String> paymentMethodComboBox;
   private JTextArea receiptTextArea;

   private JPanel panel;
   private java.util.List<Object[]> cartData;
   private String subtotalLabelText, vatLabelText, discountLabelText, finalTotalText;

    public CheckoutGUI() {
        super("Checkout");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1200, 1000);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        this.panel = new JPanel();
        this.panel.setLayout(null);
        
        this.customerLabel = new JLabel("Search Customer(Customer ID):");
        this.customerLabel.setBounds(100, 50, 200, 30);
        this.panel.add(this.customerLabel);

        this.customerField = new JTextField(20);
        this.customerField.setBounds(300, 50, 200, 30);
        this.panel.add(this.customerField);

        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(530, 50, 100, 30);
        this.searchButton.addActionListener(this);
        this.panel.add(this.searchButton);

        this.addCustomerButton = new JButton("Add Customer");
        this.addCustomerButton.setBounds(650, 50, 200, 30);
        this.addCustomerButton.addActionListener(this);
        this.panel.add(this.addCustomerButton);

        this.CustomerIDLabel = new JLabel("Customer ID:");
        this.CustomerIDLabel.setBounds(100, 100, 200, 30);
        this.panel.add(this.CustomerIDLabel);

        this.CustomerIDField = new JTextField(20);
        this.CustomerIDField.setBounds(300, 100, 200, 30);
        this.panel.add(this.CustomerIDField);

        this.CustomerNameLabel = new JLabel("Customer Name:");
        this.CustomerNameLabel.setBounds(100, 150, 200, 30);
        this.panel.add(this.CustomerNameLabel);

        this.CustomerNameField = new JTextField(20);
        this.CustomerNameField.setBounds(300, 150, 200, 30);
        this.panel.add(this.CustomerNameField);

        this.CustomerPhoneLabel = new JLabel("Customer Phone:");
        this.CustomerPhoneLabel.setBounds(100, 200, 200, 30);
        this.panel.add(this.CustomerPhoneLabel);

        this.CustomerPhoneField = new JTextField(20);
        this.CustomerPhoneField.setBounds(300, 200, 200, 30);
        this.panel.add(this.CustomerPhoneField);

        this.CustomerEmailLabel = new JLabel("Customer Email:");
        this.CustomerEmailLabel.setBounds(100, 250, 200, 30);
        this.panel.add(this.CustomerEmailLabel);

        this.CustomerEmailField = new JTextField(20);
        this.CustomerEmailField.setBounds(300, 250, 200, 30);
        this.panel.add(this.CustomerEmailField);

        this.PaymentMethodLabel = new JLabel("Payment Method:");
        this.PaymentMethodLabel.setBounds(100, 300, 200, 30);
        this.panel.add(this.PaymentMethodLabel);

        this.paymentMethodComboBox = new JComboBox<>(new String[]{"Cash", "Card", "Mobile Payment"});
        this.paymentMethodComboBox.setBounds(300, 300, 200, 30);
        this.panel.add(this.paymentMethodComboBox);

        this.PaymentByLabel = new JLabel("Payment By(Card/Mobile):");
        this.PaymentByLabel.setBounds(100, 350, 200, 30);
        this.panel.add(this.PaymentByLabel);

        this.PaymentByField = new JTextField(20);
        this.PaymentByField.setBounds(270, 350, 300, 30);
        this.panel.add(this.PaymentByField);

        this.receiptTextArea = new JTextArea(10, 50);
        this.receiptTextArea.setBounds(40, 400, 1100, 450);
        this.panel.add(this.receiptTextArea);


        this.PrintReceiptButton = new JButton("Print Receipt");
        this.PrintReceiptButton.setBounds(730, 870, 200, 30);
        this.PrintReceiptButton.addActionListener(this);
        this.panel.add(this.PrintReceiptButton);

        this.BackButton = new JButton("Back");
        this.BackButton.setBounds(960, 870, 200, 30);
        this.BackButton.addActionListener(this);
        this.panel.add(this.BackButton);

        add(this.panel);
        this.setVisible(true);
    }

    public CheckoutGUI(java.util.List<Object[]> cartData, String subtotal, String vat, String discount, String finalTotal) {
        this();
        this.cartData = cartData;
        this.subtotalLabelText = subtotal;
        this.vatLabelText = vat;
        this.discountLabelText = discount;
        this.finalTotalText = finalTotal;
        // Build receipt string
        StringBuilder receipt = new StringBuilder();
        receipt.append("========= RECEIPT =========\n");
        receipt.append(String.format("%-20s %-6s %-8s %-8s %-10s %-10s\n", "Product", "Qty", "Price", "VAT", "Discount", "Total"));
        receipt.append("-------------------------------------------------------------\n");
        for (Object[] row : cartData) {
            receipt.append(String.format("%-20s %-6s %-8s %-8s %-10s %-10s\n",
                    row[0], row[1], row[2], row[3], row[4], row[5]));
        }
        receipt.append("-------------------------------------------------------------\n");
        receipt.append(subtotal).append("\n");
        receipt.append(vat).append("\n");
        receipt.append(discount).append("\n");
        receipt.append(finalTotal).append("\n");
        receipt.append("===========================\n");
        this.receiptTextArea.setText(receipt.toString());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            String customer = this.customerField.getText().trim();
            if (customer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a customerID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CustomerController customerController = new CustomerController();
            Customer[] customers = customerController.getAllCustomer();
            Customer found = null;
            for (Customer c : customers) {
                if (c != null && (
                        c.getUserId().equalsIgnoreCase(customer) ||
                        c.getName().equalsIgnoreCase(customer) ||
                        c.getPhoneNo().equals(customer)
                )) {
                    found = c;
                    break;
                }
            }
            if (found != null) {
                CustomerIDField.setText(found.getUserId());
                CustomerNameField.setText(found.getName());
                CustomerPhoneField.setText(found.getPhoneNo());
                CustomerEmailField.setText(found.getEmail());
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource() == this.addCustomerButton) {
           this.dispose();
           new AddCustomerGUI().setVisible(true); 
        }
        else if (e.getSource() == this.PrintReceiptButton) {
            String selected = (String) paymentMethodComboBox.getSelectedItem();
            String paymentBy = PaymentByField.getText().trim();
            if ("Card".equals(selected) && paymentBy.length() != 16) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 16-digit card number.", "Invalid Card Number", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if ("Mobile Payment".equals(selected) && paymentBy.length() != 11) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 11-digit phone number.", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Generate unique 12-digit invoice number
            String invoiceNumber = String.format("%012d", Math.abs(new Random().nextLong()) % 1000000000000L);
            // Get customer info
            String customerId = CustomerIDField.getText().trim();
            String customerName = CustomerNameField.getText().trim();
            String customerPhone = CustomerPhoneField.getText().trim();
            String customerEmail = CustomerEmailField.getText().trim();
            // Get date
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // Prepare receipt
            StringBuilder receipt = new StringBuilder();
            receipt.append("========= RECEIPT =========\n");
            receipt.append("Invoice No: ").append(invoiceNumber).append("\n");
            receipt.append("Customer ID: ").append(customerId).append("\n");
            receipt.append("Customer Name: ").append(customerName).append("\n");
            receipt.append("Phone: ").append(customerPhone).append("\n");
            receipt.append("Email: ").append(customerEmail).append("\n");
            receipt.append("Payment Method: ").append(selected).append("\n");
            receipt.append("---------------------------\n");
            receipt.append(String.format("%-20s %-6s %-8s %-8s %-10s %-10s\n", "Product", "Qty", "Price", "VAT", "Discount", "Total"));
            receipt.append("-------------------------------------------------------------\n");
            double totalProfit = 0, totalRevenue = 0;
            StringBuilder invoiceData = new StringBuilder();
            for (Object[] row : cartData) {
                receipt.append(String.format("%-20s %-6s %-8s %-8s %-10s %-10s\n",
                        row[0], row[1], row[2], row[3], row[4], row[5]));
                double price = Double.parseDouble(row[2].toString());
                int qty = Integer.parseInt(row[1].toString());
                //double twentyPercent = price * qty * 0.20;
                totalProfit += price * qty * 0.10;
                totalRevenue += price * qty * 0.10;
                // Save invoice line: invoiceNo, date, customerId, product, qty, price, total, payment method
                invoiceData.append(invoiceNumber).append(",").append(date).append(",")
                    .append(customerId).append(",").append(row[0]).append(",")
                    .append(qty).append(",").append(price).append(",")
                    .append(row[5]).append(",").append(selected).append("\n");
            }
            receipt.append("-------------------------------------------------------------\n");
            receipt.append(subtotalLabelText).append("\n");
            receipt.append(vatLabelText).append("\n");
            receipt.append(discountLabelText).append("\n");
            receipt.append(finalTotalText).append("\n");
            receipt.append("===========================\n");
            // Show receipt
            JTextArea textArea = new JTextArea(receipt.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            JOptionPane.showMessageDialog(this, scrollPane, "Receipt", JOptionPane.INFORMATION_MESSAGE);
            // Save profit and revenue and invoice using dynamic lists
            FileIO fio = new FileIO();
            java.util.List<String> profitList = new java.util.ArrayList<>();
            for (String line : fio.readFile("controllers/data/Profit.txt")) {
                if (line != null && !line.trim().isEmpty()) profitList.add(line);
            }
            profitList.add(invoiceNumber + "," + date + "," + totalProfit);
            fio.writeFile("controllers/data/Profit.txt", profitList.toArray(new String[0]));

            java.util.List<String> revenueList = new java.util.ArrayList<>();
            for (String line : fio.readFile("controllers/data/Revenue.txt")) {
                if (line != null && !line.trim().isEmpty()) revenueList.add(line);
            }
            revenueList.add(invoiceNumber + "," + date + "," + totalRevenue);
            fio.writeFile("controllers/data/Revenue.txt", revenueList.toArray(new String[0]));

            java.util.List<String> invoiceList = new java.util.ArrayList<>();
            for (String line : fio.readFile("controllers/data/InvoiceList.txt")) {
                if (line != null && !line.trim().isEmpty()) invoiceList.add(line);
            }
            invoiceList.add(invoiceData.toString().trim());
            fio.writeFile("controllers/data/InvoiceList.txt", invoiceList.toArray(new String[0]));
            JOptionPane.showMessageDialog(this, "Print done!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Update product available quantities
            ProductController productController = new ProductController();
            Product[] allProducts = productController.getAllProduct();
            for (Object[] row : cartData) {
                String productName = row[0].toString();
                int qtySold = Integer.parseInt(row[1].toString());
                for (Product prod : allProducts) {
                    if (prod != null && prod.getProductName().equals(productName)) {
                        int newQty = prod.getAvailableQuantity() - qtySold;
                        prod.setAvailableQuantity(Math.max(newQty, 0));
                        break;
                    }
                }
            }
            productController.write(allProducts);
        }
        else if (e.getSource() == this.BackButton) {
           this.dispose();
           new BuyForCustomerFrame().setVisible(true);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CheckoutGUI().setVisible(true));
    }
}