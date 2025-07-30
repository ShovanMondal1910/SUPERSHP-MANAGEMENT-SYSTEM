package views.Employee;

import javax.swing.*;
import java.awt.event.*;
import models.User;
import views.*;


public class EmployeeGUI extends JFrame implements ActionListener {
    private JButton ProductListButton,BuyForCustomerButton,CustomerListButton,removeCustomerButton;
    private JButton addProductButton,removeProductButton,editProductButton;
    private JButton logoutButton;
    private JPanel panel;
    public EmployeeGUI(User user) {
        super("Employee");
        this.setSize(1200, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();  
        this.panel.setLayout(null);

        this.ProductListButton = new JButton("Product List");
        this.ProductListButton.setBounds(50, 100, 200, 40);
        this.panel.add(this.ProductListButton);
        this.ProductListButton.addActionListener(this);

        this.addProductButton = new JButton("Add Product");
        this.addProductButton.setBounds(50, 150, 200, 40);
        this.panel.add(this.addProductButton);
        this.addProductButton.addActionListener(this);

        this.CustomerListButton = new JButton("Customer List");
        this.CustomerListButton.setBounds(50, 200, 200, 40);
        this.panel.add(this.CustomerListButton);
        this.CustomerListButton.addActionListener(this);

        this.removeCustomerButton = new JButton("Remove Customer");
        this.removeCustomerButton.setBounds(50, 250, 200, 40);
        this.panel.add(this.removeCustomerButton);
        this.removeCustomerButton.addActionListener(this);  

        this.BuyForCustomerButton = new JButton("Buy For Customer");
        this.BuyForCustomerButton.setBounds(50, 300, 200, 40);
        this.panel.add(this.BuyForCustomerButton);
        this.BuyForCustomerButton.addActionListener(this);

        this.editProductButton = new JButton("Edit Product");
        this.editProductButton.setBounds(50, 350, 200, 40);
        this.panel.add(this.editProductButton);
        this.editProductButton.addActionListener(this);
        this.removeProductButton = new JButton("Remove Product");
        this.removeProductButton.setBounds(50, 400, 200, 40);
        this.panel.add(this.removeProductButton);
        this.removeProductButton.addActionListener(this);


        this.logoutButton = new JButton("Logout");
        this.logoutButton.setBounds(50, 450, 200, 40);
        this.panel.add(this.logoutButton);
        this.logoutButton.addActionListener(this);
        

        this.add(this.panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ProductListButton){
            new ProductListGUI().setVisible(true);
            dispose();
        }
        else if(e.getSource() == addProductButton){
            dispose();
            new AddProductGUI().setVisible(true);
        }
        else if(e.getSource() == CustomerListButton){
            new CustomerListGUI().setVisible(true);
            dispose();
        }
        else if(e.getSource() == removeCustomerButton){
            new RemoveCustomerGUI().setVisible(true);
            dispose();
        }
        else if(e.getSource() == BuyForCustomerButton){
            new BuyForCustomerFrame().setVisible(true);
            dispose();
        }
        else if(e.getSource() == editProductButton){
            dispose();
            new EditProductGUI().setVisible(true);
        }
        else if(e.getSource() == removeProductButton){
            new RemoveProductGUI().setVisible(true);
            dispose();
        }
        else if(e.getSource() == logoutButton){
            new LoginGUI().setVisible(true);
            dispose();
        }
    }
}
