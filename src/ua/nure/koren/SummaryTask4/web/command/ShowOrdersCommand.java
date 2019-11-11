package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.Path;
import ua.nure.koren.SummaryTask4.db.dao.OrderDao;
import ua.nure.koren.SummaryTask4.db.entity.Order;
import ua.nure.koren.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowOrdersCommand extends Command {

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
