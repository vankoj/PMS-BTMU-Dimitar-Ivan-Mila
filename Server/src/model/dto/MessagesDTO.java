package model.dto;

import java.util.Date;
import java.util.Objects;

public class MessagesDTO {
    private int id;
    private String senderUsername;
    private int groupId;
    private String message;
    private Date date;
    // TODO - add file

    public MessagesDTO() {
    }

    public MessagesDTO(int id, String sender, int groupId, String message, Date date) {
        this.id = id;
        this.senderUsername = sender;
        this.groupId = groupId;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesDTO that = (MessagesDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MessagesDTO{" +
                "id=" + id +
                ", senderUsername='" + senderUsername + '\'' +
                ", groupId=" + groupId +
                ", message='" + message + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
