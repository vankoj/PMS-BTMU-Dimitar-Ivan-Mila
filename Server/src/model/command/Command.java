package model.command;

import model.enums.CommandType;

import java.util.Objects;

public class Command {
    private CommandType type;
    private String payload;

    public Command(CommandType type) {
        this.type = type;
    }

    public Command(String payload) {
        this.payload = payload;
    }

    public Command(CommandType type, String payload) {
        this.type = type;
        this.payload = payload;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return type == command.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
