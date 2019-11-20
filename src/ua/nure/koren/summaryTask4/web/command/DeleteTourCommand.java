package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteTourCommand command.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class DeleteTourCommand extends AbstractCommand {

    private static final long serialVersionUID = -3071536593627692977L;

    private static final Logger LOG = Logger.getLogger(DeleteTourCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

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
