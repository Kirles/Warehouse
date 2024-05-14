package frames.adminFrames;

import frames.ImagePanel;

import javax.swing.*;

public class AdminFrame extends JFrame {

    private static JButton addArticleButton, addWarehouseButton, addArticleTypeButton,
            allArticlesButton, allSupplayButton, allHelpsButton, raportButton;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        AdminFrame af = new AdminFrame();
        af.setTitle("Warehouse");
        af.setResizable(false);
        af.setSize(350, 470);
        af.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        addArticleButton = new JButton("Додати предмет");
        addArticleButton.setBounds(65, 50, 200, 25);

        addArticleButton.addActionListener(e -> {
            AddArticleFrame.frame();
            af.dispose();
        });

        addArticleTypeButton = new JButton("Додати категорію предмету");
        addArticleTypeButton.setBounds(65, 100, 200, 25);

        addArticleTypeButton.addActionListener(e -> {
            AddArticleTypeFrame.frame();
            af.dispose();
        });

        addWarehouseButton = new JButton("Додати склад");
        addWarehouseButton.setBounds(65, 150, 200, 25);

        addWarehouseButton.addActionListener(e -> {
            AddWarehouseFrame.frame();
            af.dispose();
        });

        allArticlesButton = new JButton("Усі продукти на складах");
        allArticlesButton.setBounds(65, 200, 200, 25);

        allArticlesButton.addActionListener(e -> {

            af.dispose();
        });

        allSupplayButton = new JButton("Усі поставки");
        allSupplayButton.setBounds(65, 250, 200, 25);

        allSupplayButton.addActionListener(e -> {

            af.dispose();
        });

        allHelpsButton = new JButton("Уся допомога");
        allHelpsButton.setBounds(65, 300, 200, 25);

        allHelpsButton.addActionListener(e -> {

            af.dispose();
        });

        raportButton = new JButton("Звіт");
        raportButton.setBounds(65, 350, 200, 25);

        raportButton.addActionListener(e -> {

            af.dispose();
        });

        af.add(addArticleButton);
        af.add(addArticleTypeButton);
        af.add(addWarehouseButton);
        af.add(allArticlesButton);
        af.add(allSupplayButton);
        af.add(allHelpsButton);
        af.add(raportButton);
        af.add(panel);

        af.setVisible(true);
    }
}
