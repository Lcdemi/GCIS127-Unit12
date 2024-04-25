package unit12.guessing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import unit12.duplexer.Duplexer;

public class GuessingGameServer extends Duplexer implements Runnable {
    private GuessingGame game;

    public GuessingGameServer(Socket socket) throws IOException {
        super(socket);
        this.game = new GuessingGameImpl();
    }

    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(42975)) {
            while(true) {
                Socket socket = server.accept();
                GuessingGameServer ggs = new GuessingGameServer(socket);
                Thread clientThread = new Thread(ggs);
                clientThread.start();
                //ggs.run();
            }
        }
    }

    @Override
    public void run() {
        try {
            boolean sentinel = true;
            while(sentinel) {
                String request = receive();
                String[] tokens = request.split(" ");
                switch(tokens[0]) {
                    case "QUIT":
                        game.quit();
                        send("GAME_OVER");
                        break;
                    case "RESTART":
                        game.restart();
                        send("RESTARTED");
                        break;
                    case "GUESS":
                        int guess = Integer.parseInt(tokens[1]);
                        GuessResult result = game.guess(guess);
                        send(result.name());
                        break;
                    default:
                        send("ERROR");
                        break;
                }
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
