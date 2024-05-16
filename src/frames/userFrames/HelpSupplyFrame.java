package frames.userFrames;

import database.*;
import frames.MyPanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelpSupplyFrame extends JFrame {

    private static JLabel nameL, amountL;
    private static JTextField amountF;
    private static JComboBox nameF;
    private static JButton addButton, deleteButton, helpSupplyButton, exitButton;
    private static JTable jt;
    private static int row;

    private static final String[] header = new String[] {
            "Article name",
            "Amount",
    };

    private static final DefaultTableModel dtm = new DefaultTableModel(0, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public static void frame(int user_id, boolean volunteer) {
        HelpSupplyFrame hf = new HelpSupplyFrame();
        hf.setTitle("Warehouse");
        hf.setResizable(false);
        hf.setSize(435, 800);
        hf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyPanel panel = new MyPanel();

        nameL = new JLabel();
        nameL.setText("Назва");
        nameL.setBounds(10, 10, 100, 25);
        hf.add(nameL);

        List<String> optionsList = Article.getArticle();
        int size = optionsList.size();
        String[] options = optionsList.toArray(new String[size]);
        nameF = new JComboBox<>(options);
        nameF.setBounds(100, 10, 250, 25);
        hf.add(nameF);

        amountL = new JLabel();
        amountL.setText("Кількість");
        amountL.setBounds(10, 60, 100, 25);
        hf.add(amountL);

        amountF = new JTextField();
        amountF.setBounds(100, 60, 100, 25);
        hf.add(amountF);

        addButton = new JButton();
        addButton.setText("Додати");
        addButton.setBounds(10, 110, 100, 25);
        hf.add(addButton);
        addButton.addActionListener(e -> {
            String name = Objects.requireNonNull(nameF.getSelectedItem()).toString();
            String amount = amountF.getText();
            if (name.isEmpty() || amount.isEmpty()) {
                JOptionPane.showMessageDialog(hf, "Недостатньо інформації");
            }
            else {
                Object[] objs = {
                        name,
                        amount
                };
                dtm.addRow(objs);
                amountF.setText("");
            }
        });

        deleteButton = new JButton();
        deleteButton.setText("Видалити");
        deleteButton.setBounds(120, 110, 100, 25);
        hf.add(deleteButton);
        deleteButton.addActionListener(e -> {
            if(row != -1) {
                dtm.removeRow(row);
                row = -1;
            }
        });

        jt = new JTable();
        jt.setModel(dtm);
        dtm.setColumnIdentifiers(header);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(10, 150, 400, 600);
        hf.add(sp);
        jt.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                row = jt.rowAtPoint(evt.getPoint());
            }
        });

        helpSupplyButton = new JButton();
        helpSupplyButton.setText("Виконати");
        helpSupplyButton.setBounds(300, 110, 100, 25);
        hf.add(helpSupplyButton);

        helpSupplyButton.addActionListener(e -> {
            if(dtm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(hf, "Недостатньо інформації");
            }
            else {
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = currentDate.format(formatter);

                String[] warehouses = Warehouse.getAllWarehouseName();
                String selectedWarehouse = Warehouse.showComboBoxInputDialog(hf, warehouses, "Оберіть склад:");
                int warehouse_id = Warehouse.getWarehouseID(selectedWarehouse);
                int order_type;
                if(volunteer) {
                    order_type = 2;
                }
                else{
                    order_type = 1;
                }
                Order order = new Order(order_type, user_id, false,  formattedDate, warehouse_id);
                int order_id = Order.addOrder(order);
                List<Integer> idArticleList = new ArrayList<>();
                List<Integer> amountList = new ArrayList<>();
                while (dtm.getRowCount() > 0) {
                    String article_name = String.valueOf(dtm.getValueAt(0, 0));
                    int id = Article.getArticleID(article_name);
                    idArticleList.add(id);
                    int amount = Integer.parseInt((String) dtm.getValueAt(0, 1));
                    amountList.add(amount);
                    dtm.removeRow(0);
                }
                int count = 0;
                for (int i = 0; i < amountList.size(); i++) {
                    int articleID = idArticleList.get(i);
                    int amount = amountList.get(i);
                    OrderItems orderItems = new OrderItems(order_id, articleID, amount);
                    OrderItems.addOrderItems(orderItems);
                    Stock stock = new Stock(articleID, amount, warehouse_id);
                    count += Stock.updateStock(stock, volunteer);
                }
                if(count > 0) {
                    Order.updateOrderStatus(order_id);
                }
            }

        });

        exitButton = new JButton();
        exitButton.setText("Вихід");
        exitButton.setBounds(300, 70, 100, 25);
        hf.add(exitButton);

        exitButton.addActionListener(e -> {
            if(volunteer){
                VolunteerFrame.frame(user_id);
                hf.dispose();
            }
            else {
                VictimFrame.frame(user_id);
                hf.dispose();
            }
        });

        hf.add(panel);
        hf.setVisible(true);
    }

}
