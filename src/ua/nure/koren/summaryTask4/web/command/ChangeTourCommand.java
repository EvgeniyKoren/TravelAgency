package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.db.entity.Tour;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.exception.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ChangeTourCommand command. Uses for updating tour in DB
 * by manager.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class ChangeTourCommand extends AbstractCommand {

    private static final long serialVersionUID = 7232286214029478788L;

    private static final Logger LOG = Logger.getLogger(ChangeTourCommand.class);

    private TourDao tourDao = TourDao.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");

        String tourId = request.getParameter("tourId");
        LOG.trace("Request parameter: tourId --> " + tourId);
        if (tourId == null || tourId.isEmpty()) {
            throw new AppException("Tour id must not be empty");
        }
        int id = Integer.parseInt(tourId);
        if (id <= 0) {
            throw new AppException(Messages.ERR_NEGATIVE_PARAMETER);
        }

        Tour tour = tourDao.getTourById(id);
        String requestLastMinute = request.getParameter("lastMinute");
        LOG.trace("Request parameter: lastMinute --> " + requestLastMinute);
        if (requestLastMinute != null) {
            boolean success = tourDao.makeLastMinute(tour);
            if (success) {
                LOG.trace("The value of the last_minute field has been changed");
            }
        }

        String status = request.getParameter("status");
        LOG.trace("Request parameter: status --> " + status);
        if (status != null) {
            boolean success = tourDao.changeTourStatus(tour, status);
            if (success) {
                LOG.trace("Tour status has been changed to --> " + status);
            }
        }

        String saleForTour = request.getParameter("sale");
        LOG.trace("Request parameter: sale --> " + saleForTour);
        if (saleForTour != null && !saleForTour.isEmpty()) {
            int sale = Integer.parseInt(saleForTour);
            if (sale < 0) {
                throw new AppException(Messages.ERR_NEGATIVE_PARAMETER);
            }
            boolean success = tourDao.setSale(tour, sale);
            if (success) {
                LOG.trace("Tour sale has been set to --> " + sale);
            }
        }

        if (requestLastMinute == null && status == null && saleForTour == null) {
            throw new AppException(Messages.ERR_EMPTY_REQUEST);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_TOURS;
    }
}
