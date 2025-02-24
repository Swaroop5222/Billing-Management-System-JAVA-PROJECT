import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteDiscount extends JFrame implements ActionListener {

    JLabel heading, productId, productName, rate, description, activate, discount;
    JTextField productIdField, productNameField, rateField, descriptionField, activateField,discountField;
    JButton delete, reset, back, search;
    JPanel panel1;
    String user_name;
    ImageIcon i1;

    // Constructor
    DeleteDiscount() {

        // Frame settings
        setSize(600, 500);
        setLayout(null);
        setLocation(350, 100);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        i1 = new ImageIcon("src\\icons\\homepage_background32.png");
        Image img= i1.getImage().getScaledInstance(600,500,Image.SCALE_FAST);
        JLabel image= new JLabel(new ImageIcon(img));
        image.setBounds(0,0,600,500);
        add(image);

        // Heading
        heading = new JLabel("Delete Discount");
        heading.setBounds(200, 10, 165, 30);
        heading.setFont(new Font("Bodoni MT", Font.BOLD, 25));
        heading.setOpaque(true);
        heading.setBackground(new Color(10, 25, 50,180));
        heading.setForeground(Color.white);
        image.add(heading);

        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(50,50,510,320);
        panel1.setBackground(new Color(10,20,50,90));
        image.add(panel1);

        // Labels and text fields
        productId = new JLabel("Product ID:");
        productId.setBounds(30, 20, 200, 30);
        productId.setFont(new Font("Sans", Font.BOLD, 20));
        productId.setForeground(Color.white);
        panel1.add(productId);

        productIdField = new JTextField();
        productIdField.setBounds(230, 20, 200, 30);
        productIdField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(productIdField);
        panel1.add(productIdField);

        productName = new JLabel("Product Name:");
        productName.setBounds(30, 70, 280, 30);
        productName.setFont(new Font("Sans", Font.BOLD, 20));
        productName.setForeground(Color.white);
        panel1.add(productName);

        productNameField = new JTextField();
        productNameField.setBounds(230, 70, 200, 30);
        productNameField.setFont(new Font("Arial",Font.BOLD,15));
        productNameField.setEditable(false);
        pressEnter(productNameField);
        panel1.add(productNameField);

        rate = new JLabel("Rate:");
        rate.setBounds(30, 120, 290, 30);
        rate.setFont(new Font("Sans", Font.BOLD, 20));
        rate.setForeground(Color.white);
        panel1.add(rate);

        rateField = new JTextField();
        rateField.setBounds(230, 120, 200, 30);
        rateField.setFont(new Font("Arial",Font.BOLD,15));
        rateField.setEditable(false);
        pressEnter(rateField);
        panel1.add(rateField);

        description = new JLabel("Description:");
        description.setBounds(30, 170, 200, 30);
        description.setFont(new Font("Sans", Font.BOLD, 20));
        description.setForeground(Color.white);
        panel1.add(description);

        descriptionField = new JTextField();
        descriptionField.setBounds(230, 170, 200, 30);
        descriptionField.setFont(new Font("Arial",Font.BOLD,15));
        descriptionField.setEditable(false);
        pressEnter(descriptionField);
        panel1.add(descriptionField);

        activate = new JLabel("Activate:");
        activate.setBounds(30, 220, 200, 30);
        activate.setFont(new Font("Sans", Font.BOLD, 20));
        activate.setForeground(Color.white);
        panel1.add(activate);

        activateField = new JTextField();
        activateField.setBounds(230, 220, 200, 30);
        activateField.setFont(new Font("Arial",Font.BOLD,15));
        activateField.setEditable(false);
        pressEnter(activateField);
        panel1.add(activateField);

        discount=new JLabel("Discount(%)");
        discount.setBounds(30, 270, 200, 30);
        discount.setFont(new Font("Sans",Font.BOLD,20));
        discount.setForeground(Color.white);
        panel1.add(discount);

        discountField= new JTextField();
        discountField.setBounds(230, 270, 200, 30);
        discountField.setFont(new Font("Arial",Font.BOLD,15));
        discountField.setEditable(false);
        pressEnter(discountField);
        panel1.add(discountField);

        ImageIcon i2= new ImageIcon("src\\icons\\search.png");
        Image image1= i2.getImage().getScaledInstance(50, 40, Image.SCALE_DEFAULT);

        search = new JButton(new ImageIcon(image1));
        search.setBounds(450, 20, 50, 40);
        search.addActionListener(this);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.white);
        panel1.add(search);

        ImageIcon i3= new ImageIcon("src\\icons\\delete1.png");
        Image image2 = i3.getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT);

        delete = new JButton(new ImageIcon(image2));
        delete.setBounds(50, 390, 60, 50);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        image.add(delete);

        ImageIcon i4= new ImageIcon("src\\icons\\reset1.png");
        Image image3= i4.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        reset = new JButton(new ImageIcon(image3));
        reset.setBounds(230, 390, 60, 50);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        image.add(reset);

        ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
        Image image4= i5.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        back = new JButton(new ImageIcon(image4));
        back.setBounds(430, 390, 60, 50);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        image.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String productID = productIdField.getText().trim();
            if (productID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a Product ID.");
                return;
            }
            try {
                CONN c = new CONN();
                String query = "SELECT * FROM Products WHERE Product_ID = '" + productID + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    productNameField.setText(rs.getString("Product_Name"));
                    rateField.setText(rs.getString("Price"));
                    descriptionField.setText(rs.getString("P_Description"));
                    discountField.setText(rs.getString("discount"));

                    // Set YES/NO in activateField
                    String activateValue = rs.getString("Activate");
                    if ("YES".equalsIgnoreCase(activateValue)) {
                        activateField.setText("YES");
                    } else if ("NO".equalsIgnoreCase(activateValue)) {
                        activateField.setText("NO");
                    } else {
                        activateField.setText(""); // For unexpected values
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Product ID not found!");
                    resetFields();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
        } else if (ae.getSource() == delete) {
            String productID = productIdField.getText().trim();
            if (productID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please search for a product before deleting.");
                return;
            }
            try {
                CONN c = new CONN();
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the discount on this product?");
                if (response == JOptionPane.YES_OPTION) {
                    String query = "UPDATE Products SET Discount = 0 WHERE Product_ID = '" + productID + "'";
                    int rowsAffected = c.s.executeUpdate(query);

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Discount deleted successfully!");
                        resetFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete the discount.");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
        } else if (ae.getSource() == reset) {
            resetFields();
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    private void resetFields() {
        productIdField.setText("");
        productNameField.setText("");
        rateField.setText("");
        descriptionField.setText("");
        activateField.setText("");
        discountField.setText(" ");
    }

    private void pressEnter(JTextField tf){
        if(tf==productIdField){
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                        search.doClick();
                    }
                }
            });
        }
        else{
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                        delete.doClick();
                    }
                }
            });
        }
    }
}
