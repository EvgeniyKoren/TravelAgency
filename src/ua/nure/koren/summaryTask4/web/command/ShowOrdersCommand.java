package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.dao.OrderDao;
import ua.nure.koren.summaryTask4.db.entity.Order;
import ua.nure.koren.summaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ShowOrdersCommand command. Reflects all available orders in DB.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class ShowOrdersCommand extends AbstractCommand {

    private static final long serialVersionUID = 7232286214029478778L;

    private static final Logger LOG = Logger.getLogger(ShowOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        OrderDao orderDao = OrderDao.getInstance();
        List<Order> orders = orderDao.findAllOrders();
        LOG.trace("Found in DB: orderList --> " + orders);

        request.setAttribute("allOrders", orders);
        LOG.trace("Set the request attribute: allOrders --> " + orders);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_ORDERS;
    }
}
