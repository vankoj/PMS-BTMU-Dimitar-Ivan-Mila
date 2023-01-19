package model.command;

import model.enums.CommandType;
import model.enums.FriendRequestStatus;

import java.util.Objects;

public class FriendRequestCommand extends Command {
    private String username;
    private String friendUsername;
    private FriendRequestStatus status;

    public FriendRequestCommand(String payload) {
        super(CommandType.FRIEND_REQ, payload);
    }

    public FriendRequestCommand(String username, String friendUsername, FriendRequestStatus status) {
        this(null, username, friendUsername, status);
    }

    public FriendRequestCommand(String payload, String username, String friendUsername, FriendRequestStatus status) {
        super(CommandType.FRIEND_REQ, payload);
        this.username = username;
        this.friendUsername = friendUsername;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public FriendRequestStatus getStatus() {
        return status;
    }

    public void setStatus(FriendRequestStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FriendRequestCommand that = (FriendRequestCommand) o;
        return Objects.equals(username, that.username) && Objects.equals(friendUsername, that.friendUsername) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, friendUsername, status);
    }

    @Override
    public String toString() {
        return "FriendRequestCommand{" +
                "username='" + username + '\'' +
                ", friendUsername='" + friendUsername + '\'' +
                ", status=" + status +
                '}';
    }
}
