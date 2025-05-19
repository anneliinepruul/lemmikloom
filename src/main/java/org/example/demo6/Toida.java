package org.example.demo6;

import javafx.scene.canvas.Canvas;

public class Toida {
    private static final String pilt = "/kassipildid/kass_kala.png";

    public void kuva(Canvas canvas, Main main) {
        main.kuvaPiltAjutiselt(pilt, 5);
    }
}
