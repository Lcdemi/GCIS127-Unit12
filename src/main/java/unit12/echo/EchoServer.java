package unit12.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(54321);
        Socket client = server.accept();

        InputStream is = client.getInputStream();
        Scanner sc = new Scanner(is);

        OutputStream os = client.getOutputStream();
        PrintWriter pw = new PrintWriter(os);

        System.out.println("Waiting for message...");
        String line = sc.nextLine();
        System.out.println(line);
        line = "echo >> " + line + " << echo";

        System.out.println("Sending echo...");
        pw.println(line);
        pw.flush();
        System.out.println("Done!");

        sc.close();
        pw.close();
        client.close();
        server.close();
    }
}
