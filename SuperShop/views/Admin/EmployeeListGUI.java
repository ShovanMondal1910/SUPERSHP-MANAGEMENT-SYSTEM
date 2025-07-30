package views.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllers.FileIO;
import models.Employee;
import java.awt.image.BufferedImage;

public class EmployeeListGUI extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton backButton;
    private FileIO fileIO;
    
    public EmployeeListGUI() {
        fileIO = new FileIO();
        initializeComponents();
        loadEmployeeData();
    }
    
    private void initializeComponents() {
        setTitle("Employee List");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 248, 255));

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(180, 200, 240), 2)
        ));
        mainPanel.setBackground(new Color(255, 255, 255));

        // Create title label
        JLabel titleLabel = new JLabel("Employee List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(40, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Create table
        String[] columnNames = {"Photo", "Employee ID", "Name", "Gender", "Email", "Phone", "Address", "NID", "Designation", "Salary"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return ImageIcon.class;
                return String.class;
            }
        };

        employeeTable = new JTable(tableModel);
        employeeTable.setRowHeight(70);
        employeeTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        employeeTable.getTableHeader().setBackground(new Color(220, 230, 250));
        employeeTable.getTableHeader().setForeground(new Color(40, 70, 140));
        employeeTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        employeeTable.setSelectionBackground(new Color(200, 220, 255));
        employeeTable.setGridColor(new Color(220, 230, 250));
        employeeTable.setShowGrid(true);

        // Add scroll pane for table
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setPreferredSize(new Dimension(1100, 500));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 240), 1));

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(new Color(255, 255, 255));

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        refreshButton.setBackground(new Color(100, 180, 255));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setPreferredSize(new Dimension(140, 40));
        refreshButton.setBorder(BorderFactory.createLineBorder(new Color(100, 180, 255), 1));

        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(120, 140, 200));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(140, 40));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(120, 140, 200), 1));

        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadEmployeeData();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminGUI(null).setVisible(true);
            }
        });

        add(mainPanel);
    }
    
    private void loadEmployeeData() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Read employee data from file
        String[] employeeData = fileIO.readFile("controllers/data/Employees.txt");

        if (employeeData != null && employeeData.length > 0) {
            for (String data : employeeData) {
                if (data != null && !data.trim().isEmpty()) {
                    try {
                        Employee employee = new Employee().formEmployee(data);
                        // Load and scale photo
                        ImageIcon photoIcon = null;
                        String photoPath = employee.getPhotoPath();
                        if (photoPath != null && !photoPath.trim().isEmpty()) {
                            try {
                                ImageIcon rawIcon = new ImageIcon(photoPath);
                                Image img = rawIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                                photoIcon = new ImageIcon(img);
                            } catch (Exception ex) {
                                photoIcon = new ImageIcon(new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB));
                            }
                        } else {
                            photoIcon = new ImageIcon(new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB));
                        }
                        // Add employee data to table
                        Object[] rowData = {
                            photoIcon,
                            employee.getUserId(),
                            employee.getName(),
                            employee.getGender(),
                            employee.getEmail(),
                            employee.getPhoneNo(),
                            employee.getAddress(),
                            employee.getNID(),
                            employee.getDesignation(),
                            String.format("%.2f", employee.getSalary())
                        };
                        tableModel.addRow(rowData);
                    } catch (Exception e) {
                        System.err.println("Error parsing employee data: " + data);
                        e.printStackTrace();
                    }
                }
            }
        }
        // Show message if no employees found
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No employees found in the database.", "No Data", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        EmployeeListGUI employeeListGUI = new EmployeeListGUI();
        employeeListGUI.setVisible(true);
    }
}
