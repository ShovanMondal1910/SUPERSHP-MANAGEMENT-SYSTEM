package views.Admin;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class RevenueReportGUI extends JFrame {
    private final JTextArea reportArea;

    private static class RevenueRecord {
        String revenueId;
        LocalDate date;
        double totalRevenue;
        RevenueRecord(String revenueId, LocalDate date, double totalRevenue) {
            this.revenueId = revenueId;
            this.date = date;
            this.totalRevenue = totalRevenue;
        }
    }

    public RevenueReportGUI(User user) {
        setTitle("Revenue Report");
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

    private List<RevenueRecord> loadRevenues() {
        List<RevenueRecord> revenues = new ArrayList<>();
        File file = new File("controllers/data/Revenue.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Revenue data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return revenues;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String revenueId = parts[0];
                        LocalDate date = LocalDate.parse(parts[1], formatter);
                        double totalRevenue = Double.parseDouble(parts[2]);
                        revenues.add(new RevenueRecord(revenueId, date, totalRevenue));
                    } catch (Exception e) {
                        // Ignore malformed lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading revenue data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return revenues;
    }

    private void generateReport(String period) {
        List<RevenueRecord> revenues = loadRevenues();
        if (revenues.isEmpty() && !(new File("controllers/data/Revenue.txt").exists())) {
            return;
        }

        List<RevenueRecord> filteredRevenues = new ArrayList<>();
        LocalDate today = LocalDate.now();
        double totalRevenue = 0;
        String reportTitle = "";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        switch (period) {
            case "daily" -> {
                reportTitle = "Daily Revenue Report for " + today.format(dateFormatter);
                for (RevenueRecord revenue : revenues) {
                    if (revenue.date.isEqual(today)) {
                        filteredRevenues.add(revenue);
                        totalRevenue += revenue.totalRevenue;
                    }
                }
            }
            case "weekly" -> {
                LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                reportTitle = "Weekly Revenue Report (" + startOfWeek.format(dateFormatter) + " to " + endOfWeek.format(dateFormatter) + ")";
                for (RevenueRecord revenue : revenues) {
                    if (!revenue.date.isBefore(startOfWeek) && !revenue.date.isAfter(endOfWeek)) {
                        filteredRevenues.add(revenue);
                        totalRevenue += revenue.totalRevenue;
                    }
                }
            }
            case "monthly" -> {
                reportTitle = "Monthly Revenue Report for " + today.getMonth() + " " + today.getYear();
                for (RevenueRecord revenue : revenues) {
                    if (revenue.date.getYear() == today.getYear() && revenue.date.getMonth() == today.getMonth()) {
                        filteredRevenues.add(revenue);
                        totalRevenue += revenue.totalRevenue;
                    }
                }
            }
            case "yearly" -> {
                reportTitle = "Yearly Revenue Report for " + today.getYear();
                for (RevenueRecord revenue : revenues) {
                    if (revenue.date.getYear() == today.getYear()) {
                        filteredRevenues.add(revenue);
                        totalRevenue += revenue.totalRevenue;
                    }
                }
            }
        }

        StringBuilder reportContent = new StringBuilder();
        reportContent.append("\n   ").append(reportTitle).append("\n\n");
        reportContent.append("   ===============================================================\n");
        reportContent.append(String.format("   %-18s | %-15s | %s\n", "Revenue ID", "Date", "Amount (BDT)"));
        reportContent.append("   ---------------------------------------------------------------\n");

        if (filteredRevenues.isEmpty()) {
            reportContent.append("\n    No revenue data found for this period.\n");
        } else {
            for (RevenueRecord revenue : filteredRevenues) {
                reportContent.append(String.format("   %-18s | %-15s | %.2f\n", revenue.revenueId, revenue.date.toString(), revenue.totalRevenue));
            }
        }

        reportContent.append("   ===============================================================\n");
        reportContent.append(String.format("\n   Total Revenue: %.2f BDT\n", totalRevenue));

        reportArea.setText(reportContent.toString());
        reportArea.setCaretPosition(0);
    }
}
