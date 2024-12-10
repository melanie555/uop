package org.example.model.shape.factory;

import org.example.controller.ActionDraw;
import org.example.controller.AppAction;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType type;
    private AppAction action;

    public MenuState() {
        color = Color.cyan;
        type = ShapeType.RECTANGULAR;
        fill = false;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getType() {
        return type;
    }

    public void setType(ShapeType type) {
        this.type = type;
    }

    public AppAction getAction() {
        return action;
    }

    public void setAction(AppAction action) {
        this.action = action;
    }
}
