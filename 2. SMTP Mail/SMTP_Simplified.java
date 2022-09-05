// No Thread, Basic, Easier Version

import java.nio.charset.StandardCharsets;
import javax.net.ssl.*;
import java.io.*;
import java.util.Base64;

public class SMTP_Simplified {
    // Credentials
    public static String user = "s1910776135@ru.ac.bd";
    public static String pass = "MonerPasswordBoroPassword";

    private static DataOutputStream dataOutputStream;

    public static void main(String[] args) throws Exception {
        int delay = 1000;

        String username = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        String password = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("smtp.gmail.com", 465);

        final BufferedReader br = new BufferedReader(new 
InputStreamReader(sslSocket.getInputStream()));

        dataOutputStream = new 
DataOutputStream(sslSocket.getOutputStream());

        send("EHLO smtp.gmail.com\r\n");
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());
        System.out.println("SERVER: "+br.readLine());

        send("AUTH LOGIN\r\n");
        System.out.println("SERVER: "+br.readLine());
        send(username+"\r\n");
        System.out.println("SERVER: "+br.readLine());
        send(password+"\r\n");
        System.out.println("SERVER: "+br.readLine());

        send("MAIL FROM:<s1910776135@ru.ac.bd>\r\n");
        System.out.println("SERVER: "+br.readLine());

        send("RCPT TO:<ahanaf019@gmail.com>\r\n");
        System.out.println("SERVER: "+br.readLine());
        send("DATA\r\n");
        System.out.println("SERVER: "+br.readLine());

        send("Subject: Email test\r\n");
        send("Dear Meaw, Keep away from Kalnaginis.\r\n");
        send(".\r\n");
        System.out.println("SERVER: "+br.readLine());
        send("QUIT\r\n");
        System.out.println("SERVER: "+br.readLine());

    }
    private static void send(String s) throws Exception
    {
        dataOutputStream.writeBytes(s);
        System.out.println("CLIENT: "+s);
        Thread.sleep(1000);
    }

}
