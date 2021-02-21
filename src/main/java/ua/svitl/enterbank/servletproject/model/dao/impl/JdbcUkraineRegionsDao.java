package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.UkraineRegionsDao;
import ua.svitl.enterbank.servletproject.model.entity.UkraineRegions;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcUkraineRegionsDao implements UkraineRegionsDao {
    private static final Logger LOG = LogManager.getLogger(JdbcUkraineRegionsDao.class);
    private Connection connection;

    public JdbcUkraineRegionsDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcUkraineRegionsDao.class.getName());
        this.connection = connection;
    }


    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcUkraineRegionsDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcUkraineRegionsDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }
    @Override
    public Optional<UkraineRegions> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<UkraineRegions> findAll() {
        return null;
    }

    @Override
    public boolean create(UkraineRegions entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(UkraineRegions entity) throws DaoException {
        return false;
    }


    @Override
    public boolean delete(UkraineRegions entity) {
        return false;
    }
}
