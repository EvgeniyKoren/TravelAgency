package ua.nure.koren.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author E.Koren
 *
 */
public final class Path {

  // pages
  public static final String PAGE_LOGIN = "/login.jsp";
  public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
  public static final String PAGE_LIST_TOURS = "/WEB-INF/jsp/tours.jsp";
  public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin_page.jsp";
  public static final String PAGE_SIGN_IN = "/WEB-INF/jsp/signIn.jsp";
  public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

  // commands
  public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
  public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";

}
