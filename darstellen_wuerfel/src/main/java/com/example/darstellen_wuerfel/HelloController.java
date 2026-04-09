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
        drawCube();
    }

    private void drawCube() {
        GraphicsContext gc = meinCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, meinCanvas.getWidth(), meinCanvas.getHeight());
        gc.setLineWidth(2);
        gc.setStroke(Color.BLUE);

        // Würfelpunkte
        Vektor3D[] cube = {
                new Vektor3D(50, 50, 50),
                new Vektor3D(-50, 50, 50),
                new Vektor3D(-50, -50, 50),
                new Vektor3D(50, -50, 50),
                new Vektor3D(50, 50, -50),
                new Vektor3D(-50, 50, -50),
                new Vektor3D(-50, -50, -50),
                new Vektor3D(50, -50, -50)
        };

        // Leichte Drehung um X und Z
        double angleX = Math.toRadians(20);
        double angleZ = Math.toRadians(20);
        for (int i = 0; i < cube.length; i++) {
            cube[i] = cube[i].rotateX(angleX).rotateZ(angleZ);
        }

        // Kanten des Würfels
        int[][] edges = {
                {0,1},{1,2},{2,3},{3,0}, // vorne
                {4,5},{5,6},{6,7},{7,4}, // hinten
                {0,4},{1,5},{2,6},{3,7}  // verbindungen
        };

        // Zeichnen mit orthogonaler Projektion
        double centerX = meinCanvas.getWidth()/2;
        double centerY = meinCanvas.getHeight()/2;

        for (int[] edge : edges) {
            Vektor p1 = cube[edge[0]].to2D(centerX, centerY);
            Vektor p2 = cube[edge[1]].to2D(centerX, centerY);
            gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}