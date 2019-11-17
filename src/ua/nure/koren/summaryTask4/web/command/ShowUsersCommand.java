package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.dao.UserDao;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUsersCommand extends AbstractCommand {

    private static final long serialVersionUID = 7232286214029478364L;

    private static final Logger LOG = Logger.getLogger(ShowUsersCommand.class);

    private UserDao userDao = UserDao.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");

        List<User> users = userDao.findAllUsers();
        LOG.trace("Found in DB: userList --> " + users);

        request.setAttribute("allUsers", users);
        LOG.trace("Set the request attribute: allUsers --> " + users);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_USERS;
    }
}
