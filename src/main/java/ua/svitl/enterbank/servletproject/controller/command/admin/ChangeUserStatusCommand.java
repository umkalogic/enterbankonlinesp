package ua.svitl.enterbank.servletproject.controller.command.admin;

import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserStatusCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request  http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        //User user = userService.updateUserActive(id, status);
        return null;
    }
}
