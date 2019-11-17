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

public class SignInCommand extends AbstractCommand {

    private static final long serialVersionUID = -3071536593627692943L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        UserDao userDao = UserDao.getInstance();

        String firstName = request.getParameter("firstName");
        LOG.trace("Request parameter: firstName --> " + firstName);
        String lastName = request.getParameter("lastName");
        LOG.trace("Request parameter: lastName --> " + lastName);
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        String password = request.getParameter("password");
        LOG.trace("Request parameter: password --> " + password);

        if (firstName == null || lastName == null || login == null || password == null
                || firstName.isEmpty() || lastName.isEmpty() || login.isEmpty() || password.isEmpty()) {
            throw new AppException("All fields must not be empty");
        }

        boolean success = userDao.insertUser(firstName, lastName, login, password);
        if (success) {
            User user = new User(firstName, lastName, login, password);
            LOG.trace("New user is inserted --> " + user);
            LOG.debug("Command finished");
            return Path.PAGE_LOGIN;
        }
        return Path.PAGE_SIGN_IN;
    }
}
