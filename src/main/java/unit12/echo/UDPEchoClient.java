package unit12.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String message = "Hi I'm Saul Goodman. Did you know that you have rights? The constitution says you do. And so do I. I believe that until proven guilty, every man, woman, and child in this country is innocent. And that's why I fight for you, Albuquerque! Better Call Saul!";
        DatagramPacket outgoing = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName("localhost"), 54321);
        socket.send(outgoing);

        byte[] buffer = new byte[1024];
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        socket.receive(incoming); //blocking call
        byte[] data = incoming.getData();
        int length = incoming.getLength();
        String response = new String(data, 0, length);
        System.out.println(response);

        socket.close();
    }
}
