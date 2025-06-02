import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBox extends JFrame{
    private JPanel upper;
    private JButton OkButton;
    private JButton wrocButton;
    private JButton wyjscieButton;
    private JComboBox comboBox1;
    private JPanel MainPanel;

    public ComboBox(){
        super("Przykład ComboBox");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 400, height = 450;
        this.setSize(width,height);

        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = "Wybrano język: " + comboBox1.getItemAt(comboBox1.getSelectedIndex());
                JOptionPane.showMessageDialog(ComboBox.this,data);
            }
        });
        wrocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
        wyjscieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
