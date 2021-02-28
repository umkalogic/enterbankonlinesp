package ua.svitl.enterbank.servletproject.controller.command.utils;

import ua.svitl.enterbank.servletproject.utils.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class UserDataFormErrors {
    String userNameError;
    String emailError;

    public UserDataFormErrors() {
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    @Override
    public String toString() {
        return "UserDataFormErrors{" +
                "userNameError='" + userNameError + '\'' +
                ", emailError='" + emailError + '\'' +
                '}';
    }
    public String getValidUsername(HttpServletRequest request, ResourceBundle rb) {
        String userName = "";
        try {
            userName = ParametersUtils.getString(request, "username",
                    rb.getString("cannot.be.null"));
            ParametersUtils.checkUserName(userName, rb.getString("wrong.username.pattern"));
        } catch (CommandException e) {
            userNameError = e.getMessage();
        }
        return userName;
    }

    public String getValidEmail(HttpServletRequest request, ResourceBundle rb) {
        String email = "";
        try {
            email = ParametersUtils.getString(request, "email",  rb.getString("cannot.be.null"));
            ParametersUtils.checkEmail(email, rb.getString("valid.email"));
        } catch (CommandException e) {
            setEmailError(e.getMessage());
        }
        return email;
    }
}
