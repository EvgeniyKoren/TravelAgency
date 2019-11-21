package ua.nure.koren.summaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.db.DBManager;
import ua.nure.koren.summaryTask4.db.entity.Tour;
import ua.nure.koren.summaryTask4.db.entity.TourFilter;
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
 * DAO for Tour. Provides connection between entity tour and
 * tours table in travel_agency DB
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class TourDao extends AbstractDao {

    private static final String SQL_FIND_ALL_TOURS = "SELECT * FROM tours WHERE 1=1 ";
    private static final String SQL_FIND_TOUR_BY_ID = "SELECT * FROM tours WHERE id=?";
    private static final String ORDER_BY_LAST_MINUTE_DESC = " ORDER BY last_minute DESC";
    private static final String SQL_UPDATE_LAST_MINUTE = "UPDATE tours SET last_minute=? WHERE id=?";
    private static final String SQL_UPDATE_TOUR_TYPE = "UPDATE tours SET status=? WHERE id=?";
    private static final String SQL_UPDATE_TOUR_SALE = "UPDATE tours SET sale=? WHERE id=?";
    private static final String SQL_INSERT_NEW_TOUR = "INSERT INTO tours VALUE(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_TOUR = "DELETE FROM tours WHERE id=?";
    private static final String SQL_UPDATE_TOUR = "UPDATE tours SET ";

    private static final Logger LOG = Logger.getLogger(TourDao.class);

    private static TourDao instance;

    /**
     * Don't let anyone else instantiate this class
     */
    private TourDao() {}

    /**
     * Returns the TourDao object associated with the current Java application
     *
     * @return instance of TourDao
     */
    public static synchronized TourDao getInstance() {
        if (instance == null) {
            instance = new TourDao();
        }
        LOG.debug("Instance of TourDao is received --> " + instance);
        return instance;
    }

    /**
     * Finds and returns tour with specified id
     *
     * @param id Tour id
     * @return Tour
     *              Entity of required tour
     * @throws DBException
     */
    public Tour getTourById(int id) throws DBException {
        Tour tour = null;
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_TOUR_BY_ID);
            preparedStatement.setInt(n, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tour = extractTour(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_TOUR, e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        LOG.trace("Tour is obtained --> " + tour);
        return tour;
    }

    /**
     * Returns all available tours
     *
     * @param filter TourFilter entity which filled up with
     *               necessary parameters
     * @return List of tour entities
     * @throws DBException
     */
    public List<Tour> findAllTours(TourFilter filter) throws DBException {
        List<Tour> tours = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String sqlQuery = queryByFilter(filter);
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            populateStatement(preparedStatement, filter);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tours.add(extractTour(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_TOURS, e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        LOG.trace("Tours are obtained --> " + tours);
        return tours;
    }

    /**
     * Changes last minute status of tour
     *
     * @param tour
     *              Tour entity which needs to be changed
     * @return boolean
     *                 true if operation succeeded
     * @throws DBException
     */
    public boolean makeLastMinute(Tour tour) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_LAST_MINUTE);
            preparedStatement.setBoolean(n++, !tour.isLastMinute());
            preparedStatement.setInt(n, tour.getId());
            rowsNum = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TOUR_TO_LAST_MINUTE, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Changes status of tour
     *
     * @param tour
     *              Tour entity which needs to be changed
     * @param status
     *              New status of tour
     * @return boolean
     *                 true if operation succeeded
     * @throws DBException
     */
    public boolean changeTourStatus(Tour tour, String status) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_TOUR_TYPE);
            preparedStatement.setString(n++, status);
            preparedStatement.setInt(n, tour.getId());
            rowsNum = preparedStatement.executeUpdate();
            connection.commit();
            LOG.trace("Status of the tour changed to --> " + status);
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TOUR_TYPE, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Sets sale for tour
     *
     * @param tour
     *             Tour entity which needs to be changed
     * @param sale
     *             Sale that will be set to concrete tour
     * @return boolean
     *                 true if operation succeeded
     * @throws DBException
     */
    public boolean setSale(Tour tour, int sale) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_TOUR_SALE);
            preparedStatement.setInt(n++, sale);
            preparedStatement.setInt(n, tour.getId());
            rowsNum = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TOUR_SALE, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Inserts a new tour in DB
     *
     * @param tour
     *              Tour which needs to be inserted
     * @return boolean
     *                true if operation succeeded
     * @throws DBException
     */
    public boolean insertTour(Tour tour) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        int rowsNum;
        int n = 1;
        try {
            statement = connection.prepareStatement(SQL_INSERT_NEW_TOUR, Statement.RETURN_GENERATED_KEYS);
            statement.setString(n++, tour.getCountry());
            statement.setString(n++, tour.getCity());
            statement.setString(n++, tour.getHotelName());
            statement.setInt(n++, tour.getHotelType());
            statement.setInt(n++, tour.getDuration());
            statement.setInt(n++, tour.getPeopleQuantity());
            statement.setInt(n++, tour.getPrice());
            statement.setBoolean(n++, tour.isLastMinute());
            statement.setString(n++, tour.getType());
            statement.setString(n++, tour.getStatus());
            statement.setInt(n, tour.getSale());
            rowsNum = statement.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tour.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating tour failed, no ID obtained.");
            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_TOUR, e);
        } finally {
            close(connection, statement, generatedKeys);
        }
        return rowsNum > 0;
    }

    /**
     * Removes tour with specified id
     *
     * @param id Tour id
     * @return boolean
     *                true if operation succeeded
     * @throws DBException
     */
    public boolean deleteTourById(int id) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_TOUR);
            preparedStatement.setInt(n, id);
            rowsNum = preparedStatement.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_DELETE_TOUR, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Update full information about tour or update a defined part of
     * information.
     *
     * @param tour
     *             Tour which needs to be updated
     * @return boolean
     *                true if operation succeeded
     * @throws DBException
     */
    public boolean updateTour(Tour tour) throws DBException {
        Connection connection = DBManager.getConnection();
        String sqlQuery = queryByTour(tour);
        PreparedStatement preparedStatement = null;
        int rowsNum;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            populateStatementByTour(preparedStatement, tour);
            rowsNum = preparedStatement.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TOUR, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

    /**
     * Assistive method which helps to fill up statement with help of
     * TourFilter object
     *
     * @param preparedStatement
     *                          PreparedStatement object that needs to
     *                          filled up
     * @param filter TourFilter object which holds fresh information
     * @throws SQLException
     */
    private void populateStatement(PreparedStatement preparedStatement, TourFilter filter) throws SQLException {
        int n = 1;
        if (filter.getType() != null) {
            preparedStatement.setString(n++, filter.getType());
        }
        if (filter.getPrice() != 0) {
            preparedStatement.setInt(n++, filter.getPrice());
        }
        if (filter.getPeopleQuantity() != 0) {
            preparedStatement.setInt(n++, filter.getPeopleQuantity());
        }
        if (filter.getHotelType() != 0) {
            preparedStatement.setInt(n++, filter.getHotelType());
        }
    }

    /**
     * Assistive method which helps to fill up statement with help of
     * Tour object
     *
     * @param preparedStatement
     *                          PreparedStatement object that needs to
     *                          filled up
     * @param tour
     *            Tour object which holds fresh information
     * @throws SQLException
     */
    private void populateStatementByTour(PreparedStatement preparedStatement, Tour tour) throws SQLException {
        int n = 1;
        if (tour.getCountry() != null) {
            preparedStatement.setString(n++, tour.getCountry());
        }
        if (tour.getCity() != null) {
            preparedStatement.setString(n++, tour.getCity());
        }
        if (tour.getHotelName() != null) {
            preparedStatement.setString(n++, tour.getHotelName());
        }
        if (tour.getHotelType() != 0) {
            preparedStatement.setInt(n++, tour.getHotelType());
        }
        if (tour.getDuration() != 0) {
            preparedStatement.setInt(n++, tour.getDuration());
        }
        if (tour.getPeopleQuantity() != 0) {
            preparedStatement.setInt(n++, tour.getPeopleQuantity());
        }
        if (tour.getPrice() != 0) {
            preparedStatement.setInt(n++, tour.getPrice());
        }
        if (tour.isLastMinute()) {
            preparedStatement.setBoolean(n++, !tour.isLastMinute());
        }
        if (tour.getType() != null) {
            preparedStatement.setString(n++, tour.getType());
        }
        if (tour.getStatus() != null) {
            preparedStatement.setString(n++, tour.getStatus());
        }
        if (tour.getSale() != 0) {
            preparedStatement.setInt(n++, tour.getPrice());
        }
        preparedStatement.setInt(n, tour.getId());
    }

    /**
     * Forms query for PreparedStatement. Checks what information holds
     * tourFilter
     *
     * @param filter TourFilter object which holds fresh information
     * @return String with prepared query
     * @see TourFilter
     */
    private String queryByFilter(TourFilter filter) {
        String query = SQL_FIND_ALL_TOURS;
        if (filter.getType() != null) {
            query += "AND type=? ";
        }
        if (filter.getPrice() != 0) {
            query += "AND price<=? ";
        }
        if (filter.getPeopleQuantity() != 0) {
            query += "AND people_quantity=? ";
        }
        if (filter.getHotelType() != 0) {
            query += "AND hotel_type=? ";
        }
        query += ORDER_BY_LAST_MINUTE_DESC;

        LOG.trace("Query is created --> " + query);
        return query;
    }

    /**
     * Forms query for PreparedStatement. Checks what information holds
     * tour
     *
     * @param tour TourFilter object which holds fresh information
     * @return String with prepared query
     * @see Tour
     */
    private String queryByTour(Tour tour) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SQL_UPDATE_TOUR);
        if (tour.getCountry() != null) {
            stringBuilder.append("country=?").append(", ");
        }
        if (tour.getCity() != null) {
            stringBuilder.append("city=?").append(", ");
        }
        if (tour.getHotelName() != null) {
            stringBuilder.append("hotel_name=?").append(", ");
        }
        if (tour.getHotelType() != 0) {
            stringBuilder.append("hotel_type=?").append(", ");
        }
        if (tour.getDuration() != 0) {
            stringBuilder.append("duration=?").append(", ");
        }
        if (tour.getPeopleQuantity() != 0) {
            stringBuilder.append("people_quantity=?").append(", ");
        }
        if (tour.getPrice() != 0) {
            stringBuilder.append("price=?").append(", ");
        }
        if (tour.isLastMinute()) {
            stringBuilder.append("last_minute=?").append(", ");
        }
        if (tour.getType() != null) {
            stringBuilder.append("type=?").append(", ");
        }
        if (tour.getStatus() != null) {
            stringBuilder.append("status=?").append(", ");
        }
        if (tour.getSale() != 0) {
            stringBuilder.append("sale=?").append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(" ").append("WHERE id=?");
        String query = stringBuilder.toString();
        LOG.trace("Query is created --> " + query);
        return query;
    }

    /**
     * Assistive method which helps to extract user from DB
     *
     * @param resultSet ResultSet which returned after operation with DB
     * @return Tour entity
     * @throws SQLException
     */
    private Tour extractTour(ResultSet resultSet) throws SQLException {
        Tour tour = new Tour();
        tour.setId(resultSet.getInt("id"));
        tour.setCountry(resultSet.getString("country"));
        tour.setCity(resultSet.getString("city"));
        tour.setHotelName(resultSet.getString("hotel_name"));
        tour.setHotelType(resultSet.getInt("hotel_type"));
        tour.setDuration(resultSet.getInt("duration"));
        tour.setPeopleQuantity(resultSet.getInt("people_quantity"));
        tour.setPrice(resultSet.getInt("price"));
        tour.setLastMinute(resultSet.getBoolean("last_minute"));
        tour.setType(resultSet.getString("type"));
        tour.setStatus(resultSet.getString("status"));
        tour.setSale(resultSet.getInt("sale"));
        return tour;
    }
}
