package org.atamertc.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    private static SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Hibernate SessionFactory Hatasi: " + e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
