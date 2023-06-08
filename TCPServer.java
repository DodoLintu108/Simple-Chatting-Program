import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String args[]) throws Exception {
            int serverPort = 6789;

        ServerSocket welcomeSocket = new ServerSocket(serverPort);

        Socket connectionSocket = welcomeSocket.accept();

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);


        String clientSentence = inFromClient.readLine();

        if (clientSentence.equals("CONNECT")) {

            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        String clientMessage = inFromClient.readLine();
                        System.out.println("FROM CLIENT: " + clientMessage);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readThread.start();


            BufferedReader serverInputReader = new BufferedReader(new InputStreamReader(System.in));
            String serverInput;
            while ((serverInput = serverInputReader.readLine()) != null) {
                outToClient.println(serverInput);
            }
        }
    }
}
