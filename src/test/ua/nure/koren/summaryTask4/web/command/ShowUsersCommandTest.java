package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.db.dao.UserDao;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.exception.AppException;
import ua.nure.koren.summaryTask4.exception.DBException;
import ua.nure.koren.summaryTask4.web.command.ShowUsersCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowUsersCommandTest {

    @InjectMocks
    private ShowUsersCommand showUsersCommand;

    @Mock
    private UserDao userDao;

    //todo: move to abstract test class
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void execute() throws Exception {
        // given
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAllUsers()).thenReturn(users);

        // when
        String result = showUsersCommand.execute(request, response);

        // then
        assertThat(result, is("/WEB-INF/jsp/allUsers.jsp"));
        verify(userDao).findAllUsers();
        verify(request).setAttribute("allUsers", users);
    }
}