package ua.nure.koren.summaryTask4.db;

import java.util.Arrays;

public enum Role {
    ADMIN(0), CUSTOMER(2), MANAGER(1);

    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public static Role getByRoleId(int roleId) {
        return Arrays.stream(values())
                .filter(v -> v.roleId == roleId)
                .findFirst().orElse(null);
    }
    public String getName() {
        return name().toLowerCase();
    }
}
