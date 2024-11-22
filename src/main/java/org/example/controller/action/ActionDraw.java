package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionDraw {
    private MyShape sampleShape; // Фигурка-шаблон
    private MyShape shape;       // Активная фигура
    private Point2D[] points;    // Точки для рисования
    private Model model;         // Ссылка на Model

    // Конструктор
    public ActionDraw(Model model) {
        this.model = model;

        // Инициализация по умолчанию
        points = new Point2D[2]; // Массив для координат начала и конца
        sampleShape = new MyShape(); // Образец фигуры
        shape = new MyShape();       // Активная фигура автоматически создается с дефолтными параметрами
    }

    // Установить sample-образец фигуры
    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    // Установить текущую фигуру
    public void setShape(MyShape shape) {
        this.shape = shape;
        model.setMyShape(shape);
    }

    // Установить точки для рисования
    public void setPoint(int index, Point2D point) {
        if (index >= 0 && index < points.length) {
            points[index] = point;

            // Обновить текущую фигуру при наличии двух точек
            if (points[0] != null && points[1] != null) {
                shape.setFrame(points[0], points[1]);
            }
        }
    }

    // Реинициализация текущей фигуры из образца
    public void resetShapeFromSample() {
        shape = new MyShape(
                sampleShape.getColor(),
                sampleShape.getShape(),
                sampleShape.getFb()
        );
        model.setMyShape(shape);
    }
}

