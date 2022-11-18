import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        int limit = 5;

        while (limit>0) {

            System.out.println("Ogo Bolo... Shunchi !");

            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            String message = (String) (dataInputStream.readUTF()).toUpperCase();
            System.out.println(message);

            limit--;
        }
        serverSocket.close();
    }
}

