package frames;

import javax.swing.*;

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

        addArticleButton.addActionListener(e -> {
            AddArticleFrame.frame();
            af.dispose();
        });

        addArticleTypeButton = new JButton("Додати категорію предмету");
        addArticleTypeButton.setBounds(45, 100, 200, 25);

        addArticleTypeButton.addActionListener(e -> {
            AddArticleTypeFrame.frame();
            af.dispose();
        });

        addWarehouseButton = new JButton("Додати склад");
        addWarehouseButton.setBounds(45, 150, 200, 25);

        addWarehouseButton.addActionListener(e -> {
            AddWarehouseFrame.frame();
            af.dispose();
        });

        af.add(addArticleButton);
        af.add(addArticleTypeButton);
        af.add(addWarehouseButton);
        af.add(panel);

        af.setVisible(true);
    }
}
