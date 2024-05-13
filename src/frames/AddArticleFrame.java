package frames;

import database.Article;
import database.ArticleType;
import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class AddArticleFrame extends JFrame {

    private static JTextField nameF, weightF, manufF;
    private static JLabel nameL, weightL, manufL, artTypeL;
    private static JComboBox artTypeBox;
    private static JButton addButton, exitButton;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        AddArticleFrame aaf = new AddArticleFrame();
        aaf.setTitle("Warehouse");
        aaf.setResizable(false);
        aaf.setSize(400, 320);
        aaf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        nameL = new JLabel();
        nameL.setText("Name");
        nameL.setBounds(10, 20, 100, 25);
        aaf.add(nameL);

        nameF = new JTextField();
        nameF.setBounds(100, 20, 250, 25);
        aaf.add(nameF);

        weightL = new JLabel();
        weightL.setText("Wight");
        weightL.setBounds(10, 70, 100, 25);
        aaf.add(weightL);

        weightF = new JTextField();
        weightF.setBounds(100, 70, 250, 25);
        aaf.add(weightF);

        manufL = new JLabel();
        manufL.setText("Manufacture");
        manufL.setBounds(10, 120, 100, 25);
        aaf.add(manufL);

        manufF = new JTextField();
        manufF.setBounds(100, 120, 250, 25);
        aaf.add(manufF);

        artTypeL = new JLabel();
        artTypeL.setText("Type");
        artTypeL.setBounds(10, 170, 100, 25);
        aaf.add(artTypeL);

        List<String> optionsList = ArticleType.getArticleTypes();
        int size = optionsList.size();
        String[] options = optionsList.toArray(new String[size]);
        artTypeBox = new JComboBox<>(options);
        artTypeBox.setBounds(100, 170, 250, 25);
        aaf.add(artTypeBox);

        addButton = new JButton();
        addButton.setText("Додати");
        addButton.setBounds(150, 220, 100, 25);
        aaf.add(addButton);
        addButton.addActionListener(e -> {
            if(nameF.getText().isEmpty() || weightF.getText().isEmpty() || manufF.getText().isEmpty()){
                JOptionPane.showMessageDialog(new AddArticleFrame(), "Недостатньо інформації.", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int category_id = ArticleType.getArticleTypeID(Objects.requireNonNull(artTypeBox.getSelectedItem()).toString());
                Article article = new Article(nameF.getText(), Float.parseFloat(weightF.getText()), manufF.getText(), category_id);
                Article.addArticle(article);
                nameF.setText("");
                weightF.setText("");
                manufF.setText("");
            }
        });

        exitButton = new JButton();
        exitButton.setText("Назад");
        exitButton.setBounds(35, 220, 70, 25);
        aaf.add(exitButton);
        exitButton.addActionListener(e -> {
            AdminFrame.frame();
            aaf.dispose();
        });

        aaf.add(panel);
        aaf.setVisible(true);
    }


}
