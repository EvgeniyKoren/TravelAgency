package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.db.entity.Tour;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.web.command.ChangeTourCommand;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeTourCommandTest extends AbstractTestCommand {

    @InjectMocks
    private ChangeTourCommand changeTourCommand;

    @Mock
    private TourDao tourDao;

    @Test
    public void execute() throws AppException {
        // given

        String tourId = "1";
        String status = "free";
        String saleForTour = "50";
        int sale = 50;

        when(request.getParameter("tourId")).thenReturn(tourId);
        when(request.getParameter("status")).thenReturn(status);
        when(request.getParameter("sale")).thenReturn(saleForTour);
        when(request.getParameter("lastMinute")).thenReturn("true");

        Tour tour = new Tour();
        tour.setSale(88);
        when(tourDao.getTourById(1)).thenReturn(tour);
        when(tourDao.makeLastMinute(tour)).thenReturn(true);
        when(tourDao.changeTourStatus(tour, status)).thenReturn(true);
        when(tourDao.setSale(tour, sale)).thenReturn(true);

        // when
        String result = changeTourCommand.execute(request, response);

        // then
        assertThat(result, is("/controller?command=showTours"));
    }

    @Test(expected = AppException.class)
    public void executeTourIdIsNull() throws AppException {
        // given

        when(request.getParameter("tourId")).thenReturn(null);

        // when
        changeTourCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void executeTourIdIsNegative() throws AppException {
        // given

        when(request.getParameter("tourId")).thenReturn("-1");

        // when
        changeTourCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void executeSaleIsNegative() throws AppException {
        // given

        when(request.getParameter("tourId")).thenReturn("1");
        when(request.getParameter("sale")).thenReturn("-50");

        // when
        changeTourCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void executeLastMinuteStatusSaleNull() throws AppException {

        // when
        changeTourCommand.execute(request, response);
    }
}