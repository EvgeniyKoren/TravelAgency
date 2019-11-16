package ua.nure.koren.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.Path;

import java.util.Map;
import java.util.TreeMap;

public class PageContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, String> pages = new TreeMap<>();

    static {
        pages.put("admin", Path.PAGE_ADMIN);
        pages.put("signIn", Path.PAGE_SIGN_IN);
        pages.put("userPage", Path.PAGE_USER);
        pages.put("error", Path.PAGE_ERROR_PAGE);

        LOG.debug("Page container was successfully initialized");
        LOG.trace("Number of commands --> " + pages.size());
    }

    /**
     * Returns path to page with the given name.
     *
     * @param page
     *            Name of the page.
     * @return String object.
     */
    public static String getPageLocation(String page) {
        if (page == null || !pages.containsKey(page)) {
            LOG.trace("Page not found, name --> " + page);
            return pages.get("error");
        }

        return pages.get(page);
    }
}
