package frames.adminFrames;

import database.Address;
import database.Warehouse;
import frames.ImagePanel;

import javax.swing.*;

public class UpdateWarehouseFrame extends JFrame {

    private static JTextField nameF, streetF, buildF;
    private static JLabel nameL, streetL, buildL;
    private static JButton addButton, exitButton;

    public static void frame(String name) {
        UpdateWarehouseFrame uwf = new UpdateWarehouseFrame();
        uwf.setTitle("Warehouse");
        uwf.setResizable(false);
        uwf.setSize(400, 260);
        uwf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        nameL = new JLabel();
        nameL.setText("Назва");
        nameL.setBounds(10, 20, 100, 25);
        uwf.add(nameL);

        nameF = new JTextField(name);
        nameF.setBounds(100, 20, 250, 25);
        uwf.add(nameF);

        Address address = Address.getAddress(name);

        streetL = new JLabel();
        streetL.setText("Вулиця");
        streetL.setBounds(10, 70, 100, 25);
        uwf.add(streetL);

        assert address != null;
        streetF = new JTextField(address.getStreet());
        streetF.setBounds(100, 70, 250, 25);
        uwf.add(streetF);

        buildL = new JLabel();
        buildL.setText("Будинок");
        buildL.setBounds(10, 120, 100, 25);
        uwf.add(buildL);

        buildF = new JTextField(address.getBuilding());
        buildF.setBounds(100, 120, 250, 25);
        uwf.add(buildF);

        addButton = new JButton();
        addButton.setText("Змінити");
        addButton.setBounds(150, 170, 100, 25);
        uwf.add(addButton);
        addButton.addActionListener(e -> {
            if(nameF.getText().isEmpty() || streetF.getText().isEmpty() || buildF.getText().isEmpty()){
                JOptionPane.showMessageDialog(new AddArticleFrame(), "Недостатньо інформації.", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Warehouse.updateWarehouse(name, nameF.getText(), streetF.getText(), buildF.getText());
                uwf.dispose();
            }
        });

        exitButton = new JButton();
        exitButton.setText("Назад");
        exitButton.setBounds(35, 170, 70, 25);
        uwf.add(exitButton);
        exitButton.addActionListener(e -> {
            AdminFrame.frame();
            uwf.dispose();
        });

        uwf.add(panel);
        uwf.setVisible(true);
    }

}

