package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;

public class AbstractTestCommand {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }
}
