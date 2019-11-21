package ua.nure.koren.summaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public final class Path {

  // pages
  public static final String PAGE_LOGIN = "/login.jsp";
  public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
  public static final String PAGE_LIST_TOURS = "/WEB-INF/jsp/tours.jsp";
  public static final String PAGE_SIGN_IN = "/WEB-INF/jsp/signIn.jsp";
  public static final String PAGE_USER = "/WEB-INF/jsp/userPage.jsp";
  public static final String PAGE_LIST_USERS = "/WEB-INF/jsp/allUsers.jsp";
  public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/allOrders.jsp";

  // commands
  public static final String COMMAND_SHOW_USERS = "/controller?command=showUsers";
  public static final String COMMAND_SHOW_TOURS = "/controller?command=showTours";
  public static final String COMMAND_SHOW_ORDERS = "controller?command=showOrders";

}
