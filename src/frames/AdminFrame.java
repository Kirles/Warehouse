package frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {

    private static JButton addArticleButton, addWarehouseButton, addArticleTypeButton;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        AdminFrame af = new AdminFrame();
        af.setTitle("Warehouse");
        af.setResizable(false);
        af.setSize(300, 270);
        af.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        addArticleButton = new JButton("Додати предмет");
        addArticleButton.setBounds(45, 50, 200, 25);

        addArticleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        addArticleTypeButton = new JButton("Додати категорію предмету");
        addArticleTypeButton.setBounds(45, 100, 200, 25);

        addArticleTypeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        addWarehouseButton = new JButton("Додати склад");
        addWarehouseButton.setBounds(45, 150, 200, 25);

        addWarehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        af.add(addArticleButton);
        af.add(addArticleTypeButton);
        af.add(addWarehouseButton);
        af.add(panel);

        af.setVisible(true);
    }
}
