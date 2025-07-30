package views.Employee;

import javax.swing.*;
import java.awt.event.*;
import controllers.ProductController;
import models.Product;
public class RemoveProductGUI extends JFrame implements ActionListener{
    private JButton backButton,removeButton;
    private JTextField productIdField;
    private JLabel productIdLabel;
    private JPanel panel;

    public RemoveProductGUI(){
        super("Remove Product");
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.productIdLabel = new JLabel("Product ID:");
        this.productIdLabel.setBounds(50,40,100,30);
        this.panel.add(this.productIdLabel);
        this.productIdField = new JTextField();
        this.productIdField.setBounds(150,40,300,30);
        this.panel.add(this.productIdField);

        this.removeButton = new JButton("Remove");
        this.removeButton.setBounds(40,120,200,30);
        this.removeButton.addActionListener(this);
        this.panel.add(this.removeButton);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(260,120,200,30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        this.add(this.panel);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeButton){
            String productId = productIdField.getText().trim();
            if(productId.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please enter a Product ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ProductController pc = new ProductController();
            Product product = pc.searchProduct(productId);
            if(product != null){
                pc.delateProduct(productId);
                JOptionPane.showMessageDialog(this, "Product removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new ProductListGUI().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Product ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == backButton){
            new EmployeeGUI(null).setVisible(true);
            dispose();
        }
    }
    public static void main(String[] args) {
        RemoveProductGUI removeProductGUI = new RemoveProductGUI();
        removeProductGUI.setVisible(true);
    }
}
