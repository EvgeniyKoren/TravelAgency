package ua.nure.koren.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.db.DBManager;
import ua.nure.koren.SummaryTask4.db.entity.User;
import ua.nure.koren.SummaryTask4.exception.DBException;
import ua.nure.koren.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao {

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users VALUE(DEFAULT, ?, ?, ?, ?, false, 2)";

    private static final Logger LOG = Logger.getLogger(UserDao.class);

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        LOG.debug("Instance of UserDao is received --> " + instance);
        return instance;
    }

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement prst;

    public List<User> findAllUsers() throws DBException {
        List<User> users = new ArrayList<>();
        connection = DBManager.getConnection();
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
        connection = DBManager.getConnection();
        try {
            prst = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            prst.setString(1, login);
            resultSet = prst.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
        } finally {
            close(connection, statement, resultSet);
        }
        LOG.trace("User is obtained --> " + user);
        return user;
    }

    public boolean insertUser(String firstName, String lastName, String login, String pass) throws DBException {
        connection = DBManager.getConnection();
        int rowsNum = 0;
        try {
            prst = connection.prepareStatement(SQL_INSERT_NEW_USER);
            prst.setString(1, firstName);
            prst.setString(2, lastName);
            prst.setString(3, login);
            prst.setString(4, pass);
            rowsNum = prst.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, e);
        } finally {
            close(prst);
        }
        boolean result = rowsNum > 0;
        return result;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("pass"));
        user.setStatus(rs.getBoolean("status"));
        user.setRoleId(rs.getInt("role_id"));
        return user;
    }
}
