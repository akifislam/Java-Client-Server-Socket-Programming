// This Client-Server can chat together but only 1 message at each time.
// Suppose, Client can only send 1 message to Server and cannot send anymore until he/she gets reply from Server.
// This is a problem and I hope I will solve in next update.
// Akif Islam

package io.github.akifislam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000); // Jodi Port Already Busy thake
        System.out.println("Server Started on PORT 6000.\nListening ....");
        Socket socket = serverSocket.accept(); // Kew Connect hote chaile Accept korbe
        System.out.println("Server has accepted a new connection");
        Scanner sc = new Scanner(System.in);
        while (true) {

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Client

            try {
                Object clientMessage = ois.readObject();
                System.out.println("Client : " + (String) clientMessage);

                String serverMessage = (String) clientMessage;
                String toClientMessage = sc.nextLine();
                serverMessage = toClientMessage;

                // Sent to Client
                oos.writeObject(serverMessage);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not receive message from client :( ");
            }

        }


    }
}
