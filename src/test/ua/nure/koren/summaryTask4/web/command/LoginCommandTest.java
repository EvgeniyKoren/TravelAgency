package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.db.Role;
import ua.nure.koren.summaryTask4.db.dao.UserDao;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.web.command.LoginCommand;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest extends AbstractTestCommand {

    @InjectMocks
    private LoginCommand loginCommand;

    @Mock
    private UserDao userDao;

    @Test
    public void execute() throws AppException {
        //given
        String login = "login";
        String password = "password";
        int roleId = 1;

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);

        User user = new User();
        user.setFirstName("Boris");
        user.setLastName("Britva");
        user.setLogin(login);
        user.setPassword(password);
        user.setRoleId(roleId);
        Role userRole = Role.MANAGER;
        when(userDao.getUser(login)).thenReturn(user);

        //when
        String result = loginCommand.execute(request, response);

        //then
        assertThat(result, is("/WEB-INF/jsp/userPage.jsp"));
        verify(session).setAttribute("user", user);
        verify(session).setAttribute("userRole", userRole);
    }

    @Test(expected = AppException.class)
    public void executeIncorrectLogin() throws AppException {
        //given
        String login = "login";
        String password = "password";

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);

        User user = new User();
        user.setLogin("other");
        user.setPassword(password);

        //when
        loginCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void executeUserIsNull() throws AppException {
        //given
        User user = null;

        //when
        loginCommand.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void executeLoginIsNull() throws AppException {
        //given
        when(request.getParameter("login")).thenReturn(null);

        //when
        loginCommand.execute(request, response);
    }
}