package role;

public enum Permissions {
    USER("USER_ROLE"), ADMIN("ADMIN_ROLE");

    final String p;

    Permissions(String p) {
        this.p = p;
    }

    public String getP() {
        return p;
    }
}
