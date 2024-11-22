package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.factory.MyShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    public Controller() {
        model = new Model();
        MyShape shape = new MyShape(new Rectangle2D.Double());
        shape.setFb(new NoFill());
        model.setMyShape(shape);

        panel = new MyPanel(this);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void getPointOne(Point2D p){
        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

public MyShape createShape(ShapeType shapeType, Color color, Point2D point1, Point2D point2) {
    Rectangle2D.Double bounds = new Rectangle2D.Double();
    bounds.setFrameFromDiagonal(point1, point2);
    return MyShapeFactory.createShape(shapeType, color, bounds);}
}
