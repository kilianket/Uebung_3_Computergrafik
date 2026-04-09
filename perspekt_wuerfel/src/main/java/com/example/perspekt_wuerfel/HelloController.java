package com.example.perspekt_wuerfel;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class HelloController {

    @FXML
    private Pane pane;

    @FXML
    public void initialize() {
        drawPerspectiveCube();
    }

    private void drawPerspectiveCube() {
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

        double d = 5.0; // Abstand Kamera / Projektionsfläche
        double scale = 200;
        double centerX = 400;
        double centerY = 300;

        double[][] projectedVertices = new double[8][2];

        for (int i = 0; i < cubeVertices.length; i++) {
            double x = cubeVertices[i][0];
            double y = cubeVertices[i][1];
            double z = cubeVertices[i][2];

            double w = 1 + z / d;
            projectedVertices[i][0] = x / w * scale + centerX;
            projectedVertices[i][1] = -y / w * scale + centerY; // y-Achse invertieren
        }

        for (int[] edge : cubeEdges) {
            Line line = new Line(
                    projectedVertices[edge[0]][0], projectedVertices[edge[0]][1],
                    projectedVertices[edge[1]][0], projectedVertices[edge[1]][1]
            );
            pane.getChildren().add(line);
        }
    }
}