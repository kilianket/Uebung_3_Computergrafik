package com.example.darstellen_wuerfel;

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

    // Orthogonale Projektion auf 2D (Canvas zentriert)
    public Vektor to2D(double centerX, double centerY) {
        return new Vektor(x + centerX, -y + centerY); // y invertiert
    }
}