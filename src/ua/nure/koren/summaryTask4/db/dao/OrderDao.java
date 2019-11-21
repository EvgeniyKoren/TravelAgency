package ua.nure.koren.summaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.db.DBManager;
import ua.nure.koren.summaryTask4.db.entity.Order;
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
 * DAO for Order. Provides connection between entity order and
 * orders table in travel_agency DB
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class OrderDao extends AbstractDao {

    private static final String SQL_INSERT_ORDER = "INSERT INTO orders VALUE(DEFAULT, ?, ?, false)";
    private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_FIND_ORDERS_SUMM = "SELECT COUNT(*) FROM orders";

    private static final Logger LOG = Logger.getLogger(OrderDao.class);

    private static OrderDao instance;

    /**
     * Don't let anyone else instantiate this class
     */
    private OrderDao() {
    }

    /**
     * Returns the OrderDao object associated with the current Java application
     *
     * @return instance of OrderDao
     */
    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        LOG.debug("Instance of OrderDao is received --> " + instance);
        return instance;
    }

    /**
     * Insert new order in DB
     *
     * @param userId User id which makes an order
     * @param tourId Tour id which ordered by user
     * @return boolean
     * true if operation succeeded
     * @throws DBException
     */
    public boolean insertOrder(int userId, int tourId) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int n = 1;
        int rowsNum;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER);
            preparedStatement.setInt(n++, userId);
            preparedStatement.setInt(n, tourId);
            rowsNum = preparedStatement.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_ORDER, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Returns all available orders
     *
     * @return List of order entities.
     * @throws DBException
     */
    public List<Order> findAllOrders() throws DBException {
        List<Order> orders = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_ORDERS);
            while (resultSet.next()) {
                orders.add(extractOrders(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, e);
        } finally {
            close(connection, statement, resultSet);
        }
        LOG.trace("Orders are obtained --> " + orders);
        return orders;
    }

    public int getOrdersQuantity() throws DBException {
        int result = 0;
        Connection connection = DBManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ORDERS_SUMM);
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUM_ORDERS, e);
        } finally {
            close(connection, statement, resultSet);
        }
        return result;
    }

    /**
     * Assistive method which helps to extract order from DB
     *
     * @param resultSet ResultSet which returned after operation with DB
     * @return Order entity
     * @throws SQLException
     */
    private Order extractOrders(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTourId(resultSet.getInt("tour_id"));
        order.setHandled(resultSet.getBoolean("is_handled"));
        return order;
    }
}
