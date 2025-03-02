import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Billing extends JFrame implements ActionListener {

    JLabel heading, buyerDetails, dateLabel, timeLabel;
    JLabel name, contact, productDetails;
    JLabel productId, quantity;
    JLabel calculationDetails, total, paidAmount, returnAmount,dateTimeLabel;

    JTextField nameField, contactField;
    JTextField productIdField, quantityField;
    JTextField totalField, paidField, returnField;

    JButton add, reset,back,online,print;
    ImageIcon i1;
    JPanel panel1;

    JTable productTable;
    DefaultTableModel tableModel;

    int Total = 0;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy    HH:mm:ss");
    
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LocalDateTime currentTime = LocalDateTime.now();
            String currentFormattedDateTime = currentTime.format(format);
            dateTimeLabel.setText(currentFormattedDateTime);
        }
    });

    Billing() {
        setTitle("Billing System");
        setSize(1000, 550);
        setLayout(null);
        setLocation(200, 200);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        i1= new ImageIcon("src\\icons\\homepage_background32.png");
        Image img= i1.getImage().getScaledInstance(1000,550,Image.SCALE_DEFAULT);
        JLabel image=new JLabel(new ImageIcon(img));
        image.setBounds(0,0,1000,550);
        add(image);


        heading = new JLabel("Billing");
        heading.setFont(new Font("Bodoni MT", Font.BOLD, 40));
        heading.setBounds(100, 10, 130, 50);
        heading.setOpaque(true);
        heading.setBackground(new Color(10, 25, 50,180));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        timer.start();

        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(40,65,400,430);
        panel1.setBackground(new Color(10,20,50,90));
        image.add(panel1);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setBounds(750, 12, 200, 30);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        dateTimeLabel.setForeground(Color.BLACK);
        image.add(dateTimeLabel);

        buyerDetails = new JLabel("Buyer Details");
        buyerDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
        buyerDetails.setBounds(30, 20, 100, 30);
        buyerDetails.setOpaque(false);
        buyerDetails.setForeground(Color.WHITE);
        panel1.add(buyerDetails);

        name = new JLabel("Name");
        name.setBounds(30, 60, 70, 30);
        name.setForeground(Color.white);
        panel1.add(name);

        nameField = new JTextField();
        nameField.setBounds(120, 60, 80, 20);
        nameField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(nameField);

        contact = new JLabel("Contact No");
        contact.setBounds(230, 60, 90, 30);
        contact.setForeground(Color.WHITE);
        panel1.add(contact);

        contactField = new JTextField();
        contactField.setBounds(310, 60, 80, 20);
        contactField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(contactField);

        productDetails = new JLabel("Product Details");
        productDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
        productDetails.setBounds(30, 100, 120, 30);
        productDetails.setOpaque(false);
        productDetails.setForeground(Color.WHITE);
        panel1.add(productDetails);

        productId = new JLabel("Product Id");
        productId.setBounds(30, 140, 80, 30);
        productId.setFont(new Font("SansSerif", Font.BOLD, 13));
        productId.setForeground(Color.WHITE);
        panel1.add(productId);

        productIdField = new JTextField();
        productIdField.setBounds(120, 140, 80, 22);
        productIdField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(productIdField);

        quantity = new JLabel("Quantity");
        quantity.setBounds(230, 140, 100, 30);
        quantity.setFont(new Font("SansSerif", Font.BOLD, 13));
        quantity.setForeground(Color.WHITE);
        panel1.add(quantity);

        quantityField = new JTextField();
        quantityField.setBounds(300, 140, 80, 22);
        quantityField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(quantityField);

        ImageIcon i6= new ImageIcon("src\\icons\\add.png");
        Image image5= i6.getImage().getScaledInstance(40, 32, Image.SCALE_DEFAULT);

        add = new JButton(new ImageIcon(image5));
        add.setBounds(300, 180, 40, 32);
        pressEnter(add);
        add.addActionListener(this);
        panel1.add(add);

        calculationDetails = new JLabel("Calculation Details");
        calculationDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
        calculationDetails.setBounds(30, 200, 150, 30);
        calculationDetails.setOpaque(false);
        calculationDetails.setForeground(Color.WHITE);
        panel1.add(calculationDetails);

        total = new JLabel("Total");
        total.setBounds(30, 240, 100, 30);
        total.setFont(new Font("SansSerif", Font.BOLD, 13));
        total.setForeground(Color.WHITE);
        panel1.add(total);

        totalField = new JTextField();
        totalField.setBounds(200, 240, 80, 20);
        totalField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(totalField);

        paidAmount = new JLabel("Paid Amount");
        paidAmount.setBounds(30, 290, 100, 30);
        paidAmount.setFont(new Font("SansSerif", Font.BOLD, 13));
        paidAmount.setForeground(Color.WHITE);
        panel1.add(paidAmount);

        paidField = new JTextField();
        paidField.setBounds(200, 290, 80, 20);
        paidField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(paidField);

        returnAmount = new JLabel("Return Amount");
        returnAmount.setBounds(30, 340, 100, 30);
        returnAmount.setFont(new Font("SansSerif", Font.BOLD, 13));
        returnAmount.setForeground(Color.WHITE);
        panel1.add(returnAmount);

        returnField = new JTextField();
        returnField.setBounds(200, 340, 80, 20);
        returnField.setFont(new Font("Arial",Font.BOLD,15));
        panel1.add(returnField);

        String[] columnNames = {"Product ID", "Product Name", "Price", "Quantity","Discount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(450, 70, 500, 400);
        image.add(scrollPane);

        setVisible(true);

        ImageIcon i4= new ImageIcon("src\\icons\\reset1.png");
        Image image3= i4.getImage().getScaledInstance(50,40,Image.SCALE_DEFAULT);

        reset= new JButton(new ImageIcon(image3));
        reset.setBounds(300, 230, 50, 40);
        reset.addActionListener(this);
        panel1.add(reset);

        ImageIcon i2= new ImageIcon("src\\icons\\online.png");
        Image image1= i2.getImage().getScaledInstance(50, 40, Image.SCALE_DEFAULT);

        online= new JButton(new ImageIcon(image1));
        online.setBounds(300,280,50,40);
        online.addActionListener(this);
        panel1.add(online);

        ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
        Image image4= i5.getImage().getScaledInstance(50,40,Image.SCALE_DEFAULT);

        back=new JButton(new ImageIcon(image4));
        back.setBounds(300,330,50,40);
        back.addActionListener(this);
        panel1.add(back);

        ImageIcon i3= new ImageIcon("src\\icons\\print3.png");
        Image image2 = i3.getImage().getScaledInstance(50, 40, Image.SCALE_DEFAULT);

        print= new JButton(new ImageIcon(image2));
        print.setBounds(180,380,50,40);
        print.addActionListener(this);
        panel1.add(print);

        paidField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateReturnAmount();
                }
            }
        });
    }

    private void calculateReturnAmount() {
        try{
            String paidAmountText = paidField.getText();
                int paid = Integer.parseInt(paidAmountText); 
                int returnAmountValue = paid - Total;        
                returnField.setText(Integer.toString(returnAmountValue));  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String customerName = nameField.getText();
        String customerPhone = contactField.getText();
        String productId = productIdField.getText();
        String quantityText = quantityField.getText();
        int price=0,totalValue=0,quantity=0;
        String productName="";
        if (ae.getSource() == add) {
            if (customerName.isEmpty() || customerPhone.isEmpty() || productId.isEmpty() || quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                quantity = Integer.parseInt(quantityText);
                CONN conn = new CONN();
                String query = "SELECT * FROM Products WHERE Product_Id = '" + productId + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    productName = rs.getString("Product_Name");
                    String discount = rs.getString("Discount");
                    price = rs.getInt("Price");
                    totalValue = price * quantity;
                    Total += totalValue;
                    int discountValue = Integer.parseInt(discount);
                    int discountAmount =(discountValue*totalValue)/100; 
                    Total -= discountAmount;
                    totalField.setText(String.valueOf(Total));
                    tableModel.addRow(new Object[]{productId, productName, price, quantity,discount});

                    try{
                        CONN c=new CONN();
                        String insertQuery = String.format(
                            "INSERT INTO Billing (Customer_Name, Contact_No, Product_Id, Product_Name, Price, Quantity, Total,P_Time) " +
                                    "VALUES ('%s', '%s', '%s', '%s', %d, %d, %d,'%s')",
                            customerName, customerPhone, productId, productName, price, quantity, totalValue ,LocalDateTime.now().format(format));
                        c.s.executeUpdate(insertQuery);
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    productIdField.setText("");
                    quantityField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        else if (ae.getSource() == reset) {
                addToBillingHistory();
                resetFields();
        }
        else if (ae.getSource()== online)
        {
            new OnlinePayment().setVisible(true);
        }
        else if(ae.getSource()==back){
            setVisible(false);
        }
        else{
            try{
                productTable.print();
            }
            catch(PrinterException p){
                p.printStackTrace();
            }
        }
    }
    
    private void resetFields() {
        nameField.setText("");
        contactField.setText("");
        productIdField.setText("");
        quantityField.setText("");
        totalField.setText("");
        paidField.setText("");
        returnField.setText("");
        tableModel.setRowCount(0);
        Total = 0;
    }

    private void addToBillingHistory(){
        String customer_name=nameField.getText();
        String customer_number=contactField.getText();
        String total=totalField.getText();
        String time="";
        try{
            CONN c=new CONN();
            String query="SELECT P_Time FROM Billing WHERE Customer_name='"+customer_name+"'";
            ResultSet rs=c.s.executeQuery(query);
            if(rs.next()){
                time=rs.getString("P_Time");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Database Error!: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        try{
            if (nameField.getText().isEmpty() && contactField.getText().isEmpty() && totalField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nothing to reset!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            CONN c=new CONN();
            String query= "INSERT INTO Payment(Customer_Name,Contact_No,Total,P_Time) VALUES('"+customer_name+"','"+customer_number+"','"+total+"','"+time+"' )";
            int inserted= c.s.executeUpdate(query);
            if(inserted>0){
                JOptionPane.showMessageDialog(this, "Reset Successfull !!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this,"Something went wrong, please try again later ","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Internal error Occured !","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pressEnter(Component comp){
        if(comp instanceof JTextField){
            JTextField tf= (JTextField) comp;
            if(tf==nameField|| tf==contactField|| tf==productIdField|| tf==quantityField){
                tf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke){
                        if (ke.getKeyCode()==KeyEvent.VK_ENTER) {
                            add.doClick();
                        }
                    }
                });
            }
        }
    }
}
