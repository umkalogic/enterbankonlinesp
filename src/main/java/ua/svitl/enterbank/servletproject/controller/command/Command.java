package ua.svitl.enterbank.servletproject.controller.command;

import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public interface Command extends Serializable {
    /**
     * Execution method for command.
     *
     * @param request http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException;

}
