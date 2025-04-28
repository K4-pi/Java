import Database.DataTransfer;

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Server {
    private ServerSocket serverSocket;
    private final int port;

    private static String inputLine = "";

    static DataTransfer db = new DataTransfer();

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private final Socket clientSocket;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            PrintWriter out;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//            String inputLine;
            while (true) {
                try {
                    inputLine = in.readLine();
                    if (inputLine == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }
//                bufferToSave = inputLine;
                System.out.println("Received from client: " + inputLine);
//                System.out.println("this is buffer: " + bufferToSave);
                out.println(inputLine);

                if (!inputLine.isEmpty()) {
//                    Timestamp now = Timestamp.valueOf(LocalDateTime.now());
                    db.addMessage("testUser", inputLine);
                }
            }

            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.close();
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
