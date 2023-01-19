package model.command;

import model.enums.CommandType;

import java.util.Objects;

public class RegisterCommand extends Command {
    private String username; // TODO - username should be validated with regex to disallow spaces
    private String email;
    private String password;

    public RegisterCommand(String payload) {
        super(CommandType.REGISTER, payload);
    }

    public RegisterCommand(String username, String email, String password) {
        this(null, username, email, password);
    }

    public RegisterCommand(String payload, String username, String email, String password) {
        super(CommandType.REGISTER, payload);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        RegisterCommand that = (RegisterCommand) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, password);
    }

    @Override
    public String toString() {
        return "RegisterCommand{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
