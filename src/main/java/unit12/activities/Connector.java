package unit12.activities;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    public static void main(String[] args) throws IOException {
        Socket googleSocket = new Socket("www.google.com", 80);
        googleSocket.close();
        Socket nprSocket = new Socket("www.npr.org", 443);
        nprSocket.close();
        //Socket swecSocket = new Socket("swec-123.rit.edu", 33075);
        //swecSocket.close();
        Socket localHost = new Socket("localhost", 3333);
        localHost.close();
    }
}
