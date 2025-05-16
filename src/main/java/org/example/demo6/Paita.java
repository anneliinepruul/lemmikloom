package org.example.demo6;

import javafx.scene.canvas.Canvas;

public class Paita {
    private static final String IMAGE = "/kassipildid/kass_pai.png";

    public void kuva(Canvas canvas, Main main) {
        main.kuvaPiltAjutiselt(IMAGE, 5);
    }
}
