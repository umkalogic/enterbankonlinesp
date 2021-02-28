package ua.svitl.enterbank.servletproject.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ParametersUtils;
import ua.svitl.enterbank.servletproject.controller.command.utils.UserDataFormErrors;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.service.UserService;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;
import ua.svitl.enterbank.servletproject.utils.exception.CommandException;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;
import ua.svitl.enterbank.servletproject.utils.resource.ResourcesBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SubmitFormForUserUpdateCommand implements Command {
    private static final long serialVersionUID = 5002523440278557319L;
    private final UserService userService = new UserService();
    private static final Logger LOG = LogManager.getLogger(SubmitFormForUserUpdateCommand.class);

    /**
     * Execution method for command.
     *
     * @param request  http request
     * @param response http response
     * @return CommandResult with address and forward/redirect command to go once the command is executed.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        UserDataFormErrors formValidationErrors = new UserDataFormErrors();
        Integer id = null;
        try {
            ParametersUtils.checkIfNull(request, "id", "cannot.be.null");
            id = ParametersUtils.getId(request, "id", "wrong.id");

            String userName = formValidationErrors.getValidUsername(request);

            String email = formValidationErrors.getValidEmail(request);

            if (formValidationErrors.getUserNameError() != null
                    || formValidationErrors.getEmailError() != null) {
                return CommandResult.redirect(ControllerConstants.COMMAND_ADMIN_SHOW_FORM_FOR_USER_UPDATE +
                        "&id=" + request.getParameter("id") +
                        "&invalidusername=" + formValidationErrors.getUserNameError() +
                        "&invalidemail=" +  formValidationErrors.getEmailError());
            }

            String[] isactives = request.getParameterValues("isactive");
            Arrays.stream(isactives).forEach(e -> LOG.debug("ISACTIVES: {}", e));

            boolean active = false;
            if(isactives.length > 1) { // If checkbox is checked than assign it with true or 1
                active = true;
            }

            LOG.debug("Start user update command: user id={}", id);

            User user = new User();
            user.setUserId(id);
            user.setUserName(userName);
            user.setEmail(email);
            user.setIsActive(active);

            userService.updateUser(user);

            request.setAttribute("infoMessage", "user.update.successfull");
            LOG.debug("Redirecting to... {}", ControllerConstants.COMMAND_ADMINHOME);
            return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME +
                    "&infomessage=user.update.successfull");

        } catch (ServiceException ex) {
            LOG.error("Error loading user by id ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", "label.error.loading.data");
            request.setAttribute("infoMessage", ex.getMessage());
            return CommandResult.redirect(ControllerConstants.COMMAND_ADMIN_SHOW_FORM_FOR_USER_UPDATE +
                    "&id=" + request.getParameter("id") +
                    "&errormessage=" + request.getAttribute("errorMessage") +
                    "&infomessage=" + request.getAttribute("infoMessage"));

        } catch (CommandException ex) {
            LOG.error("Validation error in user update form ==> {}", ex.getMessage());
            request.setAttribute("errorMessage", "label.error.data");
            request.setAttribute("infoMessage", ex.getMessage());
            return CommandResult.redirect(ControllerConstants.COMMAND_ADMINHOME +
                    "&errormessage=" + request.getAttribute("errorMessage") +
                    "&infomessage=" + request.getAttribute("infoMessage"));
        }

    }
}
