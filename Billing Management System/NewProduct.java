import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class NewProduct extends JFrame implements ActionListener {

    JLabel heading, productId, productName, price, description,product_status;
    JTextField productIdField, productNameField, rateField, descriptionField;
    JComboBox<String> activateBox;
    JButton save, reset, back;
    String user_name;
    ImageIcon i1;
    JPanel panel1;

    public NewProduct() {
        setSize(600, 500);
        setLayout(null);
        setLocation(350, 100);
        getContentPane().setBackground(Color.WHITE);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        i1 = new ImageIcon("src\\icons\\homepage_background32.png");
        Image img= i1.getImage().getScaledInstance(600,500,Image.SCALE_FAST);
        JLabel image= new JLabel(new ImageIcon(img));
        image.setBounds(0,0,600,500);
        add(image);

        heading = new JLabel("New Product");
        heading.setBounds(200, 10, 150, 30);
        heading.setFont(new Font("Bodoni MT", Font.BOLD, 25));
        heading.setOpaque(true);
        heading.setBackground(new Color(10, 25, 50,180));
        heading.setForeground(Color. white);
        image.add(heading);

        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(50,50,460,280);
        panel1.setBackground(new Color(10,20,50,90));
        image.add(panel1);

        productId = new JLabel("Product ID");
        productId.setBounds(30, 20, 200, 30);
        productId.setFont(new Font("SansSerif", Font.BOLD, 20));
        productId.setForeground(Color.white);
        panel1.add(productId);

        productName = new JLabel("Product Name");
        productName.setBounds(30, 70, 220, 30);
        productName.setFont(new Font(" SansSerif", Font.BOLD, 20));
        productName.setForeground(Color.white);
        panel1.add(productName);

        price = new JLabel("Price");
        price.setBounds(30, 120, 200, 30);
        price.setFont(new Font("SansSerif", Font.BOLD, 20));
        price.setForeground(Color.white);
        panel1.add(price);

        description = new JLabel("Description");
        description.setBounds(30, 170, 200, 30);
        description.setFont(new Font("SansSerif", Font.BOLD,20));
        description.setForeground(Color.white);
        panel1.add(description);

        product_status = new JLabel("Activate");
        product_status.setBounds(30, 220, 200, 30);
        product_status.setFont(new Font("SansSerif", Font.BOLD, 20));
        product_status.setForeground(Color.white);
        panel1.add(product_status);

        productIdField = new JTextField();
        productIdField.setBounds(230, 20, 200, 30);
        productIdField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(productIdField);
        panel1.add(productIdField);

        productNameField = new JTextField();
        productNameField.setBounds(230, 70, 200, 30);
        productNameField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(productNameField);
        panel1.add(productNameField);

        rateField = new JTextField();
        rateField.setBounds(230, 120, 200, 30);
        rateField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(rateField);
        panel1.add(rateField);

        descriptionField = new JTextField();
        descriptionField.setBounds(230, 170, 200, 30);
        descriptionField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(descriptionField);
        panel1.add(descriptionField);

        String[] options = { "Select","YES", "NO" };
        activateBox = new JComboBox<>(options);
        activateBox.setBounds(230, 220, 100, 30);
        pressEnter(activateBox);
        panel1.add(activateBox);

        ImageIcon i2= new ImageIcon("src\\icons\\save3.png");
        Image image1= i2.getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT);

        save = new JButton(new ImageIcon(image1));
        save.setBounds(50, 360, 60, 50);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        image.add(save);

        ImageIcon i4= new ImageIcon("src\\icons\\reset1.png");
        Image image3= i4.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        reset = new JButton(new ImageIcon(image3));
        reset.setBounds(250, 360, 60, 50);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        image.add(reset);

        ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
        Image image4= i5.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        back = new JButton(new ImageIcon(image4));
        back.setBounds(450, 360, 60, 50);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        image.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {
            String Product_ID = productIdField.getText();
            String Product_Name = productNameField.getText();
            String Price = rateField.getText();
            String P_Description = descriptionField.getText();
            String Activate = (String) activateBox.getSelectedItem();

            try (Connection conn = CONN.getConnection(); 
                Statement s = conn.createStatement()) {

                if (Product_ID.isEmpty() || Product_Name.isEmpty() || Price.isEmpty() || P_Description.isEmpty() ||
                    Activate.equals("Select")) {
                
                    JOptionPane.showMessageDialog(null, "All fields are required");
                    return;
                }

                String query = "INSERT INTO Products (Product_ID,Product_Name,Price,P_Description,Activate,discount) " +
                        "VALUES('" + Product_ID + "','" + Product_Name + "','" + Price + "','" + P_Description + "','" + Activate + "','"+null+"')";
                s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Product saved successfully");
                
                int confirmed = JOptionPane.showConfirmDialog(this,"Do want add any other product ?", "Confirmation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (confirmed == JOptionPane.YES_OPTION) {
                        setVisible(false);
                        new NewProduct().setVisible(true);
                    }
                    else{
                        setVisible(false);
                    }
                
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }  
        } else if (ae.getSource() == reset) {
            productIdField.setText("");
            productNameField.setText("");
            rateField.setText("");
            descriptionField.setText("");
            activateBox.setSelectedIndex(0);
        }else if (ae.getSource()==back){
            setVisible(false);
        }
    }

    private void pressEnter(JTextField tf){
        tf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    save.doClick();
                }
            }
        });
    }

    private void pressEnter(JComboBox<String> cb){
        cb.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                    save.doClick();
                }
            }
        });
    }
}

