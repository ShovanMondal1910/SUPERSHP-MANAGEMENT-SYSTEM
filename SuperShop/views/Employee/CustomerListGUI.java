package views.Employee;
    
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class CustomerListGUI extends JFrame implements ActionListener, WindowListener{
    private JButton backButton;
    private JButton refreshButton;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private JPanel panel;

    public CustomerListGUI()
    {
        super("Customer List");
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setBounds(0,0,1200,1000);
        this.panel.setLayout(null); 

        this.backButton = new JButton("Back");
        this.backButton.setBounds(1050,20,100,30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        this.refreshButton = new JButton("Refresh");
        this.refreshButton.setBounds(930,20,100,30);
        this.refreshButton.addActionListener(this);
        this.panel.add(this.refreshButton);

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
        this.customerTable.setRowHeight(50);
        this.customerTable.getColumn("Photo").setMinWidth(60);
        this.customerTable.getColumn("Photo").setMaxWidth(60);
        this.scrollPane = new JScrollPane(this.customerTable);
        this.scrollPane.setBounds(10,70,1170,885);
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
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
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
      if(e.getSource() == refreshButton){
          DefaultTableModel model = (DefaultTableModel) this.customerTable.getModel();
          loadCustomerData(model);
      } else if(e.getSource() == backButton){
          new EmployeeGUI(null).setVisible(true);
          dispose();
      }
    }
    public static void main(String[] args) {
        CustomerListGUI customerListGUI = new CustomerListGUI();
        customerListGUI.setVisible(true);
    }
}
