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

public class ProfitReportGUI extends JFrame {
    private final JTextArea reportArea;

    private static class ProfitRecord {
        String profitId;
        LocalDate date;
        double totalProfit;
        ProfitRecord(String profitId, LocalDate date, double totalProfit) {
            this.profitId = profitId;
            this.date = date;
            this.totalProfit = totalProfit;
        }
    }

    public ProfitReportGUI(User user) {
        setTitle("Profit Report");
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

    private List<ProfitRecord> loadProfits() {
        List<ProfitRecord> profits = new ArrayList<>();
        File file = new File("controllers/data/Profit.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Profit data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return profits;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String profitId = parts[0];
                        LocalDate date = LocalDate.parse(parts[1], formatter);
                        double totalProfit = Double.parseDouble(parts[2]);
                        profits.add(new ProfitRecord(profitId, date, totalProfit));
                    } catch (Exception e) {
                        // Ignore malformed lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading profit data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return profits;
    }

    private void generateReport(String period) {
        List<ProfitRecord> profits = loadProfits();
        if (profits.isEmpty() && !(new File("controllers/data/Profit.txt").exists())) {
            return;
        }

        List<ProfitRecord> filteredProfits = new ArrayList<>();
        LocalDate today = LocalDate.now();
        double totalProfit = 0;
        String reportTitle = "";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        switch (period) {
            case "daily" -> {
                reportTitle = "Daily Profit Report for " + today.format(dateFormatter);
                for (ProfitRecord profit : profits) {
                    if (profit.date.isEqual(today)) {
                        filteredProfits.add(profit);
                        totalProfit += profit.totalProfit;
                    }
                }
            }
            case "weekly" -> {
                LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                reportTitle = "Weekly Profit Report (" + startOfWeek.format(dateFormatter) + " to " + endOfWeek.format(dateFormatter) + ")";
                for (ProfitRecord profit : profits) {
                    if (!profit.date.isBefore(startOfWeek) && !profit.date.isAfter(endOfWeek)) {
                        filteredProfits.add(profit);
                        totalProfit += profit.totalProfit;
                    }
                }
            }
            case "monthly" -> {
                reportTitle = "Monthly Profit Report for " + today.getMonth() + " " + today.getYear();
                for (ProfitRecord profit : profits) {
                    if (profit.date.getYear() == today.getYear() && profit.date.getMonth() == today.getMonth()) {
                        filteredProfits.add(profit);
                        totalProfit += profit.totalProfit;
                    }
                }
            }
            case "yearly" -> {
                reportTitle = "Yearly Profit Report for " + today.getYear();
                for (ProfitRecord profit : profits) {
                    if (profit.date.getYear() == today.getYear()) {
                        filteredProfits.add(profit);
                        totalProfit += profit.totalProfit;
                    }
                }
            }
        }

        StringBuilder reportContent = new StringBuilder();
        reportContent.append("\n   ").append(reportTitle).append("\n\n");
        reportContent.append("   ===============================================================\n");
        reportContent.append(String.format("   %-18s | %-15s | %s\n", "Profit ID", "Date", "Amount (BDT)"));
        reportContent.append("   ---------------------------------------------------------------\n");

        if (filteredProfits.isEmpty()) {
            reportContent.append("\n    No profit data found for this period.\n");
        } else {
            for (ProfitRecord profit : filteredProfits) {
                reportContent.append(String.format("   %-18s | %-15s | %.2f\n", profit.profitId, profit.date.toString(), profit.totalProfit));
            }
        }

        reportContent.append("   ===============================================================\n");
        reportContent.append(String.format("\n   Total Profit: %.2f BDT\n", totalProfit));

        reportArea.setText(reportContent.toString());
        reportArea.setCaretPosition(0);
    }
}
