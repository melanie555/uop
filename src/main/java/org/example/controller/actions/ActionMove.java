package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MyShapeFactory;

import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private Model model;
    private MyShape shape;
    private MyShape moveableShape;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public ActionMove(Model model) {
        this.model = model;
    }


    @Override
    public void mousePressed(Point2D point) {
        firstPoint = point;
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
        moveableShape = shape;
    }

    @Override
    public void mouseDragged(Point2D point) {
        secondPoint = point;
        if (shape == null) {
            return;
        }

        double deltaX = secondPoint.getX() - firstPoint.getX();
        double deltaY = secondPoint.getY() - firstPoint.getY();

        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);

        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        moveableShape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = secondPoint;
        model.update();
    }

    @Override
    public void execute() {
        /*model.addCurrentShape(moveableShape);
        model.update();*/
    }

    @Override
    public void unexecute() {
        /*moveableShape = model.getLastShape();
        model.removeLastShape();
        model.update();*/
    }

    @Override
    public AppAction cloneAction() {
        ActionMove actionMove = new ActionMove(model);
        actionMove.shape = shape.clone();
        actionMove.moveableShape = moveableShape;
        return actionMove;
    }
}