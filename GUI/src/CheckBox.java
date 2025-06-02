import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBox extends JFrame{
    private JPanel upper;
    private JButton OkButton;
    private JButton wrocButton;
    private JButton wyjscieButton;
    private JCheckBox javaPrice;
    private JCheckBox chPrice;
    private JCheckBox pythonPrice;
    private JCheckBox cppPrice;
    private JPanel MainPanel;

    public int total = 0;
    public String text = "";

    public CheckBox(){
        super("Przykład check box");
        this.setContentPane(this.MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 400, height = 450;
        this.setSize(width,height);
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(javaPrice.isSelected()){
                    total+=3500;
                    text+="Java 3 500PLN\n";
                }
                if(cppPrice.isSelected()){
                    total+=4000;
                    text+="C++ 4 000PLN\n";
                }
                if(chPrice.isSelected()){
                    total+=4500;
                    text+="C# 4 500PLN\n";
                }
                if(pythonPrice.isSelected()){
                    total+=5000;
                    text+="Python 5 000PLN\n";
                }
                JOptionPane.showMessageDialog(CheckBox.this,"Wybrano nastepujące kursy:\n" + text + "Łączna cena kursów: " + String.valueOf(total) + "PLN");
                total=0;
                text="";
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
