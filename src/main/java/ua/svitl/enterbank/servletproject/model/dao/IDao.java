package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface IDao<E, K> extends AutoCloseable {
    Optional<E> findById(K id) throws DaoException;
    List<E> findAll() throws DaoException;
    boolean create(E entity) throws DaoException;
    boolean update(E entity) throws DaoException;
    boolean delete(E entity) throws DaoException;
}