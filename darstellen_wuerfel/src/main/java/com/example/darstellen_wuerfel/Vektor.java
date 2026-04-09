package com.example.darstellen_wuerfel;

import javafx.scene.canvas.GraphicsContext;

public class Vektor {
    public double x;
    public double y;

    public Vektor(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void zeichneLinieZu(GraphicsContext gc, Vektor v) {
        gc.strokeLine(this.x, this.y, v.x, v.y);
    }
}