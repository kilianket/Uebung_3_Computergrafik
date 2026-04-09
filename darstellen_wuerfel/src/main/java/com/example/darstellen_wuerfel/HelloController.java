package com.example.darstellen_wuerfel;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HelloController {

    @FXML
    private Canvas meinCanvas;

    @FXML
    public void initialize() {
        zeichneWuerfel();
    }

    private void zeichneWuerfel() {

        GraphicsContext gc = meinCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, meinCanvas.getWidth(), meinCanvas.getHeight());
        gc.setLineWidth(2);

        Vektor3D[] wuerfel = new Vektor3D[8];

        wuerfel[0] = new Vektor3D(50, 50, 50);
        wuerfel[1] = new Vektor3D(-50, 50, 50);
        wuerfel[2] = new Vektor3D(-50, -50, 50);
        wuerfel[3] = new Vektor3D(50, -50, 50);

        wuerfel[4] = new Vektor3D(50, 50, -50);
        wuerfel[5] = new Vektor3D(-50, 50, -50);
        wuerfel[6] = new Vektor3D(-50, -50, -50);
        wuerfel[7] = new Vektor3D(50, -50, -50);

        // Rotation
        double angleX = Math.toRadians(30);
        double angleZ = Math.toRadians(30);

        for (int i = 0; i < wuerfel.length; i++) {
            wuerfel[i] = wuerfel[i].rotateX(angleX).rotateZ(angleZ);
        }

        gc.setStroke(Color.BLUE);

        for (int i = 0; i < 4; i++) {
            // vorne
            wuerfel[i].drawLineTo(gc, wuerfel[(i + 1) % 4]);

            // hinten
            wuerfel[4 + i].drawLineTo(gc, wuerfel[4 + (i + 1) % 4]);

            // verbindungen
            wuerfel[i].drawLineTo(gc, wuerfel[i + 4]);
        }
    }
}