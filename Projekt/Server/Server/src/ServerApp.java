import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ServerApp {

    public static void RunApp() throws IOException {

        JFrame frame = new JFrame("Server - 127.0.0.1:");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(750, 500);

//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                System.out.println("Closing window");
//                try {
//                    server.stop(); //close server port on exit
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });

        // Text display area
        JTextArea chatDisplay = new JTextArea();
        chatDisplay.setEditable(false);
        chatDisplay.setText("Hello \nWorld!");
//        chatDisplay.append();
        chatDisplay.setPreferredSize(new Dimension(500, 450));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(chatDisplay, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

}
