package model;

import java.util.Collection;
import java.util.Objects;

public class Message {
    private int id;
    private Account sender;
    private Collection<Account> receivers;
    private String message;

    public Message() {
    }

    public Message(int id, Account sender, Collection<Account> receivers, String message) {
        this.id = id;
        this.sender = sender;
        this.receivers = receivers;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Collection<Account> getReceivers() {
        return receivers;
    }

    public void setReceivers(Collection<Account> receivers) {
        this.receivers = receivers;
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
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receivers=" + receivers +
                ", message='" + message + '\'' +
                '}';
    }
}
