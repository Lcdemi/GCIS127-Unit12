package unit12.duplexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Duplexer {
    private final Socket socket;
    private final PrintWriter pw;
    private final BufferedReader br;
    
    public Duplexer(Socket socket) throws IOException {
        this.socket = socket;
        this.pw = new PrintWriter(socket.getOutputStream());
        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        this.br = new BufferedReader(ir);
    }

    public void send(String message) {
        System.out.println(">> " + message);
        pw.println(message);
        pw.flush();
    }

    public String receive() throws IOException {
        String message = br.readLine();
        System.out.println("<< " + message);
        return message;
    }

    public void close() throws IOException {
        this.socket.close();
        this.pw.close();
        this.br.close();
    }
}
