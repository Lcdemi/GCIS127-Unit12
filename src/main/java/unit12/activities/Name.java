package unit12.activities;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
//import java.util.Scanner;

public class Name {
    public static void main(String[] args) throws IOException {
        Socket bobbyJacques = new Socket("129.21.156.169", 42975);

        // the "hard way"
        // InputStream is = bobbyJacques.getInputStream();
        // InputStreamReader ir = new InputStreamReader(is);
        // BufferedReader br = new BufferedReader(ir);
        // String line = br.readLine();
        // System.out.println(line);

        // the "easy way"
        // Scanner sc = new Scanner(is);
        // String n = sc.nextLine();
        // System.out.println(n);
        // sc.close();

        OutputStream os = bobbyJacques.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println("Luke Demi");
        pw.flush();
        bobbyJacques.close();
    }
}
