import io.DatabaseManager;
import thread.AcceptClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Example communication:
// me:
//     send_msg sender receiver My message
// server:
//     My message
// me:
//     send_msg sender receiver Good morning, what day is it?
// server:
//     Today is saturday
public class Server {
    public static int clientsConnected = 0;
    private static ExecutorService executor;
    private static ServerSocket serverSocket = null;

    public static int getClientsConnected() {
        return clientsConnected;
    }

    public static void addClient() {
        clientsConnected++;

        Runnable worker = new AcceptClient(serverSocket);
        executor.execute(worker);
    }

    public static void main(String[] args) throws SQLException {
        DatabaseManager.getInstance();
        String ip = "127.0.0.1";
        int port = 5000;
        int backlog = 50;

        try {
            InetAddress address = InetAddress.getByName(ip);

            System.out.println("\u001B[32mStarting server...");
            serverSocket = new ServerSocket(port, backlog, address);
            System.out.println("Server has started." +
                    "\nInet address: " + serverSocket.getInetAddress() +
                    "\nPort: " + serverSocket.getLocalPort() + "\u001B[0m\n");

            executor = Executors.newCachedThreadPool();

            Runnable worker = new AcceptClient(serverSocket);
            executor.execute(worker);

            while (!executor.isTerminated()) {
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("Closing server socket...");
                assert serverSocket != null;
                serverSocket.close();
                System.out.println("Server socket closed.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
