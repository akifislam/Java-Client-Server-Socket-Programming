/*

Trying to Make a FTP Server to will react like this :
client to receiver :
"ls" or "dir" - sends all the available file lists
"-d <filename>" - server sends the file to receiver
"-u <filename>" - client sends the file to receiver

*/


package io.github.akifislam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000); // Jodi Port Already Busy thake
        System.out.println("Server Started on PORT 6000.\nListening ....");
        Socket socket = serverSocket.accept(); // Kew Connect hote chaile Accept korbe
        System.out.println("Server has accepted a new connection");
        Scanner sc = new Scanner(System.in);
        File f1, fileList[];

        while (true) {

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Client

            try {
                Object clientMessage = ois.readObject();
                System.out.println("Client : " + (String) clientMessage);

                String serverMessage = (String) clientMessage;

                // Sending All File Names
                if (serverMessage.equalsIgnoreCase("dir") || serverMessage.equalsIgnoreCase("ls")) {
                    serverMessage = ("Sending all the file names in servers directory");
                    String dirname = "/Users/akifislam/IdeaProjects/SocketProgramming/FTP/ServerDirectory";

                    // Send File List
                    f1 = new File(dirname);
                    fileList = f1.listFiles();

                    // Sort Alphabetically
                    Arrays.sort(fileList);
                    System.out.println(fileList);
                    System.out.println(fileList.toString());

                    serverMessage = "\n";
                    //Sending All File Names
                    for (int i = 0; i < fileList.length; i++) {
                        serverMessage += (fileList[i].getName() + "\n");
                    }

                } else if (serverMessage.startsWith("-d")) {
                    System.out.println("Sending a File from Server");
                    serverMessage = ("Downloading a File");
                    FileInputStream fr = new FileInputStream("/Users/akifislam/IdeaProjects/SocketProgramming/FTP/ServerDirectory/test1.java");
                    byte [] b = new byte[90000];
                    fr.read(b,0,b.length);
                    OutputStream os = socket.getOutputStream();
                    os.write(b,0,b.length);




                } else if (serverMessage.startsWith("-u")) {
                    serverMessage = ("Uploading a File");
                }


                // Sent to Client
                oos.writeObject(serverMessage);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not receive message from client :( ");
            }

        }


    }
}
