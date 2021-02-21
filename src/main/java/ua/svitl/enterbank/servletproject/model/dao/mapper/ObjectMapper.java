package ua.svitl.enterbank.servletproject.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Object mapper interface. Builds objects.
 * @param <T> the type of element to build
 */
public interface ObjectMapper<T> {
    /**
     * Builds object of type T from the given query result.
     * @param resultSet result set from the query
     * @return a built object
     * @throws SQLException if could not be completed
     */
    T map(ResultSet resultSet) throws SQLException;
}
