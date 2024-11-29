package org.example.controller;

import org.example.controller.actions.ActionDraw;
import org.example.controller.actions.AppAction;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MenuState;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.view.MyFrame;
import org.example.view.MyPanel;


import java.awt.*;
import java.awt.geom.Point2D;

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
        Menu menuController = Menu.getInstance();
        menuController.setState(menu);
        menuController.setModel(model);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();
    }
    public void getPointOne(Point2D p){
        AppAction action = menu.getAction();
        action.mousePressed(p);
    }
    public void getPointTwo(Point2D p){
        AppAction action = menu.getAction();
        action.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}