package frames.adminFrames;

import database.Article;
import database.ArticleType;
import database.Warehouse;
import frames.ImagePanel;

import javax.swing.*;
import java.util.List;

public class AdminFrame extends JFrame {

    private static JButton addArticleButton, updateArticleButton, deleteArticleButton,
            addWarehouseButton, updateWarehouseButton, deleteWarehouseButton,
            addArticleTypeButton, deleteArticleTypeButton,
            allArticlesButton, allSupplayButton, allHelpsButton, raportButton;
    private static JLabel queryLabel;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        AdminFrame af = new AdminFrame();
        af.setTitle("Warehouse");
        af.setResizable(false);
        af.setSize(630, 500);
        af.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        addArticleButton = new JButton("Додати предмет");
        addArticleButton.setBounds(30, 50, 150, 25);
        af.add(addArticleButton);

        addArticleButton.addActionListener(e -> {
            AddArticleFrame.frame();
            af.dispose();
        });

        updateArticleButton = new JButton("Оновити предмет");
        updateArticleButton.setBounds(230, 50, 150, 25);
        af.add(updateArticleButton);

        updateArticleButton.addActionListener(e -> {
            List<String> articlesList = Article.getArticle();
            String[] articles = articlesList.toArray(new String[0]);
            String selectedArticles = Warehouse.showComboBoxInputDialog(af, articles, "Оберіть продукт:");
            if(selectedArticles != null) {
               UpdateArticleFrame.frame(Article.getArticleID(selectedArticles));
            }
        });

        deleteArticleButton = new JButton("Видалити предмет");
        deleteArticleButton.setBounds(430, 50, 150, 25);
        af.add(deleteArticleButton);

        deleteArticleButton.addActionListener(e -> {

            af.dispose();
        });

        addArticleTypeButton = new JButton("<html><div style='text-align: center;'>Додати категорію<br>предметів</div></html>");
        addArticleTypeButton.setBounds(110, 100, 170, 50);
        af.add(addArticleTypeButton);

        addArticleTypeButton.addActionListener(e -> {
            AddArticleTypeFrame.frame();
            af.dispose();
        });


        deleteArticleTypeButton = new JButton("<html><div style='text-align: center;'>Видалити категорію<br>предметів</div></html>");
        deleteArticleTypeButton.setBounds(330, 100, 170, 50);
        af.add(deleteArticleTypeButton);

        deleteArticleTypeButton.addActionListener(e -> {

            af.dispose();
        });

        addWarehouseButton = new JButton("Додати склад");
        addWarehouseButton.setBounds(30, 175, 150, 25);
        af.add(addWarehouseButton);

        addWarehouseButton.addActionListener(e -> {
            AddWarehouseFrame.frame();
            af.dispose();
        });

        updateWarehouseButton = new JButton("Оновити склад");
        updateWarehouseButton.setBounds(230, 175, 150, 25);
        af.add(updateWarehouseButton);

        updateWarehouseButton.addActionListener(e -> {
            String[] warehouses = Warehouse.getAllWarehouseName();
            String selectedWarehouse = Warehouse.showComboBoxInputDialog(af, warehouses, "Оберіть склад:");
            if(selectedWarehouse != null) {
                UpdateWarehouseFrame.frame(selectedWarehouse);
            }
        });

        deleteWarehouseButton = new JButton("Видалити склад");
        deleteWarehouseButton.setBounds(430, 175, 150, 25);
        af.add(deleteWarehouseButton);

        deleteWarehouseButton.addActionListener(e -> {
            String[] warehouses = Warehouse.getAllWarehouseName();
            String selectedWarehouse = Warehouse.showComboBoxInputDialog(af, warehouses, "Оберіть склад:");
            if(selectedWarehouse != null) {
                Warehouse.deleteWarehouse(selectedWarehouse);
            }
        });

        queryLabel = new JLabel("Запити:");
        queryLabel.setBounds(140, 220, 200, 25);
        af.add(queryLabel);

        allArticlesButton = new JButton("Усі продукти на складах");
        allArticlesButton.setBounds(65, 250, 200, 25);

        allArticlesButton.addActionListener(e -> {

            //af.dispose();
        });

        allSupplayButton = new JButton("Усі поставки");
        allSupplayButton.setBounds(65, 300, 200, 25);

        allSupplayButton.addActionListener(e -> {

            //af.dispose();
        });

        allHelpsButton = new JButton("Уся допомога");
        allHelpsButton.setBounds(65, 350, 200, 25);

        allHelpsButton.addActionListener(e -> {

            //af.dispose();
        });

        raportButton = new JButton("Звіт");
        raportButton.setBounds(65, 400, 200, 25);

        raportButton.addActionListener(e -> {

            //af.dispose();
        });

        af.add(allArticlesButton);
        af.add(allSupplayButton);
        af.add(allHelpsButton);
        af.add(raportButton);
        af.add(panel);

        af.setVisible(true);
    }
}
