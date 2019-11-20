package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirect command.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class RedirectCommand extends AbstractCommand {
	
	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger LOG = Logger.getLogger(RedirectCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {		
		LOG.debug("Command starts");

		String pageName = request.getParameter("pageName");

		LOG.debug("Command finished");
		return PageContainer.getPageLocation(pageName);
	}

}