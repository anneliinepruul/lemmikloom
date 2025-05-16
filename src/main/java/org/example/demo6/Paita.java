package org.example.demo6;

import javafx.scene.canvas.Canvas;

public class Paita {
    private static final String IMAGE = "file:/C:/Users/paan/IdeaProjects/lemmikloom/paitamine.png";

    public void kuva(Canvas canvas, Main main) {
        main.kuvaPiltAjutiselt(IMAGE, 5);
    }
}
