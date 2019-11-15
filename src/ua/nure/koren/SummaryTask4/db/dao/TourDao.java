package ua.nure.koren.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.db.DBManager;
import ua.nure.koren.SummaryTask4.db.entity.Tour;
import ua.nure.koren.SummaryTask4.db.entity.TourFilter;
import ua.nure.koren.SummaryTask4.exception.DBException;
import ua.nure.koren.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDao extends Dao {

    private static final String SQL_FIND_ALL_TOURS = "SELECT * FROM tours WHERE 1=1 ";
    private static final String SQL_FIND_TOUR_BY_ID = "SELECT * FROM tours WHERE id=?";
    private static final String ORDER_BY_LAST_MINUTE_DESC = " ORDER BY last_minute DESC";
    private static final String SQL_UPDATE_LAST_MINUTE = "UPDATE tours SET last_minute=? WHERE id=?";
    private static final String SQL_UPDATE_TOUR_TYPE = "UPDATE tours SET status=? WHERE id=?";
    private static final String SQL_UPDATE_TOUR_SALE = "UPDATE tours SET sale=? WHERE id=?";
    private static final String SQL_INSERT_NEW_TOUR = "INSERT INTO tours VALUE(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_TOUR = "DELETE FROM tours WHERE id=?";
    private static final String SQL_UPDATE_TOUR = "UPDATE tours SET ";

    private static final Logger LOG = Logger.getLogger(TourDao.class);

    private static TourDao instance;

    public static synchronized TourDao getInstance() {
        if (instance == null) {
            instance = new TourDao();
        }
        LOG.debug("Instance of TourDao is received --> " + instance);
        return instance;
    }

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
    
    public boolean insertTour(Tour tour) throws DBException {
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        int rowsNum;
        int n = 1;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_TOUR);
            preparedStatement.setString(n++, tour.getCountry());
            preparedStatement.setString(n++, tour.getCity());
            preparedStatement.setString(n++, tour.getHotelName());
            preparedStatement.setInt(n++, tour.getHotelType());
            preparedStatement.setInt(n++, tour.getDuration());
            preparedStatement.setInt(n++, tour.getPeopleQuantity());
            preparedStatement.setInt(n++, tour.getPrice());
            preparedStatement.setBoolean(n++, tour.isLastMinute());
            preparedStatement.setString(n++, tour.getType());
            preparedStatement.setString(n++, tour.getStatus());
            preparedStatement.setInt(n, tour.getSale());
            rowsNum = preparedStatement.executeUpdate();
            LOG.trace("Returned number of rows --> " + rowsNum);
            connection.commit();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_INSERT_TOUR, e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rowsNum > 0;
    }

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

    private void populateStatementByTour(PreparedStatement preparedStatement, Tour tour) throws SQLException {
        int n = 1;
        if (tour.getCountry() != null) {
            preparedStatement.setString(n++, tour.getType());
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
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        stringBuilder.append(" ").append("WHERE id=?");
        String query = stringBuilder.toString();
        LOG.trace("Query is created --> " + query);
        return query;
    }

    private Tour extractTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getInt("id"));
        tour.setCountry(rs.getString("country"));
        tour.setCity(rs.getString("city"));
        tour.setHotelName(rs.getString("hotel_name"));
        tour.setHotelType(rs.getInt("hotel_type"));
        tour.setDuration(rs.getInt("duration"));
        tour.setPeopleQuantity(rs.getInt("people_quantity"));
        tour.setPrice(rs.getInt("price"));
        tour.setLastMinute(rs.getBoolean("last_minute"));
        tour.setType(rs.getString("type"));
        tour.setStatus(rs.getString("status"));
        tour.setSale(rs.getInt("sale"));
        return tour;
    }
}
