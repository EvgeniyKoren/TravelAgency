package ua.nure.koren.summaryTask4.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 846625786080834988L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
