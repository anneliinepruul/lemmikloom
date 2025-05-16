package org.example.demo6;

import javafx.scene.canvas.Canvas;

public class Toida {
    private static final String IMAGE = "file:/C:/Users/paan/IdeaProjects/lemmikloom/toitmine.png";

    public void kuva(Canvas canvas, Main main) {
        main.kuvaPiltAjutiselt(IMAGE, 5);
    }
}
