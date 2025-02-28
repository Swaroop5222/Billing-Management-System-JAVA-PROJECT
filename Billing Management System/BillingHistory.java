import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class BillingHistory extends JFrame implements ActionListener {
    JLabel heading;
    JTable table;
    JScrollPane pane;
    DefaultTableModel model;
    JButton back, print;
    ImageIcon i1;

    BillingHistory() {
        setLayout(null);
        setSize(800, 700);
        setLocation(250, 80);
        
        i1 = new ImageIcon("src\\icons\\homepage_background32.png");
        Image img = i1.getImage().getScaledInstance(800, 700, Image.SCALE_FAST);
        JLabel image = new JLabel(new ImageIcon(img));
        image.setBounds(0, 0, 800, 700);
        add(image);
        
        heading = new JLabel("Billing History");
        heading.setBounds(250, 10, 230, 45);
        heading.setFont(new Font("Bodoni MT", Font.BOLD, 35));
        heading.setOpaque(true);
        heading.setBackground(new Color(10, 25, 50, 180));
        heading.setForeground(Color.white);
        image.add(heading);

        model = new DefaultTableModel();
        model.addColumn("Customer_Name");
        model.addColumn("Contact_Number");
        model.addColumn("Total");
        model.addColumn("Time");

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        
        pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(45, 100, 700, 500);
        image.add(pane);
        
        back = createButton("src\\icons\\back2.png", 50, 610);
        image.add(back);
        
        print = createButton("src\\icons\\print3.png", 200, 610);
        image.add(print);
        
        showBillingHistory();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
        } else {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void showBillingHistory() {
        try {
            CONN c = new CONN();
            ResultSet rs = c.s.executeQuery("SELECT * FROM Payment");
            while (rs.next()) {
                Object[] row = new Object[model.getColumnCount()];
                for (int i = 1; i <= model.getColumnCount(); i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private JButton createButton(String iconPath, int x, int y) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(50, 40, Image.SCALE_DEFAULT);
        JButton button = new JButton(new ImageIcon(scaledImage));
        button.setBounds(x, y, 50, 40);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        return button;
    }
}
