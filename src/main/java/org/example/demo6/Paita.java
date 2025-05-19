package org.example.demo6;

import javafx.scene.canvas.Canvas;

public class Paita {
    private static final String pilt = "/kassipildid/kass_pai.png";

    public void kuva(Canvas canvas, Main main) {
        main.kuvaPiltAjutiselt(pilt, 5);
    }
}
