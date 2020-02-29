package by.tarlikovski.freelance.bean;

public enum Role {
    ADMIN(1),
    FREELANCER(2),
    CLIENT(3);

    private int roleNum;

    private Role(int i) {
        roleNum = i;
    }

    public static Role getRole(final int r) {
        switch (r) {
            case 1:
                return ADMIN;
            case 2:
                return FREELANCER;
            default:
                return CLIENT;
        }
    }

    public int getRoleNum() {
        return roleNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("roleNum=").append(roleNum);
        sb.append('}');
        return sb.toString();
    }
}
