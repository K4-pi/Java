import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButton extends JFrame{
    private JPanel MainPanel;
    private JRadioButton RadioWindows;
    private JRadioButton RadioLinux;
    private JRadioButton RadioMac;
    private JPanel lower;
    private JPanel upper;
    private JLabel iconLabel;
    private JButton OkButton;
    private JButton CloseBtn;

    private ImageIcon iconLinux = new ImageIcon(getClass().getResource("figures/linux-logo-penguin.png"));
    private ImageIcon iconWindows = new ImageIcon(getClass().getResource("figures/windows.png"));
    private ImageIcon iconMacOS = new ImageIcon(getClass().getResource("figures/mac.png"));

    private static ImageIcon resize(ImageIcon src, int width, int height){
        return new ImageIcon(src.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH));
    }

    public RadioButton(){
        super("Przykład radio button");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 400, height = 450;
        this.setSize(width,height);


        CloseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "";
                if(RadioLinux.isSelected()) text = "Linux";
                if(RadioWindows.isSelected()) text = "Windows";
                if(RadioMac.isSelected()) text = "MacOS";

                JOptionPane.showMessageDialog(RadioButton.this,"Wybrano system: " + text);
            }
        });
        RadioLinux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RadioLinux.isSelected())
                    iconLabel.setIcon(resize(iconLinux, 120, 120));
            }
        });

        RadioWindows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RadioWindows.isSelected())
                    iconLabel.setIcon(resize(iconWindows, 120, 120));
            }
        });
        RadioMac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RadioMac.isSelected())
                    iconLabel.setIcon(resize(iconMacOS, 120, 120));
            }
        });
    }
}
