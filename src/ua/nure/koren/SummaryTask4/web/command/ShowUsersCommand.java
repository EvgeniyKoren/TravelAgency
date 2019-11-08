package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.SummaryTask4.Path;
import ua.nure.koren.SummaryTask4.db.dao.UserDao;
import ua.nure.koren.SummaryTask4.db.entity.TourFilter;
import ua.nure.koren.SummaryTask4.db.entity.User;
import ua.nure.koren.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUsersCommand extends Command {

    private static final long serialVersionUID = 7232286214029478364L;

    private static final Logger LOG = Logger.getLogger(ShowUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        UserDao userDao = UserDao.getInstance();

        List<User> users = userDao.findAllUsers();
        LOG.trace("Found in DB: userList --> " + users);

        request.setAttribute("allUsers", users);
        LOG.trace("Set the request attribute: allUsers --> " + users);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_USERS;
    }
}
