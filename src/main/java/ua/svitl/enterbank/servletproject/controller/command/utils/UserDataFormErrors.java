package ua.svitl.enterbank.servletproject.controller.command.utils;

import ua.svitl.enterbank.servletproject.utils.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

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
    public String getValidUsername(HttpServletRequest request) {
        String userName = "";
        try {
            userName = ParametersUtils.getString(request, "username", "cannot.be.null");
            ParametersUtils.checkUserName(userName,"wrong.username.pattern");
        } catch (CommandException e) {
            userNameError = e.getMessage();
        }
        return userName;
    }

    public String getValidEmail(HttpServletRequest request) {
        String email = "";
        try {
            email = ParametersUtils.getString(request, "email", "cannot.be.null");
            ParametersUtils.checkEmail(email,"valid.email");
        } catch (CommandException e) {
            setEmailError(e.getMessage());
        }
        return email;
    }
}
