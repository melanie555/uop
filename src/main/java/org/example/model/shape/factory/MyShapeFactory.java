package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyShapeFactory {

    public static MyShape createShape(ShapeType type, Color color, Rectangle2D.Double bounds) {
        switch (type) {
            case RECTANGLE:
                return new MyShape(color, new Rectangle2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()), null);
            case ELLIPSE:
                return new MyShape(color, new Ellipse2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()), null);
            default:
                throw new IllegalArgumentException("Unknown shape type");
        }
    }
}
