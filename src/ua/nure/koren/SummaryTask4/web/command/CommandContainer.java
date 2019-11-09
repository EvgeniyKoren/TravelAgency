package ua.nure.koren.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 * 
 * @author E. Koren
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("signIn", new SignInCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("redirect", new RedirectCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("addUser", new AddUserCommand());
		commands.put("showTours", new ShowToursCommand());

		// client commands
		commands.put("order", new OrderCommand());

		// admin commands
		commands.put("modifyUser", new ModifyUserCommand());
		commands.put("showUsers", new ShowUsersCommand());

		// manager & admin commands
        commands.put("changeTour", new ChangeTourCommand());
        commands.put("addTour", new AddTourCommand());
        commands.put("deleteTour", new DeleteTourCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}