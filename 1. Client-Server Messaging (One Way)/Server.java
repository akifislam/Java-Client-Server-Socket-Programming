package io.github.akifislam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000); // Jodi Port Already Busy thake
        System.out.println("Server Started on PORT 6000.\nListening ....");

        while(true) {
            Socket socket = serverSocket.accept(); // Kew Connect hote chaile Accept korbe
            System.out.println("Server has accepted a new connection");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Client

            try {
                Object clientMessage = ois.readObject();
                System.out.println("Received : " +clientMessage);
                System.out.println("From Client : " + (String)clientMessage);

                String serverMessage = (String) clientMessage;
                serverMessage = serverMessage.toUpperCase();

                // Sent to Client
                oos.writeObject(serverMessage);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not receive message from client :( ");
            }
            System.out.println("End");

        }


    }
}
