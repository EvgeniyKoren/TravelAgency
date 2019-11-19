package ua.nure.koren.summaryTask4.db;

import java.util.Arrays;

/**
 * Role entity that can be used.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public enum Role {
    ADMIN(0), CUSTOMER(2), MANAGER(1);

    private int roleId;

    /**
     * Role constructor
     *
     * @param roleId role id
     */
    Role(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Returns role
     *
     * @param roleId id of current role
     * @return Role
     */
    public static Role getByRoleId(int roleId) {
        return Arrays.stream(values())
                .filter(v -> v.roleId == roleId)
                .findFirst().orElse(null);
    }

    /**
     * Returns name of role
     *
     * @return String name of the role
     */
    public String getName() {
        return name().toLowerCase();
    }
}
