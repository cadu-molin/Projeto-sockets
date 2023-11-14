import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * User: Carlos Eduardo Dal Molin
 * Date: 11/02/2023
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("172.16.96.14", 1234);
        System.out.println("O cliente se conectou ao servidor");

        PrintStream saida = new PrintStream(cliente.getOutputStream());
        Scanner teclado = new Scanner(System.in);
        String line = "";

        while(! "Over".equals(line)) {
            line = teclado.nextLine();
            saida.println(line);
        }

        System.out.println("A conexão foi fechada!");

        saida.close();
        teclado.close();
        cliente.close();
    }
}
