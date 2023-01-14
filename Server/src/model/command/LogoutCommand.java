package model.command;

import model.enums.CommandType;

import java.util.Objects;

public class LogoutCommand extends Command {
    private String username;

    public LogoutCommand(String payload) {
        super(CommandType.LOGOUT, payload);
    }

    public LogoutCommand(String payload, String username) {
        super(CommandType.LOGOUT, payload);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LogoutCommand that = (LogoutCommand) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username);
    }

    @Override
    public String toString() {
        return "LogoutCommand{" +
                "username='" + username + '\'' +
                '}';
    }
}
