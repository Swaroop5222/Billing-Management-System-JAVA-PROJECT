import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlinePayment extends JFrame implements ActionListener{
    
    JButton back;
    JLabel heading;
    ImageIcon qr1,qr2,i1;

OnlinePayment(){
    setSize(600,500);
    setLayout(null);
    setLocation(350,100);
    setVisible(true);
    //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    pressEnter();
    
    i1= new ImageIcon("src\\icons\\onlinepayment1.jpg");
    Image img2= i1.getImage().getScaledInstance(600,500,Image.SCALE_DEFAULT);
    JLabel image2= new JLabel(new ImageIcon(img2));
    image2.setBounds(0,0,600,500);
    add(image2);

    heading= new JLabel("Online Payment QR's");
    heading.setBounds(150,10,280,30);
    heading.setFont(new Font("Bodoni MT",Font.BOLD,30));
    heading.setOpaque(true);
    heading.setBackground(Color.decode("#4B0082"));
    heading.setForeground(Color.white);
    image2.add(heading);

    qr1= new ImageIcon("src\\icons\\swaroop_linkedin.png");
    Image img= qr1.getImage().getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING);
    JLabel image= new JLabel(new ImageIcon(img));
    image.setBounds(30,100,200,200);
    image2.add(image);

    qr2= new ImageIcon("src\\icons\\teja_linkedin.png");
    Image img1=qr2.getImage().getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING);
    JLabel image1= new JLabel(new ImageIcon(img1));
    image1.setBounds(350,100,200,200);
    image2.add(image1);

    ImageIcon i5= new ImageIcon("src\\icons\\back1.png");
    Image image4= i5.getImage().getScaledInstance(70,55,Image.SCALE_DEFAULT);

    back= new JButton(new ImageIcon(image4));
    back.setBounds(250,350,70,55);
    back.addActionListener(this);
    back.setBackground(Color.decode("#4B0082"));
    back.setForeground(Color.WHITE);
    image2.add(back);

}

public void actionPerformed(ActionEvent ae){
        setVisible(false);
}

private void pressEnter(){
    this.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                back.doClick();
            }
        }
    });
    this.setFocusable(true);
}
}
