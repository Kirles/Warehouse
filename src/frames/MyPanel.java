package frames;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    public MyPanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(135, 206, 235); // Начальный цвет (голубой)
        Color color2 = new Color(30, 144, 255);  // Конечный цвет (голубой)
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }
}
