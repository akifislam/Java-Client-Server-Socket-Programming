package io.github.akifislam;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String HOST = "127.0.0.1";
        int PORT = 6000;
        System.out.println("Client Started");
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Client Connected with Server");



        System.out.println("Enter a message to send to server");

        while (true) {

            String message = sc.nextLine();

            InputStream is = socket.getInputStream();

            // File Downloading
            FileOutputStream fr = new FileOutputStream("/Users/akifislam/IdeaProjects/SocketProgramming/FTP/ClientDirectory/test1.java");
            byte b[] = new byte[90000];
            is.read(b,0,b.length);
            fr.write(b,0,b.length);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Server
            ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Server


//            oos.writeObject(message); // Sending to Server

            try {
                Object fromServerReply = ois.readObject();
                System.out.println("Server : " + (String) fromServerReply);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


    }


}

