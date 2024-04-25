package unit12.tinychat;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import unit12.duplexer.Duplexer;

public class TinyChatServer extends Duplexer implements Runnable {
    
    public TinyChatServer(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void run() {
        try {
            String clientName = receive(); //gets name and connects client
            System.out.println(clientName + ": Connected");
            send("Connected");

            boolean sentinel = true;
            while(sentinel) {
                String message = receive();
                String[] tokens = message.split(" ");
                switch(tokens[0]) {
                    case "Quit":
                        System.out.println(clientName + ": Quit");
                        send(clientName + ": Quit");
                        break;
                    case "RESTART":
                        break;
                    case "GUESS":
                        int guess = Integer.parseInt(tokens[1]);
                        break;
                    default:
                        break;
                }
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12410);
        Socket socket = server.accept();
        TinyChatServer tcs = new TinyChatServer(socket);
        tcs.run();
        server.close();
    }
}
