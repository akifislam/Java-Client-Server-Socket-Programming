import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int limit = 5;

        while (limit>0) {
            Socket socket = new Socket("127.0.0.1", 8888);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String message = sc.nextLine();
            dataOutputStream.writeUTF(message);

            limit--;

            socket.close();
        }

    }
}
