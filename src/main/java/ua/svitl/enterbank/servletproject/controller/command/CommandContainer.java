package ua.svitl.enterbank.servletproject.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.controller.command.admin.*;
import ua.svitl.enterbank.servletproject.controller.command.user.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Command Container class: contains servlet commands.
 */
public class CommandContainer {
    private static final Logger LOG = LogManager.getLogger(CommandContainer.class);

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        // all users commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("nocommand", new NoCommand());
        commands.put("changelang", new ChangeLangCommand());

        // user commands
        commands.put("userhome", new UserHomeCommand());
        commands.put("showformforpayment", new ShowFormForPaymentCommand());
        commands.put("payments", new PaymentsCommand());
        commands.put("confirmpayment", new ConfirmPaymentCommand());
        commands.put("enableaccountrequest", new EnableAccountRequestCommand());
        commands.put("disableaccount", new DisableAccountCommand());
        commands.put("createpayment", new CreatePaymentCommand());

        // admin commands
        commands.put("adminhome", new AdminHomeCommand());
        commands.put("showusers", new ShowUsersCommand());
        commands.put("showuseraccounts", new ShowUserAccountsCommand());
        commands.put("changeuserstatus", new ChangeUserStatusCommand());
        commands.put("showformforuserupdate", new ShowFormForUserUpdateCommand());
        commands.put("submitformforuserupdate", new SubmitFormForUserUpdateCommand());
        commands.put("changeaccountstatus", new ChangeAccountStatusCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands: {}", commands.size());
    }

    /**
     * Returns command object with the given name.
     * @param commandName Command name.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found: {} ", commandName);
            return commands.get("nocommand");
        }
        return commands.get(commandName);
    }

    private CommandContainer() {}

}
