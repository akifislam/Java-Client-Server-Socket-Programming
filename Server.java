/*

Trying to Make a FTP Server to will react like this :
client to receiver :
"ls" or "dir" - sends all the available file lists
"-d <filename>" - server sends the file to receiver
"-u <filename>" - client sends the file to receiver

*/

/*
* Next Task to Do :
* 1. Check if the file exists or not
* 2. If not, then show an error message.
* */


package io.github.akifislam;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static Socket socket;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);

        while (true) {
           // Jodi Port Already Busy thake
            System.out.println("Server Started on PORT 6000.\nListening ....");
            socket = serverSocket.accept(); // Kew Connect hote chaile Accept korbe
            System.out.println("Server has accepted a new connection");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); // To Receive Data from Client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // To Send data to Client

            try {
                Object clientMessage = ois.readObject();
                System.out.println("Client : " + (String) clientMessage);

                String serverMessage = (String) clientMessage;

                // Sending All File Names
                if (serverMessage.equalsIgnoreCase("dir") || serverMessage.equalsIgnoreCase("ls")) {

                    String dirname = "/Users/akifislam/IdeaProjects/SocketProgramming/FTP/ServerDirectory";

                    // Send File List
                    File f1, fileList[];

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
                    oos.writeObject(serverMessage);
                    oos.flush();


                } else if (serverMessage.startsWith("-d")) {
                    //Getting File Names
                    String filename = "";
                    for (int i = 3; i < serverMessage.length(); i++) {
                        filename+=serverMessage.charAt(i);
                    }
                    System.out.println("Got FileName : " +filename);
                    File file = new File("/Users/akifislam/IdeaProjects/SocketProgramming/FTP/ServerDirectory/" +filename);
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis); //Get socket's output stream
                    OutputStream os = socket.getOutputStream();
                    //Read File Contents into contents array
                    byte[] contents;
                    long fileLength = file.length();
                    long current = 0;
                    long start = System.nanoTime();
                    while (current != fileLength) {
                        int size = 10000;
                        if (fileLength - current >= size)
                            current += size;
                        else {
                            size = (int) (fileLength - current);
                            current = fileLength;
                        }
                        contents = new byte[size];
                        bis.read(contents, 0, size);
                        os.write(contents);
                        System.out.print("Sending file ... " + (current * 100) / fileLength + "% complete!");

                    }

                    os.flush();
                    socket.close();

                } else {
                    oos.writeObject(serverMessage.toUpperCase());
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not receive message from client :( ");
            }
        }


    }
}
