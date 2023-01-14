package model.enums;

public enum CommandType {
    REGISTER("register"),
    LOGIN("login"),
    LOGOUT("logout"),
    SEND_MSG("send_msg"),
    FRIEND_REQ("friend_req"),
    INVALID("invalid");

    private String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandType fromString(String command) {
        for (CommandType type : CommandType.values()) {
            if (type.getCommand().equals(command)) {
                return type;
            }
        }

        return INVALID;
    }

    @Override
    public String toString() {
        return command;
    }
}
