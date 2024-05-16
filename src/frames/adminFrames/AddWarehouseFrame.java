package frames.adminFrames;

import database.Address;
import database.Warehouse;
import frames.MyPanel;

import javax.swing.*;

public class AddWarehouseFrame extends JFrame {

    private static JTextField nameF, streetF, buildF;
    private static JLabel nameL, streetL, buildL;
    private static JButton addButton, exitButton;

    public static void frame() {
        AddWarehouseFrame awf = new AddWarehouseFrame();
        awf.setTitle("Warehouse");
        awf.setResizable(false);
        awf.setSize(400, 260);
        awf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyPanel panel = new MyPanel();

        nameL = new JLabel();
        nameL.setText("Назва");
        nameL.setBounds(10, 20, 100, 25);
        awf.add(nameL);

        nameF = new JTextField();
        nameF.setBounds(100, 20, 250, 25);
        awf.add(nameF);

        streetL = new JLabel();
        streetL.setText("Вулиця");
        streetL.setBounds(10, 70, 100, 25);
        awf.add(streetL);

        streetF = new JTextField();
        streetF.setBounds(100, 70, 250, 25);
        awf.add(streetF);

        buildL = new JLabel();
        buildL.setText("Будинок");
        buildL.setBounds(10, 120, 100, 25);
        awf.add(buildL);

        buildF = new JTextField();
        buildF.setBounds(100, 120, 250, 25);
        awf.add(buildF);

        addButton = new JButton();
        addButton.setText("Додати");
        addButton.setBounds(150, 170, 100, 25);
        awf.add(addButton);
        addButton.addActionListener(e -> {
            if(nameF.getText().isEmpty() || streetF.getText().isEmpty() || buildF.getText().isEmpty()){
                JOptionPane.showMessageDialog(new AddArticleFrame(), "Недостатньо інформації.", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Address address = new Address(streetF.getText(), buildF.getText());
                Address.addAddress(address);
                Warehouse warehouse = new Warehouse(nameF.getText(), Address.getAddressID(address));
                Warehouse.addWarehouse(warehouse);
                nameF.setText("");
                streetF.setText("");
                buildF.setText("");
            }
        });

        exitButton = new JButton();
        exitButton.setText("Назад");
        exitButton.setBounds(35, 170, 70, 25);
        awf.add(exitButton);
        exitButton.addActionListener(e -> {
            AdminFrame.frame();
            awf.dispose();
        });

        awf.add(panel);
        awf.setVisible(true);
    }

}
