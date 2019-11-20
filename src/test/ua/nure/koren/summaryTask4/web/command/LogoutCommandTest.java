package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.web.command.LogoutCommand;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest extends AbstractTestCommand {

    @InjectMocks
    private LogoutCommand logoutCommand;

    @Test
    public void execute() throws ServletException, IOException, AppException {
        //given
        when(request.getSession(false)).thenReturn(session);
        //when
        String result = logoutCommand.execute(request, response);
        //then
        assertThat(result, is("/login.jsp"));
    }
}