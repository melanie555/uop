package org.example.controller;


import org.example.controller.actions.ActionDraw;
import org.example.controller.actions.ActionMove;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MenuState;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;


public class Menu extends MenuState {
    private static Menu instance;
    private JMenuBar menuBar;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private Model model;

    public MyShape getShape() {
        return shape;
    }

    public void setShape(MyShape shape) {
        this.shape = shape;
    }

    private MyShape shape;



    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    private MenuState state;
    private Menu(){
        menuBar = createMenuBar();
    }
    public static Menu getInstance(){
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        JMenu colorMenu = createColorMenu();
        JMenu actionMenu = createActionMenu();
        JMenu fillMenu = createFillMenu();
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(actionMenu);
        menuBar.add(fillMenu);

        return menuBar;
    }


    private JMenu createShapeMenu() {

        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> state.setType(ShapeType.RECTANGULAR));
        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> state.setType(ShapeType.ELLIPSE));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }
    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        blue.addActionListener(e -> state.setColor(Color.BLUE));
        colorMenu.add(blue);
        group.add(blue);
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(e -> state.setColor(Color.RED));
        colorMenu.add(red);
        group.add(red);
        JRadioButtonMenuItem orange = new JRadioButtonMenuItem("Оранжевый");
        orange.addActionListener(e -> state.setColor(Color.ORANGE));
        colorMenu.add(orange);
        group.add(orange);
        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зелёный");
        green.addActionListener(e -> state.setColor(Color.GREEN));
        colorMenu.add(green);
        group.add(green);
        JRadioButtonMenuItem cyan = new JRadioButtonMenuItem("Бирюзовый");
        cyan.addActionListener(e -> state.setColor(Color.CYAN));
        colorMenu.add(cyan);
        group.add(cyan);
        JRadioButtonMenuItem pink = new JRadioButtonMenuItem("Розовый");
        pink.addActionListener(e -> state.setColor(Color.PINK));
        colorMenu.add(pink);
        group.add(pink);
        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Черный");
        black.addActionListener(e -> state.setColor(Color.BLACK));
        colorMenu.add(black);
        group.add(black);
        JRadioButtonMenuItem white = new JRadioButtonMenuItem("Белый");
        white.addActionListener(e -> state.setColor(Color.WHITE));
        colorMenu.add(white);
        group.add(white);
        return colorMenu;
    }
    private JMenu createActionMenu() {
        JMenu actionMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Движение");
        move.addActionListener(e -> state.setAction(new ActionMove(model)));
        actionMenu.add(move);
        group.add(move);
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Рисование");
        draw.addActionListener(e -> state.setAction(new ActionDraw(model, shape)));
        actionMenu.add(draw);
        group.add(draw);
        return actionMenu;
    }
    private JMenu createFillMenu() {
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem yes = new JRadioButtonMenuItem("Есть");
        yes.addActionListener(e -> state.setFill(true));
        fillMenu.add(yes);
        group.add(yes);
        JRadioButtonMenuItem no = new JRadioButtonMenuItem("Нет");
        no.addActionListener(e -> state.setFill(false));
        fillMenu.add(no);
        group.add(no);
        return fillMenu;
    }

}