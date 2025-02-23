import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class CreateDiscount extends JFrame implements ActionListener {

    JLabel heading, productId, productName, rate, description, activate,discount;
    JTextField productIdField, productNameField, rateField, descriptionField,discountField;
    JComboBox<String> activateComboBox;
    JButton reset, back, search, save;
    ImageIcon i1;
    JPanel panel1;

    CreateDiscount() {

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

        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(50,50,510,320);
        panel1.setBackground(new Color(10,20,50,90));
        image.add(panel1);

        heading = new JLabel("Create Discount");
        heading.setBounds(200, 10, 170, 30);
        heading.setFont(new Font("Bodoni MT", Font.BOLD, 25));
        heading.setOpaque(true);
        heading.setBackground(new Color(10, 25, 50,180));
        heading.setForeground(Color.white);
        image.add(heading);

        productId = new JLabel("Product ID:");
        productId.setBounds(30, 20, 120, 30);
        productId.setFont(new Font("Sans", Font.BOLD, 20));
        productId.setForeground(Color.white);
        panel1.add(productId);

        productIdField = new JTextField();
        productIdField.setBounds(230, 20, 200, 30);
        productIdField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(productIdField);
        panel1.add(productIdField);

        productName = new JLabel("Product Name:");
        productName.setBounds(30, 70, 150, 30);
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
        rate.setBounds(30, 120, 50, 30);
        rate.setFont(new Font("Sans", Font.BOLD, 20));
        rate.setForeground(Color.white);
        panel1.add(rate);  

        discount= new JLabel("Discount(%)");
        discount.setBounds(30, 270, 120, 30);
        discount.setFont(new Font("Sans", Font.BOLD,20));   
        discount.setForeground(Color.white);
        panel1.add(discount);

        rateField = new JTextField();
        rateField.setBounds(230, 120, 200, 30);
        rateField.setFont(new Font("Arial",Font.BOLD,15));
        rateField.setEditable(false);
        pressEnter(rateField);
        panel1.add(rateField);

        description = new JLabel("Description:");
        description.setBounds(30, 170, 120, 30);
        description.setFont(new Font("Sans", Font.BOLD, 20));
        description.setForeground(Color.white);
        panel1.add(description);

        descriptionField = new JTextField();
        descriptionField.setBounds(230, 170, 200, 30);
        descriptionField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(descriptionField);
        panel1.add(descriptionField);

        activate = new JLabel("Activate:");
        activate.setBounds(30, 220, 90, 30);
        activate.setFont(new Font("Sans", Font.BOLD, 20));
        activate.setForeground(Color.white);
        panel1.add(activate);

        activateComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        activateComboBox.setBounds(230, 220, 200, 30);
        activateComboBox.setEnabled(false);
        pressEnter(activateComboBox);
        panel1.add(activateComboBox);

        discountField=new JTextField();
        discountField.setBounds(230,270,200,30);
        pressEnter(discountField);
        panel1.add(discountField);

        ImageIcon i2= new ImageIcon("src\\icons\\search.png");
        Image image1= i2.getImage().getScaledInstance(50, 40, Image.SCALE_DEFAULT);

        search = new JButton(new ImageIcon(image1));
        search.setBounds(450, 20, 50, 40);
        search.addActionListener(this);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        panel1.add(search);

        ImageIcon i4= new ImageIcon("src\\icons\\save3.png");
        Image image3= i4.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        save = new JButton(new ImageIcon(image3));
        save.setBounds(300, 390, 60, 50);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setEnabled(false);
        save.addActionListener(this);
        image.add(save);
        
        ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
        Image image4= i5.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        back = new JButton(new ImageIcon(image4));
        back.setBounds(450, 390, 60, 50);
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
                    activateComboBox.setSelectedItem(rs.getString("Activate"));

                    productNameField.setEditable(false);
                    rateField.setEditable(false);
                    descriptionField.setEditable(false);
                    activateComboBox.setEnabled(false);
                    save.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Product ID not found!");
                    resetFields();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
        } else if (ae.getSource() == save) {
            String productID = productIdField.getText().trim();
            String productName = productNameField.getText().trim();
            String rate = rateField.getText().trim();
            String description = descriptionField.getText().trim();
            String activate = activateComboBox.getSelectedItem().toString().trim();
            String discount= discountField.getText().trim();

            if (productName.isEmpty() || discount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled.");
                return;
            }

            try {
                CONN c = new CONN();
                String query = "UPDATE Products SET Product_Name = '" + productName + "', Price = '" + rate + "', P_Description = '" + description + "', Activate = '" + activate + "',Discount = '"+discount+"' WHERE Product_ID = '" + productID + "'";
                int rowsAffected = c.s.executeUpdate(query);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Discount added successfully!");
                    resetFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add the discount");
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
        activateComboBox.setSelectedIndex(0);
        productNameField.setEditable(false);
        rateField.setEditable(false);
        descriptionField.setEditable(false);
        activateComboBox.setEnabled(false);
        discountField.setText(" ");
        save.setEnabled(false);
    }

    private void pressEnter(Component comp){
        if(comp instanceof JTextField){
            JTextField tf = (JTextField) comp;
            if(tf==productIdField){
                tf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke){
                        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                            search.doClick();
                        }
                    }
                });
            }
            else{
                tf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke){
                        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                            save.doClick();
                        }
                    }
                });
            }
        }
        if(comp instanceof JComboBox<?>){
            JComboBox<?> cb= (JComboBox<?>) comp;
            cb.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                        save.doClick();
                    }
                }
            });
        }
    }
}
