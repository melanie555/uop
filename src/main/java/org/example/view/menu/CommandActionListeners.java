package org.example.view.menu;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class CommandActionListeners extends AbstractAction {
    public CommandActionListeners(String name, Icon icon, AppCommand command) {
        super(name, icon);
        putValue(COMMAND, command);
    }

    public static final String COMMAND = "Command";

    public CommandActionListeners(AppCommand command) {
        super();
        putValue(COMMAND, command);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppCommand command = (AppCommand) getValue(COMMAND);
        command.execute();
    }
}