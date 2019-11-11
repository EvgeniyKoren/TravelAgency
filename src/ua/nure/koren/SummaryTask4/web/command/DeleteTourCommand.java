package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.Path;
import ua.nure.koren.SummaryTask4.db.dao.TourDao;
import ua.nure.koren.SummaryTask4.exception.AppException;
import ua.nure.koren.SummaryTask4.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTourCommand extends Command {

    private static final long serialVersionUID = -3071536593627692977L;

    private static final Logger LOG = Logger.getLogger(DeleteTourCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        TourDao tourDao = TourDao.getInstance();

        String tourId = request.getParameter("tourId");
        LOG.trace("Request parameter: tourId --> " + tourId);
        if (tourId == null || tourId.isEmpty()) {
            throw new AppException("Tour id must not be empty");
        }
        int id = Integer.parseInt(tourId);
        if (id <= 0) {
            throw new AppException(Messages.ERR_NEGATIVE_PARAMETER);
        }

        boolean success = tourDao.deleteTourById(id);
        if (success) {
            LOG.trace("Tour № " + id + " has been deleted");
        }

        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_TOURS;
    }
}
