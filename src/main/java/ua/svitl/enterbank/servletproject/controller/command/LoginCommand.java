package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.ControllerConstants;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.UserService;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static final long serialVersionUID = -6936944036687961187L;
    private final UserService userService = new UserService();
    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("LoginCommand starts");

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("role");

        // obtain login and password from a request
        String login = request.getParameter("user_name");
        String password = request.getParameter("password");
        LOG.trace("Request parameter user_name: {}", login);

        ResourceBundle rb = ResourcesBundle.getResourceBundle(session);

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", rb.getString("label.wronguserorpassword"));
            LOG.debug("errorMessage: wrong user and/or password");
            return CommandResult.forward(ControllerConstants.PAGE_LOGIN);
        }

        try {
            LOG.debug("Start find user by login and name");
            Optional<User> user = userService.findByNameAndPassword(login, password);
            if (user.isPresent()) {
                LOG.debug("User was found: username ==> {}", user.get().getUserName());
                session.setAttribute("role", user.get().getRole().getRoleName());
                session.setAttribute("user", user.get());
                String activeUserFullName = user.get().getPerson().getFirstName() + " " +
                        user.get().getPerson().getLastName();
                session.setAttribute("activeUserName", activeUserFullName);

                if (Objects.equals("ADMIN", user.get().getRole().getRoleName())) {
                    LOG.debug("User has ADMIN role");
                    LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_ADMINHOME);
                    return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME);

                } else if (Objects.equals("USER", user.get().getRole().getRoleName())) {
                    LOG.debug("User has USER role");
                    // todo bankaccount service - list of accounts
                    LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_USERHOME);
                    return CommandResult.redirect(ControllerConstants.COMMAND_USERHOME);
                }
            } else {
                LOG.debug("No user found in the DB");
                throw new ServiceException("No such user in DB");
            }
        } catch (ServiceException ex) {
            LOG.error("Service Exception: {}", ex.getMessage());
            request.setAttribute("errorMessage", rb.getString("label.wronguserorpassword"));
            LOG.debug("errorMessage: wrong user and/or password");
            return CommandResult.forward(ControllerConstants.PAGE_LOGIN);
        }

        LOG.debug("LoginCommand ends");
        return CommandResult.forward(ControllerConstants.PAGE_LOGIN);
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
