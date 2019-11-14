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

        String filterType = request.getParameter("filterType");
        LOG.trace("Request parameter: type --> " + filterType);
        String filterPrice = request.getParameter("filterPrice");
        LOG.trace("Request parameter: price --> " + filterPrice);
        String filterPeopleQuantity = request.getParameter("filterPeopleQuantity");
        LOG.trace("Request parameter: peopleQuantity --> " + filterPeopleQuantity);
        String filterHotelType = request.getParameter("filterHotelType");
        LOG.trace("Request parameter: hotelType --> " + filterHotelType);

        TourFilter tourFilter = new TourFilter();

        if (filterType != null && !filterType.isEmpty()) {
            tourFilter.setType(filterType);
        }
        if (filterPrice != null && !filterPrice.isEmpty()) {
            int price = Integer.parseInt(filterPrice);
            tourFilter.setPrice(price);
        }
        if (filterPeopleQuantity != null && !filterPeopleQuantity.isEmpty()) {
            int peopleQuantity = Integer.parseInt(filterPeopleQuantity);
            tourFilter.setPeopleQuantity(peopleQuantity);
        }
        if (filterHotelType != null && !filterHotelType.isEmpty()) {
            int hotelType = Integer.parseInt(filterHotelType);
            tourFilter.setHotelType(hotelType);
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
