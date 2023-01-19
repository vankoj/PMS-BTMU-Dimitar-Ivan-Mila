package parsers;

import model.command.*;
import model.enums.CommandType;
import model.enums.FriendRequestStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class MessageParser {
    public static Command parse(String message) {
        String[] words = message.split(" ");
        Command result = new InvalidCommand();

        try {
            CommandType commandType = CommandType.fromString(words[0]);
            String payload = message.substring(Math.min(words[0].length() + 1, message.length()));

            result = new Command(commandType, payload);

            switch (commandType) {
                case REGISTER:
                    result = parseRegister(payload);
                    break;
                case LOGIN:
                    result = parseLogin(payload);
                    break;
                case LOGOUT:
                    result = parseLogout(payload);
                    break;
                case SEND_MSG:
                    result = parseSendMessage(payload);
                    break;
                case FRIEND_REQ:
                    result = parseFriendRequest(payload);
                    break;
            }
        } catch (Exception ex) {
            return result;
        }

        return result;
    }

    private static RegisterCommand parseRegister(String payload) {
        RegisterCommand result = new RegisterCommand(payload);

        String[] parts = payload.split(" ");
        String username = parts[0];
        String email = parts[1];
        String password = payload.substring(username.length() + email.length() + 2);

        result.setUsername(username);
        result.setEmail(email);
        result.setPassword(password);

        return result;
    }

    private static LoginCommand parseLogin(String payload) {
        LoginCommand result = new LoginCommand(payload);

        String[] parts = payload.split(" ");
        String username = parts[0];
        String password = payload.substring(username.length() + 1);

        result.setEmail(username);
        result.setPassword(password);

        return result;
    }

    private static LogoutCommand parseLogout(String payload) {
        LogoutCommand result = new LogoutCommand(payload);

        result.setUsername(payload);

        return result;
    }

    private static SendMessageCommand parseSendMessage(String payload) { // TODO - check if this message is a test message and return answer
        SendMessageCommand result = new SendMessageCommand(payload);

        String[] parts = payload.split(" ");
        String username = parts[0];
        String chatName = parts[1];
        String message = payload.substring(username.length() + chatName.length() + 2);

        result.setUsername(username);
        result.setChatName(chatName);
        result.setMessage(message);

        return result;
    }

    private static FriendRequestCommand parseFriendRequest(String payload) {
        FriendRequestCommand result = new FriendRequestCommand(payload);

        String[] parts = payload.split(" ");
        String username = parts[0];
        String friendUsername = parts[1];
        FriendRequestStatus status = FriendRequestStatus.valueOf(parts[2]);

        result.setUsername(username);
        result.setFriendUsername(friendUsername);
        result.setStatus(status);

        return result;
    }

    public static String parseTestMessage(String message) {
        String reply;

        switch (message) {
            case "Good morning, what day is it?":
                reply = "Today is " + LocalDate.now().getDayOfWeek().toString().toLowerCase();
                break;
            case "Hello, what time is it?":
                reply = "Now is " + LocalTime.now();
                break;
            case "Hello, what is the weather?":
                reply = "The weather is " + getWeather();
                break;
            case "Hello what is the temperature?":
                reply = "The temperature is  " + new Random().longs(-15, 30).findFirst().getAsLong();
                break;
            default:
                reply = null;
                break;
        }

        return reply;
    }

    private static String getWeather() {
        String[] weather = {"Cold", "Warm", "Wet", "Cloudy", "Rainy", "Sunny"};
        return weather[new Random().nextInt(5)];
    }
}
