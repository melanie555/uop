package org.example.controller;

import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MenuState;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.fill.Fill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public class Controller extends MenuState {
    private static Controller instanse;

    public static synchronized Controller getInstanse() {
        if(instanse == null)
        {
            instanse = new Controller();
        }
        return instanse;
    }

    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private MyShape sampleShape;

    /*public MenuState getState() {
        return state;

}

    public void setState(MenuState state) {
        this.state = state;
    }*/
    private MenuState menu;
    private UndoMachine machine;

    public Controller() {
        menu = new MenuState();
        MyShapeFactory sFactory = MyShapeFactory.getInstance();
        sFactory.config(menu);
        model = new Model();
        menu.setAction(new ActionDraw(model, sampleShape));
        MyPanel panel = new MyPanel(this);
        model.addObserver(panel);
        frame = new MyFrame();
        frame.setPanel(panel);
        machine = new UndoMachine();
        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setState(menu);
        menuCreator.setModel(model);
        menuCreator.setUndoMachine(machine);
        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.WEST);
        frame.revalidate();
    }
    public void getPointOne(Point2D p){
        AppAction action = menu.getAction();
        action.mousePressed(p);
        machine.add(action.cloneAction());
        machine.updateButtons();
    }
    public void getPointTwo(Point2D p){
        AppAction action = menu.getAction();
        action.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
