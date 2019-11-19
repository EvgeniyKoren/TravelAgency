package ua.nure.koren.summaryTask4.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author E.Koren
 *
 */
public class Messages {

	private Messages() {
	}
	
	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_USERS = "Cannot obtain users";

	public static final String ERR_CANNOT_OBTAIN_TOUR = "Cannot obtain tour";

	public static final String ERR_CANNOT_OBTAIN_TOURS = "Cannot obtain tours";

	public static final String ERR_CANNOT_ORDER = "Tour is not available";

	public static final String ERR_CANNOT_OBTAIN_ORDERS = "Cannot obtain orders";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

	public static final String ERR_CANNOT_UPDATE_TOUR = "Cannot update a tour";

	public static final String ERR_CANNOT_UPDATE_TOUR_TO_LAST_MINUTE = "Cannot update last minute status of tour";

	public static final String ERR_CANNOT_UPDATE_TOUR_TYPE = "Cannot update type of the tour";

	public static final String ERR_CANNOT_UPDATE_TOUR_SALE = "Cannot update sale of the tour";

	public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user";

	public static final String ERR_CANNOT_INSERT_ORDER = "Cannot insert an order";

	public static final String ERR_CANNOT_INSERT_TOUR = "Cannot insert a tour";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_EMPTY_REQUEST = "All parameters can't be empty";

	public static final String ERR_NEGATIVE_PARAMETER = "Input data can't be negative";

	public static final String ERR_CANNOT_DELETE_TOUR = "Cannot delete a tour";

}