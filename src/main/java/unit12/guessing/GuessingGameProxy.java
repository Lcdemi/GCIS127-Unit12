package unit12.guessing;

import java.io.IOException;
import java.net.Socket;

import unit12.duplexer.Duplexer;

public class GuessingGameProxy extends Duplexer implements GuessingGame {

    public GuessingGameProxy(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void restart() {
        send("RESTART");
        try {
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GuessResult guess(int number) {
        send("GUESS " + number);
        try {
            String response = receive();
            return GuessResult.valueOf(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void quit() {
        send("QUIT");
        try {
            receive();
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
