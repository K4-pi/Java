import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 9090);
        client.sendMessage("hello world!");

        client.stopConnection();
    }
}