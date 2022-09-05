import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new 
DataInputStream(socket.getInputStream());
            String message = (String) dataInputStream.readUTF();
            System.out.println(message);
            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
