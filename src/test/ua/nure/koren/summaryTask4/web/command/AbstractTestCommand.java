package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public class AbstractTestCommand {

    //todo: move to abstract test class
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
}
