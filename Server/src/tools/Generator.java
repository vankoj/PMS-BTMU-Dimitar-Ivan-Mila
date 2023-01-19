package tools;

import model.command.*;
import model.enums.FriendRequestStatus;

public class Generator {
    public static String generatePayload(Command command) {
        String payload = null;

        switch (command.getType()) {
            case REGISTER:
                RegisterCommand registerCommand = (RegisterCommand) command;
                payload = registerCommand.getUsername() + " " + registerCommand.getEmail() + " " + registerCommand.getPassword();
                break;
            case LOGIN:
                LoginCommand loginCommand = (LoginCommand) command;
                payload = loginCommand.getEmail() + " " + loginCommand.getPassword();
                break;
            case LOGOUT:
                LogoutCommand logoutCommand = (LogoutCommand) command;
                payload = logoutCommand.getUsername();
                break;
            case SEND_MSG:
                SendMessageCommand sendMessageCommand = (SendMessageCommand) command;
                payload = sendMessageCommand.getUsername() + " " + sendMessageCommand.getChatName() + " " + sendMessageCommand.getMessage();
                break;
            case FRIEND_REQ:
                FriendRequestCommand friendRequestCommand = (FriendRequestCommand) command;
                payload = friendRequestCommand.getUsername() + " " + friendRequestCommand.getFriendUsername() + " " + friendRequestCommand.getStatus();
                break;
        }
        command.setPayload(payload);

        return payload;
    }

    public static Command generateRegisterCommand(String username, String email, String password) {
        Command command = new RegisterCommand(username, email, password);
        generatePayload(command);

        return command;
    }

    public static Command generateLoginCommand(String username, String password) {
        Command command = new LoginCommand(username, password);
        generatePayload(command);

        return command;
    }

    public static Command generateLogout(String username) {
        Command command = new LogoutCommand(username);
        generatePayload(command);

        return command;
    }

    public static Command generateSendMessageCommand(String sender, String chat, String message) {
        Command command = new SendMessageCommand(sender, chat, message);
        generatePayload(command);

        return command;
    }

    public static Command generateFriendRequestCommand(String username, String friendUsername, FriendRequestStatus status) {
        Command command = new FriendRequestCommand(username, friendUsername, status);
        generatePayload(command);

        return command;
    }
}
