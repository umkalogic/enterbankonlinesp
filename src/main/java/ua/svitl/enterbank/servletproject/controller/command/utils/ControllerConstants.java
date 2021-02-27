package ua.svitl.enterbank.servletproject.controller.command.utils;

public final class ControllerConstants {
    // all users pages
    public static final String PAGE_LOGIN = "/WEB-INF/templates/login.jsp";
    public static final String PAGE_ERROR = "/WEB-INF/templates/error.jsp";
    public static final String PAGE_INDEX = "index.jsp";
    public static final String ERROR400 = "/WEB-INF/templates/error400.html";

    // admin pages
    public static final String PAGE_ADMIN_HOME = "/WEB-INF/templates/admin/home.jsp";
    public static final String PAGE_ADMIN_UPDATE_USER = "/WEB-INF/templates/admin/update_user.jsp";
    public static final String PAGE_ADMIN_USER_ACCOUNTS = "/WEB-INF/templates/admin/user_accounts.jsp";

    // user pages
    public static final String PAGE_USER_HOME = "/WEB-INF/templates/user/home.jsp";
    public static final String PAGE_USER_PAYMENTS = "/WEB-INF/templates/user/payments.jsp";
    public static final String PAGE_USER_MAKE_PAYMENT = "/WEB-INF/templates/user/make_payment.jsp";
    public static final String PAGE_USER_SEND_PAYMENT = "/WEB-INF/templates/user/send_payment.jsp";

    // pagination
    public static final int PAGE_SIZE = 5;

    // commands
    public static final String COMMAND_LOGIN = "controller?command=login";
    public static final String COMMAND_LOGOUT = "controller?command=logout";
    public static final String COMMAND_ADMINHOME = "controller?command=adminhome";
    public static final String COMMAND_USERHOME = "controller?command=userhome";
    public static final String COMMAND_ADMIN_SHOW_USER_ACCOUNTS = "controller?command=showuseraccounts";
    public static final String COMMAND_USER_PAYMENTS = "controller?command=payments";
    public static final String COMMAND_ADMIN_SHOW_FORM_FOR_USER_UPDATE = "controller?command=showformforuserupdate";
    public static final String COMMAND_USER_SHOW_FORM_FOR_PAYMENT = "controller?command=showformforpayment";

    // attributes
    public static final String ATTR_PAYMENT = "payment";


    private ControllerConstants() {}
}
