package org.example.demo6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    // Muuda tee vastavalt sellele, kuhu salvestasid pildi
    private static final String IMAGE_PATH = "file:/Users/4nn33/Library/CloudStorage/OneDrive-Personal/KOOL/ülikooli asjad/oop/demo6/_.jpeg";

    private static final int PIXEL_SIZE = 2;

    @Override
    public void start(Stage stage) {
        Image image = new Image(IMAGE_PATH);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        Canvas canvas = new Canvas(width * PIXEL_SIZE, height * PIXEL_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelReader reader = image.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                if (color.getOpacity() > 0.05) { // Filtreeri peaaegu läbipaistvad pikslid
                    gc.setFill(color);
                    gc.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                }
            }
        }

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("JavaFX: Pildi piksliline kuvamine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
