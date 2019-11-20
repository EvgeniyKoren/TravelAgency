package test.ua.nure.koren.summaryTask4;

import org.junit.Before;
import org.junit.Test;
import ua.nure.koren.summaryTask4.db.dao.TourDao;
import ua.nure.koren.summaryTask4.db.entity.Tour;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class TourDaoTest {

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
}