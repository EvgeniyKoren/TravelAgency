package ua.nure.koren.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.db.DBManager;
import ua.nure.koren.SummaryTask4.exception.DBException;
import ua.nure.koren.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao extends Dao {

    private static final String SQL_INSERT_ORDER = "INSERT INTO orders VALUE(DEFAULT, ?, ?, false)";

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
}
