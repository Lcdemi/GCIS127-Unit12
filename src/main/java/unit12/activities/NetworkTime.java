package unit12.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class NetworkTime {
    public static void main(String[] args) throws IOException {
        Socket nist = new Socket("time.nist.gov", 13);

        InputStream is = nist.getInputStream();
        Scanner sc = new Scanner(is);
        while(sc.hasNext()) {
            String s = sc.next();
            System.out.print(s + " ");
        }
        System.out.println();

        sc.close();
        nist.close();
    }

}