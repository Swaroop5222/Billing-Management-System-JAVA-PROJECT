import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductDetails extends JFrame {

    JTextField searchField;
    JButton backButton,findButton;

    public ProductDetails() {
        setTitle("Product Details");
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon i1 = new ImageIcon("src\\icons\\homepage_background32.png");
        Image img = i1.getImage().getScaledInstance(800, 600, Image.SCALE_FAST);
        JLabel image = new JLabel(new ImageIcon(img));
        image.setLayout(new BorderLayout());
        add(image);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Search by Product ID: ");
        searchField = new JTextField(15);
        searchField.setFont(new Font("Arial",Font.BOLD,15));
        pressEnter(searchField);

        ImageIcon i2= new ImageIcon("src\\icons\\search button image.png");
        Image image1= i2.getImage().getScaledInstance(100, 30, Image.SCALE_DEFAULT);
        findButton = new JButton(new ImageIcon(image1));
        findButton.setPreferredSize(new Dimension(100,30));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(findButton);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setShowGrid(false); // Optional: Hide grid lines
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 10));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        image.add(searchPanel, BorderLayout.NORTH);
        image.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
        Image image4= i5.getImage().getScaledInstance(70,60,Image.SCALE_DEFAULT);

        backButton = new JButton(new ImageIcon(image4));
        backButton.setPreferredSize(new Dimension(70,60));
        buttonPanel.add(backButton);
        image.add(buttonPanel, BorderLayout.SOUTH);
        showAllData(model);

        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productId = searchField.getText().trim();
                if (productId.isEmpty()) {
                    showAllData(model);
                } else {
                    findProductById(model, productId);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setVisible(true);
    }

    private void showAllData(DefaultTableModel model) {
        model.setRowCount(0);
        model.setColumnCount(0);

        try (Connection connection = CONN.getConnection();
            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Products")) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findProductById(DefaultTableModel model, String productId) {
        model.setRowCount(0);

        try (Connection connection = CONN.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Products WHERE Product_ID = ?")) {
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Object[] row = new Object[model.getColumnCount()];
                for (int i = 1; i <= model.getColumnCount(); i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pressEnter(JTextField tf){
        if(tf==searchField){
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                        findButton.doClick();
                    }
                }
            });
        }
    }
}
