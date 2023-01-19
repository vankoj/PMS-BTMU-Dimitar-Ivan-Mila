package model.command;

import model.enums.CommandType;

import java.util.Objects;

public class LoginCommand extends Command {
    private String email;
    private String password;

    public LoginCommand(String payload) {
        super(CommandType.LOGIN, payload);
    }

    public LoginCommand(String email, String password) {
        this(null, email, password);
    }

    public LoginCommand(String payload, String email, String password) {
        super(CommandType.LOGIN, payload);
        this.email = email;
        this.password = password;
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
        LoginCommand that = (LoginCommand) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }

    @Override
    public String toString() {
        return "LoginCommand{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
