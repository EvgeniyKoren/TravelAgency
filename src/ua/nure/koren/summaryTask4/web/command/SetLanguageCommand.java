package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;
import ua.nure.koren.summaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLanguageCommand extends Command {

    private static final long serialVersionUID = -3071536593627692478L;

    private static final Logger LOG = Logger.getLogger(SetLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String sessionLocale = request.getParameter("sessionLocale");
        LOG.trace("Request parameter: sessionLocale --> " + sessionLocale);

        if (sessionLocale != null && !sessionLocale.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("lang", sessionLocale);
            LOG.trace("Set the session attribute: lang --> " + sessionLocale);
        }

        LOG.debug("Command finished");
        return Path.COMMAND_SHOW_TOURS;
    }
}
