import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String args[]) throws Exception {
        String serverIP = "localhost";
        int serverPort = 6789;

        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket(serverIP, serverPort);

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);


        outToServer.println("CONNECT");


        Thread readThread = new Thread(() -> {
            try {
                while (true) {
                    String serverMessage = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        readThread.start();


        String userInput;
        while ((userInput = userInputReader.readLine()) != null) {
            outToServer.println(userInput);
        }
    }
}
