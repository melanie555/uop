package org.example.model.shape.factory;
import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShapeFactory {
    private MenuState state;
    private static MyShapeFactory instance;
    public static synchronized MyShapeFactory getInstance()
    {
        if(instance == null)
        {
            instance = new MyShapeFactory();
        }
        return instance;
    }
    public void config(MenuState state)
    {
        this.state = state;
    }
    public MyShape createShape()
    {
        MyShape newS = new MyShape();
        RectangularShape shape = state.getType().createShape();

        FillBehavior fillBehavior = state.isFill() ? new Fill() : new NoFill();
        fillBehavior.setShape(shape);
        fillBehavior.setColor(state.getColor());
        newS.setFb(fillBehavior);
        newS.setShape(shape);
        return newS;
    }

}
