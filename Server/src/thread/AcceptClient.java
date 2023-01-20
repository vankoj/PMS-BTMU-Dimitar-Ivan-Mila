package thread;

import model.command.Command;
import model.command.SendMessageCommand;
import parsers.MessageParser;


import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class AcceptClient implements Runnable {
    private final ServerSocket serverSocket;

    public AcceptClient(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        Socket socket = null;
        Integer clientPort = null;

        try {
            System.out.println("\u001B[33mWaiting for client...\u001B[0m\n");
            socket = this.serverSocket.accept();

            // call addClient() from Server class with reflection
            Class<?> serverClass = Class.forName("Server");
            Field field = serverClass.getField("clientsConnected");
            field.setAccessible(true);
            field.set(serverClass, (Integer) field.get(serverClass) + 1);
            serverClass.getMethod("addClient").invoke(serverClass);

            System.out.println("\u001B[36mAccepted a client request.");

            InetAddress clientAddress = socket.getInetAddress();
            clientPort = socket.getPort();
            System.out.println("Client address: " + clientAddress +
                    "\nClient port: " + clientPort + "\u001B[0m\n");

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            String message = bufferedReader.readLine();
            while (true) {
                System.out.print(String.format("\u001B[35mclient %d: \u001B[0m", clientPort));
                System.out.println(message);
                if (message.equals("exit") || message.equals("quit")) break;

                String reply = "";

                Command command = MessageParser.parse(message);
                switch (command.getType()) {
                    case REGISTER:
                        // TODO - implement
                        break;
                    case LOGIN:
                        // TODO - implement
                        break;
                    case LOGOUT:
                        // TODO - implement
                        break;
                    case SEND_MSG:
                        if (command instanceof SendMessageCommand) {
                            SendMessageCommand sendMessageCommand = (SendMessageCommand) command;
                            reply = MessageParser.parseTestMessage(sendMessageCommand.getMessage());
                            if (reply == null)
                                reply = "";
                        }
                        break;
                    case FRIEND_REQ:
                        // TODO - implement
                        break;
                    case INVALID:
                        // TODO - implement
                        break;
                }
                if (reply.isEmpty()) {
                    reply = String.format("Received %s command\n", command.getType().toString());
                    reply += command;
                }

                System.out.print("me: " + reply + "\n");
                printWriter.println(reply);
                printWriter.flush();
                if (reply.equals("exit") || reply.equals("quit")) {
                    Thread.sleep(1000);
                    break;
                }

                message = bufferedReader.readLine();
            }

            bufferedReader.close();
            printWriter.close();
        } catch
        (IOException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException |
         InterruptedException | IllegalAccessException
                        ex) {
            ex.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Field f = Class.forName("Server").getDeclaredField("clientsConnected");
                f.setAccessible(true);
                if (f.isAccessible())
                    f.set(null, (Integer) f.get(null) - 1);

                String portMessage = (clientPort == null) ? "" : String.format(" with port %d", clientPort);
                System.out.println(String.format("Closing client socket%s...", portMessage));
                assert socket != null;
                socket.close();
                System.out.println(String.format("Client socket%s closed.", portMessage));
            } catch (IOException | NoSuchFieldException | ClassNotFoundException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
}