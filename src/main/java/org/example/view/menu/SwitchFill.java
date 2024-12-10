package org.example.view.menu;

import org.example.model.shape.factory.MenuState;

public class SwitchFill implements AppCommand {
    private boolean fill;
    private MenuState state;

    public SwitchFill(boolean fill, MenuState state) {
        this.fill = fill;
        this.state = state;
    }

    @Override
    public void execute() {
        state.setFill(fill);
    }
}

