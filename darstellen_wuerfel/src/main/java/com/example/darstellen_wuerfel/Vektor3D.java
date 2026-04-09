package com.example.darstellen_wuerfel;

import javafx.scene.canvas.GraphicsContext;

public class Vektor3D {

    public double x, y, z;

    public Vektor3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Rotation um X-Achse
    public Vektor3D rotateX(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vektor3D(
                x,
                y * cos - z * sin,
                y * sin + z * cos
        );
    }

    // Rotation um Z-Achse
    public Vektor3D rotateZ(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vektor3D(
                x * cos - y * sin,
                x * sin + y * cos,
                z
        );
    }

    // Orthogonale Projektion
    public Vektor to2D() {
        return new Vektor(x + 300, y + 200);
    }

    public void drawLineTo(GraphicsContext gc, Vektor3D other) {
        Vektor p1 = this.to2D();
        Vektor p2 = other.to2D();
        gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
    }
}
