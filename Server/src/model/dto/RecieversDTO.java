package model.dto;

import java.util.Objects;

public class RecieversDTO {
    private int groupId;
    private int userAccountRecieverId;

    public RecieversDTO() {
    }

    public RecieversDTO(int groupId, int userAccountRecieverId) {
        this.groupId = groupId;
        this.userAccountRecieverId = userAccountRecieverId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserAccountRecieverId() {
        return userAccountRecieverId;
    }

    public void setUserAccountRecieverId(int userAccountRecieverId) {
        this.userAccountRecieverId = userAccountRecieverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecieversDTO that = (RecieversDTO) o;
        return groupId == that.groupId && userAccountRecieverId == that.userAccountRecieverId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userAccountRecieverId);
    }

    @Override
    public String toString() {
        return "RecieversDTO{" +
                "groupId=" + groupId +
                ", userAccountRecieverId=" + userAccountRecieverId +
                '}';
    }
}
