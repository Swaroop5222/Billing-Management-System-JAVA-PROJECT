import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ResetPassword extends JFrame implements ActionListener{
    
    JLabel heading,oldPassword,newPassword;
    JPasswordField oldPasswordField,newPasswordField;
    JButton next, back;
    ImageIcon i1;
    JPanel panel1;

    ResetPassword(){
        setLayout(null);
        setSize(600,500);
        setVisible(true);
        setLocation(350,180);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        i1 = new ImageIcon("src\\icons\\homepage_background32.png");
        Image img= i1.getImage().getScaledInstance(600,500,Image.SCALE_DEFAULT);
        JLabel image= new JLabel(new ImageIcon(img));
        image.setBounds(0,0,600,500);
        add(image);

        heading=new JLabel("Change Password");
        heading.setBounds(170,10,230,30);
        heading.setFont(new Font("Bodoni MT",Font.BOLD,30));
        heading.setOpaque(true);
        heading.setBackground(new Color(10,25,50,150));
        heading.setForeground(Color.white);
        image.add(heading);

        panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(80,100,420,110);
        panel1.setBackground(new Color(10,20,50,90));
        image.add(panel1);

        oldPassword= new JLabel("Old Password");
        oldPassword.setBounds(30,20,200,35);
        oldPassword.setFont(new Font("Arial",Font.BOLD,20));
        oldPassword.setForeground(Color.white);
        panel1.add(oldPassword);

        newPassword= new JLabel("New Password");
        newPassword.setBounds(30,70,200,35);
        newPassword.setFont(new Font("Arial",Font.BOLD,20));
        newPassword.setForeground(Color.white);
        panel1.add(newPassword);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(230,20,150,20);
        oldPasswordField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(oldPasswordField);
        panel1.add(oldPasswordField);

        newPasswordField= new JPasswordField();
        newPasswordField.setBounds(230,70,150,20);
        newPasswordField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(newPasswordField);
        panel1.add(newPasswordField);

        JOptionPane.showMessageDialog(null,"Re-enter your old Password");

        ImageIcon i3= new ImageIcon("src\\icons\\next1.png");
        Image image2 = i3.getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT);

        next= new JButton(new ImageIcon(image2));
        next.setBounds(200,230,60,50);
        next.addActionListener(this);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        image.add(next);

        ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
        Image image4= i5.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT);

        back= new JButton(new ImageIcon(image4));
        back.setBounds(350,230,60,50);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        image.add(back);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            try{
                String oldpassword= new String (oldPasswordField.getPassword());
                String newpassword=  new String(newPasswordField.getPassword());
                String query= "SELECT * FROM Login WHERE pass_key = '"+oldpassword+"'";
                CONN c= new CONN();
                ResultSet rs=c.s.executeQuery(query);
                if(rs.next()){
                    String userid= rs.getString("User_ID");
                    String next_query = "UPDATE Login SET pass_key= '"+newpassword+"' WHERE User_ID='"+userid+"' ";
                    c.s.executeUpdate(next_query);
                    JOptionPane.showMessageDialog(null,"Password Successfully Changed");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter valid Old Password");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

private void pressEnter(JPasswordField pf){
    pf.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                next.doClick();
            }
        }
    });
}
}
