package org.example.view.menu;


import org.example.controller.ActionDraw;
import org.example.controller.ActionMove;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MenuState;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;


public class MenuCreator extends MenuState {
    private static MenuCreator instance;
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
    private UndoMachine undoMachine;

    private JRadioButtonMenuItem rgbButton;

    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public void setUndoMachine(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    private MenuState state;
    private MenuCreator(){
        menuBar = createMenuBar();
    }
    public static MenuCreator getInstance(){
        if (instance == null){
            instance = new MenuCreator();
        }
        return instance;
    }
    private ArrayList<Action> createToolBarItems()
    {
        ArrayList<Action> menuItems = new ArrayList<>();
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorC = new SwitchColor(rgbButton, false, state,  null);
        menuItems.add(new CommandActionListeners("Цвет", colorIco, colorC));

        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillC = new SwitchFill(true, state);
        menuItems.add(new CommandActionListeners("Заливка", fillIco, fillC));

        URL nofillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon nofillIco = nofillUrl == null ? null : new ImageIcon(nofillUrl);
        AppCommand nofillC = new SwitchFill(false, state);
        menuItems.add(new CommandActionListeners("Заливка", nofillIco, nofillC));

        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseC = new SwitchShape(ShapeType.ELLIPSE, state);
        menuItems.add(new CommandActionListeners("Фигура", ellipseIco, ellipseC));

        URL rectanglarUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectanglarIco = rectanglarUrl == null ? null : new ImageIcon(rectanglarUrl);
        AppCommand rectanglarC = new SwitchShape(ShapeType.RECTANGULAR, state);
        menuItems.add(new CommandActionListeners("Фигура", rectanglarIco, rectanglarC));

        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawC = new SwitchAction(state, new ActionDraw(model, shape));
        menuItems.add(new CommandActionListeners("Действие", drawIco, drawC));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveC = new SwitchAction(state, new ActionMove(model));
        menuItems.add(new CommandActionListeners("Действие", moveIco, moveC));

        URL redoUrl = getClass().getClassLoader().getResource("ico/redo_16x16.png");
        ImageIcon redoIco = redoUrl == null ? null : new ImageIcon(redoUrl);
        AppCommand redoC = new SwitchRedo(undoMachine);
        CommandActionListeners redoListeenr = new CommandActionListeners("Вперед-назад", redoIco, redoC);
        menuItems.add(redoListeenr);

        URL undoUrl = getClass().getClassLoader().getResource("ico/undo_16x16.png");
        ImageIcon undoIco = undoUrl == null ? null : new ImageIcon(undoUrl);
        AppCommand undoC = new SwitchUndo(undoMachine);
        CommandActionListeners undoListener = new CommandActionListeners("Вперед-назад", undoIco, undoC);
        menuItems.add(undoListener);
        undoMachine.setUndo(undoListener);
        undoListener.setEnabled(false);
        undoMachine.setRedo(redoListeenr);
        redoListeenr.setEnabled(false);

        return menuItems;
    }
    public JToolBar createToolBar()
    {
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar(JToolBar.VERTICAL);
        subMenuItems.forEach(jToolBar::add);
        return jToolBar;
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

        rgbButton = new JRadioButtonMenuItem("RGB");
        white.addActionListener(new CommandActionListeners(new SwitchColor(rgbButton, false, state, null)));
        colorMenu.add(rgbButton);
        group.add(rgbButton);

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