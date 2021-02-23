package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.resource.ResourcesBundle;
import ua.svitl.enterbank.servletproject.model.dto.UserPersonDataDto;
import ua.svitl.enterbank.servletproject.model.service.UserService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

public class ShowUsersCommand implements Command {
    private static final long serialVersionUID = -5000523440278557319L;
    private final UserService userService = new UserService();
    private static final Logger LOG = LogManager.getLogger(ShowUsersCommand.class);

    /**
     * Execution method for command.
     *
     * @param request http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        try {
            LOG.debug("Start show users command");
            int pageSize = ControllerConstants.PAGE_SIZE;
            int pageNo = setPageNo(request);

            String sortField = request.getParameter("sortfield");
            String sortDir = request.getParameter("sortdir");
            if (sortField == null) sortField = "user_name";
            if (sortDir == null) sortDir = "asc";

            LOG.debug("Show users pagination: pageNo={}, sortField={}, sortDir={}", pageNo, sortField, sortDir);
            List<UserPersonDataDto> userList =
                    userService.getAllUsers(pageNo, pageSize + 1, sortField, sortDir);

            userList.forEach(e -> LOG.debug(e.toString()));
            request.setAttribute("listUserPersonData", userList);
            request.setAttribute("currentPage", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("prev", pageNo > 1);
            request.setAttribute("next", userList.size() == pageSize + 1);

            request.setAttribute("sortField", sortField);
            request.setAttribute("sortDir", sortDir);
            request.setAttribute("reverseSortDir", "asc".equalsIgnoreCase(sortDir) ? "desc" : "asc");

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_ADMIN_HOME);
            return CommandResult.forward(ControllerConstants.PAGE_ADMIN_HOME);
        } catch (ServiceException ex) {
            LOG.error("AdminHome: error loading users list");
            HttpSession session = request.getSession();
            ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
            LOG.debug("Get resource bundle locale ==> {}", rb.getLocale());
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            return CommandResult.forward(ControllerConstants.PAGE_ADMIN_HOME);
        }
    }

    private static int setPageNo(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("currentpage"));
        } catch (NumberFormatException ex) {
            LOG.error("Couldn't parse to Int ==> {}", ex.getMessage());
            LOG.trace("Setting currentpage to 1");
            return 1;
        }
    }
}
