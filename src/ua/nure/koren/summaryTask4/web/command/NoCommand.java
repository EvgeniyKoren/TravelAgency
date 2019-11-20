package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No command.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class NoCommand extends AbstractCommand {

	private static final long serialVersionUID = -2145976616686657267L;

	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

		LOG.debug("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}
}