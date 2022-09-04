package io.github.akifislam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String HOST = "127.0.0.1";
        int PORT = 6000;
        System.out.println("Client Started");
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Client Connected with Server");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a message to send to server");

        while (true) {

            String message = sc.nextLine();

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Server
            ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Server


            oos.writeObject(message); // Sending to Server

            try {
                Object fromServerReply = ois.readObject();
                System.out.println("Server : " + (String) fromServerReply);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


    }


}

