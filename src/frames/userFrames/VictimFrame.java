package frames.userFrames;

import database.Stock;
import database.Warehouse;
import frames.ImagePanel;
import frames.adminFrames.AddArticleFrame;

import javax.swing.*;

public class VictimFrame extends JFrame {

    private static JButton allWarehouseButton, helpButton, allArticlesButton;

    public static void frame(int user_id) {
        VictimFrame vf = new VictimFrame();
        vf.setTitle("Warehouse");
        vf.setResizable(false);
        vf.setSize(300, 300);
        vf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        helpButton = new JButton("Запросити допомогу");
        helpButton.setBounds(45, 50, 200, 25);

        helpButton.addActionListener(e -> {
            HelpSupplyFrame.frame(user_id, false);
            vf.dispose();
        });

        allWarehouseButton = new JButton("<html><div style='text-align: center;'>Переглянути<br>гуманітарні центри</div></html>");
        allWarehouseButton.setBounds(45, 100, 200, 50);

        allWarehouseButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(new AddArticleFrame(), Warehouse.getAllWarehouse(), "Гуманітарні центри", JOptionPane.PLAIN_MESSAGE);
        });

        allArticlesButton = new JButton("Усі продукти на складах");
        allArticlesButton.setBounds(45, 175, 200, 25);

        allArticlesButton.addActionListener(e -> {
            JTextArea textArea = new JTextArea(20, 25);
            textArea.setText(Stock.allWarehouseProduct());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(null, scrollPane, "Продукти на складах", JOptionPane.PLAIN_MESSAGE);
        });

        vf.add(allArticlesButton);
        vf.add(helpButton);
        vf.add(allWarehouseButton);
        vf.add(panel);

        vf.setVisible(true);
    }
}
