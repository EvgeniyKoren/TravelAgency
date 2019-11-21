package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.dao.OrderDao;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetOrdersQuantityCommand extends AbstractCommand {

    private static final long serialVersionUID = -3071536593627692224L;

    private static final Logger LOG = Logger.getLogger(GetOrdersQuantityCommand.class);

    private OrderDao orderDao = OrderDao.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        int ordersSumm = orderDao.getOrdersQuantity();
        LOG.trace("Orders sum --> " + ordersSumm);

        request.setAttribute("rowsNum", ordersSumm);
        LOG.trace("Attribute is set --> " + ordersSumm);

        return Path.COMMAND_SHOW_ORDERS;
    }
}
