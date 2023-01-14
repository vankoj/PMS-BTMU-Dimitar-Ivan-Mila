package model;

import java.util.Collection;
import java.util.Objects;

public class Chat {
    private int id;
    private String name;
    private Collection<Account> members;

    public Chat() {
    }

    public Chat(int id, String name, Collection<Account> members) {
        this.id = id;
        this.name = name;
        this.members = members;
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

    public Collection<Account> getMembers() {
        return members;
    }

    public void setMembers(Collection<Account> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
