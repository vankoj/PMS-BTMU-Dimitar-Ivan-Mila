package model.dto;

import java.util.Objects;

public class GroupChatDTO {
    private int id;
    private String name;

    public GroupChatDTO() {
    }

    public GroupChatDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupChatDTO that = (GroupChatDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GroupChatDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
