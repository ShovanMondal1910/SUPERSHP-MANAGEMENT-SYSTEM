package views.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import models.User;
import models.Employee;
import controllers.EmployeeController;

public class ExpenseReportGUI extends JFrame {
    public ExpenseReportGUI(User user) {
        super("Company Expense Report");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Company Expense Report (Salaries)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(231, 76, 60));
        add(titleLabel, BorderLayout.NORTH);

        // Table columns
        String[] columns = {"Employee ID", "Name", "Designation", "Salary"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Read employees and sum salaries
        double totalSalaries = 0.0;
        try {
            EmployeeController ec = new EmployeeController();
            Employee[] employees = ec.getAllEmployee();
            for (Employee emp : employees) {
                if (emp != null) {
                    model.addRow(new Object[]{emp.getUserId(), emp.getName(), emp.getDesignation(), String.format("%.2f", emp.getSalary())});
                    totalSalaries += emp.getSalary();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading Employees.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JLabel totalLabel = new JLabel("Total Salary Expense: " + String.format("%.2f", totalSalaries), SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(192, 57, 43));
        add(totalLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new ExpenseReportGUI(null).setVisible(true);
    }
}