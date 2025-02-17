import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Homepage extends JFrame implements ActionListener {
    JButton addDiscount, deleteDiscount, credits, resetPassword,
            newProduct, updateProduct, productDetails, deleteProduct,
            billing, logout, close, billingHistory;
    String user_name,role;
    ImageIcon i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14;
    JLabel shopname,tagline;

    Homepage(String user_name,String role) {
        this.user_name = user_name;
        this.role=role;

        i1 = new ImageIcon("src\\icons\\billing_background2.png");
        Image img= i1.getImage().getScaledInstance(1550,850,Image.SCALE_FAST);
        JLabel image= new JLabel(new ImageIcon(img));
        image.setBounds(0,0,1550,850);
        add(image);

        i2= new ImageIcon("src\\icons\\logo.png");
        Image img2= i2.getImage().getScaledInstance(150,120,Image.SCALE_DEFAULT);
        JLabel image2= new JLabel(new ImageIcon(img2));
        image2.setBounds(225,42,150,120);
        image.add(image2);

        JLabel ntext = new JLabel("Welcome " +role+" "+ user_name + "...");
        ntext.setBounds(500, 200, 1000, 50);
        ntext.setFont(new Font("Arial", Font.BOLD, 30));
        ntext.setForeground(Color.white);
        image.add(ntext);

        shopname= new JLabel("FRESHCART");
        shopname.setBounds(425, 62, 320, 50);
        shopname.setFont(new Font("Bodoni MT",Font.BOLD,50));
        shopname.setOpaque(true);
        shopname.setBackground(new Color(245,245,220,150));
        shopname.setForeground(Color.BLACK);
        image.add(shopname);

        tagline=new JLabel("Where quality meets convenience...");
        tagline.setBounds(470, 110, 275, 30);
        tagline.setFont(new Font("Arial",Font.BOLD,15));
        tagline.setOpaque(true);
        tagline.setBackground(new Color(245,245,220,150));
        tagline.setForeground(Color.BLACK);
        image.add(tagline);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy      HH:mm:ss");
        String formattedDateTime = now.format(format);

        JLabel dateTimeLabel = new JLabel(formattedDateTime);
        dateTimeLabel.setBounds(1000, 50, 350, 50);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateTimeLabel.setForeground(Color.white);
        image.add(dateTimeLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime currentTime = LocalDateTime.now();
                String currentFormattedDateTime = currentTime.format(format);
                dateTimeLabel.setText(currentFormattedDateTime);
            }
        });
        timer.start();

        setSize(1580, 850);
        setLayout(null);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        i11= new ImageIcon("src\\icons\\adddiscount.png");
        Image img11= i11.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        addDiscount = new JButton(new ImageIcon(img11));
        addDiscount.setBounds(1050, 600, 180, 50);
        addDiscount.addActionListener(this);
        addDiscount.setOpaque(false);
        addDiscount.setBorder(null);
        addDiscount.setBackground(Color.black);
        addDiscount.setForeground(Color.white);
        image.add(addDiscount);

        i12=new ImageIcon("src\\icons\\deletediscount.png");
        Image img12=i12.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        deleteDiscount = new JButton(new ImageIcon(img12));
        deleteDiscount.setBounds(1300, 600, 180, 50);
        deleteDiscount.addActionListener(this);
        deleteDiscount.setOpaque(false);
        deleteDiscount.setBorder(null);
        deleteDiscount.setBackground(Color.BLACK);
        deleteDiscount.setForeground(Color.white);
        image.add(deleteDiscount);

        i13=new ImageIcon("src\\icons\\credits.png");
        Image img13=i13.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        credits = new JButton(new ImageIcon(img13));
        credits.setBounds(550, 700, 180, 50);
        credits.addActionListener(this);
        credits.setOpaque(false);
        credits.setBorder(null);
        credits.setBackground(Color.black);
        credits.setForeground(Color.white);
        image.add(credits);

        i14=new ImageIcon("src\\icons\\resetpassword.png");
        Image img14=i14.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        resetPassword = new JButton(new ImageIcon(img14));
        resetPassword.setBounds(800, 700, 180, 50);
        resetPassword.addActionListener(this);
        resetPassword.setOpaque(false);
        resetPassword.setBorder(null);
        resetPassword.setBackground(Color.BLACK);
        resetPassword.setForeground(Color.white);
        image.add(resetPassword);

        i3=new ImageIcon("src\\icons\\newproduct.png");
        Image img3= i3.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        newProduct = new JButton(new ImageIcon(img3));
        newProduct.setBounds(50, 600, 180, 50);
        newProduct.addActionListener(this);
        newProduct.setOpaque(false);
        newProduct.setBorder(null);
        newProduct.setBackground(Color.black);
        newProduct.setForeground(Color.white);
        image.add(newProduct);


        i4=new ImageIcon("src\\icons\\updateproduct.png");
        Image img4= i4.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        updateProduct = new JButton(new ImageIcon(img4));
        updateProduct.setBounds(300, 600, 180, 50);
        updateProduct.addActionListener(this);
        updateProduct.setOpaque(false);
        updateProduct.setBorder(null);
        updateProduct.setBackground(Color.black);
        updateProduct.setForeground(Color.white);
        image.add(updateProduct);

        i5=new ImageIcon("src\\icons\\productdetails.png");
        Image img5= i5.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        productDetails = new JButton(new ImageIcon(img5));
        productDetails.setBounds(800, 600, 180, 50);
        productDetails.addActionListener(this);
        productDetails.setBackground(Color.BLACK);
        productDetails.setOpaque(false);
        productDetails.setBorder(null);
        productDetails.setForeground(Color.white);
        image.add(productDetails);

        i6=new ImageIcon("src\\icons\\deleteproduct.png");
        Image img6= i6.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        deleteProduct = new JButton(new ImageIcon(img6));
        deleteProduct.setBounds(550, 600, 180, 50);
        deleteProduct.addActionListener(this);
        deleteProduct.setOpaque(false);
        deleteProduct.setBorder(null);
        deleteProduct.setBackground(Color.BLACK);
        deleteProduct.setForeground(Color.white);
        image.add(deleteProduct);

        i7= new ImageIcon("src\\icons\\billing.png");
        Image img7= i7.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        billing = new JButton(new ImageIcon(img7));
        billing.setBounds(50, 700, 180, 50);
        billing.addActionListener(this);
        billing.setOpaque(false);
        billing.setBorder(null);
        billing.setBackground(Color.BLACK);
        billing.setForeground(Color.white);
        image.add(billing);

        i8= new ImageIcon("src\\icons\\billinghistory.png");
        Image img8= i8.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        billingHistory = new JButton(new ImageIcon(img8));
        billingHistory.setBounds(300, 700, 180, 50);
        billingHistory.addActionListener(this);
        billingHistory.setOpaque(false);
        billingHistory.setBorder(null);
        billingHistory.setBackground(Color.BLACK);
        billingHistory.setForeground(Color.white);
        image.add(billingHistory);

        i9=new ImageIcon("src\\icons\\logout.png");
        Image img9= i9.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        logout = new JButton(new ImageIcon(img9));
        logout.setBounds(1050, 700, 180, 50);
        logout.addActionListener(this);
        logout.setOpaque(false);
        logout.setBorder(null);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.white);
        image.add(logout);

        i10=new ImageIcon("src\\icons\\close.png");
        Image img10= i10.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT);
        close = new JButton(new ImageIcon(img10));
        close.setBounds(1300, 700, 180, 50);
        close.addActionListener(this);
        close.setOpaque(false);
        close.setBorder(null);
        close.setBackground(Color.BLACK);
        close.setForeground(Color.white);
        image.add(close);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addDiscount) {
            if(role.equals("Manager"))
                new CreateDiscount().setVisible(true);
            else
                JOptionPane.showMessageDialog(null, "Only Manager has access to this feature");
        } else if (ae.getSource() == deleteDiscount) {
            if(role.equals("Manager"))
                new DeleteDiscount().setVisible(true);
            else
                JOptionPane.showMessageDialog(null, "Only Manager has access to this feature");
        } else if (ae.getSource() == credits) {
            new Credits().setVisible(true);
        } else if (ae.getSource() == newProduct) {
            if(role.equals("Manager"))
                new NewProduct().setVisible(true);
            else
                JOptionPane.showMessageDialog(null, "Only Manager has access to this feature");
        } else if (ae.getSource() == updateProduct) {
            if(role.equals("Manager"))
                new UpdateProduct().setVisible(true);
            else
                JOptionPane.showMessageDialog(null, "Only Manager has access to this feature");
        } else if (ae.getSource() == productDetails) {
            new ProductDetails().setVisible(true);
        } else if (ae.getSource() == deleteProduct) {
            if(role.equals("Manager"))
                new DeleteProduct().setVisible(true);
            else
                JOptionPane.showMessageDialog(null, "Only Manager has access to this feature");
        } else if (ae.getSource() == billing) {
            new Billing();
        } else if (ae.getSource() == logout) {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to log out?", "Log out Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmed == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login().setVisible(true);
            }

        } else if (ae.getSource() == close) {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Do you really want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmed == JOptionPane.YES_OPTION) {
                setVisible(false);
                System.exit(0);
            }
        }
        else if(ae.getSource()== billingHistory){
            new BillingHistory();
        }
        else{
            new ResetPassword().setVisible(true);
        }
    }

    // private void checkByRole(JButton button){
    //     if(role=="Cashier"){
    //         JOptionPane.showMessageDialog(this,);
    //     }
    // }
}
