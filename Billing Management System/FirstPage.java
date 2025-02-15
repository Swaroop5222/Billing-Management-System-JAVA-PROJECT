import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FirstPage extends JFrame implements ActionListener{
    
    ImageIcon backgroundimage;
    JButton button;
    JLabel heading;

    FirstPage(){
        setLayout(null);
        setVisible(true);
        setSize(1550,850);
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pressEnter();

        backgroundimage = new ImageIcon("src\\icons\\firstpage_background2.jpg");
        Image i1=backgroundimage.getImage().getScaledInstance(1550,850,Image.SCALE_DEFAULT);
        JLabel image= new JLabel(new ImageIcon(i1));
        image.setBounds(0,0,1550,850);
        add(image);

        button= new JButton("Click Here To Continue");
        button.setBounds(1050, 650, 200, 50);
        button.addActionListener(this);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        image.add(button);

        heading= new JLabel("BILLING MANAGEMENT SYSTEM");
        heading.setBounds(800, 40, 900, 50);
        heading.setFont(new Font("Microsoft yaHei",Font.BOLD,35));
        heading.setForeground(Color.RED);
        image.add(heading);

        new Thread(() -> {
            try{
                while(true){
                    heading.setVisible(false);
                    Thread.sleep(500);
                    heading.setVisible(true);
                    Thread.sleep(500);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }).start();

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==button){
            new Login().setVisible(true);
        }
    }

    private void pressEnter(){
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                    button.doClick();
                }
            }
        });
    }
}
