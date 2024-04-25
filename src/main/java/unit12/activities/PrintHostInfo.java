package unit12.activities;

import java.io.IOException;
import java.net.InetAddress;

public class PrintHostInfo {
    public static void main(String[] args) throws IOException {
        InetAddress host = InetAddress.getLocalHost();
        System.out.println(host.getHostAddress());
        System.out.println(host.getHostName());

        InetAddress google = InetAddress.getByName("www.google.com");
        System.out.println(google.getHostAddress());
        System.out.println(google.getHostName());

        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());
    }
}
