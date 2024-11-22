package org.example.controller;

import java.awt.*;
import java.awt.geom.Point2D;

public interface AppAction {
    void mousePressed(Point2D point);
    void mouseDragged(Point2D point);
}
