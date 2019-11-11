package ua.nure.koren.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.db.DBManager;
import ua.nure.koren.SummaryTask4.db.entity.Order;
import ua.nure.koren.SummaryTask4.exception.DBException;
import ua.nure.koren.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends Dao {

    private static final String SQL_INSERT_ORDER = "INSERT INTO orders VALUE(DEFAULT, ?, ?, false)";
    private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";

    private static final Logger LOG = Logger.getLogger(OrderDao.class);

    private static OrderDao instance;

    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        LOG.debug("Instance of OrderDao is received --> " + instance);
        return instance;
    }

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
        return  rowsNum > 0;
    }

//    public Order getOrderById(int id) {
//        Order order = new Order();
//
//
//        return order;
//    }

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

    private Order extractOrders(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTourId(resultSet.getInt("tour_id"));
        order.setHandled(resultSet.getBoolean("is_handled"));
        return order;
    }
}
