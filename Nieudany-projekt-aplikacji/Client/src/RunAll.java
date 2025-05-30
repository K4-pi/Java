import GUI.ChatGUI;
import connection.Client;

import java.io.IOException;

public class RunAll {
    static ChatGUI chat = new ChatGUI();

    public static void start() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 9090); //start connection with server

        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                chat.ShowChatGUI(client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
