package model.dto;

import java.util.Objects;

public class AccountsDTO {
    private String username;
    private String email;
    private String userPasswordHash;
    private String userPasswordSalt;

    public AccountsDTO() {
    }

    public AccountsDTO(String username, String email, String userPasswordHash, String userPasswordSalt) {
        this.username = username;
        this.email = email;
        this.userPasswordHash = userPasswordHash;
        this.userPasswordSalt = userPasswordSalt;
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

    public String getUserPasswordHash() {
        return userPasswordHash;
    }

    public void setUserPasswordHash(String userPasswordHash) {
        this.userPasswordHash = userPasswordHash;
    }

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsDTO that = (AccountsDTO) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "AccountsDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userPasswordHash='" + userPasswordHash + '\'' +
                ", userPasswordSalt='" + userPasswordSalt + '\'' +
                '}';
    }
}
