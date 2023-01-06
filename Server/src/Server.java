import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Server {
    private static String getWeather() {
        String[] weather = {"Cold", "Warm", "Wet", "Cloudy", "Rainy", "Sunny"};
        return weather[new Random().nextInt(5)];
    }

    private static String checkMessage(String message) {
        switch (message) {
            case "Good morning, what day it is?":
                message = "Today is " + LocalDate.now().getDayOfWeek().getValue();
                break;
            case "Hello, what time is it?":
                message = "Now is " + LocalTime.now();
                break;
            case "Hello, what is the weather?":
                message = "The weather is " + getWeather();
                break;
            case "Hello what is the temperature?":
                message = "The temperature is  " + new Random().longs(-15, 30);
                break;
            default:
                message = "Invalid command";
        }
        return message;
    }

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 5000;
        int backlog = 50;

        Scanner sc = new Scanner(System.in);

        Socket socket = null;
        ServerSocket serverSocket = null;

        try {

            InetAddress address = InetAddress.getByName(ip);

            System.out.println("\u001B[32mStarting server...");
            serverSocket = new ServerSocket(port, backlog, address);
            System.out.println("Server has started." +
                    "\nInet address: " + serverSocket.getInetAddress() +
                    "\nPort: " + serverSocket.getLocalPort() + "\u001B[0m\n");

            System.out.println("\u001B[33mWaiting for client...\u001B[0m\n");
            socket = serverSocket.accept();

            System.out.println("\u001B[36mAccepted a client request.");

            InetAddress clientAddress = socket.getInetAddress();
            int clientPort = socket.getPort();
            System.out.println("Client address: " + clientAddress +
                    "\nClient port: " + clientPort + "\u001B[0m\n");

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            System.out.print("\u001B[35mclient: \u001B[0m");
            String message = bufferedReader.readLine();
            while (true) {
                System.out.println(message);
                if (message.equals("exit") || message.equals("quit")) break;

                String reply = checkMessage(message);
                System.out.print("me: " + reply);
                printWriter.println(reply);
                printWriter.flush();
                if (reply.equals("exit") || reply.equals("quit")) {
                    Thread.sleep(1000);
                    break;
                }

                System.out.print("\n\u001B[35mclient: \u001B[0m");
                message = bufferedReader.readLine();
            }

            bufferedReader.close();
            printWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing client socket...");
                assert socket != null;
                socket.close();
                System.out.println("Client socket closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Closing server socket...");
                serverSocket.close();
                System.out.println("Server socket closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}