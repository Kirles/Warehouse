package frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddArticleFrame extends JFrame {

    private static JTextField nameF, weightF, manufF;
    private static JLabel nameL, weightL, manufL, artTypeL;
    private static JComboBox artTypeBox;
    private static JButton addButton;

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

        String[] options = {"1", "2", "3"};
        artTypeBox = new JComboBox<>(options);
        artTypeBox.setBounds(100, 170, 250, 25);
        aaf.add(artTypeBox);

        addButton = new JButton();
        addButton.setText("Add");
        addButton.setBounds(150, 220, 100, 25);
        aaf.add(addButton);
        addButton.addActionListener(addButtonListener);

        aaf.add(panel);
        aaf.setVisible(true);
    }

    private static final ActionListener addButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameF.getText().isEmpty() || weightF.getText().isEmpty() || manufF.getText().isEmpty()) {
                JOptionPane.showMessageDialog(new AddArticleFrame(), "Please enter the info.", "Error!", JOptionPane.ERROR_MESSAGE);
                //jtf_foodname.requestFocus();
            }
            else {
                String name = nameF.getText();
                float weight = Float.parseFloat(weightF.getText());
                String manufacture = manufF.getText();
                int artType = Integer.parseInt(Objects.requireNonNull(artTypeBox.getSelectedItem()).toString());
            }

//            else {
//                int result = JOptionPane.showConfirmDialog(, "Insert this food data " + foodname + "?", "Insert",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.QUESTION_MESSAGE);
//                if (result == JOptionPane.YES_OPTION) {
//                    try {
//                        Statement stmt = conn.createStatement();
//                        stmt.executeUpdate("insert into tbl_foods (`food_name`, `food_price`, `food_desc`) VALUES ('" +
//                                foodname + "','" + foodprice + "','" + fooddesc + "')");
//                        loadData();
//                    } catch (Exception err) {
//                        System.out.println(err);
//                    }
//                }
//            }
        }
    };
}
