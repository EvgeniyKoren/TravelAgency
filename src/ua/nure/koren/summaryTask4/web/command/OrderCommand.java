package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.dao.OrderDao;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.db.entity.Order;
import ua.nure.koren.summaryTask4.db.entity.Tour;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * OrderCommand command. Uses for creating new order.
 * Adds new line in orders table in the DB.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class OrderCommand extends AbstractCommand {

    private static final long serialVersionUID = -3071536593627692789L;

    private static final Logger LOG = Logger.getLogger(OrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String requestTourId = request.getParameter("tourId");
        LOG.trace("Request parameter: tourId --> " + requestTourId);
        int tourId = 0;
        // validate param
        if (requestTourId != null && !requestTourId.isEmpty()) {
            tourId = Integer.parseInt(requestTourId);
        }
        if (tourId <= 0) {
            throw new AppException(Messages.ERR_NEGATIVE_PARAMETER + ": tour number");
        }

        TourDao tourDao = TourDao.getInstance();
        Tour tour = tourDao.getTourById(tourId);
        if (!tour.getStatus().equals("free")) {
           throw new AppException(Messages.ERR_CANNOT_ORDER);
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        OrderDao orderDao = OrderDao.getInstance();
        boolean success = orderDao.insertOrder(userId, tourId);
        if (success) {
            Order order = new Order(userId, tourId);
            tourDao.changeTourStatus(tour,"registered");
            LOG.trace("User " + user.getLogin() + " has made an order of tour " + tourId);
            LOG.trace("Order --> " + order);
        }
        LOG.debug("Command finished");
        return Path.PAGE_USER;
    }
}
