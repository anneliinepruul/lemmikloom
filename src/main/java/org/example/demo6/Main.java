package org.example.demo6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class Main extends Application {
    private static final String DEFAULT_IMAGE = "/kassipildid/kass.png";
    private static final int PIXEL_SIZE = 10;

    private Canvas canvas;
    private BorderPane root;
    private Label quoteLabel;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();
        canvas = new Canvas();
        root.setCenter(canvas);

        // ★ Suur ja keskele joondatud tsitaat
        quoteLabel = new Label();
        quoteLabel.setStyle("""
            -fx-font-size: 64px;
            -fx-padding: 20;
            -fx-text-fill: darkmagenta;
            -fx-font-weight: bold;
        """);
        quoteLabel.setMaxWidth(Double.MAX_VALUE);
        quoteLabel.setWrapText(true);
        quoteLabel.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(quoteLabel, Pos.CENTER);
        root.setTop(quoteLabel);

        lisaNupud();
        kuvaPilt(DEFAULT_IMAGE);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Lemmikloom: JavaFX GUI");
        stage.setScene(scene);
        stage.show();
    }

    private void lisaNupud() {
        Button toidaBtn = looRoosaNupp("Toida");
        Button paitaBtn = looRoosaNupp("Paita");
        Button motiveeriBtn = looRoosaNupp("Motiveeri õppima");

        toidaBtn.setOnAction(e -> new Toida().kuva(canvas, this));
        paitaBtn.setOnAction(e -> new Paita().kuva(canvas, this));
        motiveeriBtn.setOnAction(e -> new Motiveeri().kuva(canvas, this));

        HBox nupud = new HBox(20, toidaBtn, paitaBtn, motiveeriBtn);
        nupud.setStyle("-fx-alignment: center; -fx-padding: 20;");
        root.setBottom(nupud);
    }

    private Button looRoosaNupp(String tekst) {
        Button nupp = new Button(tekst);
        nupp.setStyle("""
            -fx-background-color: #ffb6c1;
            -fx-text-fill: black;
            -fx-font-size: 16px;
            -fx-background-radius: 20;
            -fx-padding: 10 25 10 25;
        """);
        return nupp;
    }

    public void kuvaPilt(String resourcePath) {
        Image image = new Image(getClass().getResourceAsStream(resourcePath));
        canvas.setWidth(image.getWidth() * PIXEL_SIZE);
        canvas.setHeight(image.getHeight() * PIXEL_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Tühjenda canvas enne uut joonistamist
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        PixelReader reader = image.getPixelReader();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = reader.getColor(x, y);
                if (color.getOpacity() > 0.05) {
                    gc.setFill(color);
                    gc.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                }
            }
        }
    }

    public void kuvaPiltAjutiselt(String resourcePath, int sekundid) {
        kuvaPilt(resourcePath);
        Platform.runLater(() -> {
            PauseTransition paus = new PauseTransition(Duration.seconds(sekundid));
            paus.setOnFinished(e -> kuvaPilt(DEFAULT_IMAGE));
            paus.play();
        });
    }

    public void kuvaPiltJaTekstAjutiselt(String resourcePath, String tekst, int sekundid) {
        kuvaPilt(resourcePath);
        quoteLabel.setText(tekst);
        Platform.runLater(() -> {
            PauseTransition paus = new PauseTransition(Duration.seconds(sekundid));
            paus.setOnFinished(e -> {
                kuvaPilt(DEFAULT_IMAGE);
                quoteLabel.setText("");
            });
            paus.play();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
