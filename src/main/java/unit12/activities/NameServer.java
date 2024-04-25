package unit12.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NameServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(42975)) {
            // try-with-resources
            while(true) {
                System.out.println("Listening for connections...");
                Socket client = server.accept();
                System.out.println("...client connected!");

                InputStream is = client.getInputStream();
                Scanner sc = new Scanner(is);
                String line = sc.nextLine();
                System.out.println(line);

                sc.close();
                client.close();
            }
        }
    }
}
