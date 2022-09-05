// No Thread, Short Code Edition

import java.nio.charset.StandardCharsets;
import javax.net.ssl.*;
import java.io.*;
import java.util.Base64;

public class SMTP_Mail {
    // Credentials
    public static String user = "iamakifislam@gmail.com";
    public static String pass = "passworddekharetosokhkeno";
    private static DataOutputStream dataOutputStream;
    public static BufferedReader br = null;

    public static void main(String[] args) throws Exception {
        int delay = 1000;

        String username = 
Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        String password = 
Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) 
SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) 
sslSocketFactory.createSocket("smtp.gmail.com", 465);

        br = new BufferedReader(new 
InputStreamReader(sslSocket.getInputStream()));

        dataOutputStream = new 
DataOutputStream(sslSocket.getOutputStream());

        send("EHLO smtp.gmail.com\r\n",9);
        send("AUTH LOGIN\r\n",1);
        send(username+"\r\n",1);
        send(password+"\r\n",1);
        send("MAIL FROM:<iamakifislam@gmail.com>\r\n",1);
        send("RCPT TO:<jakepathabo@gmail.com>\r\n",1);
        send("DATA\r\n",1);
        send("Subject: Thanks for stealing my heart\r\n",0);
        send("Assalamu Alaikum\r\n",0);
        send(".\r\n",1);
        send("QUIT\r\n",1);
    }
    private static void send(String s, int no_of_response_line) throws 
Exception
    {
        dataOutputStream.writeBytes(s);
        System.out.println("CLIENT: "+s);
        Thread.sleep(1000);

        // Just reading the number of lines the server will respond.
        for (int i = 0; i < no_of_response_line; i++) {
            System.out.println("SERVER : " +br.readLine());
        }
    }

}
