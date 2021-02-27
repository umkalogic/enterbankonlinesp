package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;
import ua.svitl.enterbank.servletproject.model.dto.UserPersonDataDto;
import ua.svitl.enterbank.servletproject.model.service.UserService;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

public class AdminHomeCommand implements Command {
    private static final long serialVersionUID = 8607825782323439658L;
    private final UserService userService = new UserService();
    private static final Logger LOG = LogManager.getLogger(AdminHomeCommand.class);

    /**
     * Execution method for command.
     *
     * @param request http request
     * @param response http response
     * @return Address to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (null == user) {
            request.setAttribute("errorMessage", "Session has ended.  Please login.");
            request.setAttribute("infoMessage", "You must login to enter this page");
            throw new AppException("You must login to enter this page");
        }

        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);
        try {
            int pageSize = ControllerConstants.PAGE_SIZE;
            List<UserPersonDataDto> userList =
                    userService.getAllUsers(1, pageSize + 1, "user_name", "asc");

            request.setAttribute("listUserPersonData", userList);
            ParametersUtils.setPaginationAttributes(request, "user_name", "asc", 1,
                    pageSize, userList.size());

            if (request.getParameter("infomessage") != null) {
                request.setAttribute("infoMessage", request.getParameter("infomessage"));
            }

            if (request.getParameter("errormessage") != null) {
                request.setAttribute("errorMessage", request.getParameter("errormessage"));
            }

            LOG.debug("Forwarding to... {}", ControllerConstants.PAGE_ADMIN_HOME);
            return CommandResult.forward(ControllerConstants.PAGE_ADMIN_HOME);
        } catch (ServiceException ex) {
            LOG.error("AdminHome: error loading admin home page");
            request.setAttribute("errorMessage", rb.getString("label.error.loading.data"));
            request.setAttribute("infoMessage", ex.getMessage());
            return CommandResult.forward(ControllerConstants.PAGE_ADMIN_HOME);
        }
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
