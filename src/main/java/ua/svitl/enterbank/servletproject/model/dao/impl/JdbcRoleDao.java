package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.RoleDao;
import ua.svitl.enterbank.servletproject.model.entity.Role;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcRoleDao implements RoleDao {
    private static final Logger LOG = LogManager.getLogger(JdbcRoleDao.class);
    private final Connection connection;

    public JdbcRoleDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcRoleDao.class.getName());
        this.connection = connection;
    }


    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcRoleDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcRoleDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public boolean create(Role entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Role entity) throws DaoException {
        return false;
    }


    @Override
    public boolean delete(Role entity) {
        return false;
    }
}
