package io.github.akifislam;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static Socket socket;

    public static void main(String[] args) throws IOException {
        String HOST = "127.0.0.1";
        int PORT = 6000;
        System.out.println("Client Started");
        Scanner sc = new Scanner(System.in);

        System.out.println("Client Connected with Server");



        System.out.println("Enter a message to send to server");

        while (true) {
            socket = new Socket(HOST, PORT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Server
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Server

            String message = sc.nextLine();

            System.out.println("Sending " +message + " to server");
            oos.writeObject(message); // Sending to Server

            try {
                if(message.startsWith("-d")){
                    byte[] contents = new byte[10000];
                    //Initialize the FileOutputStream to the output file's full path.
                    FileOutputStream fos = new FileOutputStream("/Users/akifislam/IdeaProjects/SocketProgramming/src/io/github/akifislam/FTP/ClientDirectory/download.pdf");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    InputStream is = socket.getInputStream(); //No of bytes read in one read() call
                    int bytesRead = 0;
                    while ((bytesRead = is.read(contents)) != -1)
                        bos.write(contents, 0, bytesRead);
                    bos.flush();
                    System.out.println("File saved successfully!");
                }
                else {
                    Object fromServerReply = ois.readObject();
                    System.out.println("Server : " + (String) fromServerReply);
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


    }
}

