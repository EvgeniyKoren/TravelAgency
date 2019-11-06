package ua.nure.koren.SummaryTask4.db.dao;

import ua.nure.koren.SummaryTask4.db.DBManager;
import ua.nure.koren.SummaryTask4.db.entity.Tour;
import ua.nure.koren.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDao extends Dao {

    private static final String SQL_FIND_ALL_TOURS = "SELECT * FROM tours ORDER BY last_minute DESC";
    private static TourDao instance;

    public static synchronized TourDao getInstance() {
        if (instance == null) {
            instance = new TourDao();
        }
        return instance;
    }

    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement prst;

    public List<Tour> findAllTours() throws DBException {
        List<Tour> tours = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_TOURS);
            while (resultSet.next()) {
                tours.add(extractTour(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, resultSet);
        }
        return tours;
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
        return tour;
    }
}
