package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.Path;
import ua.nure.koren.SummaryTask4.db.dao.UserDao;
import ua.nure.koren.SummaryTask4.db.entity.User;
import ua.nure.koren.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyUserCommand extends Command {

    private static final long serialVersionUID = 7232286214029478313L;

    private static final Logger LOG = Logger.getLogger(ShowUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        UserDao userDao = UserDao.getInstance();
        String login = request.getParameter("userLogin");
        LOG.trace("Request parameter: userLogin --> " + login);

        User user = userDao.getUser(login);
        LOG.trace("Found in DB: user --> " + user);

        boolean operationStatus = userDao.updateUserStatus(user);
        if (operationStatus) {
            LOG.trace("User status was updated successfully!");
        }

        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_USERS;
    }
}
