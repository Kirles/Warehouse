package frames.userFrames;

import database.Article;
import database.Warehouse;
import frames.MyPanel;
import frames.adminFrames.AddArticleFrame;

import javax.swing.*;
import java.util.Arrays;

public class VolunteerFrame extends JFrame {

    private static JButton allWarehouseButton, supplayButton, allArticlesButton;

    public static void main(String[] args) {
        frame(1);
    }

    public static void frame(int user_id) {
        VolunteerFrame vf = new VolunteerFrame();
        vf.setTitle("Warehouse");
        vf.setResizable(false);
        vf.setSize(300, 300);
        vf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyPanel panel = new MyPanel();

        supplayButton = new JButton("Поставити продукти");
        supplayButton.setBounds(45, 50, 200, 25);

        supplayButton.addActionListener(e -> {
            HelpSupplyFrame.frame(user_id, true);
            vf.dispose();
        });

        allWarehouseButton = new JButton("<html><div style='text-align: center;'>Переглянути<br>гуманітарні центри</div></html>");
        allWarehouseButton.setBounds(45, 100, 200, 50);

        allWarehouseButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(new AddArticleFrame(), Warehouse.getAllWarehouse(), "Гуманітарні центри", JOptionPane.PLAIN_MESSAGE);
        });

        allArticlesButton = new JButton("Які продукти потребуються");
        allArticlesButton.setBounds(45, 175, 200, 25);

        allArticlesButton.addActionListener(e -> {
            JTextArea textArea = new JTextArea(20, 25);
            textArea.setText(Article.getArticleNames());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(null, scrollPane, "Які продукти потребуються", JOptionPane.PLAIN_MESSAGE);
        });


        vf.add(allArticlesButton);
        vf.add(supplayButton);
        vf.add(allWarehouseButton);
        vf.add(panel);

        vf.setVisible(true);
    }
}
