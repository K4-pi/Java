package GUI;

public class RunGUI {
    static ChatGUI chat = new ChatGUI();

    public static void runGUI() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                chat.ShowChatGUI();
            }
        });
    }
}
