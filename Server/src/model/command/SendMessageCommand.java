package model.command;

import model.enums.CommandType;

import java.util.Objects;

public class SendMessageCommand extends Command {
    private String username;
    private String chatName;
    private String message;

    public SendMessageCommand(String payload) {
        super(CommandType.SEND_MSG, payload);
    }

    public SendMessageCommand(String username, String chatName, String message) {
        this(null, username, chatName, message);
    }

    public SendMessageCommand(String payload, String username, String chatName, String message) {
        super(CommandType.SEND_MSG, payload);
        this.username = username;
        this.chatName = chatName;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SendMessageCommand that = (SendMessageCommand) o;
        return Objects.equals(username, that.username) && Objects.equals(chatName, that.chatName) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, chatName, message);
    }

    @Override
    public String toString() {
        return "SendMessageCommand{" +
                "username='" + username + '\'' +
                ", chatName='" + chatName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
