package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.db.entity.Tour;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.web.command.AddOrUpdateTourCommand;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddOrUpdateTourCommandTest extends AbstractTestCommand {

    @InjectMocks
    private AddOrUpdateTourCommand addOrUpdateTourCommand;

    @Mock
    private TourDao tourDao;

    @Test
    public void execute() throws AppException, ServletException, IOException {
        //given
        String country = "USA";
        String city = "Los Angeles";
        String hotelName = "Hotel";
        String requestHotelType = "5";
        String requestDuration = "10";
        String requestPeopleQuantity = "2";
        String requestPrice = "11111";
        String requestLastMinute = "true";
        String type = "rest";
        String status = "free";
        String saleForTour = "10";

        when(request.getParameter("country")).thenReturn(country);
        when(request.getParameter("city")).thenReturn(city);
        when(request.getParameter("hotelName")).thenReturn(hotelName);
        when(request.getParameter("hotelType")).thenReturn(requestHotelType);
        when(request.getParameter("duration")).thenReturn(requestDuration);
        when(request.getParameter("peopleQuantity")).thenReturn(requestPeopleQuantity);
        when(request.getParameter("price")).thenReturn(requestPrice);
        when(request.getParameter("lastMinute")).thenReturn(requestLastMinute);
        when(request.getParameter("type")).thenReturn(type);
        when(request.getParameter("status")).thenReturn(status);
        when(request.getParameter("sale")).thenReturn(saleForTour);

        Tour tour = new Tour();
        tour.setCountry(country);
        tour.setStatus(status);

//        when(tourDao.insertTour(tour)).thenReturn(true);

        //when
        String result = addOrUpdateTourCommand.execute(request, response);

        //then
        assertThat(result, is("/WEB-INF/jsp/tours.jsp"));
    }

    @Test
    public void executeUpdateTour() throws AppException, ServletException, IOException {
        //given
        String country = "USA";
        String city = "Los Angeles";
        String hotelName = "Hotel";
        String tourId = "2";

        when(request.getParameter("country")).thenReturn(country);
        when(request.getParameter("city")).thenReturn(city);
        when(request.getParameter("hotelName")).thenReturn(hotelName);
        when(request.getParameter("tourId")).thenReturn(tourId);

        Tour tour = new Tour();
//        when(tourDao.updateTour(tour)).thenReturn(true);

        //when
        String result = addOrUpdateTourCommand.execute(request, response);

        //then
        assertThat(result, is("/WEB-INF/jsp/tours.jsp"));
    }

    @Test(expected = AppException.class)
    public void executeTourIdIsNegative() throws AppException, IOException, ServletException {
        // given

        when(request.getParameter("tourId")).thenReturn("-1");

        // when
        addOrUpdateTourCommand.execute(request, response);
    }
}