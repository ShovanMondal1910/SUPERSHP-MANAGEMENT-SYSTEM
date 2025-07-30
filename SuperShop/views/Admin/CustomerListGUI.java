package views.Admin;
    
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.*;

public class CustomerListGUI extends JFrame implements ActionListener, WindowListener{
    private JButton backButton;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JLabel titleLabel;

    public CustomerListGUI()
    {
        super("Customer List");
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);



        this.panel = new JPanel();
        this.panel.setBounds(0,0,1200,1000);
        this.panel.setLayout(null);
        this.panel.setBackground(new Color(245, 247, 250));

        // Title
        this.titleLabel = new JLabel("Customer Management System");
        this.titleLabel.setBounds(20, 20, 400, 40);
        this.titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        this.titleLabel.setForeground(new Color(52, 73, 94));
        this.panel.add(this.titleLabel);

        // Styled buttons
        this.backButton = new JButton("← Back");
        this.backButton.setBounds(1050,20,100,35);
        this.backButton.addActionListener(this);
        this.backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.backButton.setBackground(new Color(231, 76, 60));
        this.backButton.setForeground(Color.WHITE);
        this.backButton.setBorderPainted(false);
        this.backButton.setFocusPainted(false);
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.panel.add(this.backButton);



        // Table columns: Photo, ID, Name, Gender, Age, Email, Phone, Address, MembershipType
        String[] columns = {"Photo", "Customer ID", "Name", "Gender", "Age", "Email", "Phone", "Address", "Membership Type"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return ImageIcon.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadCustomerData(model);
        this.customerTable = new JTable(model);
        
        // Style the table
        this.customerTable.setRowHeight(60);
        this.customerTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        this.customerTable.setGridColor(new Color(230, 230, 230));
        this.customerTable.setSelectionBackground(new Color(52, 152, 219));
        this.customerTable.setSelectionForeground(Color.WHITE);
        this.customerTable.setShowGrid(true);
        this.customerTable.setIntercellSpacing(new Dimension(1, 1));
        
        // Style table header
        JTableHeader header = this.customerTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        
        // Column widths
        this.customerTable.getColumn("Photo").setMinWidth(80);
        this.customerTable.getColumn("Photo").setMaxWidth(80);
        this.customerTable.getColumn("Customer ID").setMinWidth(100);
        this.customerTable.getColumn("Customer ID").setMaxWidth(100);
        this.customerTable.getColumn("Name").setMinWidth(150);
        this.customerTable.getColumn("Gender").setMinWidth(80);
        this.customerTable.getColumn("Gender").setMaxWidth(80);
        this.customerTable.getColumn("Age").setMinWidth(60);
        this.customerTable.getColumn("Age").setMaxWidth(60);
        this.customerTable.getColumn("Email").setMinWidth(200);
        this.customerTable.getColumn("Phone").setMinWidth(120);
        this.customerTable.getColumn("Phone").setMaxWidth(120);
        this.customerTable.getColumn("Address").setMinWidth(200);
        this.customerTable.getColumn("Membership Type").setMinWidth(120);
        this.customerTable.getColumn("Membership Type").setMaxWidth(120);
        
        this.scrollPane = new JScrollPane(this.customerTable);
        this.scrollPane.setBounds(20,80,1160,875);
        this.scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        this.scrollPane.getViewport().setBackground(Color.WHITE);
        this.panel.add(this.scrollPane);

        this.add(this.panel);
        this.addWindowListener(this); // Listen for window events
        this.setVisible(true);
    }   

    private void loadCustomerData(DefaultTableModel model) {
        model.setRowCount(0); // Clear previous data
        try (BufferedReader br = new BufferedReader(new FileReader("controllers/data/Customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 11) {
                    // ID, Name, Gender, Age, Email, Phone, Address, SecurityAns, Password, MembershipType, PhotoPath
                    String id = parts[0];
                    String name = parts[1];
                    String gender = parts[2];
                    String age = parts[3];
                    String email = parts[4];
                    String phone = parts[5];
                    String address = parts[6];
                    String membership = parts[9];
                    String photoPath = parts[10];
                    ImageIcon photoIcon = getCustomerPhotoIconFromPath(photoPath);
                    model.addRow(new Object[]{photoIcon, id, name, gender, age, email, phone, address, membership});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ImageIcon getCustomerPhotoIconFromPath(String photoPath) {
        if (photoPath != null && !photoPath.trim().isEmpty()) {
            File f = new File(photoPath.trim());
            if (!f.isAbsolute()) {
                f = new File("controllers/data/Photos/" + photoPath.trim());
            }
            if (f.exists()) {
                return new ImageIcon(new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
            }
        }
        // Default photo if not found
        File defaultPhoto = new File("controllers/data/Photos/default.jpg");
        if (defaultPhoto.exists()) {
            return new ImageIcon(new ImageIcon(defaultPhoto.getAbsolutePath()).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        }
        // If no default, return empty icon
        return new ImageIcon(new java.awt.image.BufferedImage(50, 50, java.awt.image.BufferedImage.TYPE_INT_ARGB));
    }

    // WindowListener methods
    @Override
    public void windowActivated(WindowEvent e) {
        DefaultTableModel model = (DefaultTableModel) this.customerTable.getModel();
        loadCustomerData(model);
    }
    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosing(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == backButton){
          new AdminGUI(null).setVisible(true);
          dispose();
      }
    }
    public static void main(String[] args) {
        CustomerListGUI customerListGUI = new CustomerListGUI();
        customerListGUI.setVisible(true);
    }
}
