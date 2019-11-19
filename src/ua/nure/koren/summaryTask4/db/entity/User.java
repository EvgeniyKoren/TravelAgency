package ua.nure.koren.summaryTask4.db.entity;

/**
 * User entity.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class User extends AbstractEntity {

    private static final long serialVersionUID = 122625786080834988L;

    private static final int CUSTOMER = 2;

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private boolean status;
    private int roleId;

    /**
     * Default constructor
     */
    public User(){}

    /**
     * Constructor with parameters
     *
     * @param firstName User first name
     * @param lastName User last name
     * @param login User login
     * @param password User password
     */
    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.status = false;
        this.roleId = CUSTOMER;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roleId=" + roleId +
                '}';
    }
}
