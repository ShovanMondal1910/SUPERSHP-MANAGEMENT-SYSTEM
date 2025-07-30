package views.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import controllers.FileIO;


public class InvoiceListGUI extends JFrame implements ActionListener{
    private JTable invoiceTable;
    private JPanel panel;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private JButton refreshButton;

    public InvoiceListGUI()
    {
        super("Invoice List");
        this.setSize(1400, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        // Add title label
        JLabel titleLabel = new JLabel("Invoice List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(10, 10, 200, 30);
        this.panel.add(titleLabel);

        // Create table model with specified columns
        String[] columns = {"Invoice", "CustomerID", "Amount", "Date", "Time", "Total", "Discount", "Tax", "Net Payable", "Payment Method"};
        this.tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        this.invoiceTable = new JTable(tableModel);
        this.invoiceTable.setRowHeight(25);
        this.invoiceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        this.invoiceTable.setFont(new Font("Arial", Font.PLAIN, 11));
        
        // Set column widths
        this.invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(120); // Invoice
        this.invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(100); // CustomerID
        this.invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(80);  // Amount
        this.invoiceTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Date
        this.invoiceTable.getColumnModel().getColumn(4).setPreferredWidth(80);  // Time
        this.invoiceTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // Total
        this.invoiceTable.getColumnModel().getColumn(6).setPreferredWidth(80);  // Discount
        this.invoiceTable.getColumnModel().getColumn(7).setPreferredWidth(60);  // Tax
        this.invoiceTable.getColumnModel().getColumn(8).setPreferredWidth(100); // Net Payable
        this.invoiceTable.getColumnModel().getColumn(9).setPreferredWidth(120); // Payment Method

        this.scrollPane = new JScrollPane(this.invoiceTable);
        this.scrollPane.setBounds(10, 50, 1360, 660);
        this.panel.add(this.scrollPane);

        // Add buttons
        this.refreshButton = new JButton("Refresh");
        this.refreshButton.setBounds(10, 720, 100, 30);
        this.refreshButton.addActionListener(this);
        this.panel.add(this.refreshButton);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(120, 720, 100, 30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        this.add(this.panel);
        
        // Load invoice data
        loadInvoiceData();
    }

    private void loadInvoiceData() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        try {
            FileIO fio = new FileIO();
            String[] invoiceLines = fio.readFile("controllers/data/InvoiceList.txt");
            
            if (invoiceLines != null) {
                for (String line : invoiceLines) {
                    if (line != null && !line.trim().isEmpty()) {
                        String[] data = line.split(",");
                        if (data.length >= 8) {
                            // Parse invoice data: invoiceNo, date, customerId, product, qty, price, total, payment method
                            String invoiceNo = data[0];
                            String date = data[1];
                            String customerId = data[2];
                            int qty = Integer.parseInt(data[4]);
                            double price = Double.parseDouble(data[5]);
                            double total = Double.parseDouble(data[6]);
                            String paymentMethod = data[7];
                            
                            // Calculate derived values
                            double amount = price * qty;
                            double discount = amount * 0.05; // 5% discount
                            double tax = amount * 0.15; // 15% VAT
                            double netPayable = amount - discount + tax;
                            
                            // Extract time from date (assuming format includes time)
                            String time = "12:00"; // Default time if not available
                            if (date.contains(" ")) {
                                String[] dateTime = date.split(" ");
                                if (dateTime.length > 1) {
                                    time = dateTime[1];
                                }
                            }
                            
                            // Add row to table
                            Object[] row = {
                                invoiceNo,
                                customerId,
                                String.format("%.2f", amount),
                                date.split(" ")[0], // Date only
                                time,
                                String.format("%.2f", total),
                                String.format("%.2f", discount),
                                String.format("%.2f", tax),
                                String.format("%.2f", netPayable),
                                paymentMethod
                            };
                            tableModel.addRow(row);
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading invoice data: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.refreshButton) {
            loadInvoiceData();
        } else if (e.getSource() == this.backButton) {
            this.dispose();
            new AdminGUI(null).setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        InvoiceListGUI invoiceListGUI = new InvoiceListGUI();
        invoiceListGUI.setVisible(true);
    }
}
