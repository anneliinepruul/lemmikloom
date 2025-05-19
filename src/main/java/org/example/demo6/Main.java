package org.example.demo6;

import javafx.animation.PauseTransition;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Alert;

public class Main extends Application {
    private static final String pilt = "/kassipildid/kass.png";
    private static final int piksliSuurus = 10;

    private Canvas canvas;
    private Label tuvustavTekst;
    private Image praegunePilt;

    @Override
    public void start(Stage stage) {
        Logija.puhastaLogi(); // ✅ logifail tühjendatakse iga käivituse alguses

        tuvustavTekst = new Label();
        tuvustavTekst.setStyle("""
            -fx-font-size: 24px;
            -fx-padding: 20;
            -fx-text-fill: darkmagenta;
            -fx-font-weight: bold;
        """);
        tuvustavTekst.setMaxWidth(Double.MAX_VALUE);
        tuvustavTekst.setWrapText(true);
        tuvustavTekst.setAlignment(Pos.CENTER);

        canvas = new Canvas();

        StackPane canvasWrapper = new StackPane(canvas);
        StackPane.setAlignment(canvas, Pos.CENTER);
        canvasWrapper.setStyle("-fx-background-color: white;");
        canvasWrapper.setMinHeight(100);

        // Alusta nupp
        Button alustaNupp = looRoosaNupp("Alusta");
        HBox alustaBox = new HBox(alustaNupp);
        alustaBox.setAlignment(Pos.CENTER);
        alustaBox.setStyle("-fx-padding: 20;");

        // Põhinupud
        Button toidaNupp = looRoosaNupp("(T)oida");
        Button paitaNupp = looRoosaNupp("(P)aita");
        Button motiveeriNupp = looRoosaNupp("(M)otiveeri õppima");

        HBox nupudBox = new HBox(20, toidaNupp, paitaNupp, motiveeriNupp);
        nupudBox.setAlignment(Pos.CENTER);
        nupudBox.setStyle("-fx-padding: 20;");
        nupudBox.setVisible(false); // Nupud on alguses peidetud

        VBox root = new VBox();
        root.getChildren().addAll(tuvustavTekst, canvasWrapper, alustaBox, nupudBox);
        VBox.setVgrow(canvasWrapper, Priority.ALWAYS);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Lemmikloom");
        stage.setScene(scene);
        stage.show();

        // Tutvustav tekst
        tuvustavTekst.setText(
                "See on sinu uus lemmikloom. Teda saab toita, paitada või küsida temalt motiveerivaid tsitaate.\n" +
                        "Selleks saab vajutada vastavaid nuppe või vajutada klaviatuuril T, P, M klahve." +
                        "Alustamiseks vajuta all olevat nuppu või tühiku klahvi!"
        );

        // Alusta nupp
        alustaNupp.setOnAction(e -> {
            tuvustavTekst.setText("");
            alustaBox.setVisible(false);
            nupudBox.setVisible(true);
        });

        // Pildi kuvamine
        canvasWrapper.widthProperty().addListener((obs, oldVal, newVal) -> joonistaUuesti());
        canvasWrapper.heightProperty().addListener((obs, oldVal, newVal) -> joonistaUuesti());
        kuvaPilt(pilt);

        // Nuppude tegevused
        toidaNupp.setOnAction(e -> {
            new Toida().kuva(canvas, this);
            Logija.logi("Toideti kassi");
        });

        paitaNupp.setOnAction(e -> {
            new Paita().kuva(canvas, this);
            Logija.logi("Paitati kassi");
        });

        motiveeriNupp.setOnAction(e -> {
            new Motiveeri().kuva(canvas, this);
            Logija.logi("Motiveeriti õppima");
        });

        // Klaviatuuri sisend
        scene.setOnKeyPressed(event -> {
            String klahv = event.getText().toLowerCase();
            if (!nupudBox.isVisible()) return; // Klahvid ei tööta enne alustamist

            try {
                switch (klahv) {
                    case "t" -> toidaNupp.fire();
                    case "p" -> paitaNupp.fire();
                    case "m" -> motiveeriNupp.fire();
                    default -> throw new ValeKlahvErind(event.getText().charAt(0));
                }
            } catch (ValeKlahvErind e) {
                Logija.logi("Erind: " + e.getMessage()); // ✅ lisatud logimine vale klahvi korral
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Vale klahv");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
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
        praegunePilt = new Image(getClass().getResourceAsStream(resourcePath));
        joonistaUuesti();
    }

    public void kuvaPiltAjutiselt(String resourcePath, int sekundid) {
        kuvaPilt(resourcePath);
        Platform.runLater(() -> {
            PauseTransition paus = new PauseTransition(Duration.seconds(sekundid));
            paus.setOnFinished(e -> kuvaPilt(pilt));
            paus.play();
        });
    }

    public void kuvaPiltJaTekstAjutiselt(String resourcePath, String tekst, int sekundid) {
        kuvaPilt(resourcePath);
        tuvustavTekst.setText(tekst);
        Platform.runLater(() -> {
            PauseTransition paus = new PauseTransition(Duration.seconds(sekundid));
            paus.setOnFinished(e -> {
                kuvaPilt(pilt);
                tuvustavTekst.setText("");
            });
            paus.play();
        });
    }

    private void joonistaUuesti() {
        if (praegunePilt == null || canvas.getScene() == null) return;

        double width = ((Region) canvas.getParent()).getWidth();
        double height = ((Region) canvas.getParent()).getHeight();

        canvas.setWidth(width);
        canvas.setHeight(height);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        PixelReader reader = praegunePilt.getPixelReader();
        int laius = (int) praegunePilt.getWidth();
        int kõrgus = (int) praegunePilt.getHeight();

        double drawL = laius * piksliSuurus;
        double drawK = kõrgus * piksliSuurus;
        double offsetX = Math.max((width - drawL) / 2, 0);
        double offsetY = Math.max((height - drawK) / 2, 0);

        for (int y = 0; y < kõrgus; y++) {
            for (int x = 0; x < laius; x++) {
                Color color = reader.getColor(x, y);
                if (color.getOpacity() > 0.05) {
                    gc.setFill(color);
                    gc.fillRect(offsetX + x * piksliSuurus, offsetY + y * piksliSuurus, piksliSuurus, piksliSuurus);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
