package frames.adminFrames;

import database.Article;
import database.ArticleType;
import frames.ImagePanel;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class UpdateArticleFrame extends JFrame {
    private static JTextField nameF, weightF, manufF;
    private static JLabel nameL, weightL, manufL, artTypeL;
    private static JComboBox artTypeBox;
    private static JButton addButton, exitButton;

    public static void frame(int id) {
        UpdateArticleFrame uaf = new UpdateArticleFrame();
        uaf.setTitle("Warehouse");
        uaf.setResizable(false);
        uaf.setSize(400, 320);
        uaf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        Article article = Article.getArticleByID(id);

        nameL = new JLabel();
        nameL.setText("Назва");
        nameL.setBounds(10, 20, 100, 25);
        uaf.add(nameL);

        assert article != null;
        nameF = new JTextField(article.getName());
        nameF.setBounds(100, 20, 250, 25);
        nameF.setEditable(false);
        uaf.add(nameF);

        weightL = new JLabel();
        weightL.setText("Вага");
        weightL.setBounds(10, 70, 100, 25);
        uaf.add(weightL);

        weightF = new JTextField(String.valueOf(article.getWeight()));
        weightF.setBounds(100, 70, 250, 25);
        uaf.add(weightF);

        manufL = new JLabel();
        manufL.setText("Опис");
        manufL.setBounds(10, 120, 100, 25);
        uaf.add(manufL);

        manufF = new JTextField(article.getManufacture());
        manufF.setBounds(100, 120, 250, 25);
        uaf.add(manufF);

        artTypeL = new JLabel();
        artTypeL.setText("Тип");
        artTypeL.setBounds(10, 170, 100, 25);
        uaf.add(artTypeL);

        List<String> optionsList = ArticleType.getArticleTypes();
        int size = optionsList.size();
        String[] options = optionsList.toArray(new String[size]);
        artTypeBox = new JComboBox<>(options);
        artTypeBox.setSelectedItem("");
        artTypeBox.setBounds(100, 170, 250, 25);
        uaf.add(artTypeBox);

        addButton = new JButton();
        addButton.setText("Оновити");
        addButton.setBounds(150, 220, 100, 25);
        uaf.add(addButton);
        addButton.addActionListener(e -> {
            if(nameF.getText().isEmpty() || weightF.getText().isEmpty() || manufF.getText().isEmpty()){
                JOptionPane.showMessageDialog(new AddArticleFrame(), "Недостатньо інформації.", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int category_id = ArticleType.getArticleTypeID(Objects.requireNonNull(artTypeBox.getSelectedItem()).toString());
                Article newArticle = new Article(nameF.getText(), Float.parseFloat(weightF.getText()), manufF.getText(), category_id);
                Article.updateArticle(id, newArticle);
                uaf.dispose();
                AdminFrame.frame();
            }
        });

        exitButton = new JButton();
        exitButton.setText("Назад");
        exitButton.setBounds(35, 220, 70, 25);
        uaf.add(exitButton);
        exitButton.addActionListener(e -> {
            AdminFrame.frame();
            uaf.dispose();
        });

        uaf.add(panel);
        uaf.setVisible(true);
    }
}
