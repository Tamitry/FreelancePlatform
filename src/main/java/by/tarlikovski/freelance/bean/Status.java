package by.tarlikovski.freelance.bean;

public enum Status {
    NOT_CONFIRMED(1),
    CONFIRMED(2),
    PERFORMED(3);

    private int statusNum;

    private Status(int i) {
        statusNum = i;
    }

    public static Status getStatus(final int r) {
        switch (r) {
            case 1:
                return NOT_CONFIRMED;
            case 2:
                return CONFIRMED;
            default:
                return PERFORMED;
        }
    }

    public int getStatusNum() {
        return statusNum;
    }
}
