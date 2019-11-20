package ua.nure.koren.summaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.db.DBManager;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.exception.DBException;
import ua.nure.koren.summaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for User. Provides connection between entity user and
 * users table in travel_agency DB
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class UserDao extends AbstractDao {

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users VALUE(DEFAULT, ?, ?, ?, ?, false, 2)";
    private static final String SQL_UPDATE_USER_STATUS = "UPDATE users SET status=?	WHERE login=?";

    private static final Logger LOG = Logger.getLogger(UserDao.class);

    private static UserDao instance;

    /**
     * Don't let anyone else instantiate this class
     */
    private UserDao() {}

    /**
     * Returns the UserDao object associated with the current Java application
     *
     * @return instance of UserDao
     */
    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        LOG.debug("Instance of UserDao is received --> " + instance);
        return instance;
    }

    /**
     * Returns all registered users.
     *
     * @return List of user entities.
     * @throws DBException
     */
    public List<User> findAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (resultSet.next()) {
                users.add(extractUser(resultSet));
            }
        } catch (SQLException e) {
            rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, e);
        } finally {
            close(connection, statement, resultSet);
        }
        LOG.trace("Users is obtained --> " + users);
        return users;
    }

    /**
     * Finds and returns user with specified login
     *
     * @param login User login
     * @return User
     *              Entity of required user
     * @throws DBException
     */
    public User getUser(String login) throws DBException {
        User user = null;
        Connection connection = DBManager.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(n, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        LOG.trace("User is obtained --> " + user);
        return user;
    }

    /**
     * Inserts a new user in DB
     *
     * @param firstName User first name
     * @param lastName User last name
     * @param login User login
     * @param pass User password
     * @return boolean
     *                  true if operation succeeded
     * @throws DBException
     */
    public boolean insertUser(String firstName, String lastName, String login, String pass) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_USER);
            int n = 1;
            preparedStatement.setString(n++, firstName);
            preparedStatement.setString(n++, lastName);
            preparedStatement.setString(n++, login);
            preparedStatement.setString(n, pass);
            rowsNum = preparedStatement.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Update user status
     *
     * @param user User entity which needs to be updated
     * @return boolean
     *                 true if operation succeeded
     * @throws DBException
     */
    public boolean updateUserStatus(User user) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_STATUS);
            int n = 1;
            preparedStatement.setBoolean(n++, !user.isStatus());
            preparedStatement.setString(n, user.getLogin());
            rowsNum = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Assistive method which helps to extract user from DB
     *
     * @param resultSet ResultSet which returned after operation with DB
     * @return User entity
     * @throws SQLException
     */
    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("pass"));
        user.setStatus(resultSet.getBoolean("status"));
        user.setRoleId(resultSet.getInt("role_id"));
        return user;
    }
}
