package org.example.view.menu;

import org.example.model.shape.factory.MenuState;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand {
    private JRadioButtonMenuItem radioButton;
    private boolean useDefault;
    private MenuState state;
    private Color defaultColor;
    @Override
    public void execute() {
        radioButton.setSelected(!useDefault);
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        state.setColor(color);
    }
}