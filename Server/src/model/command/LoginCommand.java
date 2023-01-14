package model.command;

import model.enums.CommandType;

import java.util.Objects;

public class LoginCommand extends Command {
    private String username;
    private String password;

    public LoginCommand(String payload) {
        super(CommandType.LOGIN, payload);
    }

    public LoginCommand(String payload, String username, String password) {
        super(CommandType.LOGIN, payload);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LoginCommand that = (LoginCommand) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password);
    }

    @Override
    public String toString() {
        return "LoginCommand{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
