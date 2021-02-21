package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PersonDao;
import ua.svitl.enterbank.servletproject.model.entity.Person;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcPersonDao implements PersonDao {
    private static final Logger LOG = LogManager.getLogger(JdbcPersonDao.class);
    private Connection connection;

    public JdbcPersonDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcPersonDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcPersonDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcPersonDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }

    @Override
    public Optional<Person> findById(Integer id) {
        // query.find.person.by.person.id
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public boolean create(Person entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Person entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Person entity) {
        return false;
    }
}
