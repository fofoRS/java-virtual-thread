package org.demo.virtualThread.echoServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            
            System.out.println("Server is running on port 8080");

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread.ofVirtual().start(() -> {
                    try {
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                        String incomingMessage;

                        while((incomingMessage = in.readLine()) != null) {
                            out.println(incomingMessage);
                            System.out.println("Echoing: " + incomingMessage);
                        }

                        if (incomingMessage.equals("exit")) {
                            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                            clientSocket.close();
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}