package model.command;

import model.enums.CommandType;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    public InvalidCommand(String payload) {
        super(CommandType.INVALID, payload);
    }

    @Override
    public String toString() {
        return "InvalidCommand{}";
    }
}
