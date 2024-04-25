package unit12.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket localhost = new Socket("localhost", 54321);

        //input
        InputStream is = localhost.getInputStream();
        Scanner sc = new Scanner(is);

        //output
        OutputStream os = localhost.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        
        System.out.println("Sending a message...");
        pw.println("null");
        pw.flush();

        System.out.println("Message sent! Waiting for reply...");
        String line = sc.nextLine();
        System.out.println(line);

        sc.close();
        pw.close();
        localhost.close();
    }
}
