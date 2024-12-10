package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MyShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements org.example.controller.AppAction {
    private MyShape sampleShape;

    private MyShape drawableShape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;
    private MyShapeFactory factory;

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.sampleShape = shape;
        factory = factory.getInstance();
    }

    public void stretchShape(Point point) {
        secondPoint = point;
        sampleShape.setFrame(firstPoint, secondPoint);
        model.update();
    }
    public void createShape(Point point) {
        firstPoint = point;
        sampleShape = factory.createShape();
        model.createCurrentShape(sampleShape.clone());
        model.update();
    }

    @Override
    public void mousePressed(Point2D point) {
        secondPoint = point;
        sampleShape = factory.createShape();
        drawableShape = sampleShape;
        model.addCurrentShape(sampleShape);
        model.update();
    }

    @Override
    public void mouseDragged(Point2D point) {
        firstPoint = point;
        sampleShape.setFrame(firstPoint, secondPoint);
        drawableShape.setFrame(firstPoint, secondPoint);
        model.update();
    }
    @Override
    public void execute() {
        model.addCurrentShape(drawableShape);
        model.update();
    }
    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        model.update();
    }
    @Override
    public org.example.controller.AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model, sampleShape);
        actionDraw.sampleShape = sampleShape.clone();
        actionDraw.drawableShape = drawableShape;
        return actionDraw;
    }

}
