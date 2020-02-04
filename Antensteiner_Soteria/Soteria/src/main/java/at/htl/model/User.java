package at.htl.model;

import java.util.HashSet;

public class User {
    String username;
    byte[] password;
    HashSet roles;
    byte[] salt;

    public User(String username, byte[] password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, byte[] password, HashSet roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, byte[] password, HashSet roles, byte[] salt) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public HashSet getRoles() {
        return roles;
    }

    public void setRoles(HashSet roles) {
        this.roles = roles;
    }
}
