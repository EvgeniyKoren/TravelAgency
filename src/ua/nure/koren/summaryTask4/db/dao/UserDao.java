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

public class UserDao extends AbstractDao {

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users VALUE(DEFAULT, ?, ?, ?, ?, false, 2)";
    private static final String SQL_UPDATE_USER_STATUS = "UPDATE users SET status=?	WHERE login=?";

    private static final Logger LOG = Logger.getLogger(UserDao.class);

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        LOG.debug("Instance of UserDao is received --> " + instance);
        return instance;
    }

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

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("pass"));
        user.setStatus(rs.getBoolean("status"));
        user.setRoleId(rs.getInt("role_id"));
        return user;
    }
}
