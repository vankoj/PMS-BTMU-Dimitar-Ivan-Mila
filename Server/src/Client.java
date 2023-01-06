import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // sleep for 5 second to make sure the server is started
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ip = "127.0.0.1";
        int port = 5000;

        Scanner sc = new Scanner(System.in);

        Socket socket = null;

        try {
            InetAddress address = InetAddress.getByName(ip);

            System.out.println("\u001B[32mCreating client socket...");
            socket = new Socket(address, port);
            System.out.println("Client socket is created.\u001B[0m\n");

            System.out.println("\u001B[36mDestination address: " + socket.getInetAddress() +
                    "\nDestination port: " + socket.getPort() +
                    "\nLocal address: " + socket.getLocalAddress() +
                    "\nLocal port: " + socket.getLocalPort() + "\u001B[0m\n");

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            System.out.print("me: ");
            String reply = sc.nextLine();
            printWriter.println(reply);
            printWriter.flush();
            while (!reply.equals("exit") && !reply.equals("quit")) {
                System.out.print("\u001B[35mserver: \u001B[0m");
                String message = bufferReader.readLine();
                System.out.println(message);
                if (message.equals("exit") || message.equals("quit")) {
                    break;
                }

                System.out.print("me: ");
                reply = sc.nextLine();
                printWriter.println(reply);
                printWriter.flush();
            }

            bufferReader.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing socket...");
                assert socket != null;
                socket.close();
                System.out.println("Socket closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
