package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.web.command.DeleteTourCommand;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTourCommandTest extends AbstractTestCommand {

    @InjectMocks
    private DeleteTourCommand deleteTourCommand;

    @Mock
    private TourDao tourDao;

    @Test
    public void execute() throws AppException {
        //given
        String tourId = "1";
        int id = 1;

        when(request.getParameter("tourId")).thenReturn(tourId);
        when(tourDao.deleteTourById(id)).thenReturn(true);

        //when
        String result = deleteTourCommand.execute(request, response);

        //then
        assertThat(result, is("/controller?command=showTours"));
    }

    @Test(expected = AppException.class)
    public void executeTourIdIsNull() throws AppException {
        // given

        when(request.getParameter("tourId")).thenReturn(null);

        // when
        deleteTourCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void executeTourIdIsNegative() throws AppException {
        // given

        when(request.getParameter("tourId")).thenReturn("-1");

        // when
        deleteTourCommand.execute(request, response);
    }

}