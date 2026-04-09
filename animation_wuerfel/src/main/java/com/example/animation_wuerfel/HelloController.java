package com.example.animation_wuerfel;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class HelloController {

    @FXML
    private Pane pane;

    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;

    @FXML
    public void initialize() {
        animateCube();
    }

    private void animateCube() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                pane.getChildren().clear();
                drawRotatingCube();
                angleX += 0.01;
                angleY += 0.015;
                angleZ += 0.02;
            }
        };
        timer.start();
    }

    private void drawRotatingCube() {
        // Würfelpunkte
        double[][] cubeVertices = {
                {-1, -1, -1},
                {1, -1, -1},
                {1, 1, -1},
                {-1, 1, -1},
                {-1, -1, 1},
                {1, -1, 1},
                {1, 1, 1},
                {-1, 1, 1}
        };

        // Kanten
        int[][] cubeEdges = {
                {0,1},{1,2},{2,3},{3,0},
                {4,5},{5,6},{6,7},{7,4},
                {0,4},{1,5},{2,6},{3,7}
        };

        // Drehung
        double[][] rotatedVertices = new double[8][3];
        for (int i = 0; i < cubeVertices.length; i++) {
            double x = cubeVertices[i][0];
            double y = cubeVertices[i][1];
            double z = cubeVertices[i][2];

            // Rotation X
            double y1 = y * Math.cos(angleX) - z * Math.sin(angleX);
            double z1 = y * Math.sin(angleX) + z * Math.cos(angleX);
            y = y1; z = z1;

            // Rotation Y
            double x1 = x * Math.cos(angleY) + z * Math.sin(angleY);
            double z2 = -x * Math.sin(angleY) + z * Math.cos(angleY);
            x = x1; z = z2;

            // Rotation Z
            double x2 = x * Math.cos(angleZ) - y * Math.sin(angleZ);
            double y2 = x * Math.sin(angleZ) + y * Math.cos(angleZ);
            x = x2; y = y2;

            rotatedVertices[i][0] = x;
            rotatedVertices[i][1] = y;
            rotatedVertices[i][2] = z;
        }

        // Perspektivische Projektion
        double d = 5.0; // Abstand Kamera
        double scale = 200;
        double centerX = 400;
        double centerY = 300;

        double[][] projectedVertices = new double[8][2];
        for (int i = 0; i < rotatedVertices.length; i++) {
            double x = rotatedVertices[i][0];
            double y = rotatedVertices[i][1];
            double z = rotatedVertices[i][2];

            double w = 1 + z / d;
            projectedVertices[i][0] = x / w * scale + centerX;
            projectedVertices[i][1] = -y / w * scale + centerY; // y invertiert
        }

        // Linien zeichnen
        for (int[] edge : cubeEdges) {
            Line line = new Line(
                    projectedVertices[edge[0]][0], projectedVertices[edge[0]][1],
                    projectedVertices[edge[1]][0], projectedVertices[edge[1]][1]
            );
            pane.getChildren().add(line);
        }
    }
}