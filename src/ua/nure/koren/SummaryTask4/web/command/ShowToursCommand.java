package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.Path;
import ua.nure.koren.SummaryTask4.db.dao.TourDao;
import ua.nure.koren.SummaryTask4.db.entity.Tour;
import ua.nure.koren.SummaryTask4.db.entity.TourFilter;
import ua.nure.koren.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintValidator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.List;

public class ShowToursCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ShowToursCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        TourDao tourDao = TourDao.getInstance();

        String type = request.getParameter("type");
        LOG.trace("Request parameter: type --> " + type);
        String price = request.getParameter("price");
        LOG.trace("Request parameter: price --> " + price);
        String peopleQuantity = request.getParameter("peopleQuantity");
        LOG.trace("Request parameter: peopleQuantity --> " + peopleQuantity);
        String hotelType = request.getParameter("hotelType");
        LOG.trace("Request parameter: hotelType --> " + hotelType);

        TourFilter tourFilter = new TourFilter();

        if (type != null && !type.isEmpty()) {
            tourFilter.setType(type);
        }
        if (price != null && !price.isEmpty()) {
            int filterPrice = Integer.parseInt(price);
            tourFilter.setPrice(filterPrice);
        }
        if (peopleQuantity != null && !peopleQuantity.isEmpty()) {
            int filterPeopleQuantity = Integer.parseInt(peopleQuantity);
            tourFilter.setPeopleQuantity(filterPeopleQuantity);
        }
        if (hotelType != null && !hotelType.isEmpty()) {
            int filterHotelType = Integer.parseInt(hotelType);
            tourFilter.setHotelType(filterHotelType);
        }
        LOG.trace("TourFilter object is created --> " + tourFilter);

        List<Tour> tours = tourDao.findAllTours(tourFilter);
        LOG.trace("Found in DB: tourList --> " + tours);

        request.setAttribute("allTours", tours);
        LOG.trace("Set the request attribute: allTours --> " + tours);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_TOURS;
    }
}
