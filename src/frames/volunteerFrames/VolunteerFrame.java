package frames.volunteerFrames;

import database.Warehouse;
import frames.ImagePanel;
import frames.adminFrames.AddArticleFrame;

import javax.swing.*;

public class VolunteerFrame extends JFrame {

    private static JButton allWarehouseButton, supplayButton;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        VolunteerFrame vf = new VolunteerFrame();
        vf.setTitle("Warehouse");
        vf.setResizable(false);
        vf.setSize(300, 270);
        vf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        supplayButton = new JButton("Поставити продукти");
        supplayButton.setBounds(45, 50, 200, 25);

        supplayButton.addActionListener(e -> {

            vf.dispose();
        });

        allWarehouseButton = new JButton("<html><div style='text-align: center;'>Переглянути<br>гуманітарні центри</div></html>");
        allWarehouseButton.setBounds(45, 100, 200, 50);

        allWarehouseButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(new AddArticleFrame(), Warehouse.getAllWarehouse(), "Гуманітарні центри", JOptionPane.PLAIN_MESSAGE);
        });


        vf.add(supplayButton);
        vf.add(allWarehouseButton);
        vf.add(panel);

        vf.setVisible(true);
    }
}
