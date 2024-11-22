package org.example.model.shape.fill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;

public interface FillBehavior {
    void draw(Graphics2D g);

    void setColor(Color c);

    Color color();

    void setShape(RectangularShape s);

    RectangularShape shape();

    FillBehavior clone();
}