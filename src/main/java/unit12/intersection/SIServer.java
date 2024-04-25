package unit12.intersection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class SIServer {
    private DatagramSocket socket;
    private DatagramPacket packetIn;
    private Set<String> X;
    private Set<String> setB;

    public SIServer(int port, Set<String> setB) throws IOException {
        this.socket = new DatagramSocket(port);
        byte[] buffer = new byte[1024];
        this.packetIn = new DatagramPacket(buffer, buffer.length);
        this.X = new HashSet<>();
        this.setB = new HashSet<>(setB);
    }

    public void mainLoop() throws IOException {
        String element = ""; String previousReceived = "";
        int counter = 0;
        while(true) {
            String received = receive();
            System.out.println("Server Receiving: " + received);
            //System.out.println(setB);
            if(received.equals("END")) {
                System.out.println("Server Receiving: END");
                if(!previousReceived.equals("NO")) {
                    X.add(element);
                }
                break;
            } else if(received.equals("NO")) {
                setB.remove(element);
            } else if(setB.contains(received)) {
                //System.out.println("Added: " + received);
                X.add(received);
            } else {
                send("NO");
                System.out.println("Server Sending: NO");
            }

            if(!(element.equals(received)) && !(received.equals("NO")) && counter > 0) {
                setB.remove(element);
                //System.out.println("Added: " + element);
                X.add(element);
            }
            setB.remove(received);
            
            if(!setB.isEmpty()) {
                element = setB.iterator().next();
                send(element);
                counter++;
                System.out.println("Server Sending: " + element);
                //setB.remove(element);
            } else {
                send("END");
                System.out.println("Server Sending: END");
                break;
            }
            //System.out.println(setB);
            previousReceived = received;
        }
    }

    public void send(String message) throws IOException {
        InetAddress returnAddress = packetIn.getAddress();
        int returnPort = packetIn.getPort();
        DatagramPacket reply = new DatagramPacket(message.getBytes(), message.length(), returnAddress, returnPort);
        socket.send(reply);
    }

    public String receive() throws IOException {
        this.socket.receive(packetIn);
        return new String(packetIn.getData(), 0, packetIn.getLength());
    }

    public Set<String> getIntersection() {
        return X;
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Set<String> setB = Sets.B4();
        SIServer server = new SIServer(54321, setB);
        // String receivedMessage = server.receive();
        // System.out.println(receivedMessage);
        // server.send(receivedMessage);
        server.mainLoop();
        System.out.println("Intersection: " + server.getIntersection());
        server.close();
    }
}
