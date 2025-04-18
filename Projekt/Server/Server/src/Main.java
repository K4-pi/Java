// OpenJDK 24
// JavaFx version 24.0.1 x64 Windows SDK

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();

        server.start(9090);
//        server.stop();

    }
}