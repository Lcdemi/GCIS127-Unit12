package unit12.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class SearchPrinter {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.spotify.com/");
        URLConnection connection = url.openConnection();
        connection.connect();

        InputStream in = connection.getInputStream();
        Scanner sc = new Scanner(in);

        while(sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }
}
