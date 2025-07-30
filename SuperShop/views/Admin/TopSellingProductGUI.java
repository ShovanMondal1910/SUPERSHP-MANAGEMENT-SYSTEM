package views.Admin;

import javax.swing.*;
import java.awt.event.*;
import controllers.ProductController;
import models.Product;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;

public class TopSellingProductGUI extends JFrame implements ActionListener {
    private JButton backButton;
    private JTable productTable;
    private JScrollPane scrollPane;
    private JPanel panel;

    public TopSellingProductGUI() {
        super("Top Selling Products");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setBounds(0, 0, 900, 700);
        this.panel.setLayout(null);

        this.backButton = new JButton("Back");
        this.backButton.setBounds(750, 20, 100, 30);
        this.backButton.addActionListener(this);
        this.panel.add(this.backButton);

        String[] columnNames = {"Photo", "Product Name", "Category", "Total Sold", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
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

        // Get top selling products
        ProductController pc = new ProductController();
        Product[] topProducts = pc.getTopSellingProducts(10);
        HashMap<String, Integer> salesCount = getSalesCount();
        for (Product p : topProducts) {
            if (p != null) {
                ImageIcon icon = null;
                String imgPath = p.getImage();
                if (imgPath != null && !imgPath.isEmpty()) {
                    File imgFile = new File(imgPath);
                    if (imgFile.exists()) {
                        try {
                            Image scaledImg = javax.imageio.ImageIO.read(imgFile).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                            icon = new ImageIcon(scaledImg);
                        } catch (Exception e) {
                            icon = createDefaultIcon();
                        }
                    } else {
                        icon = createDefaultIcon();
                    }
                } else {
                    icon = createDefaultIcon();
                }
                int sold = salesCount.getOrDefault(p.getProductName(), 0);
                model.addRow(new Object[]{
                    icon,
                    p.getProductName(),
                    p.getCategory(),
                    String.valueOf(sold),
                    String.valueOf(p.getPrice())
                });
            }
        }

        this.productTable = new JTable(model);
        this.productTable.setRowHeight(60);
        this.scrollPane = new JScrollPane(this.productTable);
        this.scrollPane.setBounds(10, 70, 860, 570);
        this.panel.add(this.scrollPane);

        this.add(this.panel);
        this.setVisible(true);
    }

    private HashMap<String, Integer> getSalesCount() {
        HashMap<String, Integer> salesCount = new HashMap<>();
        controllers.FileIO fio = new controllers.FileIO();
        String[] lines = fio.readFile("controllers/data/InvoiceList.txt");
        for (String line : lines) {
            if (line != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String productName = parts[3].trim();
                    int qty = 0;
                    try { qty = Integer.parseInt(parts[4].trim()); } catch (Exception e) { qty = 0; }
                    salesCount.put(productName, salesCount.getOrDefault(productName, 0) + qty);
                }
            }
        }
        return salesCount;
    }

    private ImageIcon createDefaultIcon() {
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(60, 60, java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g2d = img.createGraphics();
        g2d.setColor(java.awt.Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, 60, 60);
        g2d.setColor(java.awt.Color.DARK_GRAY);
        g2d.drawString("No Image", 5, 35);
        g2d.dispose();
        return new ImageIcon(img);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new AdminGUI(null).setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        new TopSellingProductGUI().setVisible(true);
    }
}
