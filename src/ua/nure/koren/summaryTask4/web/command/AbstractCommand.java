package ua.nure.koren.summaryTask4.web.command;

import ua.nure.koren.summaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public abstract class AbstractCommand implements Serializable {

	private static final long serialVersionUID = 8879403055606311780L;

	/**
	 * Execution method for command.
	 * 
	 * @return Address to go once the command is executed.
	 */
	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}