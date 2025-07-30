package views.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class SalesReportGUI extends JFrame {

    private final JTextArea reportArea;

    private record Invoice(String invoiceId, String customerName, double totalAmount, LocalDate date) {}

    public SalesReportGUI(User user) {
        setTitle("Sales Report");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton dailyButton = new JButton("Daily Report");
        JButton weeklyButton = new JButton("Weekly Report");
        JButton monthlyButton = new JButton("Monthly Report");
        JButton yearlyButton = new JButton("Yearly Report");

        buttonPanel.add(dailyButton);
        buttonPanel.add(weeklyButton);
        buttonPanel.add(monthlyButton);
        buttonPanel.add(yearlyButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(reportArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        southPanel.add(backButton);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        dailyButton.addActionListener(_ -> generateReport("daily"));
        weeklyButton.addActionListener(_ -> generateReport("weekly"));
        monthlyButton.addActionListener(_ -> generateReport("monthly"));
        yearlyButton.addActionListener(_ -> generateReport("yearly"));

        backButton.addActionListener(_ -> dispose());
    }

    private List<Invoice> loadInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        File file = new File("controllers/data/InvoiceList.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Invoice data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return invoices;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        String invoiceId = parts[0];
                        String customerName = parts[1];
                        double totalAmount = Double.parseDouble(parts[2]);
                        LocalDate date = LocalDate.parse(parts[3], formatter);
                        invoices.add(new Invoice(invoiceId, customerName, totalAmount, date));
                    } catch (Exception e) {
                        // Ignore malformed lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading invoice data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return invoices;
    }

    private void generateReport(String period) {
        List<Invoice> invoices = loadInvoices();
        if (invoices.isEmpty() && !new File("controllers/data/InvoiceList.txt").exists()) {
            return;
        }

        List<Invoice> filteredInvoices = new ArrayList<>();
        LocalDate today = LocalDate.now();
        double totalSales = 0;
        String reportTitle = "";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        switch (period) {
            case "daily" -> {
                reportTitle = "Daily Sales Report for " + today.format(dateFormatter);
                for (Invoice invoice : invoices) {
                    if (invoice.date().isEqual(today)) {
                        filteredInvoices.add(invoice);
                        totalSales += invoice.totalAmount();
                    }
                }
            }
            case "weekly" -> {
                LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                reportTitle = "Weekly Sales Report (" + startOfWeek.format(dateFormatter) + " to " + endOfWeek.format(dateFormatter) + ")";
                for (Invoice invoice : invoices) {
                    if (!invoice.date().isBefore(startOfWeek) && !invoice.date().isAfter(endOfWeek)) {
                        filteredInvoices.add(invoice);
                        totalSales += invoice.totalAmount();
                    }
                }
            }
            case "monthly" -> {
                reportTitle = "Monthly Sales Report for " + today.getMonth() + " " + today.getYear();
                for (Invoice invoice : invoices) {
                    if (invoice.date().getYear() == today.getYear() && invoice.date().getMonth() == today.getMonth()) {
                        filteredInvoices.add(invoice);
                        totalSales += invoice.totalAmount();
                    }
                }
            }
            case "yearly" -> {
                reportTitle = "Yearly Sales Report for " + today.getYear();
                for (Invoice invoice : invoices) {
                    if (invoice.date().getYear() == today.getYear()) {
                        filteredInvoices.add(invoice);
                        totalSales += invoice.totalAmount();
                    }
                }
            }
        }

        StringBuilder reportContent = new StringBuilder();
        reportContent.append("\n   ").append(reportTitle).append("\n\n");
        reportContent.append("   ======================================================================\n");
        reportContent.append(String.format("   %-22s | %-25s | %s\n", "Invoice ID", "Customer", "Amount (BDT)"));
        reportContent.append("   ----------------------------------------------------------------------\n");

        if (filteredInvoices.isEmpty()) {
            reportContent.append("\n    No sales data found for this period.\n");
        } else {
            for (Invoice invoice : filteredInvoices) {
                reportContent.append(String.format("   %-22s | %-25s | %.2f\n", invoice.invoiceId(), invoice.customerName(), invoice.totalAmount()));
            }
        }

        reportContent.append("   ======================================================================\n");
        reportContent.append(String.format("\n   Total Sales: %.2f BDT\n", totalSales));

        reportArea.setText(reportContent.toString());
        reportArea.setCaretPosition(0); 
    }
}
