package ua.svitl.enterbank.servletproject.controller;

public final class ControllerConstants {
    // all users pages
    public static final String PAGE_LOGIN = "/WEB-INF/templates/login.jsp";
    public static final String PAGE_ERROR = "/WEB-INF/templates/error.jsp";

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
    public static final String PAGE_INDEX = "index.jsp";

    // commands
    public static final String COMMAND_LOGIN = "controller?command=login";
    public static final String COMMAND_LOGOUT = "controller?command=logout";
    public static final String COMMAND_ADMINHOME = "controller?command=adminhome";
    public static final String COMMAND_USERHOME = "controller?command=userhome";

    // attributes
    public static final String ATTR_PAYMENT = "payment";

    private ControllerConstants() {}
}
