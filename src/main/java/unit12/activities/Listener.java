package unit12.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Listener {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(22);

        Socket client = server.accept();
        InputStream is = client.getInputStream();
        Scanner sc = new Scanner(is);
        String line = sc.nextLine();
        System.out.println(line);

        sc.close();
        client.close();
        server.close();
    }

}
