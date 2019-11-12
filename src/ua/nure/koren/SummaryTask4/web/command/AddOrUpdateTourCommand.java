package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.Path;
import ua.nure.koren.SummaryTask4.db.dao.TourDao;
import ua.nure.koren.SummaryTask4.db.entity.Tour;
import ua.nure.koren.SummaryTask4.exception.AppException;
import ua.nure.koren.SummaryTask4.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrUpdateTourCommand extends Command {

    private static final long serialVersionUID = -3071536593627692987L;

    private static final Logger LOG = Logger.getLogger(AddOrUpdateTourCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        Tour tour = new Tour();
        TourDao tourDao = TourDao.getInstance();

        String country = request.getParameter("country");
        LOG.trace("Request parameter: country --> " + country);
        if (country != null && !country.isEmpty()) {
            tour.setCountry(country);
        }

        String city = request.getParameter("city");
        LOG.trace("Request parameter: city --> " + city);
        if (city != null && !city.isEmpty()) {
            tour.setCity(city);
        }

        String hotelName = request.getParameter("hotelName");
        LOG.trace("Request parameter: hotelName --> " + hotelName);
        if (hotelName != null && !hotelName.isEmpty()) {
            tour.setHotelName(hotelName);
        }

        String requestHotelType = request.getParameter("hotelType");
        LOG.trace("Request parameter: hotelType --> " + requestHotelType);
        if (requestHotelType != null && !requestHotelType.isEmpty()) {
            int hotelType = Integer.parseInt(requestHotelType);
            tour.setHotelType(hotelType);
        }

        String requestDuration = request.getParameter("duration");
        LOG.trace("Request parameter: duration --> " + requestDuration);
        if (requestDuration != null && !requestDuration.isEmpty()) {
            int duration = Integer.parseInt(requestDuration);
            tour.setDuration(duration);
        }

        String requestPeopleQuantity = request.getParameter("peopleQuantity");
        LOG.trace("Request parameter: peopleQuantity --> " + requestPeopleQuantity);
        if (requestPeopleQuantity != null && !requestPeopleQuantity.isEmpty()) {
            int peopleQuantity = Integer.parseInt(requestPeopleQuantity);
            tour.setPeopleQuantity(peopleQuantity);
        }

        String requestPrice = request.getParameter("price");
        LOG.trace("Request parameter: price --> " + requestPrice);
        if (requestPrice != null && !requestPrice.isEmpty()) {
            int price = Integer.parseInt(requestPrice);
            tour.setPrice(price);
        }

        String requestLastMinute = request.getParameter("lastMinute");
        LOG.trace("Request parameter: lastMinute --> " + requestLastMinute);
        if (requestLastMinute != null && requestLastMinute.equals("true")) {
            tour.setLastMinute(true);
        }

        String type = request.getParameter("type");
        LOG.trace("Request parameter: type --> " + type);
        if (type != null && !type.isEmpty()) {
            tour.setType(type);
        }

        String status = request.getParameter("status");
        LOG.trace("Request parameter: type --> " + status);
        if (status != null && !status.isEmpty()) {
            tour.setStatus(status);
        }

        String saleForTour = request.getParameter("sale");
        LOG.trace("Request parameter: sale --> " + saleForTour);
        if (saleForTour != null && !saleForTour.isEmpty()) {
            int sale = Integer.parseInt(saleForTour);
            tour.setSale(sale);
        }

        String tourId = request.getParameter("tourId");
        LOG.trace("Request parameter: tourId --> " + tourId);

        if (tourId == null || tourId.isEmpty()) {
            boolean success = tourDao.insertTour(tour);
            if (success) {
                LOG.trace("New tour is inserted --> " + tour);
                LOG.debug("Command finished");
                return Path.COMMAND_SHOW_TOURS;
            }
        } else {
            int id = Integer.parseInt(tourId);
            if (id <= 0) {
                throw new AppException(Messages.ERR_NEGATIVE_PARAMETER);
            }
            tour.setId(id);
            boolean success = tourDao.updateTour(tour);
            if (success) {
                LOG.trace("Tour № " + id + " is updated --> ");
                LOG.debug("Command finished");
                return Path.COMMAND_SHOW_TOURS;
            }
        }

        LOG.debug("Command finished");
        return Path.PAGE_LIST_TOURS;
    }
}
