package ua.svitl.enterbank.servletproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.Command;
import ua.svitl.enterbank.servletproject.controller.command.CommandContainer;
import ua.svitl.enterbank.servletproject.controller.command.CommandResult;
import ua.svitl.enterbank.servletproject.controller.command.utils.ControllerConstants;
import ua.svitl.enterbank.servletproject.utils.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 8178084090649129041L;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            process(request, response);
        } catch (Exception ex) {
            LOG.debug("Exception {} ==> {}", System.currentTimeMillis(), ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            try {
                StringBuilder sb = new StringBuilder();
                String pageString = getHtmlPage(ControllerConstants.ERROR400);
                response.setContentType("text/html");
                response.setCharacterEncoding("utf-8");
                sb.append(pageString);
                Writer writer = response.getWriter();
                if (writer != null) {
                    writer.write(sb.toString());
                }
            } catch (IOException e) {
                request.getRequestDispatcher(ControllerConstants.PAGE_ERROR).forward(request, response); // go to error page
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (Exception ex) {
            LOG.debug("Exception {} ==> {}", System.currentTimeMillis(), ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            try {
                StringBuilder sb = new StringBuilder();
                String pageString = getHtmlPage(ControllerConstants.ERROR400);
                response.setContentType("text/html");
                response.setCharacterEncoding("utf-8");
                sb.append(pageString);
                Writer writer = response.getWriter();
                if (writer != null) {
                    writer.write(sb.toString());
                }
            } catch (IOException e) {
                request.getRequestDispatcher(ControllerConstants.PAGE_ERROR).forward(request, response); // go to error page
            }
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.debug("=============================================================================");
        LOG.debug("Servlet starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: {}", commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command: {} ", command);

        // execute command and get forward address
        try {
            CommandResult page = command.execute(request, response);
            LOG.debug("CommandResult page ==> {}", page);
            String forwardPage = page.getPage();

            LOG.trace("Go to forward address: {}", forwardPage);
            dispatch(request, response, page);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            LOG.error("errorMessage from Controller#process(req, resp): {}", ex.getMessage());
            request.getRequestDispatcher(ControllerConstants.PAGE_ERROR).forward(request, response); // go to error page
        }
        LOG.debug("Servlet ends");
        LOG.debug("=============================================================================");
        request.setAttribute("errorMessage", "");

    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult page)
            throws ServletException, IOException {
        String pageToDispatch = page.getPage();
        LOG.trace("Dispatching... {}", pageToDispatch);

        if (page.isRedirect()) {
            response.setHeader("Cache-control", "no-cache");
            response.setHeader("Cache-control", "no-store");
            response.setHeader("Pragma", "no-cache");

            LOG.trace("... redirect page... {}", pageToDispatch);
            response.sendRedirect(request.getContextPath() + pageToDispatch);
        } else {
            response.setHeader("Cache-control", "no-cache");
            response.setHeader("Cache-control", "no-store");
            response.setHeader("Pragma", "no-cache");
            LOG.trace("... forward page... {}", pageToDispatch);
            request.getRequestDispatcher(pageToDispatch).forward(request, response);
        }
    }

    public String getHtmlPage(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
        }
        return sb.toString();
    }
}
