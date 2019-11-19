package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.db.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLanguageCommand extends AbstractCommand {

    private static final long serialVersionUID = -3071536593627692478L;

    private static final Logger LOG = Logger.getLogger(SetLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        String sessionLocale = request.getParameter("sessionLocale");
        LOG.trace("Request parameter: sessionLocale --> " + sessionLocale);

        String forward = Path.COMMAND_SHOW_TOURS;;
        Role userRole = null;

        if (sessionLocale != null && !sessionLocale.isEmpty()) {
            HttpSession session = request.getSession();
            userRole = (Role) session.getAttribute("userRole");
            LOG.trace("Get the session attribute: userRole --> " + userRole.getName());
            session.setAttribute("lang", sessionLocale);
            LOG.trace("Set the session attribute: lang --> " + sessionLocale);
        }

        if (userRole != null) {
            forward = Path.PAGE_USER;
        }

        LOG.debug("Command finished");
        return forward;
    }
}
