package thread;

import model.command.Command;
import model.command.SendMessageCommand;
import model.enums.CommandType;
import parsers.MessageParser;
import tools.Generator;


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

            Class.forName("Server").getMethod("addClient").invoke(null); // TODO - don't use reflection

            System.out.println("\u001B[36mAccepted a client request.");

            InetAddress clientAddress = socket.getInetAddress();
            clientPort = socket.getPort();
            System.out.println("Client address: " + clientAddress +
                    "\nClient port: " + clientPort + "\u001B[0m\n");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            String request = (String) inputStream.readObject();
            Command command = MessageParser.parse(request);

            System.out.printf("\u001B[35mclient %d: \u001B[0m", clientPort);
            System.out.println(command.serialize());

            String response = "";
            Command responseCommand = new Command(CommandType.INVALID, response);
            if (command instanceof SendMessageCommand) {
                SendMessageCommand sendMessageCommand = (SendMessageCommand) command;
                response = MessageParser.parseTestMessage(sendMessageCommand.getMessage());
                if (response != null)
                    responseCommand = Generator.generateSendMessageCommand(((SendMessageCommand) command).getUsername(), ((SendMessageCommand) command).getChatName(), response);
            } else {
                responseCommand = command;
            }

            System.out.print("me: " + responseCommand + "\n");
            outputStream.writeObject(responseCommand.serialize());

            inputStream.close();
            outputStream.close();
        } catch
        (IOException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException |
         IllegalAccessException
                        ex) {
            ex.printStackTrace();
        } finally {
            try {
                Field f = Class.forName("Server").getDeclaredField("clientsConnected");
                f.setAccessible(true);
                if (f.isAccessible())
                    f.set(null, (Integer) f.get(null) - 1);

                String portMessage = (clientPort == null) ? "" : String.format(" with port %d", clientPort);
                System.out.printf("Closing client socket%s...%n", portMessage);
                assert socket != null;
                socket.close();
                System.out.printf("Client socket%s closed.%n", portMessage);
            } catch (IOException | NoSuchFieldException | ClassNotFoundException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
}