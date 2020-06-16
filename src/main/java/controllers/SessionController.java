package controllers;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SessionController {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Properties properties = propertyLoad();
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml").addProperties(properties);

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static org.hibernate.Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static Properties propertyLoad() {

        Properties properties = new Properties();
        try {
            File external = new File("./app.properties");
            if (external.exists())
                properties.load(new FileInputStream(external));
            else
                properties.load(SessionController.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
