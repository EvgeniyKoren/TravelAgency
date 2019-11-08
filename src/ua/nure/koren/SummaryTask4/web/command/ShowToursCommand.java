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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowToursCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ShowToursCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        TourDao tourDao = TourDao.getInstance();

//        String type = request.getParameter("type");
//        LOG.trace("Request parameter: type --> " + type);
//        int price = Integer.parseInt(request.getParameter("price"));
//        LOG.trace("Request parameter: price --> " + price);
//        int peopleQuantity = Integer.parseInt(request.getParameter("peopleQuantity"));
//        LOG.trace("Request parameter: peopleQuantity --> " + peopleQuantity);
//        int hotelType = Integer.parseInt(request.getParameter("hotelType"));
//        LOG.trace("Request parameter: hotelType --> " + hotelType);
//
//        TourFilter tourFilter = new TourFilter();
//        tourFilter.setType(type);
//        tourFilter.setPrice(price);
//        tourFilter.setPeopleQuantity(peopleQuantity);
//        tourFilter.setHotelType(hotelType);
//        LOG.trace("TourFilter object is created --> " + tourFilter);

        List<Tour> tours = tourDao.findAllTours();
        LOG.trace("Found in DB: tourList --> " + tours);

//        HttpSession session = request.getSession(false);
//        session.setAttribute("allTours", tours);
        request.setAttribute("allTours", tours);
        LOG.trace("Set the request attribute: allTours --> " + tours);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_TOURS;
    }
}
