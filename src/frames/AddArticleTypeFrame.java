package frames;

import database.ArticleType;
import javax.swing.*;

public class AddArticleTypeFrame extends JFrame {
    private static JTextField typeF;
    private static JLabel typeL;
    private static JButton addButton, exitButton;

    public static void main(String[] args) {
        frame();
    }

    public static void frame() {
        AddArticleTypeFrame aatf = new AddArticleTypeFrame();
        aatf.setTitle("Warehouse");
        aatf.setResizable(false);
        aatf.setSize(400, 150);
        aatf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("source/nebo.jpg");

        typeL = new JLabel();
        typeL.setText("Name");
        typeL.setBounds(10, 20, 100, 25);
        aatf.add(typeL);

        typeF = new JTextField();
        typeF.setBounds(100, 20, 250, 25);
        aatf.add(typeF);

        addButton = new JButton();
        addButton.setText("Додати");
        addButton.setBounds(150, 70, 100, 25);
        aatf.add(addButton);
        addButton.addActionListener(e -> {
            if(typeF.getText().isEmpty()){
                JOptionPane.showMessageDialog(new AddArticleFrame(), "Недостатньо інформації.", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                ArticleType.addArticleType(typeF.getText());
                typeF.setText("");
            }
        });

        exitButton = new JButton();
        exitButton.setText("Назад");
        exitButton.setBounds(35, 70, 70, 25);
        aatf.add(exitButton);
        exitButton.addActionListener(e -> {
            AdminFrame.frame();
            aatf.dispose();
        });

        aatf.add(panel);
        aatf.setVisible(true);
    }
}
