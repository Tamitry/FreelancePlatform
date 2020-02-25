package by.tarlikovski.freelance.bean;

public enum UserStatus {
    ACTIVE(1),
    BANNED(2);
    private int statusNum;

    private UserStatus(int i) {
        statusNum = i;
    }

    public static UserStatus getStatus(final int r) {
        switch (r) {
            case 1:
                return ACTIVE;
            default:
                return BANNED;
        }
    }

    public int getStatusNum() {
        return statusNum;
    }
}
