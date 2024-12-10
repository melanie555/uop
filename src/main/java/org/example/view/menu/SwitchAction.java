package org.example.view.menu;

import org.example.controller.AppAction;
import org.example.model.shape.factory.MenuState;

public class SwitchAction implements AppCommand {
    private MenuState state;
    private AppAction action;

    public SwitchAction(MenuState state, AppAction action) {
        this.state = state;
        this.action = action;
    }

    @Override
    public void execute() {
        state.setAction(action);
    }
}
