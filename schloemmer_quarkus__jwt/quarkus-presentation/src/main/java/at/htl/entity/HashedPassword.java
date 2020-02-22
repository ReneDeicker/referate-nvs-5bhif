package at.htl.entity;

public class HashedPassword {

    private String saltHex;
    private String hexPassword;

    public HashedPassword(String saltHex, String hexPassword) {
        this.saltHex = saltHex;
        this.hexPassword = hexPassword;
    }

    public String getSaltHex() {
        return saltHex;
    }

    public void setSaltHex(String saltHex) {
        this.saltHex = saltHex;
    }

    public String getHexPassword() {
        return hexPassword;
    }

    public void setHexPassword(String hexPassword) {
        this.hexPassword = hexPassword;
    }
}
