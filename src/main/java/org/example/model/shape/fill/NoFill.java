package org.example.model.shape.fill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.RectangularShape;

public class NoFill implements FillBehavior {
    private Color color;
    private RectangularShape shape;

    public NoFill() {
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public void draw(Graphics2D g) {
        Paint paint = g.getPaint();
        g.setPaint(color);
        g.draw(shape);
        g.setPaint(paint);
    }

    @Override
    public void setShape(RectangularShape s) {
        shape = s;
    }

    @Override
    public RectangularShape shape() {
        return shape;
    }

    @Override
    public FillBehavior clone() {
        NoFill nofill = new NoFill();
        nofill.setColor(color);
        nofill.shape = (RectangularShape) shape.clone();
        return nofill;
    }
}