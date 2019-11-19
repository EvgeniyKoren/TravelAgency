package ua.nure.koren.summaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.koren.summaryTask4.exception.DBException;
import ua.nure.koren.summaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DB manager. Works with MySQL DB. Holders only one method which provides
 * connection with database.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public static synchronized Connection getConnection() throws DBException {
        DataSource ds = null;
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext =
                    (Context) initContext.lookup("java:/comp/env");

            ds = (DataSource) envContext.lookup("jdbc/travel_agency");
            con = ds.getConnection();
            LOG.trace("Data source --> " + ds);
            LOG.trace("Connection --> " + con);
        } catch (NamingException | SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }
}
