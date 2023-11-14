import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * User: Carlos Eduardo Dal Molin
 * Date: 11/02/2023
 */
public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(5555);
            System.out.println("Servidor iniciado!");

            while (true) {
                Socket client= server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());

                Thread thread = new Thread(new ClientThread(client));
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("A conexão foi fechada!");
        }
    }
}

class ClientThread implements Runnable {
    private Socket client;

    public ClientThread(Socket clientSocket) {
        this.client = clientSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buffer = new byte[4096];
                int bytesRead = client.getInputStream().read(buffer);
                String mensagem = new String(buffer, 0, bytesRead).trim();

                if ("Over".equals(mensagem)) {
                    break;
                }

                if (Objects.isNull(mensagem) || "".equals(mensagem)) {
                    continue;
                }

                System.out.println(mensagem);
            }

            System.out.println("Cliente desconectado: " + client.getInetAddress().getHostAddress());

            client.close();
        } catch (IOException e) {
            System.out.println("Cliente desconectado: ");
        }
    }
}