import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear;
    JTextField card;
    JPasswordField pwd;
    JLabel user, pass, heading;
    ImageIcon i1;

    Login() {
        setTitle("Billing System");
        setLayout(null);
        setSize(800, 600);
        setLocation(350, 150);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        i1 = new ImageIcon("src\\icons\\login_background8.jpg");
        Image img= i1.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT);
        JLabel image= new JLabel(new ImageIcon(img));
        image.setBounds(0,0,800,600);
        add(image);

        heading = new JLabel("LOGIN");
        heading.setFont(new Font("Berlin Sans FB",Font.PLAIN, 38));
        heading.setBounds(210, 60, 400, 40);
        heading.setBackground(Color.white);
        heading.setForeground(Color.white);
        image.add(heading);

        user = new JLabel("User ID");
        user.setFont(new Font("Berlin Sans FB",Font.PLAIN, 30));
        user.setBounds(120, 215, 400, 40);
        user.setBackground(Color.BLACK);
        user.setForeground(Color.WHITE);
        image.add(user);

        card = new JTextField();
        card.setBounds(325, 215, 250, 33);
        card.setFont(new Font("Berlin Sans FB",Font.PLAIN, 29));
        pressEnter(card);
        image.add(card);

        pass = new JLabel("Password");
        pass.setFont(new Font("Berlin Sans FB",Font.PLAIN, 30));
        pass.setBounds(120, 305, 400, 40);
        pass.setBackground(Color.BLACK);
        pass.setForeground(Color.WHITE);
        image.add(pass);

        pwd = new JPasswordField();
        pwd.setBounds(325, 305, 250, 33);
        pwd.setFont(new Font("Arial", Font.BOLD, 29));
        pressEnter(pwd);
        image.add(pwd);

        login = new JButton("Login");
        login.setBounds(300, 420, 100, 30);
        login.setFont(new Font("Osward", Font.BOLD, 18));
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        image.add(login);

        clear = new JButton("Clear");
        clear.setBounds(500, 420, 100, 30);
        clear.setFont(new Font("Osward", Font.BOLD, 18));
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        image.add(clear);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            CONN conn = new CONN();
            String user_id = card.getText();
            String Pass = new String(pwd.getPassword());
            String query = "SELECT * FROM Login WHERE User_ID = '" + user_id + "' AND pass_key = '" + Pass + "'";

        try 
        {
                ResultSet rs = conn.s.executeQuery(query);
        
                if (rs.next()) {
                    String user_name = rs.getString("User_name");
                    String role=rs.getString("User_Role");
                    JOptionPane.showMessageDialog(this, "Login Successful");
                   // new FirstPage().setVisible(false);
                    setVisible(false);
                    new Homepage(user_name,role).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                    card.setText("");
                    pwd.setText("");
                }
            }
                catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
                }}
                else if (ae.getSource() == clear) {
            card.setText("");
            pwd.setText("");
        }
    }

    public void pressEnter(Component comp){
        if(comp instanceof JTextField){
            JTextField tf= (JTextField) comp;
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        login.doClick();
                    }
                }
            });
        }
        else{
            JPasswordField pf = (JPasswordField) comp;
            pf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        login.doClick();
                    }
                }
            });
        }
    }

}
