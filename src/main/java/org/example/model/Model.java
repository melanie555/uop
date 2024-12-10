package org.example.model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

// TODO: 25.10.2024 Сделать singleton класс
public class Model extends Observable {
    public List<MyShape> getShapeList() {
        return shapeList;
    }

    private List<MyShape> shapeList = new ArrayList<>();
    private MyShape currentshape;

    public void createCurrentShape(MyShape shape){
        currentshape = shape;
        shapeList.add(shape);
    }
    public void addCurrentShape(MyShape sampleShape){
        shapeList.add(sampleShape);
    }
    public MyShape getLastShape() {
        int size = shapeList.size();
        return shapeList.isEmpty() ? null : shapeList.get(size - 1);
    }

    public void  removeLastShape() {
        if(shapeList == null) {
            return;
        } else {
            int size = shapeList.size();
            shapeList.remove(size - 1);
        }

    }

    public void setMyShape(MyShape myShape) {
        this.currentshape = myShape;
    }
    public void changeShape(Point2D x, Point2D y) {
        currentshape.setFrame(x, y);
        update();
    }

    public void draw(Graphics2D g) {
        shapeList.forEach(shape -> shape.draw(g));
    }
    public void update()
    {
        this.setChanged();
        this.notifyObservers();
    }
}