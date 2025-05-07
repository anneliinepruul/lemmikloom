package org.example.demo4;

// Main.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(320, 320);
        drawCat(canvas.getGraphicsContext2D());

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Pixel Art Kass");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawCat(GraphicsContext gc) {
        int pixelSize = 20;

        // Pixel art kass (8x8 maatriks)
        // 1 = must, 0 = läbipaistev
        int[][] catPixels = {
                {0,0,1,1,1,1,0,0},
                {0,1,1,1,1,1,1,0},
                {1,1,0,1,1,0,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,0,0,1,1,1},
                {1,0,1,1,1,1,0,1},
                {0,0,1,0,0,1,0,0},
                {0,0,0,1,1,0,0,0}
        };

        for (int row = 0; row < catPixels.length; row++) {
            for (int col = 0; col < catPixels[row].length; col++) {
                if (catPixels[row][col] == 1) {
                    gc.setFill(Color.BLACK);
                } else {
                    gc.setFill(Color.TRANSPARENT);
                }
                gc.fillRect(col * pixelSize, row * pixelSize, pixelSize, pixelSize);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
