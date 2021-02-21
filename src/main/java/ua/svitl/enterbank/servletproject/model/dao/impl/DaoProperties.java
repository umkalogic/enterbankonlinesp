package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class loads the DAO properties file 'db.properties'.
 */
public class DaoProperties {
    private static final Logger LOG = LogManager.getLogger(DaoProperties.class);
    private static final DaoProperties instance = new DaoProperties();

    private static final String PROPERTIES_FILE = "db.properties";
    private static final Properties PROPERTIES = new Properties();

    public static DaoProperties getInstance() {
        LOG.debug("Start load DB properties");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        try {
            if (propertiesFile == null) {
                LOG.error("No properties file: {}", PROPERTIES_FILE);
                throw new DaoException("Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
            }
            PROPERTIES.load(propertiesFile);
        } catch (IOException | DaoException ex) {
            LOG.error("Error loading properties file: {}", ex.getMessage());
        }
        LOG.debug("End load DB properties");
        return instance;
    }

    private DaoProperties() {
        super();
    }

    /**
     * Returns the DAOProperties instance specific property value associated with the given key.
     * @param key The key to be associated with a DAOProperties instance specific value.
     * @return The DAOProperties instance specific property value associated with the given key.
     * @throws DaoException If the returned property value is null or empty
     */
    public String getProperty(String key) throws DaoException {
        String property = PROPERTIES.getProperty(key);

        if (property == null || property.trim().length() == 0) {
            throw new DaoException("Required property '" + key + "'"
                        + " is missing in properties file '" + PROPERTIES_FILE + "'.");
        }

        return property;
    }

}