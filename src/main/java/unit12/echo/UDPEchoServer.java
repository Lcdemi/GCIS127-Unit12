package unit12.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoServer {
    public static void main(String[] args) throws IOException {
        byte[] buffer = new byte[1024];
        //incoming
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

        DatagramSocket socket = new DatagramSocket(54321);
        socket.receive(incoming);

        byte[] data = incoming.getData();
        int length = incoming.getLength();
        String message = new String(data, 0, length);
        System.out.println(message);

        //outgoing
        InetAddress returnAddress = incoming.getAddress();
        int returnPort = incoming.getPort();
        message = "echo >> " + message + " << echo";
        DatagramPacket reply = new DatagramPacket(message.getBytes(), message.length(), returnAddress, returnPort);
        socket.send(reply);

        socket.close();
    }
}
