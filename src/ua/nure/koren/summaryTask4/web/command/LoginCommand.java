package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.Role;
import ua.nure.koren.summaryTask4.db.dao.UserDao;
import ua.nure.koren.summaryTask4.db.entity.User;
import ua.nure.koren.summaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class LoginCommand extends AbstractCommand {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from a request
        UserDao userDao = UserDao.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = userDao.getUser(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Role userRole = Role.getByRoleId(user.getRoleId());
        LOG.trace("userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole != null) {
            forward = Path.PAGE_USER;
        }

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");
        return forward;
    }
}
