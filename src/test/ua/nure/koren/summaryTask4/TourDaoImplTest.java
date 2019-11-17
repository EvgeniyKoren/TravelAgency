package test.ua.nure.koren.summaryTask4;

import org.junit.Before;
import org.junit.Test;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.db.entity.Tour;
import ua.nure.koren.summaryTask4.exception.DBException;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class TourDaoImplTest {

    private TourDao testInstance;

    @Before
    public void setUp() {
       testInstance = TourDao.getInstance();
    }

    @Test
    public void testGetInstanceFirstTime() {
        // when
        TourDao instance = TourDao.getInstance();

        // then
        assertNotNull(instance);
    }

    @Test
    public void testGetInstance() {
        // given
        TourDao instance = TourDao.getInstance();

        // when
        TourDao instanceSecondCall = TourDao.getInstance();

        // then
        assertSame(instance, instanceSecondCall);
    }

    @Test
    public void getTourById() {
        // given
        Tour tour = new Tour();

//        testInstance.insertTour();

    }

    @Test
    public void findAllTours() {
    }

    @Test
    public void makeLastMinute() {
    }

    @Test
    public void changeTourStatus() {
    }

    @Test
    public void setSale() {
    }

    @Test
    public void insertTour() throws DBException {
        // given
        Tour tour = createTour();
        assertThat(tour.getId(), is(0));

        // when
        testInstance.insertTour(tour);

        // then
        assertThat(tour.getId(), not(0));
    }

    private Tour createTour() {
        Tour tour = new Tour();
        tour.setType("excursion");
        tour.setCountry("Russia");
        tour.setCity("Moscow");
        tour.setHotelName("Moscow Hotel");
        tour.setPrice(11100);
        tour.setPeopleQuantity(2);
        tour.setDuration(5);
        tour.setHotelType(5);
        tour.setLastMinute(false);
        tour.setStatus("free");
        tour.setSale(50);
        return tour;
    }

    @Test
    public void deleteTourById() {
    }

    @Test
    public void updateTour() {
    }
}