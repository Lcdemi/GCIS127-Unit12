package unit12.intersection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class SIClient {
    private DatagramSocket socket;
    private String SERVER_NAME;
    private int port;
    private Set<String> X;
    private Set<String> setA;

    public SIClient(String serverName, int port, Set<String> setA) throws IOException {
        this.SERVER_NAME = serverName;
        this.port = port;
        this.socket = new DatagramSocket();
        this.X = new HashSet<>();
        this.setA = new HashSet<>(setA);
    }

    public void mainLoop() throws IOException {
        String element = "";
        boolean ended = false;
        while(setA.size() > 0) {
            //System.out.println(element); //testing
            for(String string : setA) {
                element = string;
                break;
            }
            setA.remove(element);
            send(element);
            System.out.println("Client Sending: " + element);
            String response = receive();
            System.out.println("Client Receiving: " + response);
            if(response.equals("END")) {
                if(!(element.equals(response)) && !(response.equals("NO"))) {
                    setA.remove(element);
                    //System.out.println("Added: " + element);
                    X.add(element);
                }
                ended = true;
                System.out.println("Client Sending: END");
                send("END");
                break;
            } else if(response.equals("NO")) {
                do {
                    response = receive();
                    System.out.println("Client Receiving: " + response);
                } while(response.equals("NO"));
            } else {
                if(!(element.equals(response)) && !(response.equals("NO"))) {
                    setA.remove(element);
                    //System.out.println("Added: " + element);
                    X.add(element);
                }
            }
            
            if(setA.contains(response)) {
                setA.remove(response);
                X.add(response);
            } else {
                send("NO");
                System.out.println("Client Sending: NO");
            }
        }
        if(ended == false) {
            System.out.println("Client Sending: END");
            send("END");
        }
    }

    public void send(String message) throws IOException {
        DatagramPacket outgoing = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(SERVER_NAME), port);
        socket.send(outgoing);
    }

    public String receive() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        socket.receive(incoming);
        return new String(incoming.getData(), 0, incoming.getLength());
    }

    public Set<String> getIntersection() {
        return X;
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Set<String> setA = Sets.A4();
        SIClient client = new SIClient("localhost", 54321, setA);
        // String message = "Hi I'm Saul Goodman. Did you know that you have rights? The constitution says you do. And so do I. I believe that until proven guilty, every man, woman, and child in this country is innocent. And that's why I fight for you, Albuquerque! Better Call Saul!";
        // client.send(message);
        // String response = client.receive();
        // System.out.println(response);
        client.mainLoop();
        System.out.println("Intersection: " + client.getIntersection());
        client.close();
    }
}
