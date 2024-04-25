package unit12.guessing;

import java.io.IOException;
import java.net.Socket;

public class NetworkGuessingGame {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 42975);
        GuessingGame game = new GuessingGameProxy(socket);

        GamePlayer player = new GamePlayer(game);
        player.playTheGame();
    }
}
