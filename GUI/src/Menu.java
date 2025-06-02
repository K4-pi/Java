import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel MainPanel;
    private JPanel upper;
    private JPanel lower;
    private JButton przykladRadioButtonButton;
    private JButton przykladCheckBoxButton;
    private JButton wylogujButton;
    private JButton wyjscieButton;
    private JButton comboBox1;

    public Menu() {
        super("Menu");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 500, height = 500;
        this.setSize(width,height);

        wylogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm();
                setVisible(true);
            }
        });
        wyjscieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        przykladRadioButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RadioButton radioButton = new RadioButton();
                radioButton.setVisible(true);
            }
        });

        przykladCheckBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CheckBox checkBox = new CheckBox();
                checkBox.setVisible(true);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ComboBox comboBox = new ComboBox();
                comboBox.setVisible(true);
            }
        });
    }
}
