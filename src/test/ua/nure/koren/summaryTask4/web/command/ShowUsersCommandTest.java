package test.ua.nure.koren.summaryTask4.web.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.koren.summaryTask4.db.dao.UserDao;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.web.command.ShowUsersCommand;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowUsersCommandTest extends AbstractTestCommand {

    @InjectMocks
    private ShowUsersCommand showUsersCommand;

    @Mock
    private UserDao userDao;

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